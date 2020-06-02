package org.cuit.BookSystem.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cuit.BookSystem.Util.DBUtil;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {

	public boolean addCategory(Category category) {
		String sql = "INSERT INTO category (id, name, description) VALUES(?,?,?)";
		Object[] params = { category.getId(), category.getName(), category.getDescription() };
		return DBUtil.executeUpdate(sql, params);
	}

	public boolean deleteCategoryById(String id) {
		String sql = "delete from category where id = ?";
		Object[] params = { id };
		return DBUtil.executeUpdate(sql, params);
	}
	public Category findCategoryById(String id) {
		Category category = null;
		ResultSet rs = null;
		try {
			String sql = "select * from category where id =? ";
			Object[] params = { id };
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				String loc_id = rs.getString("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				category = new Category(loc_id, name, description);
			}
			return category;
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

	public List<Category> getAllCategorys() {
		Category category = null;
		List<Category> categories = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM category";
			rs = DBUtil.executeQuery(sql, null);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				category = new Category(id, name, description);
				categories.add(category);
			}
			return categories;
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
