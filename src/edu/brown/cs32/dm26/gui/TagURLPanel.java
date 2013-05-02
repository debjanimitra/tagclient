package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class TagURLPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnterElementPanel _elementPanel;
	private BelowElementPanel _below;
	private String _url;


	public TagURLPanel(MyFrame frame, JPanel changePanel, Client client, HTMLParsing parser, String url){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		this.validate();
		frame.getURLPanel().getBackButton().setEnabled(false);
		frame.setShouldStartEnabling(true);
		_url=url;
		_below=new BelowElementPanel(frame);
		_below.setBackground(ColorConstants.LIGHT_ORANGE);
		_elementPanel = new EnterElementPanel(frame, changePanel, client, parser, _below, url, this);
		this.add(_elementPanel, BorderLayout.NORTH);
		this.add(_below);
	}
	
	
	public String getURL(){
		return _url;
	}
	
	public EnterElementPanel getElementPanel(){
		return _elementPanel;
	}
	
	public BelowElementPanel getBelow(){
		return _below;
	}

}
