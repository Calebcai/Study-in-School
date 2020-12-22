package student.manager.frame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileOperator {
	public boolean exists (String name)
	{
		String information[] = FileRead ();
		int j;
		for (j=0;j<information.length;j++)
		{
			String detail[] = information[j].split(",");
			if (name.equals(detail[0]))
				break;
		}
		if (j==information.length)
			return false;
		else
			return true;
	}
	public String[] FileRead ()
	{
		InputStream in=null;
		try {
			in = new FileInputStream ("information.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(in));
		//str从文件中循环一行一行读内容，strnew把文件的内容连接成一个字符串，用换行符将每行隔开
		String str = null;
		String strnew="" ;
		try {
			while ((str = bufferedReader.readLine())!=null)
			{
				strnew += str;
				strnew +="\r\n";		
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//将字符串用换行分开成一个字符串数组
		String info [] = strnew.split("\n");
		try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return info;
	}
	
	public void FileUpdate (String newInformation) throws IOException
	{
		String oldInfor [] = new FileOperator().FileRead();
		int updatePosition = 0;
		
		for (int i=0;i<oldInfor.length;i++)
		{
			int index = oldInfor[i].indexOf(',');//记录下逗号的位置
			if (LoginFrame.userName.equals(oldInfor[i].substring(0,index)))
			{
				updatePosition = i;
			}
		}
		oldInfor[updatePosition]= newInformation;
		newInformation = "";
		for (int i=0;i<oldInfor.length;i++)
			newInformation +=oldInfor[i];
		OutputStream out = new FileOutputStream("information.txt");
		out.write(newInformation.getBytes());
		
		out.close();
	
	}
	public void FileWrite(String newInformation) throws IOException
	{
		String oldInformation [] = FileRead();
		String oldString="";
		for (int i=0;i<oldInformation.length;i++)
		{
			oldString += oldInformation[i];
		}
		
		oldString += newInformation;
		OutputStream out = new FileOutputStream("information.txt");	
		out.write(oldString.getBytes());
		out.close();
		
	}
}
