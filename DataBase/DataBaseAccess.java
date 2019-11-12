package DataBase;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DataBaseAccess{
	private Connection connection;
	
	public DataBaseAccess(){ 
		try {
            // Retrieve class by name.
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException exception) {
             //Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		
	}
	public void SetConnection(){
		try {
			connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Integra/Downloads/Ogrodnik-master/Ogrodnik-master/TEST.mdb",
			"sa",
			"");
        } catch (SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
	}
	
	public void CloseConnection(){
		try {
			connection.close();
        } catch (SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
	}
	
}