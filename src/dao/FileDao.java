package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ov.Mfile;
import ov.User;
import util.DatabaseUtil;

public class FileDao {

	public boolean insertFile(Mfile file) {
		 DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "insert into t_g_file (name,g_id,c_id,date,content,type) values(?,?,?,?,?,?)";
		     try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  file.getName());
                pstmt.setString(2,  file.getG_id());
                pstmt.setString(3, file.getC_id());
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		java.util.Date date = new java.util.Date();
        		pstmt.setString(4,sdf1.format(date));
                pstmt.setString(5, file.getContent());
                pstmt.setString(6, file.getType());
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

	public ArrayList<Mfile> getAllFilesByGroupId(int g_id){
		Mfile file = null;
		ArrayList<Mfile> list = new ArrayList<Mfile>();
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		ResultSet rs = null;
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "select * from t_g_file where g_id= "+g_id +" order by date desc";
			System.out.println(sql);
			try {
				pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		file = new Mfile();
	        		if(!"".equals(rs.getString("name"))){
	        			file.setName(rs.getString("name"));
	        		}
	        		if(!"".equals(rs.getString("c_id"))){
	        			file.setC_id(rs.getString("c_id"));
	        		}
	        		UserDao dao = new UserDao();
	        		User user = dao.getUserById(file.getC_id());
	        		file.setC_name(user.getNickName());
	        		if(!"".equals(rs.getString("date"))){
	        			Date date = new Date(rs.getDate("date").getTime());
	        			file.setC_date(date);
	        		}
	        		if(!"".equals(rs.getString("content"))){
	        			file.setContent(rs.getString("content"));
	        		}
	        		if(!"".equals(rs.getString("type"))){
	        			file.setType(rs.getString("type"));
	        		}
	        		list.add(file);
                }
	        	return list;
	
			} catch (SQLException e) {
		       e.printStackTrace();
		       return null;
			}
		}
		return null;
	}

	public boolean deleteFile(Mfile file) {
		DatabaseUtil util = new DatabaseUtil();
	     Connection conn = util.getConnection();
		     if (conn != null) {
		     PreparedStatement pstmt;
		     String sql = "delete from t_g_file where g_id = " + file.getG_id() +" and id = '"+file.getId()+"'";
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
}
