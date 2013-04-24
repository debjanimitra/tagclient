package edu.brown.cs32.vgavriel.connectorOnClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;

public class Client
{
	private Socket standardSocket;
	private Socket receiveThreadSocket;
	private int port;
	private ObjectInputStream standardInput;
	private ObjectOutputStream standardOutput;
	private ObjectInputStream receiveThreadInput;
	private ObjectOutputStream receiveThreadOutput;
	private String hostname;
	private boolean serverRunning;

	private ReceiveThread thread;

	public Client(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
		connectToServer();
		thread = new ReceiveThread();
		thread.start();
	}

	public void connectToServer()
	{
		int numTimesConnecting = 0;
		serverRunning = false;
		while(!serverRunning){
			numTimesConnecting++;
			try{
				standardSocket = new Socket(this.hostname, this.port);
				receiveThreadSocket = new Socket(this.hostname, this.port + 1);
				standardOutput = new ObjectOutputStream(standardSocket.getOutputStream());
				standardOutput.flush();
				standardInput = new ObjectInputStream(standardSocket.getInputStream());
				
				receiveThreadOutput = new ObjectOutputStream(receiveThreadSocket.getOutputStream());
				receiveThreadOutput.flush();
				receiveThreadInput = new ObjectInputStream(receiveThreadSocket.getInputStream());
				
				serverRunning = true;
			}
			catch(IOException e){
				serverRunning = false;
				int waitTime = (int) Math.min(Math.pow(2, numTimesConnecting), 30);
				System.out.println("ERROR: Cannot connect to the server. Trying again in "+waitTime+" seconds.");
				try
				{
					Thread.sleep(waitTime * 1000);
				} catch(InterruptedException e1){/*this won't happen ever*/}
			}			
		}
		System.out.println("Connected to the server.");
	}

	public void send(Message message)
	{
		boolean success = false;
		while(!success){
			try{
				standardOutput.writeObject(message);
				standardOutput.flush();
				success = true;
			}
			catch(IOException e){
				connectToServer();
				success = false;
			}
		}
	}

	public Message sendAndReceive(Message message)
	{
		System.out.println("before send");
		send(message);
		System.out.println("after send, before receive");
		Message result = null;
		while(result == null){
			try{
				result = (Message) standardInput.readObject();
				System.out.println("after receive");
			}
			catch(ClassNotFoundException e1){
				System.err.println("ERROR: Can't find class Message.");
			}
			catch(IOException e2){
				System.err.println("ERROR: Connection down, trying to reconnect.");
				connectToServer();
			}
		}
		return result;
	}

	public void kill()
	{
		try{
			standardSocket.close();
			receiveThreadSocket.close();
			standardInput.close();
			standardOutput.close();
		} catch (IOException e){/*can't do anything here*/}
	}


	/**
	 * A thread that will receive the messages sent by the server to
	 * display to the user.
	 */
	class  ReceiveThread extends Thread {
		public void run() {
			//TODO: Receive all the messages sent by the socket and display it
			//to the client.
			while(serverRunning){        		
				try {
					Message message = (Message) receiveThreadInput.readObject();
					if(message != null){
						if(message.getContent() == MessageContent.NOTIFICATION){
							//TODO: display the notification to the User
							// Perhaps this will be a list of all notifications??????
							// Thus, will it be good for the server to always send notifications
							// in a list??
						}
					}
				} catch (IOException e) {
					System.err.println("ERROR: Connection down, trying to reconnect.");
					connectToServer();
				} catch (ClassNotFoundException e) {
					System.err.println("ERROR: Can't find class Message while receiving from Server.");
				}        				
			}
		}
	}


}