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
	JFrame frame = new JFrame ("��������");
	JMenuBar menuBar = new JMenuBar ();
	JMenu menuSelect = new JMenu("��ѯ");
	JMenu menuUpdate = new JMenu ("�޸�");
	JMenu menuHelp = new  JMenu("����");
	JMenu menuExit = new JMenu("�˳�");
	
	JMenuItem menuItemSelect = new JMenuItem("���û�����ѯ");
	JMenuItem menuItemUpdate = new JMenuItem("�޸��û���Ϣ");
	JMenuItem menuItemReturn = new JMenuItem("���ص�¼");
	JMenuItem menuItemExit = new JMenuItem("�˳�");
	JMenuItem menuItemHelp = new JMenuItem("������Ϣ");
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
