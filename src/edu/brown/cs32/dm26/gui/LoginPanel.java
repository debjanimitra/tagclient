package edu.brown.cs32.dm26.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.apache.commons.net.util.Base64;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;


public class LoginPanel extends JPanel {

	/**
	 * This panel is the darker yellow panel on the top of the screen where the user can enter
	 * information to log in only if the user is already registered 
	 */
	
	private static final long serialVersionUID = 1L;
	private Client _client;

	public LoginPanel(RegistrationPanel rp, MyFrame frame, JPanel changePanel, Client client){
		super();
		_client = client;
		this.setSize(new Dimension(800, 120));
		this.setPreferredSize(new Dimension (800, 120));
		this.setBackground(ColorConstants.SEA);
		this.setVisible(true);
		this.setLayout(new GridLayout(3, 1));
		Font userInputFont=new Font("Verdana", Font.BOLD, 12);
		
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		
		GridBagConstraints c1=new GridBagConstraints();
		JTextField usernameField = new JTextField();
		usernameField.setSize(new Dimension(50, 30));
		usernameField.setPreferredSize(new Dimension(50, 30));
		JPasswordField passwordField = new JPasswordField();
		passwordField.setSize(new Dimension(30, 30));
		passwordField.setPreferredSize(new Dimension(30, 30));
		usernameField.setColumns(15);
		passwordField.setColumns(15);
		usernameField.setFont(userInputFont);
		passwordField.setFont(userInputFont);
		passwordField.addActionListener(new LoginInListener(frame, rp, usernameField, passwordField, changePanel));
		/*passwordField.addKeyListener(new PasswordListener(passwordField));*/
		
		JPanel uPanel=new JPanel();
		uPanel.setSize(new Dimension(592, 100));
		uPanel.setPreferredSize(new Dimension(592, 100));
		uPanel.setBackground(ColorConstants.SEA);
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
		pPanel.setBackground(ColorConstants.SEA);
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
		loginButton.setBackground(ColorConstants.ORANGE);
		loginButton.setForeground(Color.WHITE);
		loginButton.setSize(new Dimension(100, 50));
		loginButton.setPreferredSize(new Dimension(100, 50));
		JPanel loginButtonPanel=new JPanel();
		loginButtonPanel.setBackground(ColorConstants.SEA);
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

	private class LoginInListener implements ActionListener{

		private JTextField _usernameField;
		private JPasswordField _passwordField;
		private RegistrationPanel _registration;
		private MyFrame _frame;
		private JPanel _changePanel;
		
		public LoginInListener(MyFrame frame, RegistrationPanel registration, JTextField usernameField, JPasswordField passwordField, JPanel changePanel){
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
			String pwd = new String(_passwordField.getPassword());
			if (_usernameField.getText().trim().length()<=0){
				errors.add("    No username entered");
			}
			
			if (pwd.trim().length()<=0){
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
				String encodedPassword = Base64.encodeBase64String(pwd.getBytes());
				Message result = _client.sendAndReceive(new Message(MessageContent.USERID, (Object) userName+"\t"+encodedPassword));
				
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
					if(result != null){
						if(result.getContent() == MessageContent.ERRORHANDSHAKE_MULTIPLELOGINS){
							errors.add("    You're logged in elsewhere");
						} else if (result.getContent() == MessageContent.ERRORHANDSHAKE_UNKNOWNUSER) {
							errors.add("    Username not found");
						}
						else if(result.getContent()==MessageContent.ERRORHANDSHAKE_WRONGPASSWORD){
							errors.add("    Incorrect Password");
						}
					}
					
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
