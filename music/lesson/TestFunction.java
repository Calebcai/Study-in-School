package lesson;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFunction {

	public static void main(String[] args) {
		PassWord pwd = new PassWord();
		pwd.passwordIsValid("sda2d1s2");
	}
	//函数定义
	static void    plus(int a,int b)
	{
	//函数体，把要定义成函数的代码括起来
		int sum = a+b;
	    System.out.println("两个数的和是"+sum);

	}
	static boolean isPrime(int x)
	{
		int i;
		for (i=2;i<x;i++)
		{
			if(x%i==0)
				break;
		}
		if(i==x)
			return true;
		else
			return false;
	}
	
	static int gysMax(int m,int n)
	{
		int r=-1;
		if(m<n)
		{
			m=m+n;
			n=m-n;
			m=m-n;
		}
		while(r!=0)
		{
			r = m%n;
			m = n;
			n = r;
		}
		return m;
	}
	
	boolean telephoneIsValid(String telephone)
	{
		Pattern pattern = 
		Pattern.compile("^(13[0-9])|(15)[0,5-9]|(18[0-3,5-9])\\d{8}$");
		Matcher matcher = pattern.matcher(telephone);
		return matcher.matches();
	}
	boolean numberIsValid(String number)
	{
		Pattern pattern=
		Pattern.compile("^(17)\\d{10}$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	boolean passwordIsValid(String password)
	{
		int length=password.length();
		if (length>8)//长度对
		{
			int i;
			boolean validFlag = true;
			for (i=0;i<length;i++)
			{
				char ch = password.charAt(i);
				if ((ch<'a'||ch>'z')&&(ch>'Z'||ch<'A')&&(ch>'9'||ch<'0')&&(ch!='_'))
				{
					validFlag = false;
					break;
				}
			}
			if(validFlag)//包含字符合法
			{
				int a=0,b=0,c=0;//a,b,c表示是否包含三类字符
				for (i=0;i<length;i++)
				{
					char ch=password.charAt(i);
					if(ch=='_')
						a=1;
					else if(ch>='0'&&ch<='9')
						b=1;
					else
						c=1;
				}
				if((a+b+c)>=2)//包含字符种类正确
				{
					return true;
				}
				else//包含字符种类错误
				{
					System.out.println("字符种类不够");
					return false;
				}
			}
			else //包含字符不合法
			{
				System.out.println("包含非法字符");
				return false;
			}
		}
		else//长度错
		{
			System.out.println("密码长度错误");
			return false;
		}
	}
}
