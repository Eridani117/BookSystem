package org.cuit.BookSystem;

import java.util.List;

import org.cuit.BookSystem.Book.Book;
import org.cuit.BookSystem.Book.BookDao;
import org.junit.jupiter.api.Test;

public class BookDemo {

	BookDao bookDao = new BookDao();


    @Test

    public void add() {
        Book book = new Book();
        book.setId("2");
        book.setName("SQLServer");
        book.setAuthor("我也不知道");
        book.setImage("33333332432");
        book.setPrice(33.22);
        book.setDescription("这是一本好书");
        book.setCategory_id("2");
        bookDao.addBook(book);
        book.setId("8");
        book.setName("123");
        book.setAuthor("我也不知道");
        book.setImage("33333332432");
        book.setPrice(33.22);
        book.setDescription("这是一本好书");
        book.setCategory_id("2");
        bookDao.addBook(book);
        
        book.setId("7");
        book.setName("SQLS2r");
        book.setAuthor("我也不知道");
        book.setImage("33333332432");
        book.setPrice(33.22);
        book.setDescription("这是一本好书");
        book.setCategory_id("2");
        bookDao.addBook(book);
        book.setId("6");
        
        book.setName("SQLSer");
        book.setAuthor("我也不知道");
        book.setImage("33333332432");
        book.setPrice(33.22);
        book.setDescription("这是一本好书");
        book.setCategory_id("2");
        bookDao.addBook(book);
    }

    @Test
    public void look() {

        List<Book> bookList = bookDao.getPageData(3, 3);

        for (Book book : bookList) {
            System.out.println(book.getName());
        }

        List<Book> books = bookDao.getPageDataByCategory(0,2,"2");

        for (Book book : books) {
            System.out.println(book.getName());

        }
    }

    @Test
    public void find() {
        String id = "2";
        Book book = bookDao.findBookById(id);

        System.out.println(book.getName());
    }


}