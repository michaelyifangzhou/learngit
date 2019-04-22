package cn.yfz.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.yfz.domain.Book;
/**
 * 购物车类
 * @author asus1
 *
 */
public class Cart implements Serializable {
	/**
	 * 每一项都对应一本书
	 */
	private Map<String,CartItem> items=new HashMap<String,CartItem>();
	/**
	 * 总数量
	 */
	private int totalQuantity;
	/**
	 * 总价格
	 */
	private float totalMoney;
	public int getTotalQuantity() {
		totalQuantity=0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalQuantity+=me.getValue().getQuantity();
		}
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public float getTotalMoney() {
		totalMoney=0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalMoney+=me.getValue().getMoney();
		}
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Map<String, CartItem> getItems() {
		return items;
	}
	public void addBook(Book book){
		if(items.containsKey(book.getId())){
			CartItem item=items.get(book.getId());
			item.setQuantity(item.getQuantity()+1);
		}else{
			CartItem item=new CartItem(book);
			item.setQuantity(1);
			items.put(book.getId(), item);
		}
	}
	
}
