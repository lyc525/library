package xyz.lycwood.common.vo;

import java.io.Serializable;
import java.util.List;

public class PageObject<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6954527079622829157L;
	private List<T> records;
	private Integer pageCurrent = null;
	private Integer pageSize = null;
	private Integer pageCount = null;
	private Integer rowCount = null;
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return (rowCount - 1)/pageSize + 1;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	@Override
	public String toString() {
		return "PageObject [records=" + records + ", pageCurrent=" + pageCurrent + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", rowCount=" + rowCount + "]";
	}
	
}
