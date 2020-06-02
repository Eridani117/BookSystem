package org.cuit.BookSystem.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.cuit.BookSystem.Util.DBUtil;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

	public boolean addOrder(Order order) {

		String sql1 = "INSERT INTO orders(id,date,user_id,state,price) VALUES(?,?,?,?,?)";
		Object[] params1 = { order.getId(), order.getDate(), "1", order.isState(), order.getPrice() };

		if (!DBUtil.executeUpdate(sql1, params1))
			return false;
		Set<OrderItem> items = order.getItems();
		for (OrderItem item : items) {
			String sql2 = "INSERT INTO orderItem(id,price,quantity,order_id,book_id) VALUES(?,?,?,?,?)";
			Object[] params2 = { item.getId(), item.getPrice(), item.getQuantity(), item.getOrder_id(),
					item.getBook_id() };
			if (!DBUtil.executeUpdate(sql2, params2))
				return false;
		}
		return true;

	}

	public Order findOrder(String id) {

		Order order = null;
		ResultSet rs = null;
		OrderItem orderItem = null;
		List<OrderItem> list = new ArrayList<>();
		try {
			// 找出订单的基本信息
			String sql = "SELECT * FROM orders WHERE id=?";
			Object[] params = { id };
			rs = DBUtil.executeQuery(sql, params);

			if (rs.next()) {
				String loc_id = rs.getString("id");
				Date ordertime = rs.getDate("date");
				double price = rs.getDouble("price");
				boolean state = rs.getBoolean("state");
				String user_id = rs.getString("user_id");
				order = new Order(loc_id, ordertime, price, state, user_id);
			}
			rs.close();
			// 找出订单的所有订单项
			String sql2 = "SELECT * FROM orderItem WHERE order_id=?";
			Object[] params2 = { order.getId() };
			rs = DBUtil.executeQuery(sql2, params2);
		
			if (rs.next()) {
				String loc_id = rs.getString("id");
				String order_id = rs.getString("order_id");
				String book_id = rs.getString("book_id");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				orderItem = new OrderItem(loc_id, order_id, book_id, price, quantity);
				list.add(orderItem);
			}

			System.out.println("这是数据库拿到的list集合：" + list.size());

			// 将所有订单项装到订单里边
			order.getItems().addAll(list);
			System.out.println("这是数据库拿到的" + order.getItems().size());
			rs.close();
			// 找出该订单是属于哪一个用户的
//			String sql3 = "SELECT * FROM orders o,user u WHERE o.user_id=u.id AND o.id=? ";
//			Object[] params3 = { order.getId() };
//			rs = DBUtil.executeQuery(sql3, params3);
//			if (rs.next()) {
//				String loc_id = rs.getString("id");
//				String loc_username = rs.getString("username");
//				String loc_password = rs.getString("password");
//				String email = rs.getString("email");
//				String cellphone = rs.getString("cellphone");
//				String address = rs.getString("address");
//				user = new User(loc_id, loc_username, loc_password, email, cellphone, address);
//			}
//			order.setUser_id(user.getId());
			return order;
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

	// 更新订单的状态
	public boolean updateState(String id) {
		String sql = "UPDATE orders SET state=? WHERE id=?";
		Object[] params = { 1, id };
		return DBUtil.executeUpdate(sql, params);
	}

	// 查看已经发货或没发货的订单信息
	public List<Order> getAllOrder(boolean state) {

		ResultSet rs = null;
		Order order = null;
		List<Order> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM orders WHERE state=? ";
			Object[] params = { state };
			rs = DBUtil.executeQuery(sql, params);
			if (rs.next()) {
				String loc_id = rs.getString("id");
				Date ordertime = rs.getDate("date");
				double price = rs.getDouble("price");
				boolean loc_state = rs.getBoolean("state");
				String user_id = rs.getString("user_id");
				order = new Order(loc_id, ordertime, price, loc_state, user_id);
				list.add(order);
			}
			return list;
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

	// 通过用户的id查找用户的订单，可能不止一个
	public List<Order> findUserOrder(String user_id) {
		ResultSet rs = null;
		Order order = null;
		List<Order> list = new ArrayList<>();

		try {
			String sql = "SELECT * FROM orders WHERE user_id=? ";
			Object[] params = { user_id };
			rs = DBUtil.executeQuery(sql, params);
			if (rs.next()) {
				String id = rs.getString("id");
				Date ordertime = rs.getDate("date");
				double price = rs.getDouble("price");
				boolean state = rs.getBoolean("state");
				String loac_user_id = rs.getString("user_id");
				order = new Order(id, ordertime, price, state, loac_user_id);
				list.add(order);
			}
			return list;
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
