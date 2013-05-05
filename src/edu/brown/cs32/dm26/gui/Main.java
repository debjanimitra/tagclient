package edu.brown.cs32.dm26.gui;


import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class Main {

	public static void main(String [] args){
				
		final String DEFAULTHOSTNAME = "localhost";
		final int DEFAULTPORT = 5000;
		
		String hostname = "";
		int port = 0;
		
		if(args.length == 0){
			hostname = DEFAULTHOSTNAME;
			port = DEFAULTPORT;
		} else if(args.length != 2){
			System.out.println("Please provide 2 arguments: <hostname> <port>");
			System.exit(0);
		} else {
			hostname = args[0];
			try{
				port = Integer.parseInt(args[1]);
			} catch(NumberFormatException e){
				System.out.println("The port MUST be a number!");
				System.exit(1);
			}
		}
		
		
		Client client = new Client(hostname, port);
		new MyFrame(client);
	}
	
}