package edu.brown.cs32.dm26.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class WebTagOption extends JPanel {
	
	public WebTagOption(){
		super();
		this.setBackground(ColorConstants.GREEN);
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		Font customFont=new Font("Verdana", Font.PLAIN, 12);
	}

}
