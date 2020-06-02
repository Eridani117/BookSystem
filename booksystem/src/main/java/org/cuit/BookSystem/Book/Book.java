package org.cuit.BookSystem.Book;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	private String id;
	private String name;
	private String author;
	private String description;
	private double price;

	// 记住图片的名称
	private String image;

	// 记住分类的id
	private String category_id;

}
