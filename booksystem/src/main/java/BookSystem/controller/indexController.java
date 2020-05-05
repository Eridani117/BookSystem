package BookSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import BookSystem.entity.Book;

@Controller
public class indexController {

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> map) {
		List<Book> books = new ArrayList<>();
		Book book = new Book(1, "aaa");
		books.add(book);
		book = new Book(2, "bbb");
		books.add(book);
		book = new Book(3, "ccc");
		books.add(book);

		map.put("books", books);

		return "index";
	}
}
