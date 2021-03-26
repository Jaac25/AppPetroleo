package ConnectBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {	
	public static final String URL = "jdbc:sybase:Tds:localhost:2638";
	public static final String USERNAME = "DBA";
	public static final String PASSWORD = "sql";
	public static Connection getConection() {
		Connection con = null;
		try {
			Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
			con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			System.out.println("Conexión exitosa");
		}catch (Exception e) {
			System.out.println("error: "+e);
		}
		return con;
	}
}

