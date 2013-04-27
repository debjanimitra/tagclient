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

	
	public TagOption(Element element, Client client, String username, Document doc, String url, HTMLParsing parser, AllTagOptionsPanel allTag){
		super();
		this.setSize(new Dimension(590, 160));
		this.setPreferredSize(new Dimension(590, 160));
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		Font customFont=new Font("Verdana", Font.PLAIN, 12);
		String text=element.text();
		int start=50, end=50;
		if (text.length()<50){
			start=(int)0.5*text.length();
			end=start;
		}
		System.out.println("Start is:"+start);
		System.out.println("End is:"+end);

		if (text.charAt(start)!=' '){
			while (text.charAt(start)!=' '){
				start--;
			}
		}
		
		if (text.charAt(end)!=' '){
			while(text.charAt(end)!=' '){
				end--;
			}
		}
		
		JLabel startLabel=new JLabel("This element begins with : "+text.substring(0, start));
		JLabel endLabel= new JLabel("This element ends with : "+text.substring(text.length()-end, text.length()));
		this.setLayout(new GridLayout(4, 1));
		
	/**	JPanel fillerPanel1 = new JPanel();
		fillerPanel1.setBackground(ColorConstants.LIGHT_ORANGE);
		fillerPanel1.setSize(new Dimension(590, 5));
		fillerPanel1.setPreferredSize(new Dimension(590, 5));
		this.add(fillerPanel1); **/
		
		this.add(startLabel);
		this.add(endLabel);
		
	/**	JPanel titlePanel=new JPanel();
		titlePanel.setSize(new Dimension(590, 100));
		titlePanel.setPreferredSize(new Dimension(590, 100));
		titlePanel.setBackground(ColorConstants.LIGHT_ORANGE);
		
		
		JPanel anotherPanel=new JPanel();
		anotherPanel.setSize(new Dimension(590, 20));
		anotherPanel.setPreferredSize(new Dimension(590, 20));
		anotherPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		anotherPanel.setLayout(new BorderLayout());
		JLabel title=new JLabel("Give a name to this tag (optional):");
		title.setFont(customFont);
		anotherPanel.add(title, BorderLayout.CENTER); **/
		

		JLabel label=new JLabel("Name this element b4 selecting (OPTIONAL):");
		label.setFont(customFont);
		JPanel labelPanel=new JPanel();
		labelPanel.setSize(new Dimension(150, 30));
		labelPanel.setPreferredSize(new Dimension(150, 30));
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
		
	/**	titlePanel.setLayout(new GridLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.anchor=GridBagConstraints.LINE_START;
		titlePanel.add(anotherPanel, c1);
		GridBagConstraints c2=new GridBagConstraints();
		c2.anchor=GridBagConstraints.CENTER;
		titlePanel.add(yetAnotherPanel, c2); */
		
/**		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(anotherPanel, BorderLayout.NORTH);
		titlePanel.add(yetAnotherPanel, BorderLayout.SOUTH); 
		
		this.add(titlePanel); **/
		
	/**	JPanel fillerPanel2 = new JPanel();
		fillerPanel2.setBackground(ColorConstants.LIGHT_ORANGE);
		fillerPanel2.setSize(new Dimension(590, 30));
		fillerPanel2.setPreferredSize(new Dimension(590, 30));
		this.add(fillerPanel2); **/
		
		
		JPanel selectPanel=new JPanel();
		selectPanel.setSize(new Dimension(590, 30));
		selectPanel.setPreferredSize(new Dimension(590, 30));
		selectPanel.setBackground(ColorConstants.LIGHT_ORANGE);
		JButton selectButton=new JButton ("select this!");
		selectButton.addActionListener(new MySelectListener(allTag, client, element, username, doc, url, parser, customFont));
		selectButton.setBackground(ColorConstants.DARK_GRAY);
		selectButton.setForeground(ColorConstants.DARK_ORANGE);
		selectPanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.anchor=GridBagConstraints.CENTER;
		selectPanel.add(selectButton, c3);
		this.add(selectPanel);
		
	/**	JPanel fillerPanel3 = new JPanel();
		fillerPanel3.setBackground(ColorConstants.LIGHT_ORANGE);
		fillerPanel3.setSize(new Dimension(590, 5));
		fillerPanel3.setPreferredSize(new Dimension(590, 5));
		this.add(fillerPanel3);
	**/
		
		this.setFont(customFont);
		this.setVisible(true);
	}
	
	private class MySelectListener implements ActionListener{

		private Client _client;
		private Element _element;
		private String _username;
		private Document _doc;
		private String _url;
		private AllTagOptionsPanel _panel;
		private HTMLParsing _parser;
		private Font _font;
		
		public MySelectListener(AllTagOptionsPanel panel, Client client, Element element, String username, Document doc, String url, HTMLParsing parser, Font font){
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
			Data toSend = new Data(_element.text(),_url,_element.id(),_element.className(),_username,_doc.select("body").text(),perm);		

			if (perm == true){
				JPopupMenu pop2=new JPopupMenu();
				pop2.setSize(new Dimension(200 , 200));
				pop2.setPreferredSize(new Dimension(200, 200));
				JLabel question=new JLabel ("We can TRAK this element indefinitely. Would you like that?");
				question.setFont(_font);
				JButton yes=new JButton("Yup");
				JButton no=new JButton("No, thanks!");
				no.setSize(new Dimension(50, 50));
				no.setPreferredSize(new Dimension(50, 50));
				no.setBackground(ColorConstants.DARK_GRAY);
				no.setForeground(Color.WHITE);
				yes.setFont(_font);
				yes.setSize(new Dimension(50, 50));
				yes.setPreferredSize(new Dimension(50, 50));
				yes.setBackground(ColorConstants.DARK_GRAY);
				yes.setForeground(Color.WHITE);
				yes.setFont(_font);
				JPanel permPanel=new JPanel();
				permPanel.setSize(new Dimension(100, 70));
				permPanel.setPreferredSize(new Dimension(100, 70));
				permPanel.setLayout(new GridLayout());
				permPanel.add(question);
				permPanel.add(no);
				no.addActionListener(new NoListener(toSend, pop2));
				yes.addActionListener(new YesListener(pop2));
				pop2.show(_panel, 250, 250);
			} 
			
			
			JPopupMenu pop = new JPopupMenu ();
			pop.setLayout(new BorderLayout());			
			String title="";			
			Message result = _client.sendAndReceive(new Message(MessageContent.DATA, (Object) toSend));
			
			System.out.println("message received back");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
			pop.setSize(new Dimension(7*length, 50));
			pop.setPreferredSize(new Dimension(10*length, 50));
			JLabel label=new JLabel(title);
			pop.add(label);
			pop.show(_panel, 250, 250);
		}
		
		private class NoListener implements ActionListener{
			
			private Data _data;
			private JPopupMenu _pop;
			
			public NoListener(Data data, JPopupMenu pop){
				_data=data;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				_data.setPerm(false);
			}
			
		}
		
		private class YesListener implements ActionListener{
			
			private JPopupMenu _pop;
			
			public YesListener(JPopupMenu pop){
				_pop=pop;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
	}
	
}
