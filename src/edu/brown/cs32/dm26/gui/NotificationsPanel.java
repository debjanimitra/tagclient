
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class NotificationsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Not [] _nots;
	private JScrollPane _scroll;

	public NotificationsPanel(){
		super();
		this.setBackground(ColorConstants.BLUE);
		this.setSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(600, 600));
		_scroll=new JScrollPane();
		_scroll.setSize(new Dimension(600, 600));
		_scroll.setPreferredSize(new Dimension(600, 600));
		_nots=null;
		Not not=new Not();
		this.add(not);
	}
	
	
	public void setNots(Not [] nots){
		_nots=nots;
	}
	
	public Not[] getNots(){
		return _nots;
	}
}
