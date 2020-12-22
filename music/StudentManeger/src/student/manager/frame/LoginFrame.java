package student.manager.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;

class MyPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Image image = Toolkit.getDefaultToolkit().getImage("back.png");
	public void paintComponent (Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}	
}
public class LoginFrame implements ActionListener{
	
	MyPanel panel = new MyPanel();
	//��̬���ݱ����¼ʱ���û���
	static String userName=null;
	//��������塢����������
	JLabel labUserName = new JLabel("�û���",JLabel.CENTER);
	JLabel labPassword = new JLabel ("����",JLabel.CENTER);
	
	JTextField tfUserName = new JTextField ("�������û���",10);
	JPasswordField pfPassword = new JPasswordField(10);
	
	JButton btnLogin = new JButton ("��¼");
	JButton btnRegister = new JButton ("ע��");
	
	JFrame frame = new JFrame ("��¼����");
	
	Container cp = frame.getContentPane();
	//��ʼ������
	
	LoginFrame ()
	{
		//�����������񲼾�
		//panel Ϊ���б���ͼƬ�����
		//����panel�Ĳ��ַ�ʽ
		panel.setLayout(new GridLayout(3,2,10,10));
		// panel�������Ӹ����
		panel.add(labUserName);
		panel.add(tfUserName);
		panel.add(labPassword);
		panel.add(pfPassword);
		panel.add(btnLogin);
		panel.add(btnRegister);
        // ��panel�����ʾ�����������λ��
		cp.add(panel,BorderLayout.CENTER);
		//����󶨼�����
		// �ı������������
		tfUserName.addMouseListener(new MyMouseListener ());
		pfPassword.addKeyListener(
				new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
//							if(e.getKeyCode()==KeyEvent.VK_ENTER)
//							{
//								JOptionPane.showMessageDialog(null,"��¼");
//							}
					}
				}
				);
		//��¼��ť��ע�ᰴť�󶨶�������
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		//���ô���Ĵ�Сλ��
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 400);
		frame.setSize(400, 250);
		frame.setResizable(false);
		frame.setVisible(true);
	}
    class MyMouseListener extends MouseAdapter 
    {
    	public void mouseClicked (MouseEvent e)
    	{
    		if (tfUserName.getText().equals("�������û���"))
    			tfUserName.setText("");
    	}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnLogin)
		{
			//�����ļ��洢��Ϣ
			//���ļ��ж��û���Ϣ������info�����У�������ÿһ��Ԫ��Ϊһ���û�����Ϣ
			String info[] = new FileOperator().FileRead();//info������ÿһ�д���һ���û�����Ϣ
			String detail[];//detail������ÿ���û����û��������롢�绰���롢��������
			int i=0;// i����ѭ���ж�
			boolean userFlag=false, passFlag=false;//userFlag��passwordFlag�ֱ��ʾ�û����Ƿ��ҵ����Լ��û�����Ӧ�����Ƿ���ȷ
			for (i=0;i<info.length;i++)
			{
			     detail = info[i].split(",");//ͨ�����Ž�������Ϣ�ָ�ŵ������У�
				if (detail[0].equals(tfUserName.getText()))//detail��0�ݱ�ʾ�û����������������û�����ͬ�����޸�userFlag��ֵ�������ж������Ƿ���ȷ��������ȷ���޸�passwordFlag��ֵ�������޸ģ�˵�����벻��ȷ���˳�ѭ��
				{
					userFlag = true;
					if (detail[1].equals(String.valueOf(pfPassword.getPassword())))
					    passFlag = true;
					break;
				}
			}
			if (i==info.length){// ���ѭ������������˵��û���ҵ��Ѿ����ڵ��û���
					JOptionPane.showConfirmDialog(frame, "�ʺŲ�����");
			} 
			else //elseΪ��ǰ����ѭ���������˵���û��Ѵ��ڣ����������Ƿ���ȷ����Ҫ�ж�
			{
				if (!passFlag)
					JOptionPane.showConfirmDialog(frame, "���벻��");
			}
			//�û��������ڻ������벻�ԣ�������ʾ���������������жϣ�������ԣ����򽫲����������	
			String passwordAgain ="";
			boolean passwordAgainFlag = false;
			if (userFlag&&passFlag) //ֻ���û��������붼��ȷʱ���Ż���ʾ������������
			{
				while (!passwordAgainFlag){
					
					passwordAgain = JOptionPane.showInputDialog("���ٴ���������");//����Ի������ٴ���������
					if (passwordAgain.equals(String.valueOf(pfPassword.getPassword())))
					{
						passwordAgainFlag = true;//����������ͬʱ����ʶλ�޸�
					}	
				}//�ٴ��������룬����������������ͬʱѭ���Ż����
				userName = tfUserName.getText();//��ʾ��¼�ɹ�
				JOptionPane.showConfirmDialog(frame, "��¼�ɹ�","��ϲ"+userName,JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
				frame.dispose();//��¼������ʧ
				new OperatorFrame();//�򿪲�������
				
			}
		}
		if(e.getSource()==btnRegister)//���ע�ᰴťʱ���½�һ��ע����棬ͬʱ��¼������ʧ
		{
			new RegisterFrame();
			frame.dispose();
		}
	}

	public static void main (String args[])
	{
		new LoginFrame();//��������дӵ�¼���濪ʼ
	}
}
