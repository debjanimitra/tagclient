package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class NotificationsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel _toAdd;
	private JScrollPane _scroller;
	
	public NotificationsPanel(){
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension (600, 600));
		this.setBackground(ColorConstants.BLUE);
		this.setVisible(true);
		_toAdd=new JPanel();
		_toAdd.setSize(new Dimension(600, 570));
		_toAdd.setPreferredSize(new Dimension(600, 570));
		_toAdd.setBackground(ColorConstants.BLUE);
		_scroller=new JScrollPane(_toAdd);
		_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		_scroller.setBackground(ColorConstants.BLUE);
		_scroller.setSize(new Dimension(600, 570));
		_scroller.setPreferredSize(new Dimension(600, 570));
		this.setLayout(new BorderLayout());
		this.add(_scroller, BorderLayout.CENTER);
	}
	
	public void changePanel(AllTagOptionsPanel newPanel, MyFrame frame){
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
