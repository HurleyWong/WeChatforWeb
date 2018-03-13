package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ov.*;
import util.DatabaseUtil;

public class MessageDao {

	/**
	 * 根据群id取得该群所有消息
	 * @param m_id
	 * @param g_id
	 * @return
	 */
	public MyG_Message getGroupMessageByG_Id(String g_id){
		MyG_Message m_group_message = null ;
		P_Message p_message = null;
		ArrayList<User> list = new ArrayList<User>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		ArrayList<Integer> headImage = null;
		if (conn != null) {
			m_group_message = new MyG_Message();
			PreparedStatement pstmt;
			String sql = "select * from t_group where id = '"+g_id+"'";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	if(rs.next()) {
	        		m_group_message.setId(Integer.parseInt(rs.getString("id")));
	        		m_group_message.setName(rs.getString("name"));
	        		m_group_message.setC_date(null);
	        	}
	        	sql = "select * from t_g_message a,t_user b where a.g_id = "+g_id+" and b.id = a.c_id order by date";
	        	pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		p_message = new P_Message();
	        		p_message.setId(rs.getString("c_id"));
	        		p_message.setContent(rs.getString("content"));
	        		p_message.setHeadImage(rs.getString("head_image"));
	        		p_message.setNickName(rs.getString("nick_name"));
//	        		p_message.setC_date(rs.getString("date"));
	        		try {  
        	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	            java.util.Date parseUtilDate = sdf1.parse(rs.getString("date"));  
        	            System.out.println("parseUtilDate:"+parseUtilDate);  
        	            System.out.println(" getMyTime = "+parseUtilDate.getHours()+":"+parseUtilDate.getMinutes());
        	            p_message.setC_date(parseUtilDate);
        	        } catch (ParseException e) {  
        	            e.printStackTrace();  
        	        } 
	        		
	        		m_group_message.getList().add(p_message);
                }
	        	return m_group_message;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;

	}

	/**
	 * 将一条消息存入数据库
	 * @param m_id
	 * @param g_id
	 * @param content
	 * @return
	 */
	public boolean addGroupMessage(String m_id,String g_id,String content) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		 if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_g_message ( g_id,c_id,content,date) values (?,?,?,?)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,g_id);
                pstmt.setString(2,m_id);
                pstmt.setString(3,content);
        		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		java.util.Date date = new java.util.Date();
        		pstmt.setString(4,sdf1.format(date));
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
	 * 将一条消息存入数据库 
	 * @param m_id
	 * @param t_id
	 * @param content
	 * @return 
	 */
	public boolean addPersonMessage(String m_id,String t_id,String content) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		 if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_p_message ( c_id,t_id,content,date) values (?,?,?,?)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,m_id);
                pstmt.setString(2,t_id);
                pstmt.setString(3,content);
        		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		java.util.Date date = new java.util.Date();
        		pstmt.setString(4,sdf1.format(date));
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

	public ArrayList<P_Message> getPersonMessageByP_Id(String id,String m_id) {
		MyG_Message m_group_message = null ;
		P_Message p_message = null;
		ArrayList<P_Message> list = new ArrayList<P_Message>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			m_group_message = new MyG_Message();
			PreparedStatement pstmt;
			String sql = "select * from t_p_message where (c_id ='"+id+"' and t_id ='"+m_id+"') or (c_id='"+m_id+"' and t_id='"+id+"') order by date ";
			System.out.println(sql);
			try {
	        	pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		p_message = new P_Message();
	        		p_message.setId(rs.getString("c_id"));
	        		p_message.setContent(rs.getString("content"));
	        		p_message.setHeadImage("images/headImage.png");
	        		UserDao dao = new UserDao();
	        		User user = dao.getUserById(id);
	        		p_message.setNickName(user.getNickName());
	        		try {  
        	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	            java.util.Date parseUtilDate = sdf1.parse(rs.getString("date"));  
        	            System.out.println("parseUtilDate:"+parseUtilDate);  
        	            System.out.println(" getMyTime = "+parseUtilDate.getHours()+":"+parseUtilDate.getMinutes());
        	            p_message.setC_date(parseUtilDate);
        	        } catch (ParseException e) {  
        	            e.printStackTrace();  
        	        } 
	        		list.add(p_message);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;
	}

}
