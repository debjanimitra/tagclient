package edu.brown.cs32.dm26.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.plaf.ColorUIResource;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class Main {

	public static void main(String [] args){
				
		final String HOSTNAME = "localhost";
		final int PORT = 5000;
		
		
		Client client = new Client(HOSTNAME, PORT);
		MyFrame frame = new MyFrame(client);
	}
	
}