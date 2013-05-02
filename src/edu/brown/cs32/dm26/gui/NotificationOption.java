package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import browserOpening.BareBonesBrowserLaunch;

import com.google.common.collect.ArrayListMultimap;

import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;
import edu.brown.cs32.takhan.tag.Notification;

public class NotificationOption extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotificationOption(AllNotificationsPanel panel, MyFrame frame, Client client, ArrayList<Notification> result, NotificationsPanel parentPanel, Notification thisNotification){
		super();
		System.out.println("In notification option");
		this.setSize(new Dimension(570, 100));
		this.setPreferredSize(new Dimension(570, 100));
		this.setBackground(ColorConstants.LIGHT_SEA);
		this.setLayout(new GridLayout(2, 1));
		
		EtchedBorder border=new EtchedBorder(ColorConstants.ORANGE, ColorConstants.GREY);
		this.setBorder(border);
		
		Font customFont1=new Font("Verdana", Font.BOLD, 12);
		Font customFont2=new Font("Verdana", Font.BOLD, 13);
		
		JLabel promptLabel=new JLabel("Trakr has found a change!");
		promptLabel.setFont(customFont2);
		
		JPanel promptPanel=new JPanel();
		promptPanel.add(promptLabel);
		promptPanel.setBackground(ColorConstants.LIGHT_SEA);
		
		if (thisNotification.getTitle().length()>0){
			promptLabel.setText("Trakr has found a change in "+thisNotification.getTitle()+" !");
		}
		
		JLabel urlLabel=new JLabel("The URL is: "+thisNotification.getURL());
		urlLabel.setFont(customFont1);
		
		JPanel urlPanel=new JPanel();
		urlPanel.add(urlLabel);
		urlPanel.setBackground(ColorConstants.LIGHT_SEA);
		
		this.add(promptPanel);
		this.add(urlPanel);
		this.addMouseListener(new PanelListener(thisNotification, client, frame, parentPanel));
		
	}
	


	private class PanelListener implements MouseListener{

		private Notification _thisNotification;
		private Client _client;
		private MyFrame _frame;
		private NotificationsPanel _parentPanel;
		
		public PanelListener(Notification thisNotification, Client client, MyFrame frame, NotificationsPanel parentPanel){
			_thisNotification=thisNotification;
			_client=client;
			_frame=frame;
			_parentPanel=parentPanel;
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
	System.out.println("in action listener");
			
			Message result = _client.sendAndReceive(new Message(MessageContent.DELETE_NOTIFICATION, _thisNotification.getID()));
			if(result != null && result.getContent() == MessageContent.DONE_DELETENOTIFICATION){
				System.out.println("before barebones");
				BareBonesBrowserLaunch.openURL(_thisNotification.getURL());
				System.out.println("Message after");
				AllNotificationsPanel anp = new AllNotificationsPanel (_client, _parentPanel, _frame); 
				System.out.println("between");
				_parentPanel.changePanel(anp, _frame);
				System.out.println("After");
			} else if(result != null && result.getContent() == MessageContent.ERROR_GETNOTIFICATIONS_UNKNOWNUSER){
				// there was an error, but that should not occur
			} else if(result == null){
				// the message equals null.... not good
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
