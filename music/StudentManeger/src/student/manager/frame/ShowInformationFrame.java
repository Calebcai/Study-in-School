package student.manager.frame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShowInformationFrame {
	JFrame frame = new JFrame ("全部用户信息");
	String title[] = {"用户名","密码","电话号码","出生日期"};
	JTable table;
	JScrollPane scrollPane;
	public static void main (String args[])
	{
		new ShowInformationFrame();
	}
	ShowInformationFrame()
	{
		String information[] = new FileOperator().FileRead();
		int len = information.length;
		String infor[][] = new String[len][4];
		for (int i=0;i<len;i++)
		{
				infor[i] = information[i].split(",");
		}
	
		
		table = new JTable(infor,title);
		
		table.setBorder(BorderFactory.createLineBorder(Color.red));
		scrollPane = new JScrollPane(table);
		Container cp =  frame.getContentPane();
		
		table.setVisible(true);
		cp.add(scrollPane);
		frame.pack();
		frame.setLocation(300,400);
		frame.setVisible(true);
	}
}
