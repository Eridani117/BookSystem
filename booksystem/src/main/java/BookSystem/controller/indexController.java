package BookSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BookSystem.entity.Book;
import BookSystem.service.IBookService;
import BookSystem.service.impl.BookServiceImpl;

@Controller
public class indexController {

	@Autowired
	IBookService BookServiceImpl;
	
	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> map) {
//		List<Book> books = new ArrayList<>();
//		Book book = new Book(1, "aaa");
//		books.add(book);
//		book = new Book(2, "bbb");
//		books.add(book);
//		book = new Book(3, "ccc");
//		books.add(book);
		
		List<Book> books = BookServiceImpl.queryAllBooks();
		map.put("books", books);

		return "index";
	}
}
