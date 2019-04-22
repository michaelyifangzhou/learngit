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
	 * ��ӷ���
	 * @param c
	 */
	void addCategory(Category c);
	/**
	 * �������з���
	 * @return
	 */
	List<Category> findAllCategorys();
	/**
	 * �ҵ��ƶ�id�ķ���
	 * @param CategoryId
	 * @returnû���ҵ�����null
	 */
	Category findCategoryById(String CategoryId);
	/**
	 * ���ͼ��
	 * @param book
	 * ���ͼ�����Ϊ��   �ܳ���������쳣
	 */
	void addBook(Book book);
	/**
	 * ����id����ͼ��
	 * @param bookid
	 * @return  ���з���
	 */
	Book findBookById(String bookid);
	/**
	 * �����û��鿴��ҳ�뷵���������ҳ�йص�page����
	 * @param num �û�Ҫ����ҳ��  ���Ϊ�վ�Ĭ��Ϊ1
	 * @return 
	 */
	Page findBookPageRecords(String num);
	/**
	 * ���ݷ���id��ѯ�鼮��ҳ��Ϣ
	 * @param num
	 * @param categoryId
	 * @return
	 */
	Page findBookPageRecords(String num, String categoryId);
	/**
	 * ����û�
	 */
	void addCustomer(Customer c);
	/**
	 * ��ѯ�û�
	 * @param customerId
	 * @return
	 */
	Customer findCustomer(String customerId);
	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @return
	 */
	Customer customerLogin(String username,String password);
	/**
	 * ���ɶ��� �����б����ж�����  �����пͻ���Ϣ
	 * @param o
	 * @param items
	 * @param c
	 */
	void getOrder(Order o);
	/**
	 * ���ݶ����Ų�ѯ����
	 * @param ordernum
	 * @return
	 */
	Order findOrderByNum(String ordernum);
	/**
	 * ��ѯ�ͻ�����
	 * @param c
	 * @return
	 */
	List<Order> findCustomerOrders(String customerId);
	/**
	 * ���ö���״̬
	 * @param o
	 */
	void changeOrderStatus(Order o);
	/**
	 * ��̨�û���¼
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username,String password);
	/**
	 * ��ѯ�û����ý�ɫ
	 * @param user
	 * @return
	 */
	List<Role> findRoleByUser(User user);
	/**
	 * ���ݽ�ɫ��ѯ��Ӧ����
	 * @param role
	 * @return
	 */
	List<Function> findFunctionByRole(Role role);
	
}
