package student.manager.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateFrame implements ItemListener{
	JFrame frame = new JFrame ("修改界面");
	JLabel labUserName = new JLabel("用户名");
	JLabel labPassword = new JLabel ("密码");
	JLabel labPhone = new JLabel ("电话");
	JLabel labBirthday = new JLabel("出生日期");
	
	JTextField tfUserName = new JTextField (10);
	JTextField pfPassword = new JTextField(10);
	JTextField tfPhone = new JTextField (10);
	JComboBox<Integer> cmbBirthYear = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthMonth = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthDay = new JComboBox<Integer>();

	JButton btnUpdate = new JButton ("修改");
	JButton btnReturn = new JButton ("返回");
	
	JPanel panel1 = new JPanel ();
	JPanel panel2 = new JPanel ();
	JPanel panel3 = new JPanel ();
	JPanel panel4 = new JPanel ();
	
	Container cp = frame.getContentPane();
    // 构造函数
	UpdateFrame ()
	{
		panel1.setLayout(new GridLayout(3,2,10,10));
		panel1.add(labUserName);
		panel1.add(tfUserName);
		panel1.add(labPassword);
		panel1.add(pfPassword);
		panel1.add(labPhone);
		panel1.add(tfPhone);
		
		tfUserName.setText(LoginFrame.userName);
		tfUserName.setEnabled(false);;
		
		int year = 1990;
		for (int i=0;i<100;i++)
			cmbBirthYear.addItem(year+i);
		for (int i=1;i<=12;i++)
			cmbBirthMonth.addItem(i);
		for (int i=1;i<=31;i++)
			cmbBirthDay.addItem(i);
		
		cmbBirthYear.addItemListener(this);
		cmbBirthMonth.addItemListener(this);
		cmbBirthDay.addItemListener(this);
		
		
		panel2.setLayout(new GridLayout(1,4,10,10));
		panel2.add(labBirthday);
		panel2.add(cmbBirthYear);
		panel2.add(cmbBirthMonth);
		panel2.add(cmbBirthDay);
		
		panel3.setLayout(new GridLayout(1,2,10,10));
		panel3.add(btnUpdate);
		panel3.add(btnReturn);
		
		btnUpdate.addActionListener(new MyListener());
		btnReturn.addActionListener(
				new ActionListener ()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
					
				}
				);
		
		panel4.add(panel1,BorderLayout.CENTER);
		panel4.add(panel2,BorderLayout.SOUTH);
		
		cp.add(panel4,BorderLayout.CENTER);
		cp.add(panel3,BorderLayout.SOUTH);
		
		//frame.pack();
		frame.setSize(400,200);
		frame.setLocation(300, 400);
		frame.setResizable(false);
		frame.setVisible(true);
}
	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				String password = pfPassword.getText();
				String phone = tfPhone.getText();
				String birthYear = String.valueOf(cmbBirthYear.getSelectedItem());
				String birthMonth = String.valueOf(cmbBirthMonth.getSelectedItem());
				String birthDay = String.valueOf(cmbBirthDay.getSelectedItem());
				String newInformation = LoginFrame.userName +","+password+","+phone+","+birthYear+"-"+birthMonth+"-"+birthDay+"\r\n";
				
			    try {
					new FileOperator().FileUpdate(newInformation);
					 JOptionPane.showMessageDialog(frame,"修改成功");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==cmbBirthYear||e.getSource()==cmbBirthMonth)
		{
			int year = (int) cmbBirthYear.getSelectedItem();
			int month = (int) cmbBirthMonth.getSelectedItem();
			switch (month)
			{
			case 1: case 3: case 5: case 7: case 8: case 10 : case 12:
				cmbBirthDay.removeAllItems();
				for (int i=1;i<=31;i++)
					cmbBirthDay.addItem(i); break;
			case 4: case 6: case 9: case 11:
				cmbBirthDay.removeAllItems();
				for (int i=1;i<=30;i++)
					cmbBirthDay.addItem(i);break;
			case 2:
				cmbBirthDay.removeAllItems();
				int days = 28;
				if (year%4==0&&year%100!=0||year%400==0)
				{
					days++;
				}
				for (int i=1;i<=days;i++)
					cmbBirthDay.addItem(i);
			}
		}
	}
}