package DataBase.Connection;
import java.sql.*;

/** interfejs dla baz danych
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public interface IDataBaseConnection{
	
/** metoda otwiera polaczenia z baza.
 */
	public void SetConnection();
/** metoda zamyka polaczenia z baza.
 */
	public void CloseConnection();
	
}