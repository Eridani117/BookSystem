package org.cuit.BookSystem.Order;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	private String id;

	// 一个订单可以对应多个订单，记住订单
	private String order_id;

	// 一本书对应多个订单项，订单项一定是由书组成，记住书
	private String book_id;

	private double price;
	private int quantity;

}
