package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
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

	
	public TagOption(Element element, Client client, String username, Document doc, String url, HTMLParsing parser, TagURLPanel allTag, int index, MyFrame frame){
		super();
		this.setSize(new Dimension(570, 160));
		this.setPreferredSize(new Dimension(570, 160));
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		EtchedBorder border=new EtchedBorder(ColorConstants.SEA, ColorConstants.GREY);
		this.setBorder(border);
		Font customFont=new Font("Verdana", Font.PLAIN, 12);
		Font customFont2=new Font("Verdana", Font.BOLD, 12);
		String text=element.text();
		boolean isShort=false;
		int start=40, end=40;
		if (text.length()<41){
			isShort=true;
		}
		System.out.println("Start is:"+start);
		System.out.println("End is:"+end);
		JLabel startLabel, endLabel;

		if (isShort==false){
			if (text.charAt(start)!=' '){
				while (text.charAt(start)!=' ' && start>0){
					start--;
				}
			}
		
			if (text.charAt(end)!=' '){
				while(text.charAt(end)!=' ' && end<text.length()-1){
					end++;
				}
				end--;
			}
		
			startLabel=new JLabel(" This element begins with : "+text.substring(0, start));
			endLabel= new JLabel(" This element ends with : "+text.substring(text.length()-end, text.length()));
		}
		else{
			startLabel=new JLabel(" This element is : "+text);
			endLabel=new JLabel("");
		}
		
		this.setLayout(new GridLayout(5, 1));

		
		this.add(startLabel);
		this.add(endLabel);
		

		JLabel label=new JLabel("  Name this element b4 selecting(OPTIONAL):");
		label.setFont(customFont);
		JPanel labelPanel=new JPanel();
		labelPanel.setSize(new Dimension(130, 30));
		labelPanel.setPreferredSize(new Dimension(130, 30));
		labelPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		labelPanel.add(label);
		
		
		TextField titleField = new TextField();
		titleField.setColumns(30);
		titleField.setFont(customFont);
		titleField.setSize(new Dimension(200, 30));
		titleField.setPreferredSize(new Dimension(200, 30));
		
		JPanel tPanel=new JPanel();
		tPanel.setSize(new Dimension(200, 30));
		tPanel.setPreferredSize(new Dimension(200, 30));
		tPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		tPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=0;
		tPanel.add(titleField, c1);
		this.add(tPanel);
		
		JPanel random=new JPanel();
		random.setSize(new Dimension(140, 30));
		random.setPreferredSize(new Dimension(140, 30));
		random.setBackground(ColorConstants.LIGHT_ORANGE);
		
		JPanel optionPanel=new JPanel();
		optionPanel.setSize(new Dimension(590, 30));
		optionPanel.setPreferredSize(new Dimension(590, 30));
		optionPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		optionPanel.setLayout(new GridLayout(1,2));
		optionPanel.add(labelPanel);
		optionPanel.add(tPanel);
	//	optionPanel.add(random);
		this.add(optionPanel);
		
		JPanel permPanel=new JPanel();
		permPanel.setSize(new Dimension(590, 30));
		permPanel.setPreferredSize(new Dimension(590, 30));
		permPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		
		
		boolean perm=parser.canBePermanent(element).getPerm();
		JLabel label1=null;
		
		if (perm){
			label1=new JLabel("");
		}
		else{
			label1=new JLabel("NOTE: Trakr can only follow this element for one notification");
		}
		label1.setFont(customFont);
		JPanel labelPanel1=new JPanel();
		labelPanel1.setSize(new Dimension(388, 30));
		labelPanel1.setPreferredSize(new Dimension(388, 30));
		labelPanel1.setBackground(ColorConstants.LIGHT_ORANGE);
		labelPanel1.add(label1); 
		
	/**	 JRadioButton firstButton = new JRadioButton("Yes");
		 firstButton.setSelected(false);
		 firstButton.setBackground(ColorConstants.LIGHT_ORANGE);
		 boolean perm=parser.canBePermanent(element).getPerm();
		 if (perm==false){
			 firstButton.setEnabled(false);
		 }
		 JRadioButton secondButton = new JRadioButton("No");
		 secondButton.setSelected(true);
		 secondButton.setBackground(ColorConstants.LIGHT_ORANGE);
		 
		    // Group the radio buttons.
		 ButtonGroup group = new ButtonGroup();
		 group.add(firstButton);
		 group.add(secondButton);
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setSize(new Dimension(280, 30));
		buttonPanel.setPreferredSize(new Dimension(280, 30));
		buttonPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(firstButton);
		buttonPanel.add(secondButton);**/
		
		permPanel.setLayout(new BorderLayout());
		permPanel.add(labelPanel1, BorderLayout.WEST);
	//	permPanel.add(buttonPanel, BorderLayout.CENTER);
		this.add(permPanel);
		
		JPanel selectPanel=new JPanel();
		selectPanel.setSize(new Dimension(590, 30));
		selectPanel.setPreferredSize(new Dimension(590, 30));
		selectPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		JButton selectButton=new JButton ("select this!");
		selectButton.addMouseListener(new MySelectListener(allTag, client, element, username, doc, url, parser, customFont2, titleField, this, perm, index, frame));
		selectButton.setBackground(ColorConstants.HUNTER);
		selectButton.setForeground(Color.WHITE);
		selectPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.anchor=GridBagConstraints.CENTER;
		selectPanel.add(selectButton, c3);
		this.add(selectPanel);
		
		this.setFont(customFont);
		this.setVisible(true);
	}
	
	private class MySelectListener implements MouseListener{

		private Client _client;
		private Element _element;
		private String _username;
		private Document _doc;
		private String _url;
		private TagURLPanel _panel;
		private HTMLParsing _parser;
		private Font _font;
		private TextField _titleField;
		private TagOption _option;
		private boolean _canBePermanent;
		private int _index;
		private MyFrame _frame;
		
		public MySelectListener(TagURLPanel panel, Client client, Element element, String username, Document doc, String url, HTMLParsing parser, Font font, TextField titleField,  TagOption option, boolean canBePermanent, int index, MyFrame frame){
			_client=client;
			_element=element;
			_username=username;
			_doc=doc;
			_url=url;
			_panel=panel;
			_parser=parser;
			_font=font;
			_titleField=titleField;
			_option=option;
			_canBePermanent=canBePermanent;
			_index=index;
			_frame=frame;
		}
		
	//	@Override
	//	public void actionPerformed(ActionEvent e) {
	//		// TODO Auto-generated method stub
//
//		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			Data toSend = new Data(_element.text(),_url,"#"+_element.id(),"."+_element.className(),_username,_doc.select("body").text(), _titleField.getText(), _canBePermanent, _canBePermanent, false);		
			
			JPopupMenu pop = new JPopupMenu ();
			pop.setLayout(new BorderLayout());			
			String title="";			
			Message result = _client.sendAndReceive(new Message(MessageContent.DATA, (Object) toSend));
			
			System.out.println("message received back");			
			int flag=0;
			
			if(result == null){
				title="The message received back from the server is null!";
				flag=1;
			} else if(result.getContent() == MessageContent.DONE){
				title="Successful tag!";
				flag=2;
			} else if(result.getContent() == MessageContent.ERROR_RECEIVE_TAGALREADYEXISTS){
				title="Already following this!";
				flag=3;
			} else if(result.getContent() == MessageContent.ERROR_RECEIVE_INVALIDDATA) {
				title= "your request contains no data :(";
				flag=4;
			}
			
			int length=title.length();
			pop.setBackground(ColorConstants.EVEN_LIGHTER_SEA);
			pop.setBorder(BorderFactory.createLineBorder(ColorConstants.EVEN_LIGHTER_SEA));
			JLabel label=new JLabel(title);
			pop.add(label);
			label.setForeground(Color.RED);
			Font font=new Font("Verdana", Font.BOLD, 15);
			label.setFont(font);
			
			if (flag==2){
			pop.setSize(new Dimension((int)(10*length), 50));
			pop.setPreferredSize(new Dimension((int)(10*length), 50));
			pop.show(_frame, 40, 250);
			}
			
			if (flag==3){
				pop.setSize(new Dimension((int)(8*length), 50));
				pop.setPreferredSize(new Dimension((int)(8*length), 50));
				pop.show(_frame, 10, 250);
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
