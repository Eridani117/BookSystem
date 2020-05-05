package BookSystem.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Book {
	
	private int bookNo;
	private String bookName;
	
	public Book() {
		super();
	}
	public Book(int bookNo, String bookName) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
