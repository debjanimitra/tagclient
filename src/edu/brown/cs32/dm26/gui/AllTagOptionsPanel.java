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
	
	public AllTagOptionsPanel(ArrayList<Element> elementOptions, Client client, String username, Document doc, String url, HTMLParsing parser, TagURLPanel parent, MyFrame frame){
		super();
		this.setBackground(ColorConstants.GREY);
		_options=new ArrayList<TagOption>();
		for (int i=0; i<elementOptions.size(); i++){
			Element ele=elementOptions.get(i);
			if (ele.text().trim().length()>0){
				TagOption tag=new TagOption(ele, client, username, doc, url, parser, parent, i, frame);
				_options.add(tag);
			}
		}
		int width=570;
		int height=_options.size()*190;
		this.setSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(_options.size(), 1));
		for (int i=0; i<_options.size(); i++){
			this.add(_options.get(i));
		}
	}
	
}
