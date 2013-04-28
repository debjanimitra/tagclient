package edu.brown.cs32.dm26.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;

public class AllWebTagsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<WebTagOption> _options;
	/**
	 * @param args
	 */
	public AllWebTagsPanel(Client client, WebTagsPanel parentPanel, MyFrame frame){
		super();
		Message message = client.sendAndReceive(new Message(MessageContent.GET_WEBTAGS, null));
		System.out.println("Before null check");
		if (message !=null){
			System.out.println("msg not null");
			if (message.getContent()== MessageContent.DONE_GETWEBTAGS){
				System.out.println("Got here");
				_options = new ArrayList<WebTagOption>();
				@SuppressWarnings("unchecked")
				ArrayListMultimap<String, Data> result = (ArrayListMultimap<String, Data>) message.getObject();
				System.out.println("result size "+ result.size());
				Collection<Data> data=result.values();
				System.out.println("Size of data"+data.size());
				for (Data d: data){
					System.out.println("YAYEEEE");
					WebTagOption option=new WebTagOption(d);
					_options.add(option);
				}
				
			 int width=590;
			 int height=_options.size()*200;
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
		System.out.println("After null check");
	}

}
