package ov;

import java.util.ArrayList;

public class GroupInfo {
	

	Group group ;
	
	ArrayList<User> list ;
	
	ArrayList<Mfile> list2;
 
	public ArrayList<Mfile> getList2() {
		return list2;
	}

	public void setList2(ArrayList<Mfile> list2) {
		this.list2 = list2;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public ArrayList<User> getList() {
		return list;
	}

	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	
}
