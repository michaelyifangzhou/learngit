package cn.yfz.Impl;

import java.util.List;

import cn.yfz.commons.Page;
import cn.yfz.dao.BookDao;
import cn.yfz.dao.CategoryDao;
import cn.yfz.dao.CustomerDao;
import cn.yfz.dao.OrderDao;
import cn.yfz.dao.PrivilegeDao;
import cn.yfz.dao.impl.BookDaoImpl;
import cn.yfz.dao.impl.CategoryDaoImpl;
import cn.yfz.dao.impl.CustomerDaoImpl;
import cn.yfz.dao.impl.OrderDaoImpl;
import cn.yfz.dao.impl.PrivilegeDaoImpl;
import cn.yfz.domain.Book;
import cn.yfz.domain.Category;
import cn.yfz.domain.Customer;
import cn.yfz.domain.Function;
import cn.yfz.domain.Order;
import cn.yfz.domain.OrderItem;
import cn.yfz.domain.Role;
import cn.yfz.domain.User;
import cn.yfz.service.BusinessService;
import cn.yfz.utils.IdGenertor;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao categoryDao=new CategoryDaoImpl();
	private BookDao bookDao=new BookDaoImpl();
	private OrderDao orderDao=new OrderDaoImpl();
	private CustomerDao customerDao=new CustomerDaoImpl();
	private PrivilegeDao pdao=new PrivilegeDaoImpl();
	@Override
	public void addCategory(Category c) {
		// TODO Auto-generated method stub
		c.setId(IdGenertor.genGUID());
		categoryDao.save(c);
	}

	@Override
	public List<Category> findAllCategorys() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public Category findCategoryById(String CategoryId) {
		// TODO Auto-generated method stub
		return categoryDao.findById(CategoryId);
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		if(book==null){
			throw new IllegalArgumentException("the book cannot be null");
		}
		if(book.getCategory()==null){
			throw new IllegalArgumentException("the book category cannot be null");

		}
		book.setId(IdGenertor.genGUID());
		bookDao.save(book);
	}

	@Override
	public Book findBookById(String bookid) {
		// TODO Auto-generated method stub
		return bookDao.findBookById(bookid);
	}

	@Override
	public Page findBookPageRecords(String num) {
		// TODO Auto-generated method stub
		int pageNum=1;
		if(num!=null&&!num.equals("")){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum();
		Page page=new Page(pageNum, totalRecordsNum);
		List records=bookDao.findPageRecords(page.getStartIndex(),page.getPageSize());
		page.setRecords(records);
		return page;
	}

	@Override
	public Page findBookPageRecords(String num, String categoryId) {
		// TODO Auto-generated method stub
		
		int pageNum=1;
		if(num!=null&&!num.equals("")){
			pageNum=Integer.parseInt(num);
		}
		int totalRecordsNum=bookDao.getTotalRecordsNum(categoryId);
		Page page=new Page(pageNum, totalRecordsNum);
		List records=bookDao.findPageRecords(page.getStartIndex(),page.getPageSize(),categoryId);
		page.setRecords(records);
		return page;
	}

	@Override
	public void addCustomer(Customer c) {
		// TODO Auto-generated method stub
		c.setId(IdGenertor.genGUID());
		customerDao.save(c);
	}

	@Override
	public Customer findCustomer(String customerId) {
		// TODO Auto-generated method stub
		return customerDao.findOne(customerId);
	}

	@Override
	public Customer customerLogin(String username, String password) {
		// TODO Auto-generated method stub
		return customerDao.find(username,password);
	}

	@Override
	public void getOrder(Order o) {
		// TODO Auto-generated method stub
		if(o.getCustomer()==null){
			throw new IllegalArgumentException("订单客户信息不能为空");
		}else if(o.getItems()==null||o.getItems().size()==0){
			throw new IllegalArgumentException("订单项信息不能为空");

		}
		orderDao.save(o);
	}

	@Override
	public Order findOrderByNum(String ordernum) {
		// TODO Auto-generated method stub
		return orderDao.findByNum(ordernum);
	}

	@Override
	public List<Order> findCustomerOrders(String customerId) {
		// TODO Auto-generated method stub
		return orderDao.findByCustomer(customerId);
	}

	@Override
	public void changeOrderStatus(Order o) {
		// TODO Auto-generated method stub
		orderDao.updateStatus(o);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return pdao.find(username,password);
	}

	@Override
	public List<Role> findRoleByUser(User user) {
		// TODO Auto-generated method stub
		return pdao.findRoleByUser( user);
	}

	@Override
	public List<Function> findFunctionByRole(Role role) {
		// TODO Auto-generated method stub
		return pdao.findFunctionByRole( role);
	}

}
