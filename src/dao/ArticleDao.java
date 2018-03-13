package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.User;

import ov.Account;
import ov.Article;
import util.DatabaseUtil;

public class ArticleDao{

	/**
	 * 查询文章id
	 * @param article
	 * @return
	 */
	public int getArticleIdByArticle(Article article){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		DatabaseUtil util=new DatabaseUtil();
		try{
			conn=util.getConnection();
			if(conn!=null){
				String sql="select id from t_article where a_id=? and title=? and content=? and u_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,article.getA_id());
				pstmt.setString(2,article.getTitle());
				pstmt.setString(3,article.getContent());
				pstmt.setString(4,article.getU_id());
				rs=pstmt.executeQuery();
				while(rs.next()){
					result=rs.getInt(1);
				}
			}
			else{
				System.out.println("连接失败！");
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				util.freeConnection(conn);
			}catch(SQLException sqle){
				// TODO: handle exception
				sqle.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据文章id查询
	 * @param id
	 * @return
	 */
	public Article getArticleById(String id){
		Article article=null;
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		if(conn!=null){
			article=new Article();
			PreparedStatement pstmt;
			String sql="select * from t_article where id='"+id+"'";
			System.out.println(sql);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					article.setA_id(rs.getString("a_id"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					article.setU_id(rs.getString("u_id"));
				}
			}catch(SQLException e){
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		return article;
	}

	/**
	 * 根据标题查询文章
	 * @param title
	 * @return
	 */
	public Article getArticleByTitle(String title){
		Article article=null;
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		if(conn!=null){
			article=new Article();
			PreparedStatement pstmt;
			String sql="select * from where account_title='"+title+"'";
			System.out.println(sql);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					article.setA_id(rs.getString("a_id"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					article.setU_id(rs.getString("u_id"));
				}
			}catch(SQLException e){
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		return article;
	}

	/**
	 * 将文章数据插入到数据库
	 * @param article
	 * @return
	 */
	public boolean insertArticle(Article article){
		User user=null;
		Account account=null;

		System.out.println(" "+article.toString());

		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		if(conn!=null){
			PreparedStatement pstmt;
			String sql="insert into t_article (a_id,title,content,u_id) values(?,?,?,?)";
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,article.getA_id());
				pstmt.setString(2,article.getTitle());
				pstmt.setString(3,article.getContent());
				pstmt.setString(4,article.getU_id());

				int result=pstmt.executeUpdate();
				System.out.println(result);
				if(result>0)
					return true;
				else
					return false;
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * 查询文章
	 * @param title
	 * @return
	 */
	public boolean search(String title){
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		if(conn!=null){
			PreparedStatement pstmt;
			String sql="select * from t_article where title='"+title+"'";
			System.out.println(sql);

			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					return true;
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}











