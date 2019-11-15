package Roslina.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import Gleba.model.*;
import RodzajeRoslin.model.*;
import PoraSadzenia.model.*;
import java.util.*;
import ViewHelper.*;

public class RoslinaModel extends DataBaseAccess implements ITable
{
	public GlebaModel gleba; 
	public RodzajeRoslinModel rodzajeRoslin; 
	public PoraSadzeniaModel poraSadzenia; 
	public String GetTableName(){return "Roslina";} 
	private PropertyChangeSupport support;
	int id;
	int idGleby;
	int idPoraSadzenia;
	int idRodzaj;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public int GetIdGleby(){return idGleby;}
	public int GetIdRodzaj(){return idRodzaj;}
	public int GetIdPoraSadzenia(){return idPoraSadzenia;}
	public void SetNazwa(String nazwa){this.nazwa = nazwa;}
	public void SetOpis(String opis){this.opis = opis;}
	public void SetIdGleby(int idGleby){this.idGleby = idGleby;}
	public void SetIdRodzaj(int idRodzaj){this.idRodzaj = idRodzaj;}
	public void SetIdPoraSadzenia(int idPoraSadzenia){this.idPoraSadzenia = idPoraSadzenia;}
	public RoslinaModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
		gleba = new GlebaModel();
		rodzajeRoslin = new RodzajeRoslinModel();
		poraSadzenia = new PoraSadzeniaModel();
		Reset();
	}
	
	public boolean IsNew(){
		return (this.id ==0);
	}
	
	private void Reset(){
		this.id=0;
		this.nazwa=" ";
		this.opis=" ";
		this.idGleby=0;
		this.idRodzaj=0;
		this.idPoraSadzenia=0;
	}
	
	public boolean DeleteData() {
		SetConnection();
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
		CloseConnection();
		Reset();
		support.firePropertyChange("Roslina", "A", "B");
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertRoslina = "INSERT INTO Roslina (Name, Opis, idGleba, idRodzajRoslin, idPoraSadzenia) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertRoslina,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,this.nazwa);
			ps.setString(2,this.opis);
			if (this.idGleby != 0)
				ps.setInt(3,this.idGleby);
			else
				ps.setNull(3,Types.INTEGER);
			if (this.idGleby != 0)
				ps.setInt(4,this.idRodzaj);
			else
				ps.setNull(4,Types.INTEGER);
			if (this.idGleby != 0)
				ps.setInt(5,this.idPoraSadzenia);
			else
				ps.setNull(5,Types.INTEGER);
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
		support.firePropertyChange("Roslina", "A", "B");
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
				if (this.idGleby != 0)
					rs.updateInt("idGleba", this.idGleby);
				if (this.idRodzaj != 0)
					rs.updateInt("idRodzajRoslin", this.idRodzaj);
				if (this.idPoraSadzenia != 0)
					rs.updateInt("idPoraSadzenia", this.idPoraSadzenia);
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
		Reset();
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
				this.idGleby = rs.getInt("idGleba");
				this.idRodzaj = rs.getInt("idRodzajRoslin");
				this.idPoraSadzenia = rs.getInt("idPoraSadzenia");
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		this.gleba.GetData(this.idGleby);
		this.rodzajeRoslin.GetData(this.idRodzaj);
		this.poraSadzenia.GetData(this.idPoraSadzenia);
		CloseConnection();
		support.firePropertyChange("Roslina", "A", "B");
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
				"idGleba INT NULL CONSTRAINT fk_Gleba REFERENCES [Gleba](Id), "+
				"idRodzajRoslin INT NULL CONSTRAINT fk_RodzajeRoslin REFERENCES [RodzajeRoslin](Id), "+
				"idPoraSadzenia INT NULL CONSTRAINT fk_PoraSadzenia REFERENCES [PoraSadzenia](Id)) ";
			ret = statement.executeUpdate(createRoslina);
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
			String query = "SELECT * FROM Roslina";
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
			String query = "SELECT * FROM Roslina";
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