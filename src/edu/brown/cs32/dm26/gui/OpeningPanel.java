import java.awt.*;
import javax.swing.JPanel;


public class OpeningPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpeningPanel(MyFrame frame, JPanel changePanel){
		super();
		this.setSize(new Dimension(592, 600));
		this.setPreferredSize(new Dimension(592, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(ColorConstants.LIGHT_YELLOW);
		RegistrationPanel rp=new RegistrationPanel(frame, changePanel);
		LoginPanel op=new LoginPanel(rp, frame, changePanel);
		this.add(op, BorderLayout.NORTH);
		this.add(rp);
		this.setVisible(true);
		System.out.println("Opening panel");
	}
	
}
