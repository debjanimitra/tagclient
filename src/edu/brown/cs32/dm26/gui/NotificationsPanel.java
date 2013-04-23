package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class NotificationsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public NotificationsPanel(){
		super();
		this.setBackground(new Color(236,234,224));
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(550, 600));
		JLabel openingLabel=new JLabel("                                            Welcome to Your Notifications");
		this.setLayout(new BorderLayout());
		this.add(openingLabel, BorderLayout.CENTER);
	}
}
