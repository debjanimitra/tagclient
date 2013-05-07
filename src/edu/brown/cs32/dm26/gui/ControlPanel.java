package edu.brown.cs32.dm26.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.UnknownHostException;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.jsoup.HttpStatusException;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;



public class ControlPanel extends JPanel {
	
	/**
	 * 
	 * This panel models the panel on the left containing the Notification button, the Webtags button, 
	 * the text area to enter the URL and the Sign Out button in the bottom (which appears only once the user
	 * has logged in).  
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _signoutButton, _notificationsButton, _webTagsButton;
	private JTextField _enterURL;
	private Client _client;
	private JPanel _notificationsPanel, _webTagsPanel, _signoutPanel, _buttonPanel, _logoPanel;

	public ControlPanel(Dimension dimension, MyFrame frame, JPanel changePanel, JPanel openingPanel, Client client){
		super();
		_client = client;
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
		this.setLayout(new BorderLayout());
		EtchedBorder border=new EtchedBorder(ColorConstants.SEA, ColorConstants.GREY);
		this.setBorder(border);
		Font buttonFont=new Font("Verdana", Font.BOLD, 17);
		Font websiteFont=new Font("Verdana", Font.BOLD, 14);

		
		_buttonPanel=new JPanel();
		_buttonPanel.setSize(new Dimension(200, 250));
		_buttonPanel.setPreferredSize(new Dimension(200, 250));
		_buttonPanel.setLayout(new GridLayout(4, 1));
		_buttonPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
		
		_notificationsPanel=new JPanel();
		_notificationsPanel.setSize(new Dimension(200, 100));
		_notificationsPanel.setPreferredSize(new Dimension(200, 100));
		_notificationsButton=new JButton("Notifications");
		client.setNotification(_notificationsButton);
		_notificationsButton.setEnabled(false);
		_notificationsButton.setBackground(ColorConstants.LIGHT_GRAY);
		_notificationsButton.setForeground(Color.WHITE);
		_notificationsButton.setFont(buttonFont);
		_notificationsButton.setSize(new Dimension(200, 60));
		_notificationsButton.setPreferredSize(new Dimension(200, 60));
		NotificationsListener nl=new NotificationsListener(frame, changePanel, openingPanel);
		_notificationsButton.addActionListener(nl);
		_notificationsPanel.setLayout(new BorderLayout());
		_notificationsPanel.add(_notificationsButton, BorderLayout.SOUTH);
		
		_webTagsPanel=new JPanel();
		_webTagsPanel.setSize(new Dimension(200, 100));
		_webTagsPanel.setPreferredSize(new Dimension(200, 100));
		_webTagsButton=new JButton("Web Tags");
		_webTagsButton.setEnabled(false);
		_webTagsButton.setBackground(ColorConstants.LIGHT_GRAY);
		_webTagsButton.setForeground(Color.WHITE);
		_webTagsButton.setFont(buttonFont);
		_webTagsButton.setSize(new Dimension(200, 60));
		_webTagsButton.setPreferredSize(new Dimension(200, 60));
		_webTagsButton.addActionListener(new WebTagsListener(frame, changePanel, openingPanel, nl));
		_webTagsPanel.setLayout(new BorderLayout());
		_webTagsPanel.add(_webTagsButton, BorderLayout.SOUTH);
		
		_buttonPanel.add(_notificationsPanel);
		_buttonPanel.add(_webTagsPanel);
		this.add(_buttonPanel, BorderLayout.NORTH);
		
		_logoPanel=new JPanel();
		_logoPanel.setSize(new Dimension(200, 500));
		_logoPanel.setPreferredSize(new Dimension(200, 500));
		_logoPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
		this.add(_logoPanel, BorderLayout.CENTER);
		
		_signoutPanel = new JPanel();
		_signoutPanel.setSize(new Dimension(200, 200));
		_signoutPanel.setPreferredSize(new Dimension(200, 200));
		_signoutButton=new JButton ("Sign out");
		_signoutButton.setBackground(ColorConstants.LIGHT_GRAY);
		_signoutButton.setForeground(Color.WHITE);
		_signoutButton.setFont(websiteFont);
		this.add(_signoutButton, BorderLayout.SOUTH);
		_signoutButton.setVisible(false);
		_signoutButton.addActionListener(new SignoutListener(frame, openingPanel, changePanel, _signoutButton, _enterURL, this));
		this.setVisible(true);
	}
	
	public void setEnable(boolean bool){
		_notificationsButton.setEnabled(bool);
		_webTagsButton.setEnabled(bool);
		_signoutButton.setVisible(bool);
		if (bool==false){
			_notificationsButton.setBackground(ColorConstants.GRAY3);
			_webTagsButton.setBackground(ColorConstants.GRAY3);
			_notificationsPanel.setBackground(ColorConstants.GRAY4);
			_webTagsPanel.setBackground(ColorConstants.GRAY4);
			_buttonPanel.setBackground(ColorConstants.GRAY4);
			_logoPanel.setBackground(ColorConstants.GRAY4);
			_signoutPanel.setBackground(ColorConstants.GRAY4);
			this.setBackground(ColorConstants.GRAY4);
			this.setBorder(null);
		}else{
			_notificationsButton.setBackground(ColorConstants.LIGHT_GRAY);
			_webTagsButton.setBackground(ColorConstants.LIGHT_GRAY);
			_webTagsPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			_notificationsPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			_buttonPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			_logoPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			_signoutPanel.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			this.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			EtchedBorder border=new EtchedBorder(ColorConstants.SEA, ColorConstants.GREY);
			this.setBorder(border);
		}
	}
	
	public JButton getSignoutButton(){
		return _signoutButton;
	}
	
	public JButton getNotificationsButton(){
		return _notificationsButton;
	}
	
	public JButton getWebTagsButton(){
		return _webTagsButton;
	}
	
	public JTextField getEnterURL(){
		return _enterURL;
	}
	
	private class SignoutListener implements ActionListener {

		private JPanel _ptc;
		private JPanel _op;
		private MyFrame _frame;
		private JButton _signout;
		private JTextField _enterURL;
		private ControlPanel _cp;
		
		public SignoutListener(MyFrame frame, JPanel op, JPanel panelToChange, JButton signout, JTextField enterURL, ControlPanel cp){
			_frame=frame;
			_ptc=panelToChange;
			_op=op;
			_signout=signout;
			_enterURL=enterURL;
			_cp=cp;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("within signout listener-- before");
			_frame.setShouldStartEnabling(false);
			_frame.getURLPanel().getTextField().setText("");
			_frame.setUsername(null);
			_ptc.removeAll();
			_ptc.add(_op);
			_cp.setEnable(false);
			_signout.setVisible(false);
			_frame.getURLPanel().getTextField().setText("");
			_frame.getURLPanel().setEnable(false);
			_frame.getNotificationsButton().setEnabled(false);
			_frame.getNotificationsButton().setText("Notifications");
			_frame.getWebTagsButton().setEnabled(false);
			_ptc.repaint();
			_frame.repaint();
			_cp.revalidate();
			_ptc.revalidate();
			_frame.revalidate();
			_client.kill();
			System.out.println("within signout listener-- after");
		}
		
	}
	
	
	private class NotificationsListener implements ActionListener{

		private JPanel _ptc;
		private JPanel _panel;
		private WebTagsListener _wtl;
		private MyFrame _frame;
		
		public NotificationsListener(MyFrame frame, JPanel panelToChange, JPanel panel){
			_frame=frame;
			_ptc=panelToChange;
			_panel=panel;
			_wtl=null;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			NotificationsPanel panel=new NotificationsPanel(_client, _frame);
			_ptc.removeAll();
			_ptc.add(panel);
			_panel=panel;
			_wtl.setCurrentPanel(panel);
			_ptc.repaint();
			_frame.repaint();
			System.out.println(_panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		public void setWebTagsListener(WebTagsListener wtl){
			_wtl=wtl;
		}
		
	}
	
	
	private class WebTagsListener implements ActionListener{

		private JPanel _ptc;
		private JPanel _panel;
		private NotificationsListener _nl;
		private MyFrame _frame;
		private HTMLParsing _parser;
		
		public WebTagsListener(MyFrame frame, JPanel panelToChange, JPanel panel, NotificationsListener nl){
			_frame=frame;
			_ptc=panelToChange;
			_panel=panel;
			_nl=nl;
			_nl.setWebTagsListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			WebTagsPanel panel=new WebTagsPanel(_client, _frame);
			_ptc.removeAll();
			_ptc.add(panel);
			_panel=panel;
			_nl.setCurrentPanel(panel);
			_ptc.repaint();
			_frame.repaint();
			System.out.println(_panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		
	}
	
}