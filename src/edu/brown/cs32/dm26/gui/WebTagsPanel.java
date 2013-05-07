package edu.brown.cs32.dm26.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import edu.brown.cs32.dcorrea.htmlparsing.HTMLParsing;
import edu.brown.cs32.vgavriel.connectorOnClient.Client;


public class WebTagsPanel extends JPanel {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel _toAdd;
	private JScrollPane _scroller;

	public WebTagsPanel(Client client, MyFrame frame){
		this.setSize(new Dimension(590, 600));
		this.setPreferredSize(new Dimension (590, 600));
		this.setBackground(ColorConstants.GREY);
		this.setVisible(true);
		if (frame.getShouldStartEnabling()){
			frame.getURLPanel().getBackButton().setEnabled(true);
		}
		_scroller=new JScrollPane(_toAdd);
		_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		_scroller.setBackground(ColorConstants.GREY);
		_scroller.setSize(new Dimension(590, 600));
		_scroller.setPreferredSize(new Dimension(590, 600));
		_toAdd=new JPanel();
		_toAdd.setBackground(ColorConstants.GREY);
		_scroller.setViewportView(_toAdd);
		this.setLayout(new BorderLayout());
		this.add(_scroller, BorderLayout.CENTER);
		_scroller.revalidate();
		_scroller.getVerticalScrollBar().setUnitIncrement(16);
		AllWebTagsPanel panel=new AllWebTagsPanel(client, this, frame);
		this.revalidate();
		this.repaint();
	}
	
	public void changePanel(AllWebTagsPanel newPanel, MyFrame frame){
		_scroller.remove(_toAdd);
		_toAdd.removeAll();
		_toAdd.setSize(new Dimension(newPanel.getWidth(), newPanel.getHeight()));
		_toAdd.setPreferredSize(new Dimension(newPanel.getWidth(), newPanel.getHeight()));
		_toAdd.add(newPanel);
		_scroller.add(_toAdd);
		_scroller.setViewportView(_toAdd);
		this.repaint();
		this.revalidate();	
		frame.repaint();
		frame.revalidate();
		_scroller.revalidate();
	} 	
	
}
