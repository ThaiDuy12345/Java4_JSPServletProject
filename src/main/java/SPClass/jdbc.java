package SPClass;
import java.sql.*;
public class jdbc {
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_PS16703";
    private static String username = "sa";
    private static String password = "";
    public static PreparedStatement getStatement(String sql, Object...ags) throws SQLException {
    	try {
    		Class.forName(driver);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	Connection con = DriverManager.getConnection(url, username, password);
    	PreparedStatement ps = con.prepareStatement(sql);
    	for(int i = 0;i < ags.length;i++) {
    		ps.setObject(i+1, ags[i]);
    	}
    	return ps;
    }
    public static int executeUpdate(String sql, Object...ags) {
    	int result = 0;
    	try {	
    		result = getStatement(sql, ags).executeUpdate();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return result;	
    }
    public static ResultSet executeQuery(String sql, Object...ags) {
    	ResultSet rs = null;
    	try {
    		rs = getStatement(sql, ags).executeQuery();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return rs;
    }
}
