package xyz.lycwood.vo;

import java.io.Serializable;
import java.util.Date;

import xyz.lycwood.entity.LibDept;

public class AdminVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3801137832281900541L;
	private Integer id;
	private String adminname;
	private String password;
	private String salt;
	private String gender;
	private String phone;
	private Integer roleId;
	private LibDept libDept;
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
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public LibDept getLibDept() {
		return libDept;
	}
	public void setLibDept(LibDept libDept) {
		this.libDept = libDept;
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
		return "AdminVo [id=" + id + ", adminname=" + adminname + ", password=" + password + ", salt=" + salt
				+ ", gender=" + gender + ", phone=" + phone + ", roleId=" + roleId + ", libDept=" + libDept
				+ ", createdTime=" + createdTime + ", createdUser=" + createdUser + ", modifiedTime=" + modifiedTime
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
}
