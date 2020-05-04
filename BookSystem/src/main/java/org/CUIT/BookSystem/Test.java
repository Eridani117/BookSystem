package org.CUIT.BookSystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DBUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = DBUtil.executeQuery("select * from book", null);
		try {
			while (rs.next()) {
				System.out.println(rs.getInt("bookNo") + " " + rs.getString("bookName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
