package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Hide;
import ov.Relation;
import ov.Show;
import util.DatabaseUtil;

public class ShowDao {

	public boolean saveShow(List<Relation> list, int m_id) {
		
			DatabaseUtil util = new DatabaseUtil();
			Connection conn = util.getConnection();
			int result1 = 0;
			if (conn != null) {
				PreparedStatement pstmt;
				String sql = "";
				try {
					for (int count=0;count<list.size();count++){
					 	//新增数据
						sql = "insert into t_show (m_id, t_id) values (?, ?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1,  m_id);
						pstmt.setString(2,  list.get(count).getF_id());					
					    int result = pstmt.executeUpdate();	
					    result1 = result1+result;
					}
					if (result1 >0)
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

	public List<Show> listShow(String mid) {
		List<Show> list = new Vector<Show>();
		Show show = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select * from t_show where t_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					show = new Show();
					show.setId(rs.getInt("id"));
					show.setM_id(rs.getInt("m_id"));
					show.setT_id(rs.getString("t_id"));
					list.add(show);
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
