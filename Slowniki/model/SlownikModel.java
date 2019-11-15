package slowniki.model;
import java.beans.*;
import dataBase.table.ITable;
import dataBase.connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import viewHelper.*;

public abstract class SlownikModel extends DataBaseAccess implements ITable
{
	public abstract String GetTableName();
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	public boolean IsNew(){return (this.id ==0);}
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public SlownikModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
	}
	
	public boolean DeleteData() {
		SetConnection();
		try{
			String deleteSlownik = "DELETE FROM "+GetTableName()+" WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(deleteSlownik);
			ps.setInt(1,this.id);
			ps.executeUpdate();
		}catch(SQLException exception) 
		{
           // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		support.firePropertyChange(GetTableName(), "A", "B");
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertSlownik = "INSERT INTO "+GetTableName()+" (Name, Opis) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(insertSlownik,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,this.nazwa);
			ps.setString(2,this.opis);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				this.id = rs.getInt(1);
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return true; 
	}
	
	public void Update(String nazwa, String opis) {
		String temp = this.nazwa;
		this.nazwa = nazwa;
		this.opis = opis;
		if (id > 0){
			UpdateData();
		}
		else{
			SaveData();
		}
		support.firePropertyChange(GetTableName(), "A", "B");
	}
	
	public boolean UpdateData() 
	{
		SetConnection();
		try{
			String query = "SELECT * FROM "+GetTableName()+" WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1,this.id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				rs.updateString("Name", this.nazwa);
				rs.updateString("Opis", this.opis);
				rs.updateRow();
			}
		}catch(SQLException exception) 
		{
           // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return true; 
	}
	
	public boolean GetData(int id) {
		this.nazwa = "";
		this.opis = "";
		this.id = 0;
		SetConnection();
		try{
			String query = "SELECT * FROM "+GetTableName()+" WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				this.nazwa = rs.getString("Name");
				this.opis = rs.getString("Opis");
				this.id = rs.getInt("Id");
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return true;
	}
 
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
	
	public boolean CreateTable(){	
		SetConnection();
		if (!GetTableName().equals(""))
		{
			int ret =0;
			try{
				Statement statement = connection.createStatement();
				String createGleba = "CREATE TABLE [" + GetTableName() +"] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
					"Name VARCHAR(50) CONSTRAINT c_Name UNIQUE, " +
					"Opis VARCHAR(256))";
				ret = statement.executeUpdate(createGleba);
			}catch(SQLException exception) {
				// Output exception ClassNotFoundExceptions.
				System.out.print(exception.toString());
			}
		}
		CloseConnection();
		return true;
	}
	
	public Vector<ComboBoxItem> GetDataList()
	{
		Vector<ComboBoxItem> List = new Vector<ComboBoxItem>();
		List.add(new ComboBoxItem(0," "));
		SetConnection();
		try{
			String query = "SELECT * FROM " + GetTableName();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				List.add(new ComboBoxItem(rs.getInt("Id"),rs.getString("Name")));
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return List;
	}
	
	public Vector<String> GetDataTableColumnsNames(){
		Vector<String> names = new Vector<String>(); 
		names.add("Id");
		names.add("Nazwa");
		names.add("Opis");
		return names;
	}
	
	public Vector<Vector<String>> GetDataTableList()
	{
		Vector<Vector<String>> list = new Vector<Vector<String>>();
		SetConnection();
		try{
			String query = "SELECT * FROM " + GetTableName();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				Vector<String> row = new Vector<String>();
				row.add(String.valueOf(rs.getInt("Id")));
				row.add(rs.getString("Name"));
				row.add(rs.getString("Opis"));
				list.add(row);
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return list;
	}
}