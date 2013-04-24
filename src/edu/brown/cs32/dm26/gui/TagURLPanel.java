package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TagURLPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TagURLPanel(){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_PINK);
		this.setVisible(true);
		EnterElementPanel eep=new EnterElementPanel();
		this.add(eep, BorderLayout.NORTH);
	}

}
