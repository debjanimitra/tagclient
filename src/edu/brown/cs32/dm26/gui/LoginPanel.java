package edu.brown.cs32.dm26.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;


public class LoginPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Client _client;

	public LoginPanel(RegistrationPanel rp, MyFrame frame, JPanel changePanel, Client client){
		super();
		_client = client;
		this.setSize(new Dimension(592, 125));
		this.setPreferredSize(new Dimension (592, 125));
		this.setBackground(ColorConstants.BRIGHT_YELLOW);
		this.setVisible(true);
		this.setLayout(new GridLayout(3, 1));
		Font userInputFont=new Font("Verdana", Font.BOLD, 10);
		
		GridBagConstraints c1=new GridBagConstraints();
		TextField usernameField = new TextField();
		TextField passwordField = new TextField();
		usernameField.setColumns(25);
		passwordField.setColumns(25);
		usernameField.setFont(userInputFont);
		passwordField.setFont(userInputFont);
		passwordField.addKeyListener(new PasswordListener(passwordField));
		
		JPanel uPanel=new JPanel();
		uPanel.setSize(new Dimension(592, 100));
		uPanel.setPreferredSize(new Dimension(592, 100));
		uPanel.setBackground(ColorConstants.BRIGHT_YELLOW);
		uPanel.setLayout(new GridBagLayout());
		c1.gridx=0;
		c1.gridy=0;
		uPanel.add(new JLabel("Enter username:  "), c1);
		
		GridBagConstraints c2=new GridBagConstraints();
		c2.gridx=1;				
		c2.gridy=0;
		uPanel.add(usernameField, c2);
		

		JPanel pPanel=new JPanel();
		pPanel.setSize(new Dimension(592, 100));
		pPanel.setPreferredSize(new Dimension(592, 100));
		pPanel.setBackground(ColorConstants.BRIGHT_YELLOW);
		pPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.gridx=0;
		c3.gridy=0;
		pPanel.add(new JLabel("Enter password:  "), c3);
		
		GridBagConstraints c4=new GridBagConstraints();
		c4.gridx=1;				
		c4.gridy=0;
		pPanel.add(passwordField, c4);
		
		
		GridBagConstraints c5=new GridBagConstraints();
		c5.gridx=0;				
		c5.gridy=0;
		this.add(uPanel);
		
		GridBagConstraints c6=new GridBagConstraints();
		c6.gridx=0;				
		c6.gridy=2;
		this.add(pPanel);
		
		JButton loginButton=new JButton("Log me in!");
		loginButton.setBackground(ColorConstants.DARK_GRAY);
		loginButton.setForeground(Color.WHITE);
		loginButton.setSize(new Dimension(100, 50));
		loginButton.setPreferredSize(new Dimension(100, 50));
		JPanel loginButtonPanel=new JPanel();
		loginButtonPanel.setBackground(ColorConstants.BRIGHT_YELLOW);
		loginButtonPanel.setSize(new Dimension(592, 50));
		loginButtonPanel.setPreferredSize(new Dimension(592, 50));
		loginButtonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c8=new GridBagConstraints();
		c8.anchor=GridBagConstraints.CENTER;
		loginButtonPanel.add(loginButton, c8);		
		loginButton.addActionListener(new LoginInListener(frame, rp, usernameField, passwordField, changePanel));
		
		GridBagConstraints c7=new GridBagConstraints();
		c7.gridx=0;				
		c7.gridy=4;
		this.add(loginButtonPanel);
		
	}


private class PasswordListener implements KeyListener{
		
		private TextField _field;
		private String _userInput;
		
		public PasswordListener(TextField field){
			_field=field;
			_userInput="";
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
			//String _userInput= _field.getText();
			if (_field.getText().trim().length()<=1){
				_userInput="";
			}
			
			
			Character c=e.getKeyChar();
			if (c==8 && _userInput.length()==0){
				//do absolutely nothing!!
			}
			else if (c==8 && _userInput.length()>=1){
				_userInput=_userInput.substring(0, _userInput.length()-1);
			}
			else{
			_userInput=_userInput+e.getKeyChar();
			}
			if (_userInput!=null){
				System.out.println("input: "+_userInput);
				String temp="";
				for (int i=0; i<_userInput.length(); i++){
					temp=temp+"*";	
				}
				_field.setText(temp);
				_field.setCaretPosition(temp.length());
			}
			
			if (_userInput.length()<=0){
				_field.setText("");
			}
		}
		
	}

	
	private class LoginInListener implements ActionListener{

		private TextField _usernameField, _passwordField;
		private JPanel _registration;
		private MyFrame _frame;
		private JPanel _changePanel;
		
		public LoginInListener(MyFrame frame, JPanel registration, TextField usernameField, TextField passwordField, JPanel changePanel){
			_frame=frame;
			_usernameField=usernameField;
			_passwordField=passwordField;
			_registration=registration;
			_changePanel=changePanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ArrayList<String> errors=new ArrayList<String>();
			if (_usernameField.getText().trim().length()<=0){
				errors.add("    No username entered");
			}
			
			if (_passwordField.getText().trim().length()<=0){
				errors.add("    No password entered");
			}
			
			if (errors.size()>0){
				JPopupMenu pop = new JPopupMenu ();
				pop.setSize(new Dimension(300, 100));
				pop.setPreferredSize(new Dimension(300, 100));
				pop.setLayout(new GridLayout(errors.size()+1, 1));
				JLabel title=new JLabel ("    You have the following error(s):");
				pop.add(title);
				for (int i=0; i<errors.size(); i++){
					JLabel temp=new JLabel(errors.get(i));
					pop.add(temp);
				}
				pop.show(_registration, 150, 50);
			}
			else{
				// HANDSHAKE:
				String userName = _usernameField.getText();
				Message result = _client.sendAndReceive(new Message(MessageContent.USERID, (Object) userName));
				if(result != null && result.getContent() == MessageContent.DONE){
					_frame.getSignoutButton().setVisible(true);
					_frame.getNotificationsButton().setEnabled(true);
					_frame.getWebTagsButton().setEnabled(true);
					_frame.getEnterURL().setEnabled(true);
					_changePanel.removeAll();
					_changePanel.add(new WelcomePanel(_usernameField.getText().trim()));
					_changePanel.repaint();
					_frame.repaint();
				} else {
					errors.add("    Username not found");
					JPopupMenu pop = new JPopupMenu ();
					pop.setSize(new Dimension(300, 100));
					pop.setPreferredSize(new Dimension(300, 100));
					pop.setLayout(new GridLayout(errors.size()+1, 1));
					JLabel title=new JLabel ("    You have the following error(s):");
					pop.add(title);
					for (int i=0; i<errors.size(); i++){
						JLabel temp=new JLabel(errors.get(i));
						pop.add(temp);
					}
					pop.show(_registration, 150, 50);
				}
			}
			
			_usernameField.setText("");
			_passwordField.setText("");
			
		}
		
	
		
	}
	
}
