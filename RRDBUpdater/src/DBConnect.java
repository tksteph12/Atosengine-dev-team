import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {

	// JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/default";

   public static Connection connectTo(String url,String user,String pwd){
		Connection conn = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn= DriverManager.getConnection(url,user,pwd);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connection Established!..............");
		return conn;
		
	}
}
