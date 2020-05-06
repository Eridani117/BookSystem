package BookSystem.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping("/back")
	public String back(ModelMap map) {

		List<Book> books = bookService.queryAllBooks();
		map.addAttribute("books", books);
		return "index";
	}

	@RequestMapping("/add")
	public String add() {

		return "add";
	}

	@RequestMapping("/AddBook")
	public String addBook(Book book, ModelMap map) {

		if (bookService.addBook(book))
			map.addAttribute("error", "No Add Error");
		else
			map.addAttribute("error", "Add Error");
		return "forward:/back";
	}

	@RequestMapping("/DeleteBook")
	public String DeleteBook(Integer bookNo, ModelMap map) {

		if (bookService.deleteBookBybookNo(bookNo))
			map.addAttribute("error", "No Delete Error");
		else
			map.addAttribute("error", "Delete Error");
		return "forward:/back";
	}

	@RequestMapping("/QueryBookInfoBybookNo")
	public String QueryBookInfoBybookNo(Integer bookNo, ModelMap map) {

		Book book = bookService.queryBookBybookNo(bookNo);
		map.addAttribute("BookInfo", book);
		map.addAttribute("bookNo", bookNo);
		return "BookInfo";
	}

	@RequestMapping("/UpdateBook")
	public String UpdateBook(Book book, ModelMap map) {

		if (bookService.updateBookBybookNo(book.getBookNo(), book))
			map.addAttribute("error", "No Update Error");
		else
			map.addAttribute("error", "Update Error");
		return "forward:/back";
	}
}
