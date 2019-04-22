package cn.yfz.dao;

import java.util.List;

import cn.yfz.domain.Book;

public interface BookDao {

	void save(Book book);
	/**
	 * ���񼰶�Ӧ�ķ���ҳ��ѯ����
	 * @param bookid
	 * @return
	 */
	Book findBookById(String bookid);
	/**
	 * ����鼮�ܼ�¼��
	 * @return
	 */
	int getTotalRecordsNum();
	/**
	 * ��ҳ��ѯ  ���鼰�������Ͳ�ѯ����
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List findPageRecords(int startIndex, int pageSize);
	/**
	 * ��ȡ�ض�������鼮��¼����
	 * @param categoryId
	 * @return
	 */
	int getTotalRecordsNum(String categoryId);
	/**
	 * �����ض�����ѯ��ҳ��¼
	 * @param startIndex
	 * @param pageSize
	 * @param categoryId
	 * @return
	 */
	List findPageRecords(int startIndex, int pageSize, String categoryId);

}
