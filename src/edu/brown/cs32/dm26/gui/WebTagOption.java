package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.takhan.tag.Data;

public class WebTagOption extends JPanel {
	
	public WebTagOption(Data data){
		super();
		this.setBackground(ColorConstants.GREEN);
		this.setSize(new Dimension(590, 200));
		this.setPreferredSize(new Dimension(590, 200));
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(border);
		Font customFont1=new Font("Verdana", Font.BOLD, 12);
		this.setLayout(new GridLayout(7, 1));
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
		
	
		
		JLabel lineLOL=new JLabel("________________________________________________________________________________________________________");
		lineLOL.setFont(customFont1);
		this.add(lineLOL);
		
		Font customFont2=new Font("Verdana", Font.PLAIN, 12);
		
		/**
		 * 
		 * Making changes to the title of the element
		 *  
		 */
		
		JLabel changeTitle=new JLabel("Give this tag another name: ");
		changeTitle.setFont(customFont2);
		JPanel labelPanel=new JPanel();
		labelPanel.setSize(new Dimension(130, 30));
		labelPanel.setPreferredSize(new Dimension(130, 30));
		labelPanel.setBackground(ColorConstants.GREEN);
		labelPanel.add(changeTitle);
		
		
		TextField titleField = new TextField();
		titleField.setColumns(30);
		titleField.setFont(customFont2);
		titleField.setSize(new Dimension(200, 30));
		titleField.setPreferredSize(new Dimension(200, 30));
		
		JPanel tPanel=new JPanel();
		tPanel.setSize(new Dimension(200, 30));
		tPanel.setPreferredSize(new Dimension(200, 30));
		tPanel.setBackground(ColorConstants.GREEN);
		tPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=0;
		c1.gridy=0;
		tPanel.add(titleField, c1);
			
		JPanel titlePanel=new JPanel();
		titlePanel.setSize(new Dimension(590, 30));
		titlePanel.setPreferredSize(new Dimension(590, 30));
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
		 firstButton.setSelected(false);
		 firstButton.setBackground(ColorConstants.GREEN);
		 if (data.getCanBePermanent()==false){
			 firstButton.setEnabled(false);
		 }
		 JRadioButton secondButton = new JRadioButton("No");
		 secondButton.setSelected(true);
		 secondButton.setBackground(ColorConstants.GREEN);
		 
		    // Group the radio buttons.
		 ButtonGroup group = new ButtonGroup();
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
		
		JPanel savePanel=new JPanel();
		savePanel.setSize(new Dimension(590, 30));
		savePanel.setPreferredSize(new Dimension(590, 30));
		savePanel.setBackground(ColorConstants.GREEN);
		JButton saveButton=new JButton ("Save");
		//selectButton.addActionListener(new MySelectListener(allTag, client, element, username, doc, url, parser, customFont, titleField, secondButton, this, perm));
		saveButton.setBackground(ColorConstants.DARK_GRAY);
		saveButton.setForeground(ColorConstants.DARK_GREEN);
		savePanel.setLayout(new GridBagLayout());
		GridBagConstraints c3=new GridBagConstraints();
		c3.anchor=GridBagConstraints.CENTER;
		savePanel.add(saveButton, c3);
		this.add(savePanel);
	
		System.out.println("Done adding this");
		
	}

}
