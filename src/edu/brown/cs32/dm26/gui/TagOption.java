package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.brown.cs32.dcorrea.htmlparsing.ElementInfo;
import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;

public class TagOption extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public TagOption(Element element, Client client, String username, Document doc, String url, HTMLParsing parser){
		super();
		this.setSize(new Dimension(590, 90));
		this.setPreferredSize(new Dimension(590, 90));
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		Font customFont=new Font("Verdana", Font.PLAIN, 12);
		String text=element.text();
		int start=40, end=40;
		if (text.length()<40){
			start=(int)0.4*text.length();
			end=start;
		}
		JLabel startLabel=new JLabel("This element begins with: "+text.substring(0, start));
		JLabel endLabel= new JLabel("This element ends with: "+text.substring(text.length()-end, text.length()));
		this.setLayout(new GridLayout(3, 1));
		this.add(startLabel);
		this.add(endLabel);
		
		JPanel selectPanel=new JPanel();
		selectPanel.setSize(new Dimension(590, 30));
		selectPanel.setPreferredSize(new Dimension(590, 30));
		selectPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		JButton selectButton=new JButton ("select this!");
		selectButton.addActionListener(new MySelectListener(this, client, element, username, doc, url, parser, customFont));
		selectButton.setBackground(ColorConstants.DARK_GRAY);
		selectButton.setForeground(ColorConstants.DARK_ORANGE);
		selectPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.anchor=GridBagConstraints.CENTER;
		selectPanel.add(selectButton, c1);
		this.add(selectPanel);
		
		this.setFont(customFont);
		this.setVisible(true);
	}
	
	private class MySelectListener implements ActionListener{

		private Client _client;
		private Element _element;
		private String _username;
		private Document _doc;
		private String _url;
		private TagOption _panel;
		private HTMLParsing _parser;
		private Font _font;
		
		public MySelectListener(TagOption panel, Client client, Element element, String username, Document doc, String url, HTMLParsing parser, Font font){
			_client=client;
			_element=element;
			_username=username;
			_doc=doc;
			_url=url;
			_panel=panel;
			_parser=parser;
			_font=font;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			ElementInfo info=_parser.canBePermanent(_element);
			boolean perm=info.getPerm();
			Data toSend = new Data(_element.text(),_url,_element.id(),_element.className(),_username,_doc,perm);		
			JPopupMenu pop = new JPopupMenu ();
			if (perm == true){
				JPopupMenu pop2=new JPopupMenu();
				JLabel question=new JLabel ("We can TRAK this element indefinitely. Would you like that?");
				question.setFont(_font);
				JButton no=new JButton("No, thanks!");
				no.setSize(new Dimension(50, 50));
				no.setPreferredSize(new Dimension(50, 50));
				no.setBackground(ColorConstants.DARK_GRAY);
				no.setForeground(Color.WHITE);
				no.setFont(_font);
				JPanel permPanel=new JPanel();
				permPanel.setSize(new Dimension(100, 70));
				permPanel.setPreferredSize(new Dimension(100, 70));
				permPanel.setLayout(new GridLayout());
				permPanel.add(question);
				permPanel.add(no);
				no.addActionListener(new noListener(toSend));
				pop2.show(_panel, 200, 0);
			}
			
			pop.setLayout(new BorderLayout());
			String title="";			
			Message result = _client.sendAndReceive(new Message(MessageContent.DATA, (Object) toSend));
			if(result == null){
				title="The message received back from the server is null!";
			} else if(result.getContent() == MessageContent.DONE){
				title="Succes! We received the request and will TRAK your element";
			} else if(result.getContent() == MessageContent.ERROR_RECEIVE_TAGALREADYEXISTS){
				title="we're already TRAKing this for you!";
			} else if(result.getContent() == MessageContent.ERROR_RECEIVE_INVALIDDATA) {
				title= "your request contains no data :(";
			}
			
			int length=title.length();
			pop.setSize(new Dimension(5*length, 50));
			pop.setPreferredSize(new Dimension(5*length, 50));
			JLabel label=new JLabel(title);
			pop.add(label);
			pop.show(_panel, 200, 0);
		}
		
		private class noListener implements ActionListener{
			
			private Data _data;
			
			public noListener(Data data){
				_data=data;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_data.setPerm(false);
			}
			
		}
		
	}
	
}
