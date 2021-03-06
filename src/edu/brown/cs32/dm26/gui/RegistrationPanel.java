package edu.brown.cs32.dm26.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.commons.net.util.Base64;
import org.apache.commons.validator.routines.EmailValidator;

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
		JTextField usernameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JPasswordField reenterPasswordField = new JPasswordField();
		JTextField emailField = new JTextField();
		usernameField.setSize(new Dimension(30, 30));
		usernameField.setPreferredSize(new Dimension(30, 30));
		passwordField.setSize(new Dimension(30, 30));
		passwordField.setPreferredSize(new Dimension(30, 30));
		reenterPasswordField.setSize(new Dimension(30, 30));
		reenterPasswordField.setPreferredSize(new Dimension(30, 30));
		emailField.setSize(new Dimension(30, 30));
		emailField.setPreferredSize(new Dimension(30, 30));
		usernameField.setColumns(13);
		passwordField.setColumns(13);
		reenterPasswordField.setColumns(13);
		emailField.setColumns(13);
		usernameField.setFont(userInputFont);
		passwordField.setFont(userInputFont);
		reenterPasswordField.setFont(userInputFont);
		emailField.setFont(userInputFont);
		emailField.addActionListener(new RegisterMeListener(this, usernameField, passwordField, reenterPasswordField, emailField, frame, changePanel));


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
		
		JPanel anotherRando3=new JPanel();
		anotherRando3.setBackground(ColorConstants.GREY);
		this.add(anotherRando3);
		

	}
	


	private class RegisterMeListener implements ActionListener{

		private JTextField _usernameField, _emailField;
		private JPasswordField _passwordField, _reenterField;
		private JPanel _registration, _changePanel;
		private MyFrame _frame;

		public RegisterMeListener(JPanel registrationPanel, JTextField usernameField, JPasswordField passwordField, JPasswordField reenterField, JTextField emailField, MyFrame frame, JPanel changePanel){
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
			String pwd = new String(_passwordField.getPassword());
			String repwd = new String(_reenterField.getPassword());

			ArrayList<String> errors=new ArrayList<String>();
						
			
			if (_usernameField.getText().trim().length()<=0){
				errors.add("    No username entered");
			}

			if (pwd.trim().length()<=0 || repwd.trim().length()<=0){
				errors.add("    No password entered");
				passwordFlag=true;
			}

			if (_emailField.getText().trim().length() <= 0){
				errors.add("    No email entered");
			}
			EmailValidator val = EmailValidator.getInstance();
			if(!val.isValid(_emailField.getText())){
				errors.add("    Invalid Email Address");
			}

			if (pwd.trim().compareTo(repwd.trim()) !=0){
				if (passwordFlag==false){
					errors.add("    Passwords do not match");
				}
			}

			if(!pwd.trim().equals(pwd)){
				errors.add("    You can't have leading or trailing");
				errors.add("    spaces or tabs in your password.");
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
				String encodedPassword = Base64.encodeBase64String(pwd.getBytes());
				Message result = _client.sendAndReceive(new Message(MessageContent.NEWUSERID, (Object) userName+"\t"+encodedPassword+"\t"+_emailField.getText()));
				if(result != null && result.getContent() == MessageContent.DONE){
					_client.setUserID(userName+"\t"+encodedPassword);
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
