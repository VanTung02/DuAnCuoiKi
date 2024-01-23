package quanliphongtro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;
import com.mysql.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection(){
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			try {
				String url = "jdbc:mysql://localhost:3306/quanliphongtrotest?";
				String user = "root";
				String password = "";
				c = DriverManager.getConnection(url, user, password);				 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		}
		return c;
	}
	


	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		System.out.println(connection);
	}
}