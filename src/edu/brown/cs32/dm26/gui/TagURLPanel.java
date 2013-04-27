package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class TagURLPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnterElementPanel _elementPanel;

	public TagURLPanel(MyFrame frame, JPanel changePanel, Client client, HTMLParsing parser, String url){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		this.validate();
		BelowElementPanel below=new BelowElementPanel(frame);
		below.setBackground(ColorConstants.LIGHT_ORANGE);
		_elementPanel = new EnterElementPanel(frame, changePanel, client, parser, below, url);
		this.add(_elementPanel, BorderLayout.NORTH);
		this.add(below);
	}
	
	public EnterElementPanel getElementPanel(){
		return _elementPanel;
	}

}
