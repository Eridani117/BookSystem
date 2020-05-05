package BookSystem.BookDao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import BookSystem.BookDao.IBookDao;
import BookSystem.DButil.DBUtil;
import BookSystem.entity.Book;

@Repository
public class BookDaoImpl implements IBookDao {

	@Override
	public boolean addStudent(Book book) {
		String sql = "insert into student(bookNo,bookName) values(?,?) ";
		Object[] params = { book.getBookNo(), book.getBookName() };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public boolean updateStudentBySno(int bookNo, Book book) {
		String sql = "update student set bookNo =?,bookName=?";
		Object[] params = { book.getBookNo(), book.getBookName() };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
	public int getTotalCount() {
		String sql = "select count(*) from book";
		return DBUtil.getTotalCount(sql);
	}

	@Override
	public boolean deleteBookBybookNo(int bookNo) {
		String sql = "delete from book where bookNo = ?";
		Object[] params = { bookNo };
		return DBUtil.executeUpdate(sql, params);
	}

	@Override
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

	@Override
	public List<Book> queryAllBooksByPage(int currentPage, int pageSize) {
		String sql = "select *from " + "(" + "select rownum r, t.* from"
				+ "(select s.* from student s order by sno asc) t "

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

	@Override
	public boolean isExist(int bookNo) {
		return queryBookBybookNo(bookNo) == null ? false : true;
	}

	@Override
	public Book queryBookBybookNo(int bookNo) {
		Book book = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DBUtil.getConnection();
			String sql = "select * from student where sno =? ";
			pstmt = DBUtil.connection.prepareStatement(sql);
			pstmt.setInt(1, bookNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bookNo = rs.getInt("bookNo");
				String bookName = rs.getString("bookName");

				book = new Book(bookNo, bookName);
			}
			return book;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

}
