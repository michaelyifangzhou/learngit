package cn.yfz.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 订单
 * @author asus1
 *
 */
public class Order implements Serializable {
	private String ordernum;
	private int quantity;
	private float money;
	private Customer customer;
	/**
	 * 订单状态  0，未付款 1，已付款 2，已发货
	 */
	private int status;
	private List<OrderItem> items=new ArrayList<OrderItem>();
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [ordernum=" + ordernum + ", quantity=" + quantity + ", money=" + money + ", customer=" + customer
				+ "]";
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
