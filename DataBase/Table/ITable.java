package DataBase.Table;
import DataBase.Connection.*;

public interface ITable extends IDataBaseConnection
{
	public int CreateTable();
	public int SaveData();
	public int UpdateData();
	public int DeleteData();
	public int GetData();
}