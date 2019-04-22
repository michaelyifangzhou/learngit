package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Book;

public interface BookDao {

	void save(Book book);
	/**
	 * 巴蜀及对应的分类页查询出来
	 * @param bookid
	 * @return
	 */
	Book findBookById(String bookid);
	/**
	 * 活的书籍总记录数
	 * @return
	 */
	int getTotalRecordsNum();
	/**
	 * 分页查询  把书及所属类型查询出来
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List findPageRecords(int startIndex, int pageSize);
	/**
	 * 获取特定分类的书籍记录总数
	 * @param categoryId
	 * @return
	 */
	int getTotalRecordsNum(String categoryId);
	/**
	 * 根据特定类别查询分页记录
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return
	 */
	List findPageRecords(int startIndex, int pageSize, String categoryId);

}
