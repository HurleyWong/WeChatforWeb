package ov;

public class Account{

	//公众号id
	private String id="id";
	//公众号名称
	private String account_name="account_name";
	//开通公众号的用户id
	private String c_id="c_id";
	//公众号创建日期
	private String date="date";
	//公众号功能介绍
	private String introduction="introduction";

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getAccount_name(){
		return account_name;
	}
	public void setAccount_name(String account_name){
		this.account_name=account_name;
	}
	public String getC_id(){
		return c_id;
	}
	public void setC_id(String c_id){
		this.c_id=c_id;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date=date;
	}
	public String getIntroduction(){
		return introduction;
	}
	public void setIntroduction(String introduction){
		this.introduction=introduction;
	}

	@Override
	public String toString(){
		return "account:\n"+
				"id="+this.getId()+"\n"+
				"account_name="+this.getAccount_name()+"\n"+
				"c_id="+this.getC_id()+"\n"+
				"introduction="+this.getIntroduction();
	}

}
