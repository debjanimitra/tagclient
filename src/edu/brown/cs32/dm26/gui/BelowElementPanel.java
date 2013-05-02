package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class BelowElementPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane _scroller;
	private JPanel _toAdd;
	
	public BelowElementPanel(MyFrame frame){
		this.setSize(new Dimension(592, 475));
		this.setPreferredSize(new Dimension (592, 475));
		this.setBackground(ColorConstants.GREY);
		this.setVisible(true);
		_toAdd=new JPanel();
		_toAdd.setSize(new Dimension(592, 400));
		_toAdd.setPreferredSize(new Dimension(592, 400));
		_toAdd.setBackground(ColorConstants.GREY);
		_scroller=new JScrollPane(_toAdd);
		_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		_scroller.setBackground(ColorConstants.GREY);
		_scroller.setSize(new Dimension(592, 400));
		_scroller.setPreferredSize(new Dimension(592, 400));
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
