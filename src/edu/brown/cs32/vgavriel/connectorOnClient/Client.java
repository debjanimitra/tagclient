package edu.brown.cs32.vgavriel.connectorOnClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
	private Socket socket;
	private int port;
	private ObjectInputStream input;
	private ObjectOutputStream output;
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
				socket = new Socket(this.hostname, this.port);
				output = new ObjectOutputStream(socket.getOutputStream());
				output.flush();
				input = new ObjectInputStream(socket.getInputStream());
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
				output.writeObject(message);
				output.flush();
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
		send(message);
		Message result = null;
		while(result == null){
			try{
				result = (Message) input.readObject();

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

	public void kill() throws IOException
	{
		socket.close();
		input.close();
		output.close();
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
					Message message = (Message) input.readObject();
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