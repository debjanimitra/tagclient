package edu.brown.cs32.dm26.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jsoup.nodes.Element;

import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;

public class SelectThisButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectThisButton(OptionsPanel options, Client client){
		super();
		this.addActionListener(new SelectListener(options, client));
	}
	
	private class SelectListener implements ActionListener{

		private OptionsPanel _options;
		private Client _client;
		
		public SelectListener(OptionsPanel options, Client client){
			_options=options;
			_client=client;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.dataMaker();
		}
		
		public void dataMaker(){
			Data newData=new Data(_options.getElement().text(), _options.getURL(), _options.getElement().id(), _options.getElement().className(),  _options.getUsername(), _options.getDoc(), false);
			Message result =_client.sendAndReceive(new Message(MessageContent.DATA, newData));
			if(result != null && result.getContent() == MessageContent.DONE){
				// display success to the user
			} else if(result != null && result.getContent() == MessageContent.ERROR_RECEIVE_TAGALREADYEXISTS){
				// display to user that the tag has already been made/recored
			}
		}
		
	}

}
