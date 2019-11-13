package Roslina.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import Gleba.model.*;
import java.util.*;

public class RoslinaModel extends DataBaseAccess implements ITable
{
	public GlebaModel gleba; 
	private static String dbTableName = "Roslina"; 
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public void SetNazwa(String nazwa){this.nazwa = nazwa;}
	public void SetOpis(String opis){this.opis = opis;}
	public RoslinaModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
		gleba = new GlebaModel();
	}
	
	public boolean DeleteData() {
		try{
			String deleteRoslina = "DELETE FROM Roslina WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(deleteRoslina);
			ps.setInt(1,this.id);
			ps.executeUpdate();
		}catch(SQLException exception) 
		{
           // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertRoslina = "INSERT INTO Roslina (Name, Opis) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(insertRoslina,Statement.RETURN_GENERATED_KEYS);
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
		support.firePropertyChange("Roslina", "", "");
		if (id > 0){
			UpdateData();
		}
		else{
			SaveData();
		}
	}
	
	public boolean UpdateData() 
	{
		SetConnection();
		try{
			String query = "SELECT * FROM Roslina WHERE id = ?";
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
		try{
			String query = "SELECT * FROM Roslina WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				this.nazwa = rs.getString("Name");
				this.opis = rs.getString("Opis");
				this.id = rs.getInt("Id");
				this.gleba.GetData(rs.getInt("idGleba"));
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
		int ret =0;
		try{
			Statement statement = connection.createStatement();
			String createRoslina = "CREATE TABLE [Roslina] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
				"Name VARCHAR(50) CONSTRAINT c_Name UNIQUE, " +
				"Opis VARCHAR(256), "+
				"idGleba INT NULL CONSTRAINT fk_Gleba REFERENCES [Gleba](Id)) ";
			ret = statement.executeUpdate(createRoslina);
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return true;
	}
	
	public ArrayList<String> GetDataList()
	{
		ArrayList<String> List = new ArrayList<String>();
		SetConnection();
		try{
			String query = "SELECT * FROM Roslina";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				List.add(rs.getString("Name"));
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return List;
	}
}