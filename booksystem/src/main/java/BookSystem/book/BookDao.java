package BookSystem.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import BookSystem.DButil.DBUtil;

@Repository
public class BookDao {

	public boolean addBook(Book book) {
		String sql = "insert into book(bookNo,bookName) values(?,?) ";
		Object[] params = { book.getBookNo(), book.getBookName() };
		return DBUtil.executeUpdate(sql, params);
	}

	public boolean updateBookBybookNo(int bookNo, Book book) {
		String sql = "update book set bookName=? WHERE bookNo = ?";
		
		Object[] params = {  book.getBookName(),bookNo };
		return DBUtil.executeUpdate(sql, params);
	}

	public int getTotalCount() {
		String sql = "select count(*) from book";
		return DBUtil.getTotalCount(sql);
	}

	public boolean deleteBookBybookNo(int bookNo) {
		String sql = "delete from book where bookNo = ?";
		Object[] params = { bookNo };
		return DBUtil.executeUpdate(sql, params);
	}

	public List<Book> queryAllBooks() {
		Book book = null;
		List<Book> books = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "select * from book";
			rs = DBUtil.executeQuery(sql, null);
			while (rs.next()) {
				int bookNo = rs.getInt("bookNo");
				String bookName = rs.getString("bookName");
				book = new Book(bookNo, bookName);
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt);
		}
	}

	public List<Book> queryAllBooksByPage(int currentPage, int pageSize) {
		String sql = "select *from " + "(" + "select rownum r, t.* from"
				+ "(select s.* from book s order by bookNo asc) t "

				+ "where rownum<=?" + ")" + "where r>=?";
		Object[] params = { currentPage * pageSize, (currentPage - 1) * pageSize + 1 };

		List<Book> books = new ArrayList<>();

		ResultSet rs = DBUtil.executeQuery(sql, params);

		try {
			while (rs.next()) {
				Book book = new Book(rs.getInt("bookNo"), rs.getString("bookName"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean isExist(int bookNo) {
		boolean flag = queryBookBybookNo(bookNo) == null ? false : true;
		System.out.println(flag);
		return flag;
	}

	public Book queryBookBybookNo(int bookNo) {
		Book book = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book where bookNo =? ";
			Object[] params = { bookNo };
			rs = DBUtil.executeQuery(sql, params);
			
			
			if (rs.next()) {
				bookNo = rs.getInt("bookNo");
				String bookName = rs.getString("bookName");
				book = new Book(bookNo, bookName);
			}
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt);
		}
	}

}
