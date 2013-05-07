package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JPopupMenu;

import javax.swing.border.EtchedBorder;


import org.jsoup.HttpStatusException;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class URLPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField _field;
	private JButton _goButton, _backButton, _forwardButton;
	private JLabel _label;
	private TagURLPanel _previous;
	private JPanel _labelPanel, _fieldPanel, _buttonPanel, _goPanel, _backPanel;
	private MyFrame _frame;

	public URLPanel(MyFrame frame, JPanel changePanel, Client client){
		super();
		this.setSize(new Dimension(800, 44));
		this.setPreferredSize(new Dimension(800, 44));
		this.setBackground(ColorConstants.SUPER_HUNTER);
		EtchedBorder border=new EtchedBorder(ColorConstants.SUPER_HUNTER, ColorConstants.HUNTER);
		this.setBorder(border);
		_frame=frame;
		_previous=null;
		Font customFont=new Font("Verdana", Font.BOLD, 12);
		_label = new JLabel(" Enter URL :");
		_label.setFont(customFont);
		_label.setForeground(ColorConstants.LIGHT_SEA);
		_labelPanel=new JPanel();
		_labelPanel.setBackground(ColorConstants.SUPER_HUNTER);
		_labelPanel.setLayout(new BorderLayout());
		_labelPanel.setSize(new Dimension(100, 40));
		_labelPanel.setPreferredSize(new Dimension(100, 40));
		_labelPanel.add(_label, BorderLayout.CENTER);
		
		_field=new TextField();
		_field.setColumns(55);
		_field.setSize(new Dimension(550, 40));
		_field.setPreferredSize(new Dimension(550, 40));
		_field.setFont(customFont);
		_fieldPanel=new JPanel();
		_fieldPanel.setBackground(ColorConstants.SUPER_HUNTER);
		_fieldPanel.setSize(new Dimension(550, 40));
		_fieldPanel.setPreferredSize(new Dimension(550, 40));
		_fieldPanel.add(_field);
		
		_goButton=new JButton("Fetch!");
		_goButton.setFont(customFont);
		_goButton.setSize(new Dimension(75, 30));
		_goButton.setPreferredSize(new Dimension(75, 30));
		_goButton.setBackground(ColorConstants.ORANGE);
		_goButton.setForeground(Color.WHITE);
		_goPanel=new JPanel();
		_goPanel.setBackground(ColorConstants.SUPER_HUNTER);
		_goPanel.setSize(new Dimension(75, 40));
		_goPanel.setPreferredSize(new Dimension(75, 40));
		_goPanel.add(_goButton);
		
		
		_backButton = new JButton("<--");
		_backButton.setFont(customFont);
		_backButton.setBackground(ColorConstants.ORANGE);
		_backButton.setForeground(Color.WHITE);
		_backButton.setSize(new Dimension(65, 30));
		_backButton.setPreferredSize(new Dimension(65, 30));
		_backButton.addActionListener(new BackListener(changePanel, frame, _field, this));

		
		_backPanel=new JPanel();
		_backPanel.setSize(new Dimension(65, 40));
		_backPanel.setPreferredSize(new Dimension(65, 40));
		_backPanel.add(_backButton);
		_backPanel.setBackground(ColorConstants.SUPER_HUNTER);
		
		_buttonPanel=new JPanel();
		_buttonPanel.setBackground(ColorConstants.SUPER_HUNTER);
		_buttonPanel.setSize(new Dimension(145, 40));
		_buttonPanel.setPreferredSize(new Dimension(145, 40));
		_buttonPanel.setLayout(new BorderLayout());
		_buttonPanel.add(_goPanel, BorderLayout.WEST);
		_buttonPanel.add(_backPanel, BorderLayout.CENTER);
		
		GoListener goListener=new GoListener(changePanel, client, frame, _field, this);
		GoListener goListener1=new GoListener(changePanel, client, frame, _field, this);
		_field.addKeyListener(goListener);
		_goButton.addActionListener(goListener1); 
		
		
		this.setLayout(new BorderLayout());
		this.add(_labelPanel, BorderLayout.WEST);
		this.add(_fieldPanel, BorderLayout.CENTER);
		this.add(_buttonPanel, BorderLayout.EAST);
	}
	
	
	public void setPrevious(TagURLPanel panel){
		_previous=panel;
	}
	
	public TagURLPanel getPrevious(){
		return _previous;
	}
	
	public void setEnable(boolean bool){
		_field.setText("");
		_label.setEnabled(bool);
		_field.setEnabled(bool);
		_goButton.setEnabled(bool);
		_backButton.setEnabled(bool);
		if (bool==false){
			_goButton.setBackground(ColorConstants.GRAY3);
			_backButton.setBackground(ColorConstants.GRAY3);
			_goPanel.setBackground(ColorConstants.GRAY4);
			_backPanel.setBackground(ColorConstants.GRAY4);
			_buttonPanel.setBackground(ColorConstants.GRAY4);
			_fieldPanel.setBackground(ColorConstants.GRAY4);
			_labelPanel.setBackground(ColorConstants.GRAY4);
			this.setBackground(ColorConstants.GRAY4);
			this.setBorder(null);
		}
		else{
			_goButton.setBackground(ColorConstants.ORANGE);
			_backButton.setBackground(ColorConstants.ORANGE);
			_buttonPanel.setBackground(ColorConstants.SUPER_HUNTER);
			_fieldPanel.setBackground(ColorConstants.SUPER_HUNTER);
			_labelPanel.setBackground(ColorConstants.SUPER_HUNTER);
			_goPanel.setBackground(ColorConstants.SUPER_HUNTER);
			_backPanel.setBackground(ColorConstants.SUPER_HUNTER);
			this.setBackground(ColorConstants.SUPER_HUNTER);
			EtchedBorder border=new EtchedBorder(ColorConstants.SUPER_HUNTER, ColorConstants.HUNTER);
			this.setBorder(border);
		}
		_frame.revalidate();
	}
	
	public JLabel getLabel(){
		return _label;
	}
	
	public JButton getBackButton(){
		return _backButton;
	}
	
	public JButton getGoButton(){
		return _goButton;
	}
	
	public TextField getTextField(){
		return _field;
	}

	
	private class GoListener implements ActionListener, KeyListener{

		private JPanel _changePanel;
		private Client _client;
		private MyFrame _frame;
		private TextField _field;
		private URLPanel _urlPanel;
		
		public GoListener(JPanel changePanel, Client client, MyFrame frame, TextField field, URLPanel urlPanel){
			_changePanel=changePanel;
			_client=client;
			_frame=frame;
			_field=field;
			_urlPanel=urlPanel;
		}
		
		private void fetch(){
			String input=_field.getText().trim();
			if (!input.startsWith("http://") && !input.startsWith("https://")){
				input="http://"+input;
				_field.setText(input);
				_field.repaint();
				_field.revalidate();
			}
			HTMLParsing parser;
			try {

				parser = new HTMLParsing(input);
				TagURLPanel tag = new TagURLPanel(_frame, _changePanel, _client, parser, input);
				_changePanel.removeAll();
				_urlPanel.setPrevious(tag);
				_changePanel.add(tag);
				_changePanel.repaint();
				_changePanel.revalidate();
				_frame.repaint();
				_frame.revalidate();
			} catch (IOException e1) {
				// this website does not exist (maybe try with adding "www." in front of it and try again?
				Font font=new Font("Verdana", Font.BOLD, 15);
				JPopupMenu pop =  new JPopupMenu();
				JLabel title1=new JLabel("Invalid URL");
				title1.setForeground(Color.RED);
				title1.setFont(font);
				pop.setLayout(new BorderLayout());
				pop.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
				pop.setBorder(BorderFactory.createLineBorder(ColorConstants.EVEN_LIGHTER_SEA));
				pop.add(title1, BorderLayout.CENTER);
				pop.setSize(new Dimension(100, 60));
				pop.setPreferredSize(new Dimension(100, 60));
				pop.show(_frame, 55, 250);
				_frame.repaint();
				_frame.revalidate();
			} 
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			fetch();				
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar()=='\n'){
				fetch();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	private class BackListener implements ActionListener, KeyListener {

		private JPanel _changePanel;
		private MyFrame _frame;
		private TextField _field;
		private URLPanel _urlPanel;
		
		public BackListener(JPanel changePanel, MyFrame frame, TextField field, URLPanel urlPanel){
			_changePanel=changePanel;
			_frame=frame;
			_field=field;
			_urlPanel=urlPanel;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar()==37){
				if (_urlPanel.getPrevious()!=null){
					_changePanel.removeAll();
					_field.setText(_urlPanel.getPrevious().getURL());
					_changePanel.add(_urlPanel.getPrevious());
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.getURLPanel().getBackButton().setEnabled(false);
					_frame.repaint();
					_frame.revalidate();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
				if (_urlPanel.getPrevious()!=null){
					_changePanel.removeAll();
					_field.setText(_urlPanel.getPrevious().getURL());
					_changePanel.add(_urlPanel.getPrevious());
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.getURLPanel().getBackButton().setEnabled(false);
					_frame.repaint();
					_frame.revalidate();
				}
			}
		}
		
	}
	


