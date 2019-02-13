package xyz.lycwood.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装部门信息的数据表
 * @author Administrator
 *
 */
public class LibDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2590645789713740887L;
	/**
	 * 
	 */
	private Integer id;
	private String name;
	private String note;
	private Integer parentId;
	private String sort;
	private Date createdTime;
	private String createdUser;
	private Date modifiedTime;
	private String modifiedUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "LibDept [id=" + id + ", name=" + name + ", note=" + note + ", parentId=" + parentId + ", sort=" + sort
				+ ", createdTime=" + createdTime + ", createdUser=" + createdUser + ", modifiedTime=" + modifiedTime
				+ ", modifiedUser=" + modifiedUser + "]";
	}
}
