package dao;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import ov.Moment;
import ov.Myfriend;
import ov.User;
import util.DatabaseUtil;

/**
 * 关于User的数据库操作
 * @author jack
 *
 */
public class UserDao {
	
	/**
	 * 	通过User的id得到一个User实例
	 * @param id
	 * @return
	 */
	public User getUserById(String id) {
		User user = null ;
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			user = new User();
			PreparedStatement pstmt;
			String sql = "select * from t_user where id = '"+id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		user.setId(id);
	        		user.setNickName(rs.getString("nick_name"));
	        		user.setPassword(rs.getString("password"));
	        		if(rs.getString("real_name")!=null) {
	        			user.setRealName(rs.getString("real_name"));
	        		}
	        		if(rs.getString("head_image")!=null) {
/*	        			try {
							BufferedInputStream in = new BufferedInputStream(new FileInputStream(rs.getString("head_image")));
							int i;
							headImage = new ArrayList<Integer>();
							while((i=in.read())!=-1){ 
								headImage.add(i);
							}*/
							user.setHeadImage(rs.getString("head_image"));
/*						} catch (IOException e) {
							System.out.println("UserDao 读取头像异常");
							e.printStackTrace();
						}*/
	        		}
	        		if(rs.getString("age")!=null) {
		        		user.setAge(Integer.parseInt(rs.getString("age")));
	        		}
	        		if(rs.getString("gender")!=null) {
	        			user.setGender(Integer.parseInt(rs.getString("gender")));
	        		}
	        		if(rs.getString("birthday")!=null) {
	        			Date date = new Date(rs.getDate("birthday").getTime());
	        			user.setBirthday(date);
	        		}
	        		if(rs.getString("phone_number")!=null) {
	        			user.setPhoneNumber(rs.getString("phone_number"));
	        		}
	        		if(rs.getString("signature")!=null) {
	        			user.setSignature(rs.getString("signature"));
	        			
	        		}
	        		user.setBalance(Double.parseDouble(rs.getString("balance")));

                }
	        	return user;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}

		}
		return user;
	}
		
	/**
	 * 插入一个用户并返回是否插入成功
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user) {
		 DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_user (id,nick_name,password,phone_number,head_image,balance) values(?,?,?,?,?,?)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  user.getId());
                pstmt.setString(2,  user.getNickName());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getPhoneNumber());
                pstmt.setString(5, user.getHeadImage());
                pstmt.setDouble(6, user.getBalance());
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
	
	public boolean addFriends(String m_id,String f_id) {
		 DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_relation (m_id,f_id,date,is_black) values(?,?,?,0)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  m_id);
                pstmt.setString(2,  f_id);
                
                Date date = new Date();
                java.sql.Date date2 = new java.sql.Date(date.getTime());
                pstmt.setDate(3, date2);
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
	 * 根据id查找User并返回是否存在 
	 * @param id
	 * @return
	 */
	public boolean search(String id) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_user where id = '"+id+"'";
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
	 * 根据id和password登录
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean login1(String id,String password) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_user where id = '"+id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		User user = new User();
	        		if(password.equals(rs.getString("password"))) {
	        			return true;
	        		}
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
	 * 根据用户名和密码登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login2(String username,String password) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_user where username = '"+username+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if (rs.next()) {
	        		if(password.equals(rs.getString("password"))) {
	        			return true;
	        		}
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
	 * 根据用户名返回一个User实例
	 * @param parameter
	 * @return
	 */
	public User getUserByUserName(String username) {
		return null;
	}
	
	/**
	 * 删除好友
	 * @param m_id
	 * @param f_id
	 * @return
	 */
	public boolean deleteShipByIds(String m_id,String f_id) {
		DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "delete from t_relation where (m_id = '" + m_id +"' and f_id = '"+f_id+"') or (m_id = '" + f_id +"' and f_id = '"+m_id+"')";
		     System.out.println(sql);
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
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public ArrayList<String> getFriendsByFriendsName(String str,String id){
		ArrayList<String> list = new ArrayList<String>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_user a,t_relation b where b.m_id = '" + id +"'  and b.f_id = a.id and a.nick_name like '%"+str+"%' order by convert(a.nick_name using gbk) collate gbk_chinese_ci ";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		list.add(rs.getString("nick_name"));		
                }
	        	sql ="select * from t_group a,t_g_person b where b.u_id = '"+id+"' and a.id = b.g_id and a.name like '%"+str+"%' order by convert(a.name using gbk) collate gbk_chinese_ci ";
	        	System.out.println(sql);
	        	pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		list.add(rs.getString("name"));	
	        	}
	        	System.out.println("搜索到了:"+list.size()+"条记录");
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}

		}
		
		return null;
	}
	
	/**
	 * 根据我的id得到我所有好友实例的集合
	 * @return
	 */
	public ArrayList<Myfriend> getFriedsByMyId(String id){
		ArrayList<Myfriend> list = new ArrayList<Myfriend>();
		Myfriend friend = null ;
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_relation b,t_user a  where b.m_id = '"+id+"' and a.id = b.f_id order by convert(a.nick_name using gbk) collate gbk_chinese_ci";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		friend = new Myfriend();
	        		friend.setId(rs.getString("f_id"));
	        		friend.setHeadImage(rs.getString("head_Image"));
	        		friend.setNickName(rs.getString("nick_name"));
//	        		String sql1 = "select * from t_p_message  where c_id='"+id+"' or t_id='"+id+"'  order by date desc ";
	        		String sql1 = "select * from t_p_message  where (c_id='"+id+"' and t_id='"+friend.getId()+"')or(c_id='"+friend.getId()+"' and t_id='"+id+"')  order by date desc ";
	        		pstmt = conn.prepareStatement(sql1);
	        		rs2 = pstmt.executeQuery();
	        		if(rs2.next()) {
	        			System.out.println("sql=="+sql1);
	        			friend.setN_message(rs2.getString("content"));
	        			System.out.println(rs2.getString("content"));
	        			System.out.println("getDate = "+rs2.getDate("date"));
	        			System.out.println("getString = "+rs2.getString("date"));
	        	        try {  
	        	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        	            Date parseUtilDate = sdf1.parse(rs2.getString("date"));  
	        	            System.out.println("parseUtilDate:"+parseUtilDate);  
	        	            System.out.println(" getMyTime = "+parseUtilDate.getHours()+":"+parseUtilDate.getMinutes());
	        	    		friend.setN_m_date(parseUtilDate.getHours()+":"+parseUtilDate.getMinutes());
	        	        } catch (ParseException e) {  
	        	            e.printStackTrace();  
	        	        }  
//	        			Date date = new Date(rs2.getDate("date").getTime());
//	        			System.out.println(rs2.getDate("date"));
//	        			String hour = date.getHours()+":";
//	        			String minute = date.getMinutes()+":";
//	        			String seconds = date.getSeconds()+"";
//	        			System.out.println(rs2.getString(hour+minute+seconds));
	        		}
	        		list.add(friend);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}

		}
		
		return null;
	}
	
	public List<User> listUser(List<Moment> momentList) {
		List<User> list = new Vector<User>();
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				for(int i=0;i<momentList.size();i++){
				String sql = "select * from t_user   where id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, momentList.get(i).getC_id());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					user = new User();
					user.setId(rs.getString("id"));
					user.setNickName(rs.getString("nick_name"));
					user.setPassword(rs.getString("password"));
					user.setRealName(rs.getString("real_name"));
					user.setHeadImage(rs.getString("head_image"));
					user.setAge(rs.getInt("age"));
					user.setGender(rs.getInt("gender"));
					user.setBirthday(rs.getDate("birthday"));
					user.setPhoneNumber(rs.getString("phone_number"));
					user.setSignature(rs.getString("signature"));
					user.setBalance(rs.getDouble("balance"));					
				}
				list.add(user);
				}
			} else {
				System.out.println("数据库连接失败，请检查");
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				util.freeConnection(conn);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return list;
	}
}
