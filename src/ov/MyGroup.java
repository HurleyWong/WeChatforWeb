package ov;

public class MyGroup extends Group{

	private String n_message = "Hello";
	
	private String n_m_date = "11:20";

	public String getN_message() {
		return n_message;
	}

	public void setN_message(String n_message) {
		this.n_message = n_message;
	}

	public String getN_m_date() {
		return n_m_date;
	}

	public void setN_m_date(String n_m_date) {
		this.n_m_date = n_m_date;
	}
	
	@Override
	public String toString(){
		return "group: \n"+" \n " +
				"headImage = "+this.getImage()+ " \n " +
				"c_id = "+this.getC_id()+" \n " +
				"id = "+this.getId()+" \n " +
				"groupname = "+this.getName()+" \n " +
				"n_message = " + this.getN_message()+"\n"+
				"n_m_date =" + this.getN_m_date() +"\n";
	}
}
