package cn.yfz.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.yfz.dao.BookDao;
import cn.yfz.domain.Book;
import cn.yfz.domain.Category;
import cn.yfz.utils.DBCPUtils;

public class BookDaoImpl implements BookDao {
	private QueryRunner qr=new QueryRunner(DBCPUtils.getDataSource());
	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		try {
			qr.update("insert into books(id,name,price,author,path,filename,description,categoryId) values(?,?,?,?,?,?,?,?)", book.getId(),book.getName(),book.getPrice(),book.getAuthor(),book.getPath(),book.getFilename(),book.getDescription(),book.getCategory().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book findBookById(String bookid) {
		// TODO Auto-generated method stub
		try {
			Book book=qr.query("select * from books where id=?" , new BeanHandler<Book>(Book.class),bookid);
			if(book!=null){
				Category c=qr.query("select * from categorys where id=(select categoryId from books where id=?)",new BeanHandler<Category>(Category.class),bookid );
				book.setCategory(c);
			}
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalRecordsNum() {
		// TODO Auto-generated method stub
		//long
		try {
			Object obj=qr.query("select count(*) from books", new ScalarHandler(1));
			Long num=(long) obj;
			return num.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List findPageRecords(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		try {
			List<Book> books=qr.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class),startIndex,pageSize );
			if(books!=null&&books.size()>0){
				for(Book book:books){
					Category c=qr.query(
							"select * from categorys where id=(select categoryId from books where id=?)",
							new BeanHandler<Category>(Category.class),
							book.getId());
					book.setCategory(c);
				}
			
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getTotalRecordsNum(String categoryId) {
		// TODO Auto-generated method stub
		try {
			Object obj=qr.query("select count(*) from books where categoryId=?", new ScalarHandler(1),categoryId);
			Long num=(long) obj;
			return num.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public List findPageRecords(int startIndex, int pageSize, String categoryId) {
		// TODO Auto-generated method stub
		try {
			List<Book> books=qr.query("select * from books where categoryId=? limit ?,?", new BeanListHandler<Book>(Book.class),categoryId,startIndex,pageSize );
			if(books!=null&&books.size()>0){
				for(Book book:books){
					Category c=qr.query(
							"select * from categorys where id=?",
							new BeanHandler<Category>(Category.class),
							categoryId);
					book.setCategory(c);
				}
			
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
