package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.google.common.collect.ArrayListMultimap;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.takhan.tag.Data;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;
import edu.brown.cs32.vgavriel.connectorOnServer.Message;
import edu.brown.cs32.vgavriel.connectorOnServer.MessageContent;

public class WebTagOption extends JPanel {
	
	public WebTagOption(Data data, AllWebTagsPanel panel, MyFrame frame, Client client, ArrayListMultimap<String, Data> result, WebTagsPanel parentPanel){
		super();
		this.setBackground(ColorConstants.PALE_GREEN);
		this.setSize(new Dimension(585, 250));
		this.setPreferredSize(new Dimension(585, 250));
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		this.setBorder(border);
		Font customFont1=new Font("Verdana", Font.BOLD, 12);
		this.setLayout(new GridLayout(9, 1));
		JLabel labelURL=new JLabel("URL: "+data.getURL());
		labelURL.setFont(customFont1);
		this.add(labelURL);
		JLabel labelTitle=new JLabel("Title: "+data.getTitle());
		this.add(labelTitle);
		labelTitle.setFont(customFont1);
		if (data.getPerm()==true){
			JLabel labelPerm=new JLabel("Is this being TRAKed indefinitely: Yes");
			labelPerm.setFont(customFont1);
			this.add(labelPerm);
		}
		else {
			JLabel labelPerm=new JLabel("Is this being TRAKed indefinitely: No");
			labelPerm.setFont(customFont1);
			this.add(labelPerm);
		}
		
	
		
		JPanel filler=new JPanel();
		filler.setBackground(ColorConstants.PALE_GREEN);
		filler.setSize(new Dimension(590, 30));
		filler.setPreferredSize(new Dimension(590, 30));
		this.add(filler);
				
				
				
		Font customFont2=new Font("Verdana", Font.PLAIN, 12);
		
		/**
		 * 
		 * Making changes to the title of the element
		 *  
		 */
		
		JLabel changeTitle=new JLabel("Give this tag another name: ");
		changeTitle.setFont(customFont2);
		JPanel labelPanel=new JPanel();
		labelPanel.setSize(new Dimension(130, 35));
		labelPanel.setPreferredSize(new Dimension(130, 35));
		labelPanel.setBackground(ColorConstants.GREEN);
		labelPanel.add(changeTitle);
		
		
		TextField titleField = new TextField();
		titleField.setColumns(30);
		titleField.setFont(customFont2);
		titleField.setSize(new Dimension(200, 30));
		titleField.setPreferredSize(new Dimension(200, 30));
		
		JPanel tPanel=new JPanel();
		tPanel.setSize(new Dimension(200, 35));
		tPanel.setPreferredSize(new Dimension(200, 35));
		tPanel.setBackground(ColorConstants.GREEN);
		tPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=0;
		tPanel.add(titleField, c1);
			
		JPanel titlePanel=new JPanel();
		titlePanel.setSize(new Dimension(590, 35));
		titlePanel.setPreferredSize(new Dimension(590, 35));
		titlePanel.setBackground(ColorConstants.GREEN);
		titlePanel.setLayout(new GridLayout(1,2));
		titlePanel.add(labelPanel);
		titlePanel.add(tPanel);
		this.add(titlePanel);
		
		/**
		 * 
		 * Making changes to the indefinite/definite nature of the tag
		 * 
		 */
		
		JPanel permPanel=new JPanel();
		permPanel.setSize(new Dimension(590, 30));
		permPanel.setPreferredSize(new Dimension(590, 30));
		permPanel.setBackground(ColorConstants.GREEN);
		

		JLabel label1=new JLabel("Re-choose if you want to TRAK this indefinitely: ");
		label1.setFont(customFont2);
		JPanel labelPanel1=new JPanel();
		labelPanel1.setSize(new Dimension(310, 30));
		labelPanel1.setPreferredSize(new Dimension(310, 30));
		labelPanel1.setBackground(ColorConstants.GREEN);
		labelPanel1.add(label1); 

		
		 JRadioButton firstButton = new JRadioButton("Yes");
		 firstButton.setBackground(ColorConstants.GREEN);
		 if (data.getCanBePermanent()==false){
			 firstButton.setEnabled(false);
		 }
		 JRadioButton secondButton = new JRadioButton("No");
		 secondButton.setBackground(ColorConstants.GREEN);
		 
		    // Group the radio buttons.
		 ButtonGroup group = new ButtonGroup();
		 
		 if (data.getPerm()==true){
			 firstButton.setSelected(true);
			 secondButton.setSelected(false);
		 }
		 else{
			 secondButton.setSelected(true);
			 firstButton.setSelected(false);
		 }
		 group.add(firstButton);
		 group.add(secondButton);
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setSize(new Dimension(280, 30));
		buttonPanel.setPreferredSize(new Dimension(280, 30));
		buttonPanel.setBackground(ColorConstants.GREEN);
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(firstButton);
		buttonPanel.add(secondButton);
		
		permPanel.setLayout(new BorderLayout());
		permPanel.add(labelPanel1, BorderLayout.WEST);
		permPanel.add(buttonPanel, BorderLayout.CENTER);
		this.add(permPanel);
		
		/**
		 * 
		 * Discard a certain tag
		 * 
		 */
		
		JPanel discardPanel=new JPanel();
		discardPanel.setSize(new Dimension(590, 30));
		discardPanel.setPreferredSize(new Dimension(590, 30));
		discardPanel.setBackground(ColorConstants.GREEN);
		

		JLabel label2=new JLabel("Would you like to discard this tag: ");
		label2.setFont(customFont2);
		JPanel labelPanel2=new JPanel();
		labelPanel2.setSize(new Dimension(310, 30));
		labelPanel2.setPreferredSize(new Dimension(310, 30));
		labelPanel2.setBackground(ColorConstants.GREEN);
		labelPanel2.add(label2); 

		
		 JRadioButton firstButton2 = new JRadioButton("Yes");
		 firstButton2.setSelected(false);
		 firstButton2.setBackground(ColorConstants.GREEN);
		 JRadioButton secondButton2 = new JRadioButton("No");
		 secondButton2.setSelected(true);
		 secondButton2.setBackground(ColorConstants.GREEN);
		 
		    // Group the radio buttons.
		 ButtonGroup group2 = new ButtonGroup();
		 group2.add(firstButton2);
		 group2.add(secondButton2);
		
		JPanel buttonPanel2=new JPanel();
		buttonPanel2.setSize(new Dimension(280, 30));
		buttonPanel2.setPreferredSize(new Dimension(280, 30));
		buttonPanel2.setBackground(ColorConstants.GREEN);
		buttonPanel2.setLayout(new GridLayout(1, 2));
		buttonPanel2.add(firstButton2);
		buttonPanel2.add(secondButton2);
		
		discardPanel.setLayout(new BorderLayout());
		discardPanel.add(labelPanel2, BorderLayout.WEST);
		discardPanel.add(buttonPanel2, BorderLayout.CENTER);
		this.add(discardPanel);		
		
		JPanel filler2=new JPanel();
		filler2.setBackground(ColorConstants.PALE_GREEN);
		filler2.setSize(new Dimension(590, 30));
		filler2.setPreferredSize(new Dimension(590, 30));
		this.add(filler2);
		
		JPanel savePanel=new JPanel();
		savePanel.setSize(new Dimension(590, 30));
		savePanel.setPreferredSize(new Dimension(590, 30));
		savePanel.setBackground(ColorConstants.PALE_GREEN);
		JButton saveButton=new JButton ("Save");
		//selectButton.addActionListener(new MySelectListener(allTag, client, element, username, doc, url, parser, customFont, titleField, secondButton, this, perm));
		saveButton.setBackground(ColorConstants.DARK_GRAY);
		saveButton.setForeground(ColorConstants.GREEN);
		saveButton.addActionListener(new SaveListener(titleField, firstButton, firstButton2, frame, client, result, data, parentPanel));
		savePanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.anchor=GridBagConstraints.CENTER;
		savePanel.add(saveButton, c3);
		this.add(savePanel);
	
		System.out.println("Done adding this");
		
	}
	
