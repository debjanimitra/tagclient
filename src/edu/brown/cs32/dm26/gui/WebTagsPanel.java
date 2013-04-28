package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;


public class WebTagsPanel extends JPanel {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Client client;

	public WebTagsPanel(Client client){
		super();
		this.setBackground(ColorConstants.GREEN);
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(550, 600));
		JLabel openingLabel=new JLabel("                                            Welcome to Your Web Tags");
		this.setLayout(new BorderLayout());
		this.add(openingLabel, BorderLayout.CENTER);
	}
}
