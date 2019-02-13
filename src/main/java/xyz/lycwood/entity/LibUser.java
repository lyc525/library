package xyz.lycwood.entity;

import java.io.Serializable;
import java.util.Date;

public class LibUser implements Serializable {
	private static final long serialVersionUID = 7817897471886089332L;
	private Integer id;
	private String userNum;
	private String username;
	private String password;
	private String salt;
	private String gender;
	private String phone;
	private String cardId;
	private Integer vip;
	private String hobby;
	private Integer valid=1;
	private Date createdTime;
	private Date lastTime;
	private Integer bookNum;
	private Integer overNum;
	private Integer borNum;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getUserNum() {
		return userNum;
	}

	public final void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getSalt() {
		return salt;
	}

	public final void setSalt(String salt) {
		this.salt = salt;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final String getCardId() {
		return cardId;
	}

	public final void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public final Integer getVip() {
		return vip;
	}

	public final void setVip(Integer vip) {
		this.vip = vip;
	}

	public final String getHobby() {
		return hobby;
	}

	public final void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public final Integer getValid() {
		return valid;
	}

	public final void setValid(Integer valid) {
		this.valid = valid;
	}

	public final Date getCreatedTime() {
		return createdTime;
	}

	public final void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public final Date getLastTime() {
		return lastTime;
	}

	public final void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public final Integer getBookNum() {
		return bookNum;
	}

	public final void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public final Integer getOverNum() {
		return overNum;
	}

	public final void setOverNum(Integer overNum) {
		this.overNum = overNum;
	}

	public final Integer getBorNum() {
		return borNum;
	}

	public final void setBorNum(Integer borNum) {
		this.borNum = borNum;
	}

	@Override
	public String toString() {
		return "LibUser [id=" + id + ", userNum=" + userNum + ", username=" + username + ", password=" + password
				+ ", salt=" + salt + ", gender=" + gender + ", phone=" + phone + ", cardId=" + cardId + ", vip=" + vip
				+ ", hobby=" + hobby + ", valid=" + valid + ", createTime=" + createdTime + ", lastTime=" + lastTime
				+ ", bookNum=" + bookNum + ", overNum=" + overNum + ", borNum=" + borNum + "]";
	}

}
