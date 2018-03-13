package ov;

import java.util.ArrayList;
import java.util.Date;

public class Myfriend extends User{

	
	private String remarkName = "remarkName";
	
	private String n_message = "Hello";
	
	private String n_m_date = "11:20";


	public String getN_m_date() {
		return n_m_date;
	}

	public void setN_m_date(String n_m_date) {
		this.n_m_date = n_m_date;
	}

	public String getN_message() {
		return n_message;
	}

	public void setN_message(String n_message) {
		this.n_message = n_message;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	
	@Override
	public String toString() {
		return "Myfriend: \n"+" \n " +
				"headImage = "+this.getHeadImage()+ " \n " +
				"id = "+this.getId()+" \n " +
				"remark_name = " +this.getRemarkName()+"\n"+
				"nickname = "+this.getNickName()+" \n " +
				"n_message = " + this.getN_message()+"\n"+
				"n_m_date =" + this.getN_m_date() +"\n";
		
	}
}
