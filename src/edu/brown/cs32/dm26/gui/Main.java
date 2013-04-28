package edu.brown.cs32.dm26.gui;


import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class Main {

	public static void main(String [] args){
				
		final String HOSTNAME = "localhost";
		final int PORT = 6000;
		
		
		Client client = new Client(HOSTNAME, PORT);
		new MyFrame(client);
	}
	
}