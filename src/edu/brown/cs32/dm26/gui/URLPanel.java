package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public URLPanel(MyFrame frame, JPanel changePanel, Client client){
		super();
		this.setSize(new Dimension(800, 40));
		this.setPreferredSize(new Dimension(800, 40));
		_previous=null;
		Font customFont=new Font("Verdana", Font.BOLD, 12);
		_label = new JLabel("   Enter URL:");
		_label.setFont(customFont);
		JPanel labelPanel=new JPanel();
		labelPanel.setLayout(new BorderLayout());
		labelPanel.setSize(new Dimension(100, 40));
		labelPanel.setPreferredSize(new Dimension(100, 40));
		labelPanel.add(_label, BorderLayout.CENTER);
		
		_field=new TextField();
		_field.setColumns(59);
		_field.setSize(new Dimension(550, 40));
		_field.setPreferredSize(new Dimension(550, 40));
		_field.setFont(customFont);
		JPanel fieldPanel=new JPanel();
		fieldPanel.setSize(new Dimension(550, 40));
		fieldPanel.setPreferredSize(new Dimension(550, 40));
		fieldPanel.add(_field);
		
		_goButton=new JButton("Fetch!");
		_goButton.setFont(customFont);
		_goButton.setSize(new Dimension(75, 30));
		_goButton.setPreferredSize(new Dimension(75, 30));
		JPanel goPanel=new JPanel();
		goPanel.setSize(new Dimension(75, 40));
		goPanel.setPreferredSize(new Dimension(75, 40));
		goPanel.add(_goButton);
		
		
		_backButton = new JButton("<--");
		_backButton.setFont(customFont);
		_backButton.setFont(customFont);
		_backButton.setSize(new Dimension(65, 30));
		_backButton.setPreferredSize(new Dimension(65, 30));
		_backButton.addActionListener(new BackListener(changePanel, frame, _field, this));
		
		
		JPanel backPanel=new JPanel();
		backPanel.setSize(new Dimension(65, 40));
		backPanel.setPreferredSize(new Dimension(65, 40));
		backPanel.add(_backButton);
		
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setSize(new Dimension(145, 40));
		buttonPanel.setPreferredSize(new Dimension(145, 40));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(goPanel, BorderLayout.WEST);
		buttonPanel.add(backPanel, BorderLayout.CENTER);
		
		GoListener goListener=new GoListener(changePanel, client, frame, _field, this);
		_field.addKeyListener(goListener);
		_goButton.addActionListener(goListener); 
		
		
		this.setLayout(new BorderLayout());
		this.add(labelPanel, BorderLayout.WEST);
		this.add(fieldPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.EAST);
	}
	
	public void setPrevious(TagURLPanel panel){
		_previous=panel;
	}
	
	public TagURLPanel getPrevious(){
		return _previous;
	}
	
	public void setEnable(boolean bool){
		_label.setEnabled(bool);
		_field.setEnabled(bool);
		_goButton.setEnabled(bool);
		_backButton.setEnabled(bool);
//		_forwardButton.setEnabled(bool);
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
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
				_changePanel.removeAll();
				String input=_field.getText().trim();
				if (input.startsWith("http://")==false){
					input="http://"+input;
				}
				_field.setText(input);
				HTMLParsing parser;
				try {
					parser = new HTMLParsing(input);
					TagURLPanel tag = new TagURLPanel(_frame, _changePanel, _client, parser, input);	
					_urlPanel.setPrevious(tag);
					_changePanel.add(tag);
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.repaint();
					_frame.revalidate();
					System.out.println("herehere");
				} catch (UnknownHostException e1) {
					// this website does not exist (maybe try with adding "www." in front of it and try again?
				} catch (HttpStatusException e1) {
					// there might actually be a typo in the URL, the entered website doesn't exist
				} catch (IllegalArgumentException e3){
					
				}
				
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar()=='\n'){
				_changePanel.removeAll();
				String input=_field.getText().trim();
				if (input.startsWith("http://")==false){
					input="http://"+input;
				}
				_field.setText(input);
				HTMLParsing parser;
				try {
					parser = new HTMLParsing(input);
					TagURLPanel tag = new TagURLPanel(_frame, _changePanel, _client, parser, input);
					_urlPanel.setPrevious(tag);
					_changePanel.add(tag);
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.repaint();
					_frame.revalidate();
					System.out.println("herehere");
				} catch (UnknownHostException e1) {
					// this website does not exist (maybe try with adding "www." in front of it and try again?
				} catch (HttpStatusException e1) {
					// there might actually be a typo in the URL, the entered website doesn't exist
				} catch (IllegalArgumentException e1){
					//??
				}
				
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getKeyChar());
			if (e.getKeyChar()==37){
				System.out.println("FRIGGIN HERE");
				if (_urlPanel.getPrevious()!=null){
					_changePanel.removeAll();
					_field.setText(_urlPanel.getPrevious().getURL());
					_changePanel.add(_urlPanel.getPrevious());
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.getURLPanel().getBackButton().setEnabled(false);
					_frame.repaint();
					_frame.revalidate();
					System.out.println("herehere");
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				System.out.println("Array stuuf is"+Arrays.toString(_changePanel.getComponents()));
				if (_urlPanel.getPrevious()!=null){
					_changePanel.removeAll();
					_field.setText(_urlPanel.getPrevious().getURL());
					_changePanel.add(_urlPanel.getPrevious());
					_changePanel.repaint();
					_changePanel.revalidate();
					_frame.getURLPanel().getBackButton().setEnabled(false);
					_frame.repaint();
					_frame.revalidate();
					System.out.println("herehere");
				}
			}
		}
		
	}
	


