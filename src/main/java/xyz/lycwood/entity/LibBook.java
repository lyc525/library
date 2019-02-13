package xyz.lycwood.entity;

import java.io.Serializable;

public class LibBook implements Serializable{
	/**
	 * 随机生成序列化id
	 */
	private static final long serialVersionUID = -8200858945521445728L;
	private Integer id;
	private String location;
	private String name;
	private String author;
	private Integer valid;
	private String category;//类别
	private String press;//出版社
	private String summary;//说明
	private Integer totalNum;//总数量
	private Integer oddNum;//剩余数量
	private String imgUrl;
	private String downUrl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getOddNum() {
		return oddNum;
	}
	public void setOddNum(Integer oddNum) {
		this.oddNum = oddNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "LibBook [id=" + id + ", location=" + location + ", name=" + name + ", author=" + author + ", category="
				+ category + ", press=" + press + ", summary=" + summary + ", totalNum=" + totalNum + ", oddNum="
				+ oddNum + ", imgUrl=" + imgUrl + ", downUrl=" + downUrl + "]";
	}
}
