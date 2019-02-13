package xyz.lycwood.common.vo;

import java.io.Serializable;
/**
 * 封装角色信息
 * @author Administrator
 *
 */
public class CheckBox implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5379820440566657659L;
	private Integer id;
	private String name;
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
	
}
