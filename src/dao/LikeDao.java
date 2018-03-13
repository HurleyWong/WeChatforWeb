package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Like;
import ov.Moment;
import ov.User;
import util.DatabaseUtil;


public class LikeDao {

	public boolean saveLike(Like like) {
		System.out.println(like.getC_id()+"savelike");
		System.out.println(like.getM_id()+"savelike");
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		if (conn != null) {
			PreparedStatement pstmt1,pstmt2;
			String sql = "";
			try {   
				    ResultSet rs = null;
				    sql = "select * from t_like where c_id = ? and  m_id = ?";
				    pstmt1 = conn.prepareStatement(sql);
				    pstmt1.setString(1, like.getC_id());
				    pstmt1.setInt(2, like.getM_id());
				    System.out.println(sql);
				    rs = pstmt1.executeQuery();
				    System.out.println(rs);
				    if(rs.next()){
				    	//删除一行记录
				    	sql = "delete from t_like where c_id = ? and  m_id = ?";				    	
				    	pstmt2 = conn.prepareStatement(sql);
				    	pstmt2.setString(1,  like.getC_id());
						pstmt2.setInt(2,  like.getM_id());
						System.out.println(sql);					    
				    }else{
				    	//增加一行记录
				    	sql = "insert into t_like (c_id, m_id) values (?, ?)";					    
					    pstmt2 = conn.prepareStatement(sql);
					    pstmt2.setString(1,  like.getC_id());
					    pstmt2.setInt(2,  like.getM_id());
					    System.out.println(sql);
				    }
				int result = pstmt2.executeUpdate();
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

	public List<List<Like>> listLike(List<Moment> momentList) {
		List<List<Like>> list = new Vector<List<Like>>();
		Like like = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				for(int i=0;i<momentList.size();i++){
				List<Like> likelist = new Vector<Like>();
				String sql = "select * from t_like where m_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, momentList.get(i).getId());
				System.out.println(sql);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					like = new Like();
					like.setId(rs.getInt("id"));
					like.setC_id(rs.getString("c_id"));
					like.setM_id(rs.getInt("m_id"));
					likelist.add(like);
				}
				list.add(likelist);
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
