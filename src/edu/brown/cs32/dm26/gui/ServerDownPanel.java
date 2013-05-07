package edu.brown.cs32.dm26.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerDownPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JLabel _label1;
	private MyFrame _frame;

	public ServerDownPanel(MyFrame frame){
		super();
		this.setSize(new Dimension(600, 595));
		this.setPreferredSize(new Dimension(600, 595));
		this.setBackground(ColorConstants.GREY);
		Font font=new Font("Verdana", Font.BOLD, 15);
		_frame=frame;
		_label1=new JLabel("");
		_label1.setSize(new Dimension(600, 150));
		_label1.setPreferredSize(new Dimension(600, 150));
		_label1.setFont(font);
		frame.getURLPanel().getBackButton().setEnabled(false);
		this.setLayout(new BorderLayout());
		this.add(_label1, BorderLayout.CENTER);
	}
	
	public void changePanel(String string){
		this.removeAll();
		_label1.setText(string);
		this.add(_label1);
		this.repaint();
		_frame.repaint();
		this.revalidate();
		_frame.revalidate();
	}
	
	
}
