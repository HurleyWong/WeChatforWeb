package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ov.Group;
import ov.MyGroup;
import ov.Myfriend;
import ov.User;
import util.DatabaseUtil;

/**
 * 关于群组的数据库操作
 * @author jack
 *
 */
public class GroupDao {
	
	

	/**
	 * 根据群id返回一个Group实例
	 * @param id
	 * @return
	 */
	public Group getGroupById(int id) {
		Group group = null ;
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			group = new Group();
			PreparedStatement pstmt;
			String sql = "select * from t_group where id = '"+id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		group.setId(id);
	        		group.setC_id(rs.getString("c_id"));
	        		group.setName(rs.getString("name"));
	        		group.setImage("images/headImage.png");
	        		if(!rs.getString("size").equals("")) {
		        		group.setSize(Integer.parseInt(rs.getString("size")));
	        		}
	        		if(!"".equals(rs.getString("c_date"))) {
	        			Date date = new Date(rs.getDate("c_date").getTime());
		        		group.setC_date(date);
	        		}
	        		if(!"".equals(rs.getString("image"))) {
						group.setImage(rs.getString("image"));
	        		}
                }
	        	return group;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<User> getGroupPerson3(int id){
		Group group = null ;
		User user = null;
		ArrayList<User> list = new ArrayList<User>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			group = new Group();
			PreparedStatement pstmt;
			String sql = "select * from t_g_person where g_id = '"+id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		user = new User();
	        		user.setId(rs.getString("u_id"));
	        		list.add(user);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;
	}
	
	/**
	 * 根据群id返回该群所有User
	 * @param id
	 * @return
	 */
	public ArrayList<User> getGroupPerson(int id){
		User user = null;
		ArrayList<User> list = new ArrayList<User>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_g_person a,t_user b where g_id="+id+" and b.id = a.u_id";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		user = new User();
	        		if(!"".equals(rs.getString("u_id"))){
	        			user.setId(rs.getString("u_id"));
	        		}
	        		if(!"".equals(rs.getString("nick_name"))){
	        			user.setNickName(rs.getString("nick_name"));
	        		}
	        		if(!"".equals(rs.getString("head_image"))){
	        			user.setHeadImage(rs.getString("head_image"));
	        		}
	        		list.add(user);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;
	}
	
	/**
	 * 根据我的id返回我所有的群
	 * @return
	 */
	public ArrayList<MyGroup> getMyGroupsByMyId(String id){
		ArrayList<MyGroup> list = new ArrayList<MyGroup>();
		MyGroup group = null;
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql ="select * from t_g_person a,t_group b where a.u_id = '"+id+"' and a.g_id = b.id";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		group = new MyGroup();
	        		group.setId(Integer.parseInt(rs.getString("g_id")));
	        		if(rs.getString("image")!=null) {
	        			group.setImage(rs.getString("image"));
	        		}
	        		group.setImage(rs.getString("image"));
	        		group.setName(rs.getString("name"));
        			group.setC_id(rs.getString("c_id"));
	        		String sql1 = "select * from t_g_message where g_id = "+group.getId()+" order by date desc";
	        		System.out.println("sql1"+sql1);
	        		pstmt = conn.prepareStatement(sql1);
	        		rs2 = pstmt.executeQuery();
	        		if(rs2.next()) {
	        			System.out.println("sql1=="+sql1);
	        			group.setN_message(rs2.getString("content"));
	        	        try {  
	        	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        	            java.util.Date parseUtilDate = sdf1.parse(rs2.getString("date"));  
	        	            group.setN_m_date(parseUtilDate.getHours()+":"+parseUtilDate.getMinutes());
	        	        } catch (ParseException e) {  
	        	            e.printStackTrace();  
	        	        }  
	        		}
	        		list.add(group);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
			
		}
		
		return null;
	}
	
	/**
	 * 根据群id删除群和群成员 群成员不需要操作
	 * @param id
	 * @return
	 */
	public boolean deleteGroup(int id) {
		 DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "delete from t_group where id = " + id;
		     try {
                pstmt = conn.prepareStatement(sql);

                int result = pstmt.executeUpdate();
                if (result > 0)
                    return true;
                else
                    return false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

	        } else {
	            return false;
	        }
	}
	
	/**
	 * 插入一个群组并返回是否插入成功
	 * @param user
	 * @return
	 */
	public boolean insertGroup(Group group) {
		 DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_group(id,name,c_id,size,c_date,image) values(?,?,?,?,?,?)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  group.getId()+"");
                pstmt.setString(2,  group.getName());
                pstmt.setString(3,  group.getC_id()+"");
                pstmt.setString(4, group.getSize()+"");
                java.sql.Date  sqlDate = new java.sql.Date(group.getC_date().getTime());  
                pstmt.setDate(5, sqlDate);
           
                pstmt.setString(6, group.getImage());
                int result = pstmt.executeUpdate();
                if (result > 0)
                    return true;
                else
                    return false;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

	        } else {
	            return false;
	        }
	}
	
	public boolean insertListPersonToGroup(ArrayList<String> list ,int id) {
		for(String o :list) {
			if(insertPersonToGroup(o,id)) {
				
			}else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将一个用户插入到群——人表中并返回是否插入成功
	 * @param user
	 * @param g_id
	 * @return
	 */
	public boolean insertPersonToGroup(String u_id,int g_id) {
		if(isLiveGroup(g_id,u_id)) {	//用户已存在
			return false;
		}
		DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_g_person (u_id,g_id) values(?,?)";
		     try {
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, u_id);
               pstmt.setString(2,  g_id+"");
               int result = pstmt.executeUpdate();
               if (result > 0)
                   return true;
               else
                   return false;
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }

	        } else {
	            return false;
	        }
	}

	/**
	 * 
	 * 根据id查找Group并返回是否存在 
	 * @param id
	 * @return
	 */
	public boolean searchGroup(int id) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_group where id = "+id;
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		return true;
                }
	        	return false;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return false;
			}

		}
        return false;
		
	}
	
	/**
	 * 移除群组中的成员
	 * @param g_id
	 * @param t_id
	 * @return
	 */
	public boolean movePersontoGroup(String g_id,String t_id) {
		DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "delete from t_g_person where g_id = " + g_id +" and u_id = '"+t_id+"'";
		     try {
               pstmt = conn.prepareStatement(sql);

               int result = pstmt.executeUpdate();
               if (result > 0)
                   return true;
               else
                   return false;
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }

	        } else {
	            return false;
	       }
	}

	public boolean isLiveGroup(int g_id,String id) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_g_person where g_id ="+g_id+" and u_id ='"+id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		return true;
                }
	        	return false;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return false;
			}

		}
        return false;
		
	}
}
