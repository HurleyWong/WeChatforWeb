package ov;

import java.util.Date;

/**
 * 
 * @author k
 *	¸ĞÏëÀà
 *
 */
public class Moment {
	
	private int id = 0; 
	
	private String c_id = "c_id";
	
	private String image = "image";
	
	private String content = "content";
	
	private Date date = new Date();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
   
}
