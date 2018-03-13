package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Hide;
import ov.Moment;
import ov.Show;
import util.DatabaseUtil;


public class MomentDao {

	public boolean saveMoment(Moment moment) {
		
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "";
			try {
				
				 	//新增数据
					sql = "insert into t_moment (c_id, image, content, date) values (?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,  moment.getC_id());
					pstmt.setString(2,  moment.getImage());
					pstmt.setString(3, moment.getContent());
					pstmt.setDate(4, (java.sql.Date)moment.getDate());
									
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

	public List<Moment> listMoment() {
		List<Moment> list = new Vector<Moment>();
		Moment moment = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select * from t_moment order by id desc ";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					moment = new Moment();
					moment.setId(rs.getInt("id"));
					moment.setC_id(rs.getString("c_id"));
					moment.setImage(rs.getString("image"));
					moment.setContent(rs.getString("content"));
					moment.setDate(rs.getDate("date"));
					list.add(moment);
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

	public List<Moment> listMyMoment(String id) {
		List<Moment> list = new Vector<Moment>();
		Moment moment = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select * from t_moment where c_id = "+id;
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					moment = new Moment();
					moment.setId(rs.getInt("id"));
					moment.setC_id(rs.getString("c_id"));
					moment.setImage(rs.getString("image"));
					moment.setContent(rs.getString("content"));
					moment.setDate(rs.getDate("date"));
					list.add(moment);
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

	public boolean deteleMoment(int m_id) {
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		if (conn != null) {
			String sql = "delete from t_moment where id = ?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_id);
				System.out.println("sql=" + sql);
				int result = pstmt.executeUpdate();
				if (result > 0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;

	}

	public int getMomentIdByMoment(Moment moment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select id from t_moment where c_id = ? and image = ? and content = ? and date = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  moment.getC_id());
				pstmt.setString(2,  moment.getImage());
				pstmt.setString(3, moment.getContent());
				pstmt.setDate(4, (java.sql.Date)moment.getDate());
				rs = pstmt.executeQuery();
				while(rs.next()){
				     result = rs.getInt(1);
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
		return result;
		
	}

	public List<Moment> getAllMyMoment(String id){
		return null;
	}
}
