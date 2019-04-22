package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Customer;
import cn.yfz.domain.Order;

public interface OrderDao {
	/**
	 * 保存订单信息
	 * 还要保存订单项信息
	 * @param o
	 */
	void save(Order o);
	/**
	 * 根据订单号查询订单
	 * @param ordernum
	 * @return 订单基本信息
	 */
	Order findByNum(String ordernum);
	/**
	 * 根据客户查询订单  按照日期将序排列
	 * @param c
	 * @return
	 */
	List<Order> findByCustomer(String customerId);
	/**
	 * 更改数据库中订单状态
	 * @param o
	 */
	void updateStatus(Order o);

}