	private class SaveListener implements ActionListener{

		private TextField _field;
		private JRadioButton _yesIndefinite, _yesDiscard;
		private MyFrame _frame;
		private Client _client;
		private ArrayListMultimap<String, Data> _arrList;
		private Data _thisData;
		private WebTagsPanel _parentPanel;
		
		public SaveListener(TextField titleField, JRadioButton yesIndefiniteButton, JRadioButton yesDiscardButton, MyFrame frame, Client client, ArrayListMultimap<String, Data> arrList, Data thisData, WebTagsPanel parentPanel){
			_field=titleField;
			_yesIndefinite=yesIndefiniteButton;
			_yesDiscard=yesDiscardButton;
			_frame=frame;
			_client=client;
			_arrList=arrList;
			_thisData=thisData;
			_parentPanel=parentPanel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (_field.getText().length()>0){
				_thisData.setTitle(_field.getText().trim());
			}
			_thisData.setPerm(_yesIndefinite.isSelected());
			if (_yesDiscard.isSelected()){
				 _arrList.remove(_thisData.getURL(), _thisData);
			}
			
			System.out.println("here");
			
			Message result = _client.sendAndReceive(new Message(MessageContent.UPDATE_WEBTAGS, (Object) _arrList));
			
			if(result != null && result.getContent() == MessageContent.DONE_UPDATEWEBTAGS){
				System.out.println("Message after");
				AllWebTagsPanel awtp = new AllWebTagsPanel (_client, _parentPanel, _frame); 
				System.out.println("between");
				_parentPanel.changePanel(awtp, _frame);
				
				System.out.println("After");
			} else{
				//do something??
			}
			
			
		}
		
	}

}
