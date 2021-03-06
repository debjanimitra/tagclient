package edu.brown.cs32.dm26.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.common.collect.ArrayListMultimap;

import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.takhan.tag.Notification;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;


public class AllNotificationsPanel extends JPanel {
	
	private ArrayList<NotificationOption> _options;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public AllNotificationsPanel(Client client, NotificationsPanel parentPanel, MyFrame frame){
		super();
		this.setBackground(ColorConstants.GREY);
		Message message = client.sendAndReceive(new Message(MessageContent.GET_NOTIFICATIONS, null));
		if (message !=null){
			if (message.getContent()== MessageContent.DONE_GETNOTIFICATIONS){
				_options = new ArrayList<NotificationOption>();
				@SuppressWarnings("unchecked")
				ArrayList<Notification> result = (ArrayList<Notification>) message.getObject();
				if (result.size()>0){
					frame.getNotificationsButton().setText("Notifications ("+result.size()+")");
				}
				else{
					frame.getNotificationsButton().setText("Notifications");
				}
				
				frame.getNotificationsButton().repaint();
				frame.getNotificationsButton().revalidate();
				for (Notification n: result){
					NotificationOption option=new NotificationOption(this, frame, client, result, parentPanel, n);
					_options.add(option);
				}
				
			 int width=570;
			 int height=_options.size()*100;
			 this.setSize(new Dimension(width, height));
			 this.setPreferredSize(new Dimension(width, height));
			 this.setLayout(new GridLayout(_options.size(), 1));
			 for (int i=0; i<_options.size(); i++){
				 this.add(_options.get(i));
			 }
			 
			 parentPanel.changePanel(this, frame);
				
			} else {
				// something must be terribly wrong. received wrong message from server
			}
		}
		parentPanel.repaint();
		parentPanel.revalidate();	
		frame.repaint();
		frame.revalidate();
		
	}
	
	

}
