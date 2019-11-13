package Ogrod.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import ViewHelper.*;

public class OgrodModel extends DataBaseAccess implements ITable
{
	private static String dbTableName = "Ogrod"; 
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public int GetId(){return id;}
	public String GetOpis(){return opis;}
	public void SetNazwa(String nazwa){this.nazwa = nazwa;}
	public void SetOpis(String opis){this.opis = opis;}
	public OgrodModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
	}
	private void Reset(){
		this.id=0;
		this.nazwa=" ";
		this.opis=" ";
	}
	
	public boolean DeleteData() {
		SetConnection();
		try{
			String deleteOgrod = "DELETE FROM Ogrod WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(deleteOgrod);
			ps.setInt(1,this.id);
			ps.executeUpdate();
			Reset();
		}catch(SQLException exception) 
		{
           // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		support.firePropertyChange("Ogrod", "A", "B");
		CloseConnection();
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertOgrod = "INSERT INTO Ogrod (Name, Opis) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(insertOgrod,Statement.RETURN_GENERATED_KEYS);
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
	
	public void Update() {
		if (id > 0){
			UpdateData();
		}
		else{
			SaveData();
		}
		support.firePropertyChange("Ogrod", "A", "B");
	}
	
	public boolean UpdateData() 
	{
		SetConnection();
		try{
			String query = "SELECT * FROM Ogrod WHERE id = ?";
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
		SetConnection();
		int temp = this.id;
		Reset();
		try{
			String query = "SELECT * FROM Ogrod WHERE id = ?";
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
		support.firePropertyChange("OgrodGetData", "a", "b");
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
		int ret =0;
		try{
			Statement statement = connection.createStatement();
			String createOgrod = "CREATE TABLE [Ogrod] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
				"Name VARCHAR(50) CONSTRAINT c_Name UNIQUE, " +
				"Opis VARCHAR(256)) ";
				//"idGleba INT NULL CONSTRAINT fk_Gleba REFERENCES [Gleba](Id)) ";
			ret = statement.executeUpdate(createOgrod);
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
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
			String query = "SELECT * FROM Ogrod";
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
}