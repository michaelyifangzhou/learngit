package cn.yfz.commons;

import java.util.List;

public class Page {
	private List records;
	/**
	 * ��ǰҳ��
	 */
	private int pageNum;
	/**
	 * ��ҳ��
	 */
	private int totalPageNum;
	/**
	 * ��һҳ
	 */
	private int prePageNum;
	/**
	 * ��һҳ
	 */
	private int nextPageNum;
	/**
	 * ÿҹ��¼����
	 */
	private int pageSize=3;
	/**
	 * ��¼������
	 */
	private int totalRecordsNum;
	/**
	 * ÷Ҳ��ʼ��¼������
	 */
	private int startIndex;
	/**
	 * ��ѯ��ҳ������ĵ�ַ
	 */
	private String url;
	
	public Page(int pageNum, int totalRecordsNum) {
		super();
		this.pageNum = pageNum;
		this.totalRecordsNum = totalRecordsNum;
		totalPageNum=totalRecordsNum%pageSize==0?
				totalRecordsNum/pageSize:
			(totalRecordsNum/pageSize)+1;
		startIndex=pageSize*(pageNum-1);
		
	}
	public List getRecords() {
		return records;
	}
	public void setRecords(List records) {
		this.records = records;
	}
	public int getPageNum() {
		
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getPrePageNum() {
		prePageNum=pageNum-1;
		if(prePageNum<1){
			prePageNum=1;
		}
		return prePageNum;
	}
	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}
	public int getNextPageNum() {
		nextPageNum=pageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum=totalPageNum;
		}
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}
	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int strtIndex) {
		this.startIndex = strtIndex;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
