package org.cuit.BookSystem.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cuit.BookSystem.Util.DBUtil;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

	public boolean addBook(Book book) {
		String sql = "INSERT INTO book (id,name,description,author,price,image,category_id) VALUES(?,?,?,?,?,?,?)";
		Object[] params = { book.getId(), book.getName(), book.getDescription(), book.getAuthor(), book.getPrice(),
				book.getImage(), book.getCategory_id() };
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean isExist(String id) {
		boolean flag = findBookById(id) == null  ? false :true;
		return flag;
	}
	public Book findBookById(String id) {
		Book book = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM book WHERE id=?";
			Object[] params = { id };
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				String loc_id = rs.getString("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String category_id = rs.getString("category_id");
				book = new Book(loc_id, name, author, description, price, image, category_id);
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

	/** 得到图书的分页数据 */
	public List<Book> getPageData(int start, int end) {
		Book book = null;
		List<Book> books = new ArrayList<>();
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book limit ?,?";
			Object[] params = { start, end };
			rs = DBUtil.executeQuery(sql, params);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String category_id = rs.getString("category_id");
				book = new Book(id, name, author, description, price, image, category_id);
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

	/** 得到按照分类图书的分页数据 */
	public List<Book> getPageDataByCategory(int start, int end, String category_id) {
		Book book = null;
		List<Book> books = new ArrayList<>();
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book WHERE category_id=? limit ?,?";
			Object[] params = { category_id, start, end };
			rs = DBUtil.executeQuery(sql, params);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String loc_category_id = rs.getString("category_id");
				book = new Book(id, name, author, description, price, image, loc_category_id);
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

	/**
	 * 得到图书的总记录数
	 */
	public int getTotalCount() {

		String sql = "select count(*) from book";
		return DBUtil.getTotalCount(sql,null);
	}

	
	/**
     * 得到分类后图书的总记录数
     * getCategoryTotalRecord
     */
    public long getCategoryTotalCount(String category_id) {

            String sql = "SELECT COUNT(*) FROM book WHERE category_id=?";
            Object[] params = { category_id };
            return DBUtil.getTotalCount(sql,params);

    }
    
    public boolean deleteBookByBookId(String id) {
    	String sql = "delete from book where id = ?";
		Object[] params = { id };
		return DBUtil.executeUpdate(sql, params);
    }
    public boolean deleteBookBybookNo(int bookNo) {
		String sql = "delete from book where bookNo = ?";
		Object[] params = { bookNo };
		return DBUtil.executeUpdate(sql, params);
	}
}
