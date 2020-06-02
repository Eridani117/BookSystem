package org.cuit.BookSystem.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	/* 添加图书 */
	public boolean addBook(Book book) {
		if(!bookDao.isExist(book.getId())) {
			return bookDao.addBook(book);
		}
		return false;
	}

	/* 查找图书 */
	public Book findBook(String id) {
		return bookDao.findBookById(id);
	}

	/* 获取图书的分页数据 */
	public Page getPageData(String pageNum) {
		Page page = null;
		if (pageNum == null) {
			page = new Page(1, bookDao.getTotalCount());
		} else {
			page = new Page(Integer.valueOf(pageNum), bookDao.getTotalCount());
		}

		List<Book> books = bookDao.getPageData(page.getStartIndex(), page.getLinesize());
		page.setList(books);

		return page;

	}

	/* 获取图书分类后的分页数据 */
	public Page getPageDataBycategory_id(String currentPageCount, String category_id) {
		Page page = null;
		if (currentPageCount == null) {
			page = new Page(1, bookDao.getCategoryTotalCount(category_id));
		} else {
			page = new Page(Integer.valueOf(currentPageCount), bookDao.getCategoryTotalCount(category_id));
		}

		List<Book> books = bookDao.getPageDataByCategory(page.getStartIndex(), page.getLinesize(), category_id);
		page.setList(books);
		return page;

	}
	
	public boolean deleteBookBybookNo(String id) {
		if(!bookDao.isExist(id)) {
			return false;
		}else {
			return bookDao.deleteBookByBookId(id);
		}
	}
}
