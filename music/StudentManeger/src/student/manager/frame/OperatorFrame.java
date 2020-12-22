package student.manager.frame;


import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class MyPanel1 extends JPanel
{
	private static final long serialVersionUID = 1L;
	Image image = Toolkit.getDefaultToolkit().getImage("back.png");
	public void paintComponent (Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}	
}

public class OperatorFrame {
	MyPanel1 panel = new MyPanel1();
	JFrame frame = new JFrame ("操作界面");
	JMenuBar menuBar = new JMenuBar ();
	JMenu menuSelect = new JMenu("查询");
	JMenu menuUpdate = new JMenu ("修改");
	JMenu menuHelp = new  JMenu("帮助");
	JMenu menuExit = new JMenu("退出");
	
	JMenuItem menuItemSelect = new JMenuItem("按用户名查询");
	JMenuItem menuItemUpdate = new JMenuItem("修改用户信息");
	JMenuItem menuItemReturn = new JMenuItem("返回登录");
	JMenuItem menuItemExit = new JMenuItem("退出");
	JMenuItem menuItemHelp = new JMenuItem("帮助信息");
	Container cp = frame.getContentPane();
	
//	ImageIcon icon = new ImageIcon ("back.jpg");
//	JLabel lab = new JLabel(icon);
//	JPanel panel;
//	
	
	OperatorFrame ()
	{
//		lab.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
//		frame.getLayeredPane().add(lab,Integer.MIN_VALUE);
//		panel = (JPanel)cp;
//		
//		panel.setOpaque(false);
		menuSelect.add(menuItemSelect);
		menuUpdate.add(menuItemUpdate);
		menuExit.add(menuItemReturn);
		menuExit.addSeparator();
		menuExit.add(menuItemExit);
		menuHelp.add(menuItemHelp);
		menuBar.add(menuSelect);
		menuBar.add(menuUpdate);
		menuBar.add(menuExit);
		menuBar.add(menuHelp);
		
		frame.setJMenuBar(menuBar);
		frame.add(panel);
		menuItemSelect.addActionListener(new MyActionListener());
		menuItemUpdate.addActionListener(new MyActionListener());
		menuItemReturn.addActionListener(new MyActionListener());
		menuItemExit.addActionListener(new MyActionListener());
		menuItemHelp.addActionListener(new MyActionListener());
		
		frame.setSize(300, 200);;                                                          
		frame.setLocation(300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
			
	}
	class MyActionListener implements ActionListener {

	
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==menuItemSelect)
			{
				
				new SelectFrame ();
			}
			if (e.getSource()==menuItemUpdate)
			{
				
				new UpdateFrame();
			}
			if (e.getSource()==menuItemExit)
			{
				frame.dispose();
			}
			if (e.getSource()==menuItemReturn)
			{
				new LoginFrame();
				frame.dispose();
			}
			if (e.getSource()==menuItemHelp)
			{
				//ta.setText("hello,can I help you ?");
			}
		}
		
	}
	
}
