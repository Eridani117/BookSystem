package BookSystem.service;

import java.util.List;

import BookSystem.entity.Book;

public interface IBookService {
	
	public Book queryBookBySno(int sno);

	// 查询全部学生
	public List<Book> queryAllBooks();

	public List<Book> queryBooksByPage(int currentPage, int pageSize);

	public int getTotalCount();

	public boolean updateBookBybookNo(int bookNo, Book book);

	public boolean deleteBookBybookNo(int bookNo);

	public boolean addBook(Book book);

}
