package org.cuit.BookSystem.Controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.cuit.BookSystem.Book.Book;
import org.cuit.BookSystem.Book.BookService;
import org.cuit.BookSystem.Book.Page;
import org.cuit.BookSystem.Category.Category;
import org.cuit.BookSystem.Category.CategoryService;
import org.cuit.BookSystem.Order.Order;
import org.cuit.BookSystem.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ManagerController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;

	@Autowired
	OrderService orderService;

	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		System.out.println(securityContextImpl.getAuthentication().getName());
		return "/manager";
	}

	@RequestMapping("/background/head")
	public String head() {
		return "/background/head";
	}

	@RequestMapping("/background/left")
	public String left() {
		return "/background/left";
	}

	@RequestMapping("/background/body")
	public String body() {
		return "/background/body";
	}

	@RequestMapping("/background/addCategory")
	public String addCategoryPage() {
		return "/background/addCategory";
	}

	@PostMapping("/background/addCategory")
	public String addCategory(Category category, ModelMap map) {
		category.setId(UUID.randomUUID().toString());
		categoryService.addCategory(category);
		map.addAttribute("message", "添加分类成功！");
		return "/background/addCategory";
	}

	@RequestMapping("/background/deleteCategory")
	public String deleteCategory(String id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/background/ShowCategory";
	}

	@GetMapping("/background/ShowCategory")
	public String ShowCategory(ModelMap map) {
		List<Category> list = categoryService.getAllCategory();
		map.addAttribute("list", list);
		return "/background/ShowCategory";
	}

	@GetMapping("/background/addBook")
	public String addBookPage(ModelMap map) {
		List<Category> list = categoryService.getAllCategory();
		map.addAttribute("list", list);
		return "/background/addBook";
	}

	@PostMapping("/background/addBook")
	public String addBook(@RequestParam("image") MultipartFile file, HttpServletRequest request, Book book,
			BindingResult result) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		// System.out.println("1:"+System.getProperty("user.dir"));
		// System.out.println("2:"+request.getSession().getServletContext().getRealPath("image")
		// );
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new Exception("Cannot store file with relative path outside current directory " + filename);
			}
			// System.out.println("3:"+Paths.get(request.getSession().getServletContext().getRealPath("image")).resolve(filename));
			Path rootLocation = Paths.get(request.getSession().getServletContext().getRealPath("image"));
			// System.out.println("4:"+rootLocation.resolve(filename));
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			// throw new Exception("Failed to store file " + filename, e);
			e.printStackTrace();
		}
		book.setId(UUID.randomUUID().toString());
		book.setImage(filename);
		bookService.addBook(book);
		return "/background/addBook";
	}

	@GetMapping("/background/ShowBook")
	public String ShowBookPage(ModelMap map, String currentPageCount) {
		Page page = bookService.getPageData(currentPageCount);
		map.addAttribute("page", page);
		return "/background/listBook";
	}

	@GetMapping("/background/showOrderItem")
	public String showOrderItem(String order_id, ModelMap map, HttpServletRequest request) {

		Order order = orderService.findOrder(order_id);
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");

		order.setUser_id(securityContextImpl.getAuthentication().getName());
		map.addAttribute("order", order);
		return "/background/listDetail";
	}

	@GetMapping("/background/orderSendOut")
	public String orderSendOut(String id, ModelMap map) {
		orderService.sendOutOrder(id);
		return "redirect:/background/OrderFalse";
	}

	@GetMapping("/background/OrderFalse")
	public String OrderFalse(ModelMap map) {
		List<Order> list = orderService.getAllOrder(false);
		map.addAttribute("list", list);

		return "/background/listOrder";
	}

	@GetMapping("/background/OrderTrue")
	public String OrderTrue(ModelMap map) {
		List<Order> list = orderService.getAllOrder(true);
		map.addAttribute("list", list);
		return "/background/listOrder";
	}

	@GetMapping("/background/deleteBook")
	public String deleteBook(ModelMap map, String id) {
		bookService.deleteBookBybookNo(id);
		return "forward:/background/ShowBook";

	}
}
