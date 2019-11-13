package DataBase.Table;
import DataBase.Connection.*;
import java.util.*;

public interface ITable extends IDataBaseConnection
{
	public boolean CreateTable();
	public boolean SaveData();
	public boolean UpdateData();
	public boolean DeleteData();
	public boolean GetData(int id);
	public ArrayList<String> GetDataList();
}