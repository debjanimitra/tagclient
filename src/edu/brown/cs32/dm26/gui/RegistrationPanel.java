package edu.brown.cs32.dm26.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;


public class RegistrationPanel extends JPanel {
	
	/**
	 * This is the lighter yellow panel on the bottom of the screen where a new user can register 
	 */
	
	private static final long serialVersionUID = 1L;
	private Client _client;

	public RegistrationPanel(MyFrame frame, JPanel changePanel, Client client){
		super();
		_client = client;
		this.setSize(new Dimension(592, 470));
		this.setPreferredSize(new Dimension (592, 470));
		this.setBackground(ColorConstants.GREY);
		this.setVisible(true);
		this.setLayout(new GridLayout(10, 1));
		Font declarationFont=new Font("Verdana", Font.BOLD, 20);
		JLabel declarationLabel1=new JLabel("                              Not a member?");
		JLabel declarationLabel2=new JLabel("                              Not a problem!");
		JLabel declarationLabel3=new JLabel("                              Register below.");
		declarationLabel1.setFont(declarationFont);
		declarationLabel2.setFont(declarationFont);
		declarationLabel3.setFont(declarationFont);
		JPanel anotherRando=new JPanel();
		anotherRando.setBackground(ColorConstants.GREY);
		this.add(anotherRando);
		this.add(declarationLabel1);
		this.add(declarationLabel2);
		this.add(declarationLabel3);
		
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		
		Font userInputFont = new Font("Verdana", Font.BOLD, 14);
		TextField usernameField = new TextField();
		TextField passwordField = new TextField();
		TextField reenterPasswordField = new TextField();
		TextField emailField = new TextField();
		usernameField.setColumns(25);
		passwordField.setColumns(25);
		reenterPasswordField.setColumns(25);
		emailField.setColumns(25);
		usernameField.setFont(userInputFont);
		passwordField.setFont(userInputFont);
		reenterPasswordField.setFont(userInputFont);
		emailField.setFont(userInputFont);
		
		passwordField.addKeyListener(new PasswordListener(passwordField));
		reenterPasswordField.addKeyListener(new PasswordListener(reenterPasswordField));
		
		
		JPanel uPanel=new JPanel();
		uPanel.setSize(new Dimension(592, 100));
		uPanel.setPreferredSize(new Dimension(592, 100));
		uPanel.setBackground(ColorConstants.GREY);
		uPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=0;
		JLabel l1=new JLabel("Desired username:  ");
		l1.setFont(userInputFont);
		uPanel.add(l1, c1);
		GridBagConstraints c2=new GridBagConstraints();
		c2.gridx=1;				
		c2.gridy=0;
		uPanel.add(usernameField, c2);
		
		JPanel pPanel=new JPanel();
		pPanel.setSize(new Dimension(592, 100));
		pPanel.setPreferredSize(new Dimension(592, 100));
		pPanel.setBackground(ColorConstants.GREY);
		pPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.gridx=0;
		c3.gridy=0;
		JLabel l2=new JLabel("Desired password:  ");
		l2.setFont(userInputFont);
		pPanel.add(l2, c3);
		GridBagConstraints c4=new GridBagConstraints();
		c4.gridx=1;				
		c4.gridy=0;
		pPanel.add(passwordField, c4);
		
		JPanel rPanel=new JPanel();
		rPanel.setSize(new Dimension(592, 100));
		rPanel.setPreferredSize(new Dimension(592, 100));
		rPanel.setBackground(ColorConstants.GREY);
		rPanel.setLayout(new GridBagLayout());
		GridBagConstraints c5=new GridBagConstraints();
		c5.gridx=0;
		c5.gridy=0;
		JLabel l3=new JLabel("Re-type Password:  ");
		l3.setFont(userInputFont);
		rPanel.add(l3, c5);
		GridBagConstraints c6=new GridBagConstraints();
		c6.gridx=1;				
		c6.gridy=0;
		rPanel.add(reenterPasswordField, c6);
		
		JPanel ePanel=new JPanel();
		ePanel.setSize(new Dimension(592, 100));
		ePanel.setPreferredSize(new Dimension(592, 100));
		ePanel.setBackground(ColorConstants.GREY);
		ePanel.setLayout(new GridBagLayout());
		GridBagConstraints c7=new GridBagConstraints();
		c7.gridx=0;
		c7.gridy=0;
		JLabel l4=new JLabel("  Enter your email:  ");
		l4.setFont(userInputFont);
		ePanel.add(l4, c7);
		GridBagConstraints c8=new GridBagConstraints();
		c8.gridx=1;				
		c8.gridy=0;
		ePanel.add(emailField, c8);
		
		JButton registerButton = new JButton ("Register me!");
		registerButton.setBackground(ColorConstants.ORANGE);
		registerButton.setForeground(Color.WHITE);
		registerButton.setSize(new Dimension(100, 50));
		registerButton.setPreferredSize(new Dimension(100, 50));
		JPanel registerButtonPanel=new JPanel();
		registerButtonPanel.setBackground(ColorConstants.GREY);
		registerButtonPanel.setSize(new Dimension(592, 50));
		registerButtonPanel.setPreferredSize(new Dimension(592, 50));
		registerButtonPanel.setLayout(new GridBagLayout());
		registerButton.addActionListener(new RegisterMeListener(this, usernameField, passwordField, reenterPasswordField, emailField, frame, changePanel));
		GridBagConstraints c9=new GridBagConstraints();
		c9.anchor=GridBagConstraints.CENTER;
		registerButtonPanel.add(registerButton, c9);
			
		this.add(uPanel);
		this.add(pPanel);
		this.add(rPanel);
		this.add(ePanel);
		this.add(registerButtonPanel);
		
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
	System.out.println("Text field says "+_field.getText());
			
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
			
		
		}
		
	}
	
	
	private class RegisterMeListener implements ActionListener{

		private TextField _usernameField, _emailField, _passwordField, _reenterField; 
		private JPanel _registration, _changePanel;
		private MyFrame _frame;
		
		public RegisterMeListener(JPanel registrationPanel, TextField usernameField, TextField passwordField, TextField reenterField, TextField emailField, MyFrame frame, JPanel changePanel){
			_usernameField=usernameField;
			_passwordField=passwordField;
			_reenterField=reenterField;
			_emailField=emailField;
			_registration=registrationPanel;
			_frame=frame;
			_changePanel=changePanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			boolean passwordFlag=false;
			
			ArrayList<String> errors=new ArrayList<String>();
			
			if (_usernameField.getText().trim().length()<=0){
				errors.add("    No username entered");
			}
			
			if (_passwordField.getText().trim().length()<=0 || _reenterField.getText().trim().length()<=0){
				errors.add("    No password entered");
				passwordFlag=true;
			}
			
			if (_emailField.getText().trim().length() <= 0){
				errors.add("    No email entered");
			}
			
			if (_passwordField.getText().trim().compareTo(_reenterField.getText().trim()) !=0){
				if (passwordFlag==false){
					errors.add("    Passwords do not match");
				}
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
			
			else {
				// HANDSHAKE:
				String userName = _usernameField.getText();
				Message result = _client.sendAndReceive(new Message(MessageContent.NEWUSERID, (Object) userName));
				if(result != null && result.getContent() == MessageContent.DONE){
					_client.setUserID(userName);
					_frame.getControlPanel().setEnable(true);
					_frame.setUsername(userName);
					_frame.getURLPanel().setEnable(true);
					_frame.getSignoutButton().setVisible(true);
					_frame.getNotificationsButton().setEnabled(true);
					_frame.getWebTagsButton().setEnabled(true);
					_changePanel.removeAll();
					_changePanel.add(new WelcomePanel(_usernameField.getText().trim(), _frame));
					_changePanel.repaint();
					_frame.repaint();
				} else {
					errors.add("    Username already exists");
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
			_reenterField.setText("");
			_emailField.setText("");
			
			
			
		}
		
	}


}
