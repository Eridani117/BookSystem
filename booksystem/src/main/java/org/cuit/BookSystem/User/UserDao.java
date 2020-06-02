package org.cuit.BookSystem.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.cuit.BookSystem.Util.DBUtil;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {
	public boolean register(User user) {

        String sql = "INSERT INTO user (id,username,cellphone,address,email,password) VALUES(?,?,?,?,?,?)";
        Object[] params = {  user.getId(),user.getUsername(), user.getCellphone(), user.getAddress(), user.getEmail(), user.getPassword() };
        return DBUtil.executeUpdate(sql, params);
	}

    public User login(String username, String password) {
    	
	
    	User user = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM user WHERE username = ? AND password=?";
	    	Object[] params = {username, password};
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				String id = rs.getString("id");
			     String loc_username=rs.getString("username");
			     String loc_password=rs.getString("password");
			     String email=rs.getString("email");
			     String cellphone=rs.getString("cellphone");
			     String address=rs.getString("address");
			     user = new User(id, loc_username, loc_password,email,cellphone,address);
			}
			return user;
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

    public User findUser(String id) {
    	User user = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM user WHERE id=?";
	    	Object[] params = {id};
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				 String loc_id=rs.getString("id");
			     String username=rs.getString("username");
			     String password=rs.getString("password");
			     String email=rs.getString("email");
			     String cellphone=rs.getString("cellphone");
			     String address=rs.getString("address");
			     user = new User(loc_id, username, password,email,cellphone,address);
			}
			return user;
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
