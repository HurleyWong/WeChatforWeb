package ov;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author jack
 *	用户表
 *
 */
public class User {
	
	private String id ="id";

	private String nickName = "nickName" ;
	
	private String realName = "realName";
	
	private String password = "password";
	
	private String headImage = null;
	
	private int age = 0;
	
	private int gender =1;		//1��ʾ�� 0��ʾŮ
	
	private Date birthday = new Date();
	
	private String phoneNumber = "phoneNumber";
	
	private String signature = "signature";
	
	private double balance = 1000;

	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "user: \n"+" \n " +
				"headImage = "+this.getHeadImage()+ " \n " +
				"id = "+this.getId()+" \n " +
				"nickname = "+this.getNickName()+" \n " +
				"realname = "+this.getRealName()+" \n " +
				"password = "+this.getPassword()+" \n " +
				"age = " +this.getAge()+" \n " +
				"signature = "+this.getSignature()+" \n " +
				"phonenumber = "+this.getPhoneNumber()+" \n " +
				"gender = "+this.getGender()+" \n " +
				"birthday = "+this.getBirthday()+" \n " +
				"balance = "+this.getBalance();
	}

}
