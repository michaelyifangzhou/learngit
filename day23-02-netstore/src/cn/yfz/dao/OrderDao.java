package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Customer;
import cn.yfz.domain.Order;

public interface OrderDao {
	/**
	 * ���涩����Ϣ
	 * ��Ҫ���涩������Ϣ
	 * @param o
	 */
	void save(Order o);
	/**
	 * ���ݶ����Ų�ѯ����
	 * @param ordernum
	 * @return ����������Ϣ
	 */
	Order findByNum(String ordernum);
	/**
	 * ���ݿͻ���ѯ����  �������ڽ�������
	 * @param c
	 * @return
	 */
	List<Order> findByCustomer(String customerId);
	/**
	 * �������ݿ��ж���״̬
	 * @param o
	 */
	void updateStatus(Order o);

}
