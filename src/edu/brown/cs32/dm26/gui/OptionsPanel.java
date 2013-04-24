package edu.brown.cs32.dm26.gui;

import javax.swing.JPanel;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class OptionsPanel extends JPanel {

	private Element _element;
	private String _text;
	private String _url;
	private String _username;
	private Document _doc;
	
	public OptionsPanel(Element element, String text, String url, String username, Document doc){
		super();
		_element=element;
		_text=text;
		_url=url;
		_username=username;
		_doc=doc;
	}
	
	public Element getElement(){
		return _element;
	}
	
	public String getText(){
		return _text;
	}
	
	public String getURL(){
		return _url;
	}
	
	public String getUsername(){
		return _username;
	}
	
	public Document getDoc(){
		return _doc;
	}
	
}
