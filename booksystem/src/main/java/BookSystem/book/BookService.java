package BookSystem.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;

	public Book queryBookBybookNo(int bookNo) {

		return bookDao.queryBookBybookNo(bookNo);
	}

	public List<Book> queryAllBooks() {
		return bookDao.queryAllBooks();
	}

	public List<Book> queryBooksByPage(int currentPage, int pageSize) {
		return bookDao.queryAllBooksByPage(currentPage, pageSize);
	}

	public int getTotalCount() {
		return bookDao.getTotalCount();
	}

	public boolean updateBookBybookNo(int bookNo, Book book) {
		if (bookDao.isExist(bookNo)) {
			bookDao.updateBookBybookNo(bookNo, book);
			return true;
		}
		return false;
	}

	public boolean deleteBookBybookNo(int bookNo) {
		if (bookDao.isExist(bookNo)) {
			bookDao.deleteBookBybookNo(bookNo);
			return true;
		}
		return false;
	}

	public boolean addBook(Book book) {
		if (!bookDao.isExist(book.getBookNo())) {
			bookDao.addBook(book);
			return true;
		} else {
			System.out.println("增加的书已存在");
			return false;
		}
	}

}
