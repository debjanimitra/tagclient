package edu.brown.cs32.dm26.gui;

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
	
	public BelowElementPanel(){
		this.setSize(new Dimension(592, 475));
		this.setPreferredSize(new Dimension (592, 475));
		this.setBackground(ColorConstants.LIGHT_ORANGE);
		this.setVisible(true);
		_scroller=new JScrollPane();
		_scroller.setBackground(ColorConstants.LIGHT_ORANGE);
		_scroller.setSize(new Dimension(592, 475));
		_scroller.setPreferredSize(new Dimension(592, 475));
		_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	public void changePanel(AllTagOptionsPanel newPanel, MyFrame frame){
		this.removeAll();
		_scroller.removeAll();
		_scroller.add(newPanel);
		this.add(_scroller);
	//	this.add(newPanel);
		this.repaint();
		this.revalidate();	
		frame.repaint();
		frame.revalidate();
		_scroller.revalidate();
	}
}
