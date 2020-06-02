package org.cuit.BookSystem.Order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String id;

	// 下单的时间、日期、状态
	private Date date;
	private double price;
	private boolean state;

	// 一个用户可以有多个订单，把用户记住
	private String user_id;

	// 一个订单中有多个订单项
	private Set<OrderItem> items = new HashSet<>();

	public Order(String id, Date ordertime, double price, boolean state, String user_id) {
		super();
		this.id = id;
		this.date = ordertime;
		this.price = price;
		this.state = state;
		this.user_id = user_id;
	}

}
