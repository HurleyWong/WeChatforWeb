package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.catalina.User;

import ov.Account;
import util.DatabaseUtil;

public class AccountDao{

	public List<Account> listAccount(){
		List<Account> list=new Vector<Account>();
		Account account=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		DatabaseUtil util=new DatabaseUtil();
		try{
			conn=util.getConnection();
			if(conn!=null){
				String sql="select * from t_account order by id desc";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					account=new Account();
					account.setId(rs.getString("id"));
					account.setAccount_name(rs.getString("account_name"));
					account.setC_id(rs.getString("c_id"));
					account.setIntroduction(rs.getString("introduction"));
					list.add(account);
				}
			}
			else{
				System.out.println("数据库连接失败！");
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
		return list;

	}

	/**
	 * 根据公众号id查询
	 * @param id
	 * @return
	 */
	public Account getAccountById(String id){
		Account account=null;
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		if(conn!=null){
			account=new Account();
			PreparedStatement pstmt;
			String sql="select * from t_account where id='"+id+"'";
			System.out.println(sql);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					account.setId(id);
					account.setAccount_name(rs.getString("account_name"));
					if(rs.getString("introduction")!=null){
						account.setIntroduction("introduction");
					}
				}
			}catch(SQLException e){
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		return account;
	}

	/**
	 * 根据公众号名称查询
	 * @param account_name
	 * @return
	 */
	public Account getAccountByName(String account_name){
		Account account=null;
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		String id=null;
		if(conn!=null){
			account=new Account();
			PreparedStatement pstmt;
			String sql="select * from where account_name='"+account_name+"'";
			System.out.println(sql);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					account.setId(id);
					account.setAccount_name("account_name");
					if(rs.getString("introduction")!=null){
						account.setIntroduction("introduction");
					}
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		return account;
	}

	/**
	 * 将公众号数据插入数据库
	 * @param account
	 * @return
	 */
	public boolean insertAccount(Account account){
		User user=null;

		System.out.println(account.toString());

		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		if(conn!=null){
			PreparedStatement pstmt;
			String sql="insert into t_account (id,account_name,c_id,introduction) values(?,?,?,?)";
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,account.getId());
				pstmt.setString(2,account.getAccount_name());
				pstmt.setString(3,account.getC_id());
				//pstmt.setString(3,account.getDate());
				pstmt.setString(4,account.getIntroduction());
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
		else{
			return false;
		}
	}

	/**
	 * 查询公众号
	 * @param id
	 * @return
	 */
	public boolean search(String id){
		DatabaseUtil util=new DatabaseUtil();
		Connection conn=util.getConnection();
		ResultSet rs=null;
		if(conn!=null){
			PreparedStatement pstmt;
			String sql="select * from t_account where id='"+id+"'";
			System.out.println(sql);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					return true;
				}
				return false;
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}










