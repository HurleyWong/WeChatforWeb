package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ov.Article;
import ov.ArticleReview;
import ov.User;
import util.DatabaseUtil;

public class ArticleReviewDao{

	/**
	 * 将文章评论数据插入到数据库
	 * @param articleReview
	 * @return
	 */
	public boolean insertReview(ArticleReview articleReview){

		User user=null;
		Article article=null;

		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		if(conn!=null){
			PreparedStatement pstmt;
			String sql="insert into t_a_review (c_id,content) values(?,?)";
			try{
				pstmt=conn.prepareStatement(sql);
				//pstmt.setString(1,articleReview.getA_id());
				pstmt.setString(1,articleReview.getC_id());
				pstmt.setString(2,articleReview.getContent());

				int result=pstmt.executeUpdate();
				if(result>0){
					return true;
				}
				else{
					return false;
				}
			}catch(SQLException e){
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		else{
			return false;
		}
	}
}
