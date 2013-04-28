package edu.brown.cs32.dm26.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;


public class NotificationOption extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotificationOption(){
		super();
		this.setSize(new Dimension(592, 100));
		this.setPreferredSize(new Dimension(592, 100));
		this.setBackground(ColorConstants.BLUE);
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		Font customFont1=new Font("Verdana", Font.BOLD, 12);
		
		JLabel label=new JLabel("You have a new notification!");
		label.setFont(customFont1);
		
		
		
		
	}
}
