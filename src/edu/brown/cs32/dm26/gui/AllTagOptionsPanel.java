package edu.brown.cs32.dm26.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class AllTagOptionsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TagOption> _options;
//	private HTMLParsing _parser;
//	private String _userInput;
	
	public AllTagOptionsPanel(ArrayList<Element> elementOptions, Client client, String username, Document doc, String url, HTMLParsing parser){
		super();
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		_options=new ArrayList<TagOption>();
		for (int i=0; i<elementOptions.size(); i++){
			Element ele=elementOptions.get(i);
			System.out.println(i+" th element= "+ele.text());
			if (ele.text().trim().length()>0){
				TagOption tag=new TagOption(ele, client, username, doc, url, parser, this);
				_options.add(tag);
			}
		}
		System.out.println("Size of arraylist is "+elementOptions.size());
		int width=590;
		int height=_options.size()*190;
		this.setSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(_options.size(), 1));
		for (int i=0; i<_options.size(); i++){
			System.out.println("Adding"+_options.get(i));
			this.add(_options.get(i));
		}
	}
	
}
