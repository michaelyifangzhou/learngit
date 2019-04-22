package cn.yfz.web.beans;

import java.io.Serializable;

import cn.yfz.domain.Book;

public class CartItem implements Serializable{
	private Book book;
	private int quantity;
	private float money;
	public CartItem(Book book) {
		super();
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getMoney() {
		return book.getPrice()*quantity;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public Book getBook() {
		return book;
	}
	
}
