package org.cuit.BookSystem.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cuit.BookSystem.Book.Book;
import org.cuit.BookSystem.Book.BookService;
import org.cuit.BookSystem.Book.Page;
import org.cuit.BookSystem.Cart.Cart;
import org.cuit.BookSystem.Category.Category;
import org.cuit.BookSystem.Category.CategoryService;
import org.cuit.BookSystem.Order.Order;
import org.cuit.BookSystem.Order.OrderService;
import org.cuit.BookSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("cart")
@Controller
public class ClientController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;

	@Autowired
	OrderService orderService;

	@GetMapping("/client")
	public String clent(HttpServletRequest request) {
		
//		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
//				.getAttribute("SPRING_SECURITY_CONTEXT");
//		if(securityContextImpl != null)
//		System.out.println(securityContextImpl.getAuthentication().getName());
		return "client";
	}

	@GetMapping("/toLogin")
	public String tologin() {
		return "toLogin";
	}
	
	@GetMapping("/client/head")
	public String head() {
		return "/client/head";
	}

	@GetMapping("/client/index")
	public String index(ModelMap map, String currentPageCount,HttpServletRequest request) {
		List<Category> categories = categoryService.getAllCategory();
		map.addAttribute("categories", categories);

		// 得到所有分类的图书，给body页面
		Page page = bookService.getPageData(currentPageCount);
		map.addAttribute("page", page);
		return "/client/body";
	}

	@GetMapping("/client/ListBook")
	public String ListBook(ModelMap map, String category_id, String currentPageCount) {

		Page page = bookService.getPageDataBycategory_id(currentPageCount, category_id);
		List<Category> categories = categoryService.getAllCategory();
		map.addAttribute("page", page);
		map.addAttribute("categories", categories);

		return "/client/body";
	}

	@GetMapping("/client/BuyBook")
	public String BuyBook(ModelMap map, String book_id, Cart cart) {
		Book book = bookService.findBook(book_id);
		if (cart == null) {
			cart = new Cart();
		}
		orderService.buyBook(cart, book);
		map.addAttribute("cart", cart);
		
		return "redirect:/client/listCart";
	}

	@GetMapping("/client/CreateOrder")
	@ResponseBody
	public String CreateOrder(ModelMap map, Cart cart, User user, HttpServletRequest request) {
		if (cart == null) {
			return "您购物车没有商品，无法生成订单";
		}
		
		//用户数据应从数据库中取得
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");
		user = new User();
		user.setId(securityContextImpl.getAuthentication().getName());
		
		orderService.createOrder(cart, user);
		cart = null;
		return "订单已经生成了，准备好钱来收货把";
	}

	@GetMapping("/client/listCart")
	public String listCart(ModelMap map, Cart cart) {
		// 把该用户的购物车给JSP页面显示
		map.addAttribute("cart", cart);
		return "/client/listCart";
	}
	@GetMapping("/client/clearCart")
	public String clear(ModelMap map, Cart cart) {
		
		map.remove("cart");
		System.out.println("did clear");
		return "/client/listCart";
	}

	@GetMapping("/client/LookOrder")
	public String LookOrder(ModelMap map, User user,HttpServletRequest request) {
		//user.getId()
		List<Order> orders = orderService.findUserOrder("1");
		System.out.println("这里是订单有多少个：" + orders.size());
		map.addAttribute("orders", orders);
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");
		user = new User();
		user.setId(securityContextImpl.getAuthentication().getName());
		map.addAttribute("user", user);
		// 交给相对应的JSP 显示

		return "/client/listOrder";
	}

}
