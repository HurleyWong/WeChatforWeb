package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import ov.Like;
import ov.Moment;
import ov.Review;
import util.DatabaseUtil;


public class ReviewDao {

	public boolean saveReview(Review review) {
		
		DatabaseUtil util = new DatabaseUtil();
		Connection conn = util.getConnection();
		if (conn != null) {
			PreparedStatement pstmt;
			String sql = "";
			try {
				
				 	//新增数据
					sql = "insert into t_review (m_id, r_id ,content ,date) values (?, ? ,? ,?)";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,  review.getM_id());
					pstmt.setString(2,  review.getR_id());
					pstmt.setString(3,  review.getContent());
					pstmt.setDate(4,  (java.sql.Date)review.getDate());
					
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

	public List<List<Review>> listReview(List<Moment> momentList) {
		
		List<List<Review>> list = new Vector<List<Review>>();
		Review review = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DatabaseUtil util = new DatabaseUtil();
		try {
			
			conn = util.getConnection();
			if (conn != null) {
				for(int i=0;i<momentList.size();i++){
				List<Review> reviewlist = new Vector<Review>();
				String sql = "select * from t_review where m_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, momentList.get(i).getId());
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					review = new Review();
					review.setId(rs.getInt("id"));
					review.setM_id(rs.getInt("m_id"));
					review.setR_id(rs.getString("r_id"));
					review.setContent(rs.getString("content"));
					review.setDate(rs.getDate("date"));
					reviewlist.add(review);
				}
				list.add(reviewlist);
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
