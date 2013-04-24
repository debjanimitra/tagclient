package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EnterElementPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public EnterElementPanel(){
		super();
		this.setSize(new Dimension(592, 129));
		this.setPreferredSize(new Dimension (592, 129));
		this.setBackground(ColorConstants.DARK_PINK);
		this.setVisible(true);
		Font font=new Font("Verdana", Font.BOLD, 10);
		
		JLabel taggingPrompt=new JLabel("Please enter the first word(s) of the element you want to tag:");
		taggingPrompt.setFont(font);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setSize(new Dimension(592, 43));
		labelPanel.setPreferredSize(new Dimension(592, 43));
		labelPanel.setBackground(ColorConstants.ORANGE);
		labelPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=1;
		c1.gridy=1;
		labelPanel.add(taggingPrompt, c1);
		
			
		TextField inputField = new TextField();
		inputField.setColumns(25);
		inputField.setFont(font);
		inputField.addKeyListener(new urlListener(inputField));
		
		JPanel inputPanel = new JPanel();
		inputPanel.setSize(new Dimension(592, 43));
		inputPanel.setPreferredSize(new Dimension(592, 43));
		inputPanel.setBackground(ColorConstants.BLUE);
		inputPanel.setLayout(new GridBagLayout());
		inputPanel.setVisible(true);
		GridBagConstraints c2=new GridBagConstraints();
		c2.gridx=1;
		c2.gridy=1;
		inputPanel.add(inputField, c2);
				
		
		JButton trakButton = new JButton ("trak this!");
		trakButton.setBackground(ColorConstants.DARK_GRAY);
		trakButton.setForeground(Color.WHITE);
		trakButton.setSize(new Dimension(100, 33));
		trakButton.setPreferredSize(new Dimension(100, 33));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(592, 43));
		buttonPanel.setPreferredSize(new Dimension(592, 43));
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.gridx=1;
		c3.gridy=1;
		buttonPanel.add(trakButton, c3);
		
		this.setLayout(new GridLayout(3, 1));
		this.add(labelPanel);
		this.add(buttonPanel);
		this.add(inputPanel);

		System.out.println("eep");
		
	}
	
	private class urlListener implements KeyListener{

		private TextField _urlField;
		
		public urlListener(TextField urlField){
			_urlField=urlField;
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
