package lesson;

import java.util.Scanner;

public class gonYueShu {
	public static int yue(int a,int b) {
		if (a%b==0) {
			return b;
		}
		int c = a%b;
		return yue(b, c);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入第一个数");
		int a = sc.nextInt();
		System.out.println("请输入第二个数");
		int b = sc.nextInt();
		System.out.println("请输入公约数是"+yue(a, b));
	}

}
