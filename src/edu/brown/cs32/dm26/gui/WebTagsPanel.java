package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WebTagsPanel extends JPanel {

	public WebTagsPanel(){
		super();
		this.setBackground(new Color(236,234,224));
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(550, 600));
		JLabel openingLabel=new JLabel("                                            Welcome to Your Web Tags");
		this.setLayout(new BorderLayout());
		this.add(openingLabel, BorderLayout.CENTER);
	}
}
