package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomePanel(String user, MyFrame frame){
		super();
		this.setSize(new Dimension(600, 595));
		this.setPreferredSize(new Dimension(600, 595));
		this.setBackground(ColorConstants.GREY);
		Font font=new Font("Verdana", Font.BOLD, 30);
		JLabel label1=new JLabel("   Trakr welcomes you, "+user+"!");
		label1.setSize(new Dimension(600, 600));
		label1.setPreferredSize(new Dimension(600, 600));
		System.out.println(label1.getSize());
		label1.setFont(font);
		frame.getURLPanel().getBackButton().setEnabled(false);
		this.setLayout(new BorderLayout());
		this.add(label1, BorderLayout.CENTER);
	}
	

}
