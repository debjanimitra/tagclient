import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WelcomePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomePanel(String user){
		super();
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(ColorConstants.BRIGHT_YELLOW);
		Font font=new Font("Verdana", Font.BOLD, 35);
		JLabel label1=new JLabel("  Welcome to trakr, "+user+" "+"!");
		label1.setSize(new Dimension(600, 600));
		label1.setPreferredSize(new Dimension(600, 600));
		System.out.println(label1.getSize());
		label1.setFont(font);
		this.setLayout(new BorderLayout());
		this.add(label1, BorderLayout.CENTER);
	}
	

}