package student.manager.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectFrame {
	JFrame frame = new JFrame ("查询界面");
	JLabel labUserName = new JLabel("用户名");
	JLabel labPassword = new JLabel ("密码");
	JLabel labPhone = new JLabel ("电话");
	JLabel labBirthday = new JLabel("出生日期");
	
	JTextField tfUserName = new JTextField (10);
	JTextField pfPassword = new JTextField(10);
	JTextField tfPhone = new JTextField (10);
	JTextField tfBirthday = new JTextField (10);

	ImageIcon icon = new ImageIcon("Search-icon.png");
	JButton btnSelect = new JButton ("查询",icon);
	JButton btnSelectAll = new JButton("查询全部",icon);
	JButton btnReturn = new JButton ("返回");
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	Container cp = frame.getContentPane();
	
	SelectFrame ()
	{
		panel1.setLayout(new GridLayout(1,5,10,10));
		panel1.add(labUserName);
		panel1.add(tfUserName);
		panel1.add(btnSelect);
		panel1.add(btnSelectAll);
		panel1.add(btnReturn);
		
		panel2.setLayout(new GridLayout(3,2,10,10));
		panel2.add(labPassword);
		panel2.add(pfPassword);
		panel2.add(labPhone);
		panel2.add(tfPhone);
		panel2.add(labBirthday);
		panel2.add(tfBirthday);
		tfUserName.setText(LoginFrame.userName);
		tfUserName.setEnabled(false);
		cp.add(panel1,BorderLayout.NORTH);
		cp.add(panel2,BorderLayout.CENTER);
		btnSelectAll.setEnabled(false);
		if (LoginFrame.userName.equals("admin"))
		{
			btnSelectAll.setEnabled(true);
		}
			
		btnSelectAll.addActionListener(	
				new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ShowInformationFrame ();
			}
			
		}
		);
		btnReturn.addActionListener(
			
				new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
					
				}
				);
		btnSelect.addActionListener(
				new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
							
							String info [] = new FileOperator().FileRead();
							String name = LoginFrame.userName;
							for (int i=0;i<info.length;i++)
							{
								String stu[] = info[i].split(",");
								for (int j=0;j<stu.length;j++)
								if (name.equals(stu[0]))
								{
									pfPassword.setText(stu[1]);
									tfPhone.setText(stu[2]);
									tfBirthday.setText(stu[3]);
								}
							}
				
					}
					
				}
				);
		frame.pack();
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300,400);
		frame.setVisible(true);
		
	}
}
