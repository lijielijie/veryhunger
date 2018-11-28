package com.entity;

import com.base.BaseEntity;

public class User extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7348201655066132843L;

	private String uid;

    private String ownname;

    private String name;

    private String pass;

    private String sex;

    private String state;

    private String lname;

    private String gname;

    private String exp;

    private String needexp;

    private String lastdate;

    private String rmk1;

    private String rmk2;

    private String rmk3;

    private String rmk4;

    private String rmk5;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getOwnname() {
        return ownname;
    }

    public void setOwnname(String ownname) {
        this.ownname = ownname == null ? null : ownname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp == null ? null : exp.trim();
    }

    public String getNeedexp() {
        return needexp;
    }

    public void setNeedexp(String needexp) {
        this.needexp = needexp == null ? null : needexp.trim();
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate == null ? null : lastdate.trim();
    }

    public String getRmk1() {
        return rmk1;
    }

    public void setRmk1(String rmk1) {
        this.rmk1 = rmk1 == null ? null : rmk1.trim();
    }

    public String getRmk2() {
        return rmk2;
    }

    public void setRmk2(String rmk2) {
        this.rmk2 = rmk2 == null ? null : rmk2.trim();
    }

    public String getRmk3() {
        return rmk3;
    }

    public void setRmk3(String rmk3) {
        this.rmk3 = rmk3 == null ? null : rmk3.trim();
    }

    public String getRmk4() {
        return rmk4;
    }

    public void setRmk4(String rmk4) {
        this.rmk4 = rmk4 == null ? null : rmk4.trim();
    }

    public String getRmk5() {
        return rmk5;
    }

    public void setRmk5(String rmk5) {
        this.rmk5 = rmk5 == null ? null : rmk5.trim();
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", ownname=" + ownname + ", name=" + name + ", pass=" + pass + ", sex=" + sex
				+ ", state=" + state + ", lname=" + lname + ", gname=" + gname + ", exp=" + exp + ", needexp=" + needexp
				+ ", lastdate=" + lastdate + ", rmk1=" + rmk1 + ", rmk2=" + rmk2 + ", rmk3=" + rmk3 + ", rmk4=" + rmk4
				+ ", rmk5=" + rmk5 + "]";
	}
    
}