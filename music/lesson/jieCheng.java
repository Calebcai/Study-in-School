package lesson;

public class jieCheng {
	public static int testHomeWork1(int n) {
		int a = 1;
		for(int i=1;i<=n;i++) {
			a = a*i;
		}
		return a;
	}
	public static void main(String[] args) {
		System.out.println("½×³ËÊÇ"+testHomeWork1(4));
	}

}
