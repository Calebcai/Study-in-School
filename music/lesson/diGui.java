package lesson;

public class diGui {
	public static int jc(int n) {
		if (n==1) {
			return n;
		}
		else {
			return n*jc(n-1);
		}
	}
	public static void main(String args[]){
		System.out.println("n�Ľ׳���"+jc(4));
	}
}
//�ݹ�����Լ�
