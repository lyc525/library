package xyz.lycwood.vo;

import java.io.Serializable;
import java.util.List;

public class RoleVo implements Serializable{

private static final long serialVersionUID = 5596713921495157423L;
	
	private Integer id; //角色id	
	private String name;//角色名称
	private String note;//角色备注
	private List<Integer> menuIds;//菜单id
	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
}
