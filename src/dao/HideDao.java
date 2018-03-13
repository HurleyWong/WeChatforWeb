package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Hide;
import ov.Moment;
import util.DatabaseUtil;


public class HideDao {

	public boolean saveHide(Hide hide) {
		
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "";
			try {
				
				 	//新增数据
					sql = "insert into t_hide (c_id, t_id) values (?, ?)";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,  hide.getC_id());
					pstmt.setString(2,  hide.getT_id());
					
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

	public List<Hide> listHide(String mid) {
		List<Hide> list = new Vector<Hide>();
		Hide hide = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select * from t_hide where t_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					hide = new Hide();
					hide.setId(rs.getInt("id"));
					hide.setC_id(rs.getString("c_id"));
					hide.setT_id(rs.getString("t_id"));
					list.add(hide);
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
