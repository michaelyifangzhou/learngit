package cn.yfz.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.yfz.dao.CategoryDao;
import cn.yfz.domain.Category;
import cn.yfz.utils.DBCPUtils;

public class CategoryDaoImpl implements CategoryDao {
	private QueryRunner qr=new QueryRunner(DBCPUtils.getDataSource());
	@Override
	public void save(Category c) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into categorys(id,name,description) values(?,?,?)", c.getId(),c.getName(),c.getDescription());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from categorys", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category findById(String categoryId) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select * from categorys where id=?", new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
