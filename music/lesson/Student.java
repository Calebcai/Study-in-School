package classtest;

import java.util.ArrayList;

public class Student {
	//鏁版嵁鎴愬憳
	String name,sex,number;
	double score[]= new double[5];
	//鍊熼槄鐨勫浘涔︿俊鎭�
	ArrayList<Book> bookList = new ArrayList<Book>();
	Student (String name)
	{
		this.name = name;
	}
	Student (String name,String number)
	{
		this.name = name;
		this.number = number;
	}
	Student ()
	{
		
	}
	Student (String name1,String sex1,String number1,double score1[])
	{
		name = name1;
		sex = sex1;
		number = number1;
		score = score1;
	}
	//褰撴妸鍑芥暟瀹氫箟涓烘垚鍛樺嚱鏁版椂锛屽鏋滃弬鏁版槸绫荤殑鏁版嵁鎴愬憳
	//鍙傛暟鍙互鐪佺暐涓嶅啓
	void display()
	{
		System.out.println("瀛︾敓濮撳悕锛�"+name);
		System.out.print("骞冲潎鎴愮哗锛�");
		double sum =0;
		for(int i=0;i<score.length;i++)
			sum += score[i];
		System.out.println(sum/score.length);
	}
	//瀹氫箟涓�涓�熼槄鍥句功鐨勫嚱鏁�
	void borrowBook(Book book)
	{
		//ArrayList闆嗕腑涓坊鍔犳暟鎹敤add(Object)
		bookList.add(book);
	}
	//杈撳嚭鍊熼槄鍥句功淇℃伅鐨勫嚱鏁�
	void listDisplay()
	{
		//濡備綍鑾峰彇list鐨勫ぇ灏�
		Book book;
		int size = bookList.size();
		//濡備綍鑾峰彇list涓殑瀵硅薄 get(int)
		for (int i=0;i<size;i++)
		{
			book = bookList.get(i);
			System.out.println(book.name);
		}
		
	}
	
}
