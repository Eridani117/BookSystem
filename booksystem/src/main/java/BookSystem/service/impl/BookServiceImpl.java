package BookSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BookSystem.BookDao.IBookDao;
import BookSystem.entity.Book;
import BookSystem.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	IBookDao bookDao;

	@Override
	public Book queryBookBybookNo(int bookNo) {
		
		return bookDao.queryBookBybookNo(bookNo);
	}

	@Override
	public List<Book> queryAllBooks() {
		return bookDao.queryAllBooks();
	}

	@Override
	public List<Book> queryBooksByPage(int currentPage, int pageSize) {
		return bookDao.queryAllBooksByPage(currentPage, pageSize);
	}

	@Override
	public int getTotalCount() {
		return bookDao.getTotalCount();
	}

	@Override
	public boolean updateBookBybookNo(int bookNo, Book book) {
		if(!bookDao.isExist(bookNo)) {
			bookDao.updateStudentBySno(bookNo, book);
		}
		return false;
	}

	@Override
	public boolean deleteBookBybookNo(int bookNo) {
		if(!bookDao.isExist(bookNo)) {
			bookDao.deleteBookBybookNo(bookNo);
		}
		return false;
	}

	@Override
	public boolean addBook(Book book) {
		if (!bookDao.isExist(book.getBookNo())) {
			bookDao.addStudent(book);
			return true;
		} else {
			System.out.println("增加的书已存在");
			return false;
		}
	}

}
