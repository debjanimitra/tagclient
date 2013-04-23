import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;
import javax.swing.border.Border;



public class ControlPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _signoutButton, _notificationsButton, _webTagsButton;
	private JTextField _enterURL;

	public ControlPanel(Dimension dimension, MyFrame frame, JPanel changePanel, JPanel openingPanel){
		super();
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setBackground(ColorConstants.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		Border lineBorder=BorderFactory.createLineBorder(ColorConstants.PINK);
		this.setBorder(lineBorder);
		Font buttonFont=new Font("Verdana", Font.BOLD, 20);
		Font websiteFont=new Font("Verdana", Font.BOLD, 14);

		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setSize(new Dimension(200, 250));
		buttonPanel.setPreferredSize(new Dimension(200, 250));
		buttonPanel.setLayout(new GridLayout(4, 1));
		buttonPanel.setBackground(ColorConstants.LIGHT_GRAY);
		
		JPanel notificationsPanel=new JPanel();
		notificationsPanel.setSize(new Dimension(200, 100));
		notificationsPanel.setPreferredSize(new Dimension(200, 100));
		_notificationsButton=new JButton("Notifications");
		_notificationsButton.setEnabled(false);
		_notificationsButton.setBackground(ColorConstants.DARK_GRAY);
		_notificationsButton.setForeground(ColorConstants.BLUE);
		_notificationsButton.setFont(buttonFont);
		_notificationsButton.setSize(new Dimension(200, 60));
		_notificationsButton.setPreferredSize(new Dimension(200, 60));
		NotificationsListener nl=new NotificationsListener(frame, changePanel, openingPanel);
		_notificationsButton.addActionListener(nl);
		notificationsPanel.setLayout(new BorderLayout());
		notificationsPanel.add(_notificationsButton, BorderLayout.SOUTH);
		
		JPanel webTagsPanel=new JPanel();
		webTagsPanel.setSize(new Dimension(200, 100));
		webTagsPanel.setPreferredSize(new Dimension(200, 100));
		_webTagsButton=new JButton("Web Tags");
		_webTagsButton.setEnabled(false);
		_webTagsButton.setBackground(ColorConstants.DARK_GRAY);
		_webTagsButton.setForeground(ColorConstants.GREEN);
		_webTagsButton.setFont(buttonFont);
		_webTagsButton.setSize(new Dimension(200, 60));
		_webTagsButton.setPreferredSize(new Dimension(200, 60));
		_webTagsButton.addActionListener(new WebTagsListener(frame, changePanel, openingPanel, nl));
		webTagsPanel.setLayout(new BorderLayout());
		webTagsPanel.add(_webTagsButton, BorderLayout.SOUTH);
		
		JPanel enterURLPanel=new JPanel();
		enterURLPanel.setSize(new Dimension(200, 50));
		enterURLPanel.setPreferredSize(new Dimension(200, 50));
		_enterURL=new JTextField();
		_enterURL.setEnabled(false);
		_enterURL.setFont(websiteFont);
		_enterURL.setColumns(20);
		_enterURL.setForeground(ColorConstants.DARK_GRAY);
		_enterURL.setText("enter website & hit enter");
		MyListener listener=new MyListener(_enterURL);
		_enterURL.addKeyListener(listener);
		_enterURL.addMouseListener(listener);
		JPanel random=new JPanel();
		random.setSize(new Dimension(200, 30));
		random.setPreferredSize(new Dimension(200, 30));
		random.setLayout(new BorderLayout());
		_enterURL.setSize(new Dimension(200, 30));
		_enterURL.setPreferredSize(new Dimension(200, 30));
		enterURLPanel.setLayout(new BorderLayout());
		enterURLPanel.setBackground(ColorConstants.LIGHT_GRAY);
		
/**		JButton goButton=new JButton("trakr this!");
		goButton.setSize(new Dimension(100, 30));
		goButton.setPreferredSize(new Dimension(100, 30));
		goButton.setBackground(ColorConstants.DARK_GRAY);
		goButton.setForeground(ColorConstants.ORANGE);
		JPanel goPanel=new JPanel();
		goPanel.setSize(new Dimension(200, 30));
		goPanel.setPreferredSize(new Dimension(200, 30));
		goPanel.setBackground(ColorConstants.LIGHT_GRAY);
		goPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1=new GridBagConstraints();
		c1.gridx=1;
		c1.gridy=1;
		goPanel.add(goButton, c1); **/
		
		random.add(_enterURL, BorderLayout.CENTER);
		enterURLPanel.add(random, BorderLayout.SOUTH);
		
		buttonPanel.add(notificationsPanel);
		buttonPanel.add(webTagsPanel);
		buttonPanel.add(enterURLPanel);
		this.add(buttonPanel, BorderLayout.NORTH);
		
	//	Border line=BorderFactory.createLineBorder(Color.BLACK);
		JPanel logoPanel=new JPanel();
		logoPanel.setSize(new Dimension(200, 500));
		logoPanel.setPreferredSize(new Dimension(200, 500));
		logoPanel.setBackground(ColorConstants.LIGHT_GRAY);
		JLabel label=new JLabel("           Logo goes here");
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(label, BorderLayout.CENTER);
	//	logoPanel.setBorder(line);
		this.add(logoPanel, BorderLayout.CENTER);
		
		JPanel signoutPanel = new JPanel();
		signoutPanel.setSize(new Dimension(200, 200));
		signoutPanel.setPreferredSize(new Dimension(200, 200));
		_signoutButton=new JButton ("Sign out");
		_signoutButton.setBackground(ColorConstants.DARK_GRAY);
		_signoutButton.setForeground(ColorConstants.BRIGHT_YELLOW);
		this.add(_signoutButton, BorderLayout.SOUTH);
		_signoutButton.setVisible(false);
		_signoutButton.addActionListener(new SignoutListener(frame, openingPanel, changePanel, _signoutButton));
		this.setVisible(true);
	}
	
	public JButton getSignoutButton(){
		return _signoutButton;
	}
	
	public JButton getNotificationsButton(){
		return _notificationsButton;
	}
	
	public JButton getWebTagsButton(){
		return _webTagsButton;
	}
	
	public JTextField getEnterURL(){
		return _enterURL;
	}
	
	private class SignoutListener implements ActionListener {

		private JPanel _ptc;
		private JPanel _op;
		private MyFrame _frame;
		private JButton _signout;
		
		public SignoutListener(MyFrame frame, JPanel op, JPanel panelToChange, JButton signout){
			_frame=frame;
			_ptc=panelToChange;
			_op=op;
			_signout=signout;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			_ptc.removeAll();
			_ptc.add(_op);
			_signout.setVisible(false);
			_frame.getNotificationsButton().setEnabled(false);
			_frame.getWebTagsButton().setEnabled(false);
			_frame.getEnterURL().setEnabled(false);
			_ptc.repaint();
			_frame.repaint();
		}
		
	}
	
	private class MyListener implements KeyListener, MouseListener{

		private JTextField _field;
		private int _counter;

		
		public MyListener(JTextField field){
			_field=field;
			_counter=0;
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
			
			String userInput=_field.getText();
			if (e.getKeyCode()==10){
				System.out.println("Now open url");
			}
			if (userInput.length()==0){	
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
	
	
	private class NotificationsListener implements ActionListener{

		private JPanel _ptc;
		private JPanel _panel;
		private WebTagsListener _wtl;
		private JFrame _frame;
		
		public NotificationsListener(JFrame frame, JPanel panelToChange, JPanel panel){
			_frame=frame;
			_ptc=panelToChange;
			_panel=panel;
			_wtl=null;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JPanel panel=new NotificationsPanel();
			_ptc.removeAll();
			_ptc.add(panel);
	//		_ptc.invalidate();
	//		_ptc.validate();
			_panel=panel;
			_wtl.setCurrentPanel(panel);
			_ptc.repaint();
			_frame.repaint();
			System.out.println(_panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		public void setWebTagsListener(WebTagsListener wtl){
			_wtl=wtl;
		}
		
	}
	
	
	private class WebTagsListener implements ActionListener{

		private JPanel _ptc;
		private JPanel _panel;
		private NotificationsListener _nl;
		private JFrame _frame;
		
		public WebTagsListener(JFrame frame, JPanel panelToChange, JPanel panel, NotificationsListener nl){
			_frame=frame;
			_ptc=panelToChange;
			_panel=panel;
			_nl=nl;
			_nl.setWebTagsListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JPanel panel=new WebTagsPanel();
			_ptc.removeAll();
			_ptc.add(panel);
		//	_ptc.invalidate();
		//	_ptc.validate();
			_panel=panel;
			_nl.setCurrentPanel(panel);
			_ptc.repaint();
			_frame.repaint();
			System.out.println(_panel);
		}
		
		public void setCurrentPanel(JPanel panel){
			_panel=panel;
		}
		
		
	}
	
}
