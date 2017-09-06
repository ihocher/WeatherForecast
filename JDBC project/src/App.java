import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	private static final String url = "jdbc:mysql://localhost:3306/test2";
	private static final String user = "root";
	private static final String password = "4321qwert";
	
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;
	
	

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		String query = "select count(*) from student";
		
		try{
			
			con = DriverManager.getConnection(url, user, password);
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				int count = rs.getInt(1);
				System.out.println("Total number of students: " + count);
				
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try { con.close(); } catch(SQLException se) { System.out.println("An error occured. Bad connection"); }
			try { stmt.close(); } catch(SQLException se) { System.out.println("An error occured. Trables with stmt"); }
			try { rs.close(); } catch(SQLException se) { System.out.println("An error occured. Trables with rs"); }
		}

	}

}
