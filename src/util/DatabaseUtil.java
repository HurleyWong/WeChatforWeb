package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DatabaseUtil {
		public Connection getConnection() {
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String url = "jdbc:mysql://localhost:3306/web_classdesign?useUnicode=true&characterEncoding=utf8";
				conn = DriverManager.getConnection(url, "root", "123456");
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}

		public void freeConnection(Connection conn) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
}
