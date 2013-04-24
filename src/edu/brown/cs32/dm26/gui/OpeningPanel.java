package edu.brown.cs32.dm26.gui;
import java.awt.*;
import javax.swing.JPanel;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;


public class OpeningPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpeningPanel(MyFrame frame, JPanel changePanel, Client client){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_YELLOW);
		RegistrationPanel rp=new RegistrationPanel(frame, changePanel, client);
		LoginPanel op=new LoginPanel(rp, frame, changePanel, client);
		this.add(op, BorderLayout.NORTH);
		this.add(rp);
		this.setVisible(true);
		System.out.println("this");
		for (int i=0; i<this.getComponentCount(); i++){
			this.getComponent(i);
		}
	}
	
}
