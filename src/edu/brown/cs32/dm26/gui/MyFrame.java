import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlPanel _cp;
	
	public MyFrame(){
		super("Welcome to TRAKR!");
		this.setVisible(true);
		this.setPreferredSize(new Dimension(800, 600));
		Container con=this.getContentPane();
		con.setBackground(ColorConstants.LIGHT_GRAY);
		JPanel changePanel=new JPanel();
		changePanel.setSize(new Dimension(592, 600));
		changePanel.setPreferredSize(new Dimension(592, 600));
		changePanel.setBackground(ColorConstants.ORANGE);
		changePanel.setLayout(new BorderLayout());
		OpeningPanel openingPanel=new OpeningPanel(this, changePanel);
		changePanel.add(openingPanel, BorderLayout.CENTER);
		_cp=new ControlPanel(new Dimension(200, 600), this, changePanel, openingPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.add(changePanel, BorderLayout.EAST);
		this.add(_cp, BorderLayout.WEST);
		this.pack();
	}
	
	public JButton getSignoutButton(){
		return _cp.getSignoutButton();
	}
	
	public JButton getNotificationsButton(){
		return _cp.getNotificationsButton();
	}
	
	public JButton getWebTagsButton(){
		return _cp.getWebTagsButton();
	}
	
	public JTextField getEnterURL(){
		return _cp.getEnterURL();
	}

}
