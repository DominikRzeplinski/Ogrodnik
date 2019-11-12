package DataBase.Connection;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DataBaseAccess implements IDataBaseConnection{
	public static Connection connection;
	
	public DataBaseAccess(){ 
		try {
            // Retrieve class by name.
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException exception) {
             //Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		
	}
	
	public static void CreateDataBaseAccess(){
		try {
			connection = DriverManager.getConnection("jdbc:ucanaccess://Ogrodnik.mdb" + ";newdatabaseversion=V2010",
			"sa",
			"");
			connection.close();
        } catch (SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
	}
	
	public void SetConnection(){
		try {
			connection = DriverManager.getConnection("jdbc:ucanaccess://TEST.mdb",
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