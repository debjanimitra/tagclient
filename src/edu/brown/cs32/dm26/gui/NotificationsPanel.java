package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class NotificationsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel _toAdd;
	private JScrollPane _scroller;
	
	public NotificationsPanel(Client client, MyFrame frame){
		this.setSize(new Dimension(590, 600));
		this.setPreferredSize(new Dimension (590, 600));
		this.setBackground(ColorConstants.GREY);
		this.setVisible(true);
		_toAdd=new JPanel();
		if (frame.getShouldStartEnabling()){
			frame.getURLPanel().getBackButton().setEnabled(true);
		}
		_toAdd.setBackground(ColorConstants.GREY);
		_scroller=new JScrollPane(_toAdd);
		_scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		_scroller.setBackground(ColorConstants.GREY);
		_scroller.setSize(new Dimension(590, 600));
		_scroller.setPreferredSize(new Dimension(590, 600));
		this.setLayout(new BorderLayout());
		this.add(_scroller, BorderLayout.CENTER);
		AllNotificationsPanel allNotification=new AllNotificationsPanel(client, this, frame);
		_scroller.revalidate();
		_scroller.getVerticalScrollBar().setUnitIncrement(16);
		this.repaint();
		this.revalidate();
	}
	
	public void changePanel(AllNotificationsPanel newPanel, MyFrame frame){
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
