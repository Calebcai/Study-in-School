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

	// �������
	JLabel labUserName = new JLabel("�û���");
	JLabel labPassword = new JLabel ("����");
	JLabel labPhone = new JLabel ("�绰");
	JLabel labBirthday = new JLabel("��������");
	
	JTextField tfUserName = new JTextField (10);
	JPasswordField pfPassword = new JPasswordField(10);
	JTextField tfPhone = new JTextField (10);
	//���������������б��ʾ���û����б�ѡ�����ڣ������Ǵ��ı������룬���Ա��������������
	JComboBox<Integer> cmbBirthYear = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthMonth = new JComboBox<Integer>();
	JComboBox<Integer> cmbBirthDay = new JComboBox<Integer>();
	
	JButton btnOk = new JButton ("ȷ��");
	JButton btnReturn = new JButton ("����");
	
	JPanel panel1 = new JPanel ();
	JPanel panel2 = new JPanel ();
	JPanel panel3 = new JPanel ();
	JPanel panel4 = new JPanel ();
	
	
	JFrame frame = new JFrame ("ע�����");
	Container cp = frame.getContentPane();
    // ���캯��
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
			boolean userNameFlag = false;//ע��ʱ��Ҫ�����û����Ƿ��ظ����Լ��绰�����Ƿ��ʽ��ȷ
			boolean phoneFlag = false;//���Լ�������ʶ������ʾ��������Ϣ�Ƿ���ȷ
			String userName = tfUserName.getText();//���ı�������ȡ������û���
			if (new FileOperator().exists(userName)){ //���ļ��в����Ƿ���ڸ��û���
				JOptionPane.showConfirmDialog(frame, "�û����Ѵ���");//����Ѵ�������ʾ���û��Ѵ���
				tfUserName.setText("");//ͬʱ���û����ı�����Ϊ��
			}
			else
			{
				userNameFlag = true;
			}
			String password = String.valueOf(pfPassword.getPassword()); 
			//�绰������������ʽ�ж�
			String phone = tfPhone.getText();
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0[1-2][0-3]-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            phoneFlag = matcher.matches();
            
            if(!phoneFlag)
            	JOptionPane.showConfirmDialog(frame, "�绰�������","����������绰����",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);

			String birthyear = String.valueOf(cmbBirthYear.getSelectedItem());
			String birthmonth = String.valueOf(cmbBirthMonth.getSelectedItem());
			String birthday = String.valueOf(cmbBirthDay.getSelectedItem());
		
			//���û������ظ����ҵ绰������ȷʱ�����û������Ϣд���ļ�
			if (userNameFlag&&phoneFlag){
				String newInformation = userName+","+password+","+phone+","+birthyear+"-"+birthmonth+"-"+birthday+"\r\n";
				try {
					new FileOperator().FileWrite(newInformation);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//JOptionPane.showConfirmDialog(frame, "ע��ɹ�", "�ɹ�", JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
				JOptionPane.showMessageDialog(frame, "ע��ɹ�");
			}
			else
			{
				JOptionPane.showConfirmDialog(frame, "������������Ϣ","��Ϣ����",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
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

