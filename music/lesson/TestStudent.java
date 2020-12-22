package classtest;

public class TestStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		double grade[] = {90,90,90,90,90};
		
		Student s1 = new Student();
//		s1.display();
//		Student s2 = new Student("张三");
//		s2.display();
//		Student s3 = new Student("李四","男","121334",grade);
//		s3.display();
		Book book1 = new Book("JAVA编程思想","0001",58.9);
		Book book2 = new Book("高数","0002",89);
		s1.borrowBook(book2);
		s1.borrowBook(book1);
		s1.listDisplay();
	}

}
