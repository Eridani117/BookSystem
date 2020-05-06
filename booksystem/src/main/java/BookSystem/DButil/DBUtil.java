package BookSystem.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//通用的数据操作方法
public class DBUtil {

	private static String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/factory?serverTimezone=UTC";
	private static final String USERNAME = "root"; // 数据库用户
	private static final String PASSWORD = "password"; // 用户root的连接数据库的密码

	public static PreparedStatement pstmt = null;
	public static Connection connection = null;
	public static ResultSet rs = null;

	// 通用的：当前页的数据集合 ,因此当前的数据 是强烈依赖于实体类，例如 显示当前页的学生， List<Student>
	// 因此需要将此方法 写入到dao层

//	public static List<Student> 

	// 查询总数
	public static int getTotalCount(String sql) { // select count(1) from student
		int count = -1;
		try {
			pstmt = createPreParedStatement(sql, null);
			rs = pstmt.executeQuery();// 
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt);
		}
		return count;
	}

	// 通用的增删改
	public static boolean executeUpdate(String sql, Object[] params) {// {"zs",1}
		try {
			// Object[] obs = { name,age ,...,x} ;
//			  String sql = "delete from xxx where Name = ? or id = ?  " ;
//			  pstmt.setInt(1,sno );
			// setXxx()方法的个数 依赖于 ?的个数， 而?的个数 又和 数组params的个数一致
			// setXxx()方法的个数 ->数组params的个数一致
			pstmt = createPreParedStatement(sql, params);
			int count = pstmt.executeUpdate();
			if (count > 0)
				return true;
			else
				return false;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeAll(null, pstmt);
		}
	}

	// 通用的查 :通用 表示 适合与 任何查询
	public static ResultSet executeQuery(String sql, Object[] params) {// select xxx from xx where name=? or id=?

		try {

			// String sql = "select * from student" ;//select enmae ,job from xxxx
			// where...id>3

			pstmt = createPreParedStatement(sql, params);
			rs = pstmt.executeQuery();
			return rs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Statement
	public static void closeAll(ResultSet rs, Statement stmt) {

		try {
			connection = DBUtil.getConnection();
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVERCLASS);
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static PreparedStatement createPreParedStatement(String sql, Object[] params)
			throws ClassNotFoundException, SQLException {
		pstmt = getConnection().prepareStatement(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		return pstmt;
	}

}
