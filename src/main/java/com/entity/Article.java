package com.entity;

import com.base.BaseEntity;

public class Article extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1726560279422575252L;
	
	private String id;

    private String title;//文章标题

    private String userId;//用户id

    private String role;//文章公开权限

    private String centent;//正文

    private String state;//文章失效状态

    private String createTime;//创建时间
    
    private String updateTime;//最近更新时间

    private int readCount;//被浏览次数
    
    private String rmk1;

    private String rmk2;

    private String rmk3;

    private String rmk4;

    private String rmk5;

    
    private String userName;//不在数据库内
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getRmk1() {
		return rmk1;
	}

	public void setRmk1(String rmk1) {
		this.rmk1 = rmk1;
	}

	public String getRmk2() {
		return rmk2;
	}

	public void setRmk2(String rmk2) {
		this.rmk2 = rmk2;
	}

	public String getRmk3() {
		return rmk3;
	}

	public void setRmk3(String rmk3) {
		this.rmk3 = rmk3;
	}

	public String getRmk4() {
		return rmk4;
	}

	public void setRmk4(String rmk4) {
		this.rmk4 = rmk4;
	}

	public String getRmk5() {
		return rmk5;
	}

	public void setRmk5(String rmk5) {
		this.rmk5 = rmk5;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", userId=" + userId + ", role=" + role + ", centent="
				+ centent + ", state=" + state + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", readCount=" + readCount + ", rmk1=" + rmk1 + ", rmk2=" + rmk2 + ", rmk3=" + rmk3 + ", rmk4=" + rmk4
				+ ", rmk5=" + rmk5 + ", userName=" + userName + "]";
	}

	
}
