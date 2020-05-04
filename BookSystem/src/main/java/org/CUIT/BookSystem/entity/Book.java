package org.CUIT.BookSystem.entity;

import org.springframework.stereotype.Repository;

@Repository
public class Book {
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
	private int bookNo;
	private String bookName;
}
