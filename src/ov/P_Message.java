package ov;

import java.util.Date;

public class P_Message extends User{
		
	private String content;
	
	private Date c_date;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getC_date() {
		return c_date;
	}

	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	
	@Override
	public String toString() {
		return "MyP_Message: \n"+" \n " +
				"headImage = "+this.getHeadImage()+ " \n " +
				"id = "+this.getId()+" \n " +
				"content = "+this.getContent() +"\n"+
				"c_date = "+this.getC_date()+"\n"+
				"gender = "+this.getGender()+" \n " +
				"balance = "+this.getBalance();
	}
}
