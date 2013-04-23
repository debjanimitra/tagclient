import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class WTags extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WTags(){
		super();
		this.setSize(new Dimension(592, 100));
		this.setPreferredSize(new Dimension(592, 100));
		this.setBackground(ColorConstants.BLUE);
		LineBorder border=new LineBorder(Color.WHITE, 3, false);
		this.setBorder(border);
		Font customFont=new Font("Verdana", Font.PLAIN, 12);
		this.setFont(customFont);
	}

}
