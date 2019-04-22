package cn.yfz.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.yfz.dao.OrderDao;
import cn.yfz.domain.Order;
import cn.yfz.domain.OrderItem;
import cn.yfz.utils.DBCPUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner qr=new QueryRunner(DBCPUtils.getDataSource());
	@Override
	public void save(Order o) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into orders(ordernum,quantity,money,status,customerId) values(?,?,?,?,?)", o.getOrdernum(),o.getQuantity(),o.getMoney(),o.getStatus(),o.getCustomer().getId());
			List<OrderItem> items=o.getItems();
			for(OrderItem i:items){
				qr.update("insert into orderitems(id,quantity,price,bookId,ordernum) values(?,?,?,?,?)", i.getId(),i.getQuantity(),i.getPrice(),i.getBook().getId(),i.getOrder().getOrdernum());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Order findByNum(String ordernum) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from orders where ordernum=?", new BeanHandler<Order>(Order.class), ordernum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByCustomer(String customerId) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from orders where customerId=? order by ordernum desc", new BeanListHandler<Order>(Order.class), customerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateStatus(Order o) {
		// TODO Auto-generated method stub
		try {
			qr.update("update orders set status=? where ordernum=?", o.getStatus(),o.getOrdernum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
