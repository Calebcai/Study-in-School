package student.manager.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame implements ActionListener,ItemListener{

	// 声明组件
	JLabel labUserName = new JLabel("用户名");
	JLabel labPassword = new JLabel ("密码");
	JLabel labPhone = new JLabel ("电话");
	JLabel labBirthday = new JLabel("出生日期");
	
	JTextField tfUserName = new JTextField (10);
	JPasswordField pfPassword = new JPasswordField(10);
	JTextField tfPhone = new JTextField (10);
	//出生日期用下拉列表表示，用户从列表选择日期，而不是从文本框输入，可以避免输入错误日期
	JComboBox<Integer> cmbBirthYear = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthMonth = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthDay = new JComboBox<Integer>();
	
	JButton btnOk = new JButton ("确定");
	JButton btnReturn = new JButton ("返回");
	
	JPanel panel1 = new JPanel ();
	JPanel panel2 = new JPanel ();
	JPanel panel3 = new JPanel ();
	JPanel panel4 = new JPanel ();
	
	
	JFrame frame = new JFrame ("注册界面");
	Container cp = frame.getContentPane();
    // 构造函数
	RegisterFrame ()
	{
		panel1.setLayout(new GridLayout(3,2,10,10));
		panel1.add(labUserName);
		panel1.add(tfUserName);
		panel1.add(labPassword);
		panel1.add(pfPassword);
		panel1.add(labPhone);
		panel1.add(tfPhone);
		
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
		panel3.add(btnOk);
		panel3.add(btnReturn);
		
		panel4.add(panel1,BorderLayout.CENTER);
		panel4.add(panel2,BorderLayout.SOUTH);
		
		cp.add(panel4,BorderLayout.CENTER);
		cp.add(panel3,BorderLayout.SOUTH);
		
		btnOk.addActionListener(this);
		btnReturn.addActionListener(this);
		frame.setSize(400,200);
		frame.setLocation(300, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btnOk)
		{
			boolean userNameFlag = false;//注册时需要考虑用户名是否重复，以及电话号码是否格式正确
			boolean phoneFlag = false;//所以加两个标识符来表示这两个信息是否正确
			String userName = tfUserName.getText();//从文本框中提取输入的用户名
			if (new FileOperator().exists(userName)){ //到文件中查找是否存在该用户名
				JOptionPane.showConfirmDialog(frame, "用户名已存在");//如果已存在则提示，用户已存在
				tfUserName.setText("");//同时将用户名文本框置为空
			}
			else
			{
				userNameFlag = true;
			}
			String password = String.valueOf(pfPassword.getPassword()); 
			//电话号码用正则表达式判断
			String phone = tfPhone.getText();
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0[1-2][0-3]-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            phoneFlag = matcher.matches();
            
            if(!phoneFlag)
            	JOptionPane.showConfirmDialog(frame, "电话号码错误","请重新输入电话号码",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);

			String birthyear = String.valueOf(cmbBirthYear.getSelectedItem());
			String birthmonth = String.valueOf(cmbBirthMonth.getSelectedItem());
			String birthday = String.valueOf(cmbBirthDay.getSelectedItem());
		
			//当用户名不重复而且电话号码正确时，将用户这册信息写入文件
			if (userNameFlag&&phoneFlag){
				String newInformation = userName+","+password+","+phone+","+birthyear+"-"+birthmonth+"-"+birthday+"\r\n";
				try {
					new FileOperator().FileWrite(newInformation);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//JOptionPane.showConfirmDialog(frame, "注册成功", "成功", JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
				JOptionPane.showMessageDialog(frame, "注册成功");
			}
			else
			{
				JOptionPane.showConfirmDialog(frame, "请重新输入信息","信息错误",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource()==btnReturn)
		{
			new LoginFrame ();
			frame.dispose();
		}
	}

	public void itemStateChanged(ItemEvent e) {
	
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

