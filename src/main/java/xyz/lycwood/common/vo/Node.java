package xyz.lycwood.common.vo;

import java.io.Serializable;
/**
 * 在部门添加时,用于封装部门的树结构信息的值对象;
 * @author Administrator
 *
 */
public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8638882928018398764L;
	private Integer id;
	private String name;
	private Integer parentId;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", parentId=" + parentId + "]";
	}
	
}
