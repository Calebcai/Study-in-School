package classtest;

public class Book {
	//图书名，编号，价格
	String name,bookId;
	double price;
	
	//构造函数
	Book()
	{
	}
	Book (String name,String bookId,double price)
	{
		this.name = name;
		this.bookId = bookId;
		this.price = price;
	}
	
	void display () 
	{
		System.out.println("图书名称："+name);
		System.out.println("图书编号："+bookId);
	}
}
