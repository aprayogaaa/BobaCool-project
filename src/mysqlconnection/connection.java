package mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
	
	public static Connection connection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bobacool?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
