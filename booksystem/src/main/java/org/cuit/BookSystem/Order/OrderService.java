package org.cuit.BookSystem.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cuit.BookSystem.Book.Book;
import org.cuit.BookSystem.Cart.Cart;
import org.cuit.BookSystem.Cart.CartItem;
import org.cuit.BookSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	public void createOrder(Cart cart, User user) {
		// 用户信息从数据库中获取
		
		// 订单的基本信息
		String order_id = UUID.randomUUID().toString();
		Order order = new Order();
		order.setId(order_id);
		order.setPrice(cart.getPrice());
		order.setDate(new Date());
		order.setState(false);
		order.setUser_id(user.getId());

		// 订单项的基本信息
		// 得到每个购物项，购物项就作为订单项
		for (Map.Entry<String, CartItem> me : cart.getMap().entrySet()) {

			OrderItem orderItem = new OrderItem();
			CartItem cartItem = me.getValue();

			orderItem.setId(UUID.randomUUID().toString());
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setBook_id(cartItem.getBook().getId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrder_id(order_id);
			order.getItems().add(orderItem);
		}

		orderDao.addOrder(order);

	}

	public Order findOrder(String order_id) {
		return orderDao.findOrder(order_id);
	}

	public List<Order> getAllOrder(boolean state) {
		return orderDao.getAllOrder(state);
	}

	public void buyBook(Cart cart, Book book) {
		cart.addBook2Cart(book);
	}

	public void sendOutOrder(String id) {
		orderDao.updateState(id);
	}

	public List<Order> findUserOrder(String user_id) {
		return orderDao.findUserOrder(user_id);
	}
}
