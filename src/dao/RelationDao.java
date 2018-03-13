package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Relation;
import util.DatabaseUtil;

public class RelationDao {
	public List<Relation> listFriend(String id) {
		List<Relation> list = new Vector<Relation>();
		Relation relation = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
try {
			
			conn = util.getConnection();
			if (conn != null) {
				String sql = "select * from t_relation where m_id = '"+id+"'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					relation = new Relation();
					relation.setId(rs.getInt("id"));
					relation.setM_id(rs.getString("m_id"));
					relation.setF_id(rs.getString("f_id"));					
					relation.setDate(rs.getDate("date"));
					relation.setType(rs.getString("type"));
					relation.setRemark_name(rs.getString("remark_name"));
					list.add(relation);
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
