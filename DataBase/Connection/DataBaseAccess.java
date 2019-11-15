package DataBase.Connection;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/** klasa dostepowa do bazy danych access
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class DataBaseAccess implements IDataBaseConnection{
	/**Obiekt reprezentuje polaczenie z baza danych*/
	public static Connection connection;
	
/** konstruktor klasy dostepowej, sprawdza czy sterowniki sa dostępne.
 */
	public DataBaseAccess(){ 
		try {
            // Retrieve class by name.
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException exception) {
             //Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		
	}
	
/** metoda tworzy baze danych Ogrodnik.mdb.
 */
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
	
/** metoda otwiera polaczenia z baza.
 */
	public void SetConnection(){
		try {
			connection = DriverManager.getConnection("jdbc:ucanaccess://Ogrodnik.mdb",
			"sa",
			"");
        } catch (SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
	}
	
/** metoda zamyka polaczenia z baza.
 */
	public void CloseConnection(){
		try {
			connection.close();
        } catch (SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
	}
	
}