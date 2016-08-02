package org.luo.enterprise.entity;

import java.sql.Date;


public class AdminInfo {
	private int id;
	private String login_name;
	private String password_md5;
	private String password;
	private String real_name;
	private String idcard_no;
	private char gender;
	private String phone;
	private String email;
	private Date enrolldate;
	//添加验证码属性
//	private String admin_code;
//	public String getAdmin_code() {
//		return admin_code;
//	}
//	public void setAdmin_code(String admin_code) {
//		this.admin_code = admin_code;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword_md5() {
		return password_md5;
	}
	public void setPassword_md5(String password_md5) {
		this.password_md5 = password_md5;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getIncard_no() {
		return getIdcard_no();
	}
	public void setIncard_no(String incard_no) {
		this.idcard_no = incard_no;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	public String getIdcard_no() {
		return idcard_no;
	}
	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}
}
