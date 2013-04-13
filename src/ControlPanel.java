import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.Caret;


public class ControlPanel extends JPanel {

	public ControlPanel(Dimension dimension, JFrame frame, JPanel openingPanel){
		super();
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setBackground(new Color(236,234,224));
		this.setLayout(new BorderLayout());
		Border lineBorder=BorderFactory.createLineBorder(new Color(99,170,156));
		this.setBorder(lineBorder);
		Font buttonFont=new Font("Verdana", Font.BOLD, 20);
		Font websiteFont=new Font("Verdana", Font.BOLD, 14);
		Color dimGray=new Color(108, 108, 108);
		Color dimOrange=new Color(210,105,30);
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setSize(new Dimension(200, 250));
		buttonPanel.setPreferredSize(new Dimension(200, 250));
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.setBackground(new Color(236,234,224));
		
		JPanel notificationsPanel=new JPanel();
		notificationsPanel.setSize(new Dimension(200, 100));
		notificationsPanel.setPreferredSize(new Dimension(200, 100));
		JButton notificationsButton=new JButton("Notifications");
		notificationsButton.setBackground(dimGray);
		notificationsButton.setForeground(dimOrange);
		notificationsButton.setFont(buttonFont);
		notificationsButton.setBackground(dimGray);
		notificationsButton.setSize(new Dimension(200, 60));
		notificationsButton.setPreferredSize(new Dimension(200, 60));
		NotificationsListener nl=new NotificationsListener(frame, openingPanel);
		notificationsButton.addActionListener(nl);
		notificationsPanel.setLayout(new BorderLayout());
		notificationsPanel.add(notificationsButton, BorderLayout.SOUTH);
		
		JPanel webTagsPanel=new JPanel();
		webTagsPanel.setSize(new Dimension(200, 100));
		webTagsPanel.setPreferredSize(new Dimension(200, 100));
		JButton webTagsButton=new JButton("Web Tags");
		webTagsButton.setBackground(dimGray);
		webTagsButton.setForeground(dimOrange);
		webTagsButton.setFont(buttonFont);
		webTagsButton.setBackground(dimGray);
		webTagsButton.setSize(new Dimension(200, 60));
		webTagsButton.setPreferredSize(new Dimension(200, 60));
		webTagsButton.addActionListener(new WebTagsListener(frame, openingPanel, nl));
		webTagsPanel.setLayout(new BorderLayout());
		webTagsPanel.add(webTagsButton, BorderLayout.SOUTH);
		
		JPanel enterURLPanel=new JPanel();
		enterURLPanel.setSize(new Dimension(200, 50));
		enterURLPanel.setPreferredSize(new Dimension(200, 50));
		JTextField enterURL=new JTextField();
		enterURL.setFont(websiteFont);
		enterURL.setColumns(20);
		enterURL.setForeground(dimOrange);
		enterURL.setText("  enter full website here");
		MyListener listener=new MyListener(enterURL);
		enterURL.addKeyListener(listener);
		enterURL.addMouseListener(listener);
		JPanel random=new JPanel();
		random.setSize(new Dimension(200, 30));
		random.setPreferredSize(new Dimension(200, 30));
		random.setLayout(new BorderLayout());
		enterURL.setSize(new Dimension(200, 30));
		enterURL.setPreferredSize(new Dimension(200, 30));
		enterURLPanel.setLayout(new BorderLayout());
		enterURLPanel.setBackground(new Color(236,234,224));
		random.add(enterURL, BorderLayout.SOUTH);
		enterURLPanel.add(random, BorderLayout.SOUTH);
		
		buttonPanel.add(notificationsPanel);
		buttonPanel.add(webTagsPanel);
		buttonPanel.add(enterURLPanel);
		this.add(buttonPanel, BorderLayout.NORTH);
		
	//	Border line=BorderFactory.createLineBorder(Color.BLACK);
		JPanel logoPanel=new JPanel();
		logoPanel.setSize(new Dimension(200, 550));
		logoPanel.setPreferredSize(new Dimension(200, 550));
		logoPanel.setBackground(Color.WHITE);
		JLabel label=new JLabel("           Logo goes here");
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(label, BorderLayout.CENTER);
	//	logoPanel.setBorder(line);
		this.add(logoPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private class MyListener implements KeyListener, MouseListener{

		private JTextField _field;
		private int _counter;
		private String _userInput;
		private Caret _caret;
		
		public MyListener(JTextField field){
			_field=field;
			_counter=0;
			_userInput="";
			_caret=_field.getCaret();
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		 	
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

			
			//String _userInput= _field.getText();
			Character c=e.getKeyChar();
			if (c==8 && _userInput.length()>=1){
				_userInput=_userInput.substring(0, _userInput.length()-1);
				_counter--;
			}
			else{
				int pos=_caret.getDot();
				if (pos < _userInput.length()-1){
					String first=_userInput.substring(0, pos-1);
					first=first+e.getKeyChar();
					String second=_userInput.substring(pos-1, _userInput.length());
					first=first+second;
					_userInput=first;
				//	_caret.setDot(pos);
				}
				
				else{
					_userInput=_userInput+e.getKeyChar();
				}
				_counter++;
			}
				if (_userInput!=null && _counter>0){
					_field.setText(_userInput);
				}
				if (_userInput!=null && _counter==0){
					_field.setText("  enter full website here");
				}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (_counter==0){
				_field.setText("");
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	private static class MyButtonUI extends BasicButtonUI
	{
	    public void paint ( Graphics g, JComponent c )
	    {
	        JButton myButton = ( JButton ) c;
	        ButtonModel buttonModel = myButton.getModel ();

	        if ( buttonModel.isPressed () || buttonModel.isSelected () )
	        {
	            g.setColor ( Color.GRAY );
	        }
	        else
	        {
	            g.setColor (new Color(80, 208, 125));
	        }
	        g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );

	        super.paint ( g, c );
	    }
	}
	
	private class NotificationsListener implements ActionListener{

		private JFrame _frame;
		private JPanel _panel;
		private WebTagsListener _wtl;
		
		public NotificationsListener(JFrame frame, JPanel panel){
			_frame=frame;
			_panel=panel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(_panel);
			JPanel panel=new NotificationsPanel();
			_frame.getContentPane().remove(_panel);
			_frame.add(panel);
			_frame.getContentPane().invalidate();
			_frame.getContentPane().validate();
			_panel=panel;
			_wtl.setCurrentPanel(panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		public void setWebTagsListener(WebTagsListener wtl){
			_wtl=wtl;
		}
		
	}
	
	
	private class WebTagsListener implements ActionListener{

		private JFrame _frame;
		private JPanel _panel;
		private NotificationsListener _nl;
		
		public WebTagsListener(JFrame frame, JPanel panel, NotificationsListener nl){
			_frame=frame;
			_panel=panel;
			_nl=nl;
			_nl.setWebTagsListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(_panel);
			JPanel panel=new WebTagsPanel();
			_frame.getContentPane().remove(_panel);
			_frame.add(panel);
			_frame.getContentPane().invalidate();
			_frame.getContentPane().validate();
			_panel=panel;
			_nl.setCurrentPanel(panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		
	}
	
}
