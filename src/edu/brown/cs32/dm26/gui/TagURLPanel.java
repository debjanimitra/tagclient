package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class TagURLPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TagURLPanel(String userInput, MyFrame frame, JPanel changePanel, Client client){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		this.validate();
		EnterElementPanel elementPanel = new EnterElementPanel(frame, changePanel, client);
		this.add(elementPanel, BorderLayout.NORTH);
	}

}
