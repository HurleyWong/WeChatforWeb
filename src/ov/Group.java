package ov;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author jack
 *
 */
public class Group {

	private int id = 0;

	private String name = "";
	
	private String c_id = "0";
	
	private int size = 0;
	
	private Date c_date = new Date();
	
	private String image = null;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getC_date() {
		return c_date;
	}

	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
