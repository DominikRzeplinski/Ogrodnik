package OgrodRosliny.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import Roslina.model.*;
import java.util.*;
import ViewHelper.*;

public class OgrodRoslinyModel extends DataBaseAccess implements ITable
{
	public RoslinaModel roslina; 
	public String GetTableName(){return "OgrodRosliny";} 
	private PropertyChangeSupport support;
	int id;
	int idRosliny;
	int idOgrodu;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public int GetIdRosliny(){return idRosliny;}
	public void SetIdRosliny(int idRosliny){this.idRosliny = idRosliny;}
	public OgrodRoslinyModel(int idOgrodu){
		this.idOgrodu = idOgrodu;
		CreateTable();
        support = new PropertyChangeSupport(this);
		roslina = new RoslinaModel();
		Reset();
	}
	
	public boolean IsNew(){
		return (this.id ==0);
	}
	
	private void Reset(){
		this.id=0;
		this.nazwa=" ";
		this.opis=" ";
		this.idRosliny=0;
	}
	
	public boolean DeleteData() {
		SetConnection();
		try{
			String deleteRoslina = "DELETE FROM OgrodRosliny WHERE id = ?";
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
		support.firePropertyChange("OgrodRosliny", "A", "B");
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertOgrodRosliny = "INSERT INTO OgrodRosliny (idRosliny, idOgrodu) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(insertOgrodRosliny,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,this.idRosliny);
			ps.setInt(2,this.idOgrodu);
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
		support.firePropertyChange("OgrodRosliny", "A", "B");
	}
	
	public boolean UpdateData() 
	{
		SetConnection();
		try{
			String query = "SELECT * FROM OgrodRosliny WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1,this.id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				rs.updateInt("idRosliny", this.idRosliny);
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
			String query = "SELECT ogr.Id, ogr.idRosliny, r.Name, r.Opis  FROM OgrodRosliny ogr INNER JOIN Roslina r ON r.id = ogr.idRosliny WHERE ogr.id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				this.nazwa = rs.getString("Name");
				this.opis = rs.getString("Opis");
				this.id = rs.getInt("Id");
				this.idRosliny = rs.getInt("idRosliny");
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		this.roslina.GetData(this.idRosliny);
		
		CloseConnection();
		support.firePropertyChange("OgrodRosliny", "A", "B");
		return true;
	}
 
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
		roslina.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
		roslina.removePropertyChangeListener(pcl);
    }
	
	public boolean CreateTable(){	
		SetConnection();
		int ret =0;
		try{
			Statement statement = connection.createStatement();
			String createRoslina = "CREATE TABLE [OgrodRosliny] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
				"idOgrodu INT NULL CONSTRAINT fk_Ogrody REFERENCES [Ogrod](Id), "+
				"idRosliny INT NULL CONSTRAINT fk_Rosliny REFERENCES [Roslina](Id)) ";
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
			String query = "SELECT ogr.Id, ogr.idRosliny, r.Name, r.Opis  FROM OgrodRosliny ogr INNER JOIN Roslina r ON r.id = ogr.idRosliny WHERE ogr.idOgrodu = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,idOgrodu);
			ResultSet rs = ps.executeQuery();
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
			String query = "SELECT ogr.Id, ogr.idRosliny, r.Name, r.Opis  FROM OgrodRosliny ogr INNER JOIN Roslina r ON r.id = ogr.idRosliny WHERE ogr.idOgrodu = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,idOgrodu);
			ResultSet rs = ps.executeQuery();
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