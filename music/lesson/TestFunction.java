package lesson;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFunction {

	public static void main(String[] args) {
		PassWord pwd = new PassWord();
		pwd.passwordIsValid("sda2d1s2");
	}
	//��������
	static void    plus(int a,int b)
	{
	//�����壬��Ҫ����ɺ����Ĵ���������
		int sum = a+b;
	    System.out.println("�������ĺ���"+sum);

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
		if (length>8)//���ȶ�
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
			if(validFlag)//�����ַ��Ϸ�
			{
				int a=0,b=0,c=0;//a,b,c��ʾ�Ƿ���������ַ�
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
				if((a+b+c)>=2)//�����ַ�������ȷ
				{
					return true;
				}
				else//�����ַ��������
				{
					System.out.println("�ַ����಻��");
					return false;
				}
			}
			else //�����ַ����Ϸ�
			{
				System.out.println("�����Ƿ��ַ�");
				return false;
			}
		}
		else//���ȴ�
		{
			System.out.println("���볤�ȴ���");
			return false;
		}
	}
}
