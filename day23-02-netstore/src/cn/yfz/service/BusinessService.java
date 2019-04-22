package cn.yfz.service;

import java.util.List;

import cn.yfz.commons.Page;
import cn.yfz.domain.Book;
import cn.yfz.domain.Category;
import cn.yfz.domain.Customer;
import cn.yfz.domain.Function;
import cn.yfz.domain.Order;
import cn.yfz.domain.OrderItem;
import cn.yfz.domain.Role;
import cn.yfz.domain.User;

public interface BusinessService {
	/**
	 * 添加分类
	 * @param c
	 */
	void addCategory(Category c);
	/**
	 * 查找所有分类
	 * @return
	 */
	List<Category> findAllCategorys();
	/**
	 * 找到制定id的分类
	 * @param CategoryId
	 * @return没有找到返回null
	 */
	Category findCategoryById(String CategoryId);
	/**
	 * 添加图书
	 * @param book
	 * 如果图书类别为空   跑出参数类别异常
	 */
	void addBook(Book book);
	/**
	 * 根据id查找图书
	 * @param bookid
	 * @return  还有分类
	 */
	Book findBookById(String bookid);
	/**
	 * 根据用户查看的页码返回所有与分页有关的page对象
	 * @param num 用户要看的页码  如果为空就默认为1
	 * @return 
	 */
	Page findBookPageRecords(String num);
	/**
	 * 根据分类id查询书籍分页信息
	 * @param num
	 * @param categoryId
	 * @return
	 */
	Page findBookPageRecords(String num, String categoryId);
	/**
	 * 添加用户
	 */
	void addCustomer(Customer c);
	/**
	 * 查询用户
	 * @param customerId
	 * @return
	 */
	Customer findCustomer(String customerId);
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	Customer customerLogin(String username,String password);
	/**
	 * 生成订单 订单中必须有订单项  必须有客户信息
	 * @param o
	 * @param items
	 * @param c
	 */
	void getOrder(Order o);
	/**
	 * 根据订单号查询订单
	 * @param ordernum
	 * @return
	 */
	Order findOrderByNum(String ordernum);
	/**
	 * 查询客户订单
	 * @param c
	 * @return
	 */
	List<Order> findCustomerOrders(String customerId);
	/**
	 * 更该订单状态
	 * @param o
	 */
	void changeOrderStatus(Order o);
	/**
	 * 后台用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
	/**
	 * 查询用户用用角色
	 * @param user
	 * @return
	 */
	List<Role> findRoleByUser(User user);
	/**
	 * 根据角色查询对应功能
	 * @param role
	 * @return
	 */
	List<Function> findFunctionByRole(Role role);
	
}
