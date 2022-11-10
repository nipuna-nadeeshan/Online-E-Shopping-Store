package SQLDriver;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriver {
	private static Connection conn;

	public static Connection getConnection() {
		if(conn == null) {
			String url = "jdbc:mysql://localhost:3306/estore";
			String user = "root";
			String pass = "neo2525";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url,user,pass);
				return conn;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			
		}
		return conn;
	}
	
}
