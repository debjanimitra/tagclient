package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;


public class MyFrame extends JFrame {
	
	/**
	 * This class models the frame on which the applications UI is. 
	 * The frame cannot be resized.
	 * The frame has 2 separate parts: 
	 * 		The control panel on the left
	 * 		The change panel on the right
	 * 
	 * Throughout the execution of the program, the control panel remains in 
	 * its position, unchanged. The user can navigate options on the control panel.
	 * 
	 * The change panel, as its name suggests, changes. These changes depend on 
	 * the selections that the user has made on the control panel. 
	 */
	
	private static final long serialVersionUID = 1L;
	private ControlPanel _cp;
	private String _username;
	private URLPanel _urlPanel;
	private boolean _shouldStartEnabling;
	private JPanel _changePanel, _backupChangePanel;
	
	public MyFrame(Client client){
		super("Woof woof!");
		client.setFrame(this);
		//this.client = client;		
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800, 669));
		Container con=this.getContentPane();
		con.setBackground(ColorConstants.LIGHT_GRAY);
		_shouldStartEnabling=false;
		
		/**
		 * I havent created a separte class for the changePanel. The changePanel is just a JPanel
		 * whose components are updated based on where the user has navigated
		 */
		this.setLayout(new BorderLayout());
		JPanel bottomPanel=new JPanel();
		bottomPanel.setSize(new Dimension(800, 600));
		bottomPanel.setPreferredSize(new Dimension(800, 600));
		bottomPanel.setLayout(new BorderLayout());
		_username=null;
		_changePanel=new JPanel();
		_changePanel.setSize(new Dimension(592, 600));
		_changePanel.setPreferredSize(new Dimension(592, 600));
		_changePanel.setLayout(new BorderLayout());
		OpeningPanel openingPanel=new OpeningPanel(this, _changePanel, client);
		_changePanel.add(openingPanel, BorderLayout.CENTER);
		_cp=new ControlPanel(new Dimension(200, 600), this, _changePanel, openingPanel, client);
		_cp.setEnable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		bottomPanel.add(_changePanel, BorderLayout.EAST);
		bottomPanel.add(_cp, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		_urlPanel=new URLPanel(this, _changePanel, client);
		_urlPanel.setEnable(false);
		this.add(_urlPanel, BorderLayout.NORTH);
		this.pack();
	}
	
	public void setBackupChangePanel(JPanel changePanel){
		_backupChangePanel=changePanel;
	}
	
	public JPanel getBackUpChangePanel(){
		return _backupChangePanel;
	}
	
	public void setChangePanel(JPanel changePanel){
		_changePanel=changePanel;
	}
	
	public JPanel getChangePanel(){
		return _changePanel;
	}
	
	public ControlPanel getControlPanel(){
		return _cp;
	}
	
	public boolean getShouldStartEnabling(){
		return _shouldStartEnabling;
	}
	
	public void setShouldStartEnabling(boolean start){
		_shouldStartEnabling=start;
	}
	
	public URLPanel getURLPanel(){
		return _urlPanel;
	}

	
	public void setUsername(String username){
		_username=username;
	}
	
	public String getUsername(){
		return _username;
	}
	
	public JButton getSignoutButton(){
		return _cp.getSignoutButton();
	}
	
	public JButton getNotificationsButton(){
		return _cp.getNotificationsButton();
	}
	
	public JButton getWebTagsButton(){
		return _cp.getWebTagsButton();
	}
	
	public JTextField getEnterURL(){
		return _cp.getEnterURL();
	}
	

}
