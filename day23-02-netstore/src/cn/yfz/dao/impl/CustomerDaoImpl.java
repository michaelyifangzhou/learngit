package cn.yfz.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.yfz.dao.CustomerDao;
import cn.yfz.domain.Customer;
import cn.yfz.utils.DBCPUtils;

public class CustomerDaoImpl implements CustomerDao {
	private QueryRunner qr=new QueryRunner(DBCPUtils.getDataSource());
	@Override
	public void save(Customer c) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into customers(id,username,password,nickname,phonenum,address,email) values(?,?,?,?,?,?,?)", c.getId(),c.getUsername(),c.getPassword(),c.getNickname(),c.getPhonenum(),c.getAddress(),c.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Customer findOne(String customerId) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from customers where id=?",new BeanHandler<Customer>(Customer.class) , customerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Customer find(String username, String password) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from customers where username=? and password=?",new BeanHandler<Customer>(Customer.class) , username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
