package BookSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import BookSystem.BookDao.IBookDao;
import BookSystem.entity.Book;
import BookSystem.service.IBookService;

public class BookServiceImpl implements IBookService {

	@Autowired
	IBookDao bookDao;

	@Override
	public Book queryBookBySno(int sno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryBooksByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateBookBybookNo(int bookNo, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBookBybookNo(int bookNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
