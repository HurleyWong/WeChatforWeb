package ov;

import java.util.Date;

public class Relation {
	private int id = 0;
	
	private String m_id = "m_id";
	
	private String f_id = "f_id";
	
	private Date date = new Date();
	
	private String type = "type";
	
	private String remark_name = "remark_name";
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getF_id() {
		return f_id;
	}

	public void setF_id(String f_id) {
		this.f_id = f_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}
}
