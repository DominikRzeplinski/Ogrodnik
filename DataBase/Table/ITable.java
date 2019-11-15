package dataBase.table;
import dataBase.connection.*;
import java.util.*;

/** intefejs dostepowy do tabeli
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public interface ITable extends IDataBaseConnection
{
/** metoda Tworzy tabelę.
 *@return true jeśli operacja poprawna
 */
	public boolean CreateTable();
/** metoda Zapisuje dane do tabeli.
 *@return true jeśli operacja poprawna
 */
	public boolean SaveData();
/** metoda aktualizuję dane w tabeli.
 *@return true jeśli operacja poprawna
 */
	public boolean UpdateData();
/** metoda usuwa dane z tabeli.
 *@return true jeśli operacja poprawna
 */
	public boolean DeleteData();
/** metoda pobiera rekord z tabeli
 *@return true jeśli operacja poprawna
 *@param id rekordu
 */
	public boolean GetData(int id);
/** metoda pobiera liste z tabeli 
 *@return vector rekordow
 *@param <T> object
 */
	public <T> Vector<T> GetDataList();
}