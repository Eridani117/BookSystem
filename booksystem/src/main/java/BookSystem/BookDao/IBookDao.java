package BookSystem.BookDao;

import java.util.List;

import BookSystem.entity.Book;



public interface IBookDao {

	public boolean addStudent(Book book) ;
	
	//根据书号学号修改书： 根据bookNo知道待修改的人 ，把这本书这个人 修改成student
	public boolean updateStudentBySno(int bookNo,Book book)  ;
	
	//获得书的总数
	public int getTotalCount();
	
	//根据书号学号删除书
	public boolean deleteBookBybookNo(int bookNo)  ;
	
	//查询全部书(很多书)
	public List<Book> queryAllBooks() ;
	
	//查询指定页码的书
	public List<Book> queryAllBooksByPage(int currentPage,int pageSize) ;
	
	
	//查询此书是否存在
	public boolean isExist(int bookNo);
	
	//根据书号 查询书
	public Book queryBookBybookNo(int bookNo) ;
	
	
	
	
	
	
	
	
	
}
