package edu.brown.cs32.dm26.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.plaf.ColorUIResource;

import edu.brown.cs32.vgavriel.connectorOnClient.Client;

public class Main {

	public static void main(String [] args){
		//try {
		 //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 //   System.out.println(UIManager.getSystemLookAndFeelClassName());
			//for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    //    if ("Nimbus".equals(info.getName())) {
		     //       UIManager.setLookAndFeel(info.getClassName());
		      //      break;
		       // }
		   // }
		//} catch (Exception e) {
		 //   e.printStackTrace();
		//}
				
		final String HOSTNAME = "localhost";
		final int PORT = 5000;
		
		MyFrame frame = new MyFrame();
		Client client = new Client(HOSTNAME, PORT);
		
		//UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black ));
		//UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
		//UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN, 11));
	}
	
}