package edu.brown.cs32.vgavriel.connectorOnClient;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import com.google.common.collect.ArrayListMultimap;

import edu.brown.cs32.dm26.gui.MyFrame;
import edu.brown.cs32.dm26.gui.ServerDownPanel;
import edu.brown.cs32.dm26.gui.WelcomeBackPanel;
import edu.brown.cs32.dm26.gui.WelcomePanel;
import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.takhan.tag.Notification;
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
	private JButton notificationButton;
	private MyFrame _frame;
	private String _userID;

	private ReceiveThread thread;
	//private boolean _running;

	public Client(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
		this.notificationButton=null;
		_frame = null;
		_userID = "";
		//_running = true;
		connectToServer();
		thread = new ReceiveThread();
		thread.start();

	}

	public void setFrame(MyFrame frame){
		if(frame == null){
			System.err.println("THIS IS BAD");
		}
		_frame = frame;
	}

	public void setNotification(JButton notification){
		this.notificationButton=notification;
	}
	
	public void setUserID(String id){
		_userID = id;
	}


	public void connectToServer()
	{
		/*if(!_running){
			_frame.getURLPanel().getTextField().setText("");
			return;
		}*/
		int numTimesConnecting = 0;
		serverRunning = false;
		while(!serverRunning){
			/*
				DEBJANI: DISABLE all components here!
			*/
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

				if(!_userID.equals("")){
					sendAndReceive(new Message(MessageContent.USERID, (Object) _userID));
				}

				serverRunning = true;
				if (_frame!=null && !_userID.isEmpty()){
					_frame.getControlPanel().setEnable(true);
					_frame.getURLPanel().setEnable(true);
					_frame.getChangePanel().removeAll();
					_frame.getChangePanel().add(new WelcomeBackPanel(_frame));
					_frame.getChangePanel().repaint();
					_frame.repaint();
					_frame.revalidate();
				}
				
			}
			catch(IOException e){
				
				serverRunning = false;
				int waitTime = (int) Math.min(Math.pow(2, numTimesConnecting), 30);
				if (_frame!=null && !_userID.isEmpty()){
					_frame.getChangePanel().removeAll();
					ServerDownPanel sdp=new ServerDownPanel(_frame);
					_frame.getChangePanel().add(sdp);
					sdp.changePanel("    ERROR: Cannot connect to the server. Trying again in "+waitTime+" seconds.");
					System.out.println("ERROR: Cannot connect to the server. Trying again in "+waitTime+" seconds.");
				
					_frame.getControlPanel().setEnable(false);
					_frame.getURLPanel().setEnable(false);
					_frame.getURLPanel().getTextField().setText("");
					_frame.getURLPanel().getTextField().repaint();
					_frame.getURLPanel().getTextField().revalidate();
					_frame.getChangePanel().repaint();
					_frame.getURLPanel().repaint();
					_frame.getURLPanel().revalidate();
					_frame.repaint();
					_frame.revalidate();
				}
				try
				{
					Thread.sleep(waitTime * 1000);
				} catch(InterruptedException e1){/*this won't happen ever*/}
			}			
		}
		if (serverRunning){
			System.out.println("Connected to the server.");
		}
	}

	private void waitForReconnect(){
		if(serverRunning)
			connectToServer();
		while(!serverRunning){
			// waiting for the reconnection to the server
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {}					
		}		
	}

	/*	public void handShake(){
		// HANDSHAKE:
		String userName = _userName;
		Message result = sendAndReceive(new Message(MessageContent.USERID, (Object) userName));
		if(result != null && result.getContent() == MessageContent.DONE){

		} else {
			if(result != null){
				if(result.getContent() == MessageContent.ERRORHANDSHAKE_MULTIPLELOGINS){
					errors.add("    You're logged in elsewhere");
				} else if (result.getContent() == MessageContent.ERRORHANDSHAKE_UNKNOWNUSER) {
					errors.add("    Username not found");
				}
			}

			JPopupMenu pop = new JPopupMenu ();
			pop.setSize(new Dimension(300, 100));
			pop.setPreferredSize(new Dimension(300, 100));
			pop.setLayout(new GridLayout(errors.size()+1, 1));
			JLabel title=new JLabel ("    You have the following error(s):");
			pop.add(title);
			for (int i=0; i<errors.size(); i++){
				JLabel temp=new JLabel(errors.get(i));
				pop.add(temp);
			}
			pop.show(_registration, 150, 50);
		}
	}*/

	public void send(Message message)
	{
		//_running = true;
		boolean success = false;
		while(!success){
			try{
				standardOutput.writeObject(message);
				standardOutput.flush();
				standardOutput.reset();
				success = true;
			}
			catch(IOException e){
				System.err.println("ERROR: Connection down, trying to reconnect.");
				waitForReconnect();
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
				result = (Message) standardInput.readObject();
			}
			catch(ClassNotFoundException e1){
				System.err.println("ERROR: Can't find class Message.");
			}
			catch(IOException e2){
				System.err.println("ERROR: Connection down, trying to reconnect.");
				waitForReconnect();
			}
		}
		return result;
	}

	public void kill()
	{
		try{
			//_running = false;
			_userID = "";
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
			while(serverRunning/* && _running*/){        		
				try {
					Message message = (Message) receiveThreadInput.readObject();
					if(message != null){
						if(message.getContent() == MessageContent.NOTIFICATIONLIST){
							ArrayList<Notification> notifications = (ArrayList<Notification>) message.getObject();
							if (notifications.size()<=0){
								notificationButton.setText("Notifications");
							}
							else{
								notificationButton.setText("Notifications ("+notifications.size()+")");
							}
							notificationButton.revalidate();
							notificationButton.repaint();
						}
					}
				} catch (IOException e) {
					System.err.println("ERROR: Connection down, trying to reconnect.");
					waitForReconnect();
				} catch (ClassNotFoundException e) {
					System.err.println("ERROR: Can't find class Message while receiving from Server.");
				}        				
			}
		}
	}


}