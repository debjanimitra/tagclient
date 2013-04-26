package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.nodes.Element;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;


public class EnterElementPanel extends JPanel {

	private Client _client;
	private String _input;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnterElementPanel(MyFrame frame, JPanel changePanel, Client client, HTMLParsing parser,  BelowElementPanel below, String url){
		super();
		_client = client;
		this.setSize(new Dimension(592, 129));
		this.setPreferredSize(new Dimension (592, 129));
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		this.setVisible(true);
		this.setLayout(new GridLayout(3, 1));
		Font font=new Font("Verdana", Font.BOLD, 12);
		
		JLabel prompt=new JLabel("                          Enter the first word(s) of the element you want to tag:");
		prompt.setFont(font);
		JPanel labelPanel=new JPanel();
		labelPanel.setSize(new Dimension(592, 43));
		labelPanel.setPreferredSize(new Dimension(592, 43));
		labelPanel.setBackground(ColorConstants.DARK_ORANGE);
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(prompt, BorderLayout.CENTER);
		this.add(labelPanel);
		
		TextField elementField = new TextField();
		elementField.setColumns(50);
		elementField.setFont(font);
		
		JPanel ePanel=new JPanel();
		ePanel.setSize(new Dimension(592, 100));
		ePanel.setPreferredSize(new Dimension(592, 100));
		ePanel.setBackground(ColorConstants.DARK_ORANGE);
		ePanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=0;
		ePanel.add(elementField, c1);
		this.add(ePanel);
		
		JButton trakButton=new JButton("Trak this!");
		trakButton.setBackground(ColorConstants.DARK_GRAY);
		trakButton.setForeground(Color.WHITE);
		trakButton.setSize(new Dimension(100, 50));
		trakButton.setPreferredSize(new Dimension(100, 50));
		JPanel trakButtonPanel=new JPanel();
		trakButtonPanel.setBackground(ColorConstants.DARK_ORANGE);
		trakButtonPanel.setSize(new Dimension(592, 50));
		trakButtonPanel.setPreferredSize(new Dimension(592, 50));
		trakButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c8=new GridBagConstraints();
		c8.anchor=GridBagConstraints.CENTER;
		trakButtonPanel.add(trakButton, c8);		
		trakButton.addActionListener(new TrakButtonListener(this, elementField, parser, below, frame, changePanel, client, url));
		this.add(trakButtonPanel);
	}
	
	public String getUserInput(){
		return _input;
	}
	
	public void setUserInput(String input){
		_input=input;
	}
	
	private class TrakButtonListener implements ActionListener{

		private TextField _field;
		private HTMLParsing _parser;
		private BelowElementPanel _below;
		private MyFrame _frame;
		private JPanel _changePanel;
		private Client _client;
		private String _url;
		
		public TrakButtonListener(EnterElementPanel panel, TextField field, HTMLParsing parser,  BelowElementPanel below, MyFrame frame, JPanel changePanel, Client client, String url){
			_field=field;
			_parser=parser;
			_below=below;
			_frame=frame;
			_changePanel=changePanel;
			_client=client;
			_url=url;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String userInput=_field.getText().trim();
			ArrayList<Element> suggestionElements=_parser.findElement(userInput);
			AllTagOptionsPanel all=new AllTagOptionsPanel(suggestionElements, _client, _frame.getUsername(), _parser.getDocument(), _url, _parser);
			_below.changePanel(all, _frame);
			_changePanel.repaint();
			_changePanel.revalidate();
			_frame.repaint();
			_frame.revalidate();
		}
		
	}
	
	
}
