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
	//静态数据保存登录时的用户名
	static String userName=null;
	//组件、窗体、容器的声明
	JLabel labUserName = new JLabel("用户名",JLabel.CENTER);
	JLabel labPassword = new JLabel ("密码",JLabel.CENTER);
	
	JTextField tfUserName = new JTextField ("请输入用户名",10);
	JPasswordField pfPassword = new JPasswordField(10);
	
	JButton btnLogin = new JButton ("登录");
	JButton btnRegister = new JButton ("注册");
	
	JFrame frame = new JFrame ("登录界面");
	
	Container cp = frame.getContentPane();
	//初始化界面
	
	LoginFrame ()
	{
		//容器设置网格布局
		//panel 为带有背景图片的面板
		//设置panel的布局方式
		panel.setLayout(new GridLayout(3,2,10,10));
		// panel面板上添加各组件
		panel.add(labUserName);
		panel.add(tfUserName);
		panel.add(labPassword);
		panel.add(pfPassword);
		panel.add(btnLogin);
		panel.add(btnRegister);
        // 将panel面板显示在容器中央的位置
		cp.add(panel,BorderLayout.CENTER);
		//组件绑定监听器
		// 文本框绑定鼠标监听器
		tfUserName.addMouseListener(new MyMouseListener ());
		pfPassword.addKeyListener(
				new KeyAdapter()
				{
					public void keyPressed(KeyEvent e)
					{
//							if(e.getKeyCode()==KeyEvent.VK_ENTER)
//							{
//								JOptionPane.showMessageDialog(null,"登录");
//							}
					}
				}
				);
		//登录按钮和注册按钮绑定动作监听
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		//设置窗体的大小位置
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
    		if (tfUserName.getText().equals("请输入用户名"))
    			tfUserName.setText("");
    	}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnLogin)
		{
			//利用文件存储信息
			//从文件中读用户信息，存在info数组中，数组中每一个元素为一个用户的信息
			String info[] = new FileOperator().FileRead();//info数组中每一行代表一个用户的信息
			String detail[];//detail数组存放每个用户的用户名、密码、电话号码、出生日期
			int i=0;// i变量循环判断
			boolean userFlag=false, passFlag=false;//userFlag、passwordFlag分别表示用户名是否找到，以及用户名对应密码是否正确
			for (i=0;i<info.length;i++)
			{
			     detail = info[i].split(",");//通过逗号将各个信息分割放到数组中，
				if (detail[0].equals(tfUserName.getText()))//detail［0］表示用户名，如果和输入的用户名相同，则修改userFlag的值；接着判断密码是否正确，密码正确则修改passwordFlag的值，否则不修改，说明密码不正确，退出循环
				{
					userFlag = true;
					if (detail[1].equals(String.valueOf(pfPassword.getPassword())))
					    passFlag = true;
					break;
				}
			}
			if (i==info.length){// 如果循环正常结束，说明没有找到已经存在的用户名
					JOptionPane.showConfirmDialog(frame, "帐号不存在");
			} 
			else //else为提前结束循环的情况，说明用户已存在，但是密码是否正确还需要判断
			{
				if (!passFlag)
					JOptionPane.showConfirmDialog(frame, "密码不对");
			}
			//用户名不存在或者密码不对，都会提示，重新输入后继续判断，如果不对，程序将不会继续运行	
			String passwordAgain ="";
			boolean passwordAgainFlag = false;
			if (userFlag&&passFlag) //只有用户名和密码都正确时，才会提示重新输入密码
			{
				while (!passwordAgainFlag){
					
					passwordAgain = JOptionPane.showInputDialog("请再次输入密码");//输入对话框中再次输入密码
					if (passwordAgain.equals(String.valueOf(pfPassword.getPassword())))
					{
						passwordAgainFlag = true;//两次密码相同时，标识位修改
					}	
				}//再次输入密码，当两次输入密码相同时循环才会结束
				userName = tfUserName.getText();//提示登录成功
				JOptionPane.showConfirmDialog(frame, "登录成功","恭喜"+userName,JOptionPane.OK_OPTION,JOptionPane.PLAIN_MESSAGE);
				frame.dispose();//登录界面消失
				new OperatorFrame();//打开操作界面
				
			}
		}
		if(e.getSource()==btnRegister)//点击注册按钮时，新建一个注册界面，同时登录界面消失
		{
			new RegisterFrame();
			frame.dispose();
		}
	}

	public static void main (String args[])
	{
		new LoginFrame();//程序的运行从登录界面开始
	}
}
