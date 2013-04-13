import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyFrame extends JFrame {
	
	public MyFrame(){
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800, 600));
		Container con=this.getContentPane();
		con.setBackground(new Color(236,234,224));
		//this.setBackground(new Color(236, 234, 224));
		JPanel openingPanel=new JPanel();
		openingPanel.setSize(new Dimension(600, 600));
		openingPanel.setPreferredSize(new Dimension(550, 600));
		openingPanel.setBackground(new Color(236,234,224));
		JLabel openingLabel=new JLabel("                                       Welcome to ______________");
		openingPanel.setLayout(new BorderLayout());
		openingPanel.add(openingLabel, BorderLayout.CENTER);
		ControlPanel cp=new ControlPanel(new Dimension(200, 600), this, openingPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(openingPanel, BorderLayout.EAST);
		this.add(cp, BorderLayout.WEST);
		this.pack();
	}
	

}
