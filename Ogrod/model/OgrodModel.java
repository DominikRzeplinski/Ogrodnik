package ogrod.model;
import java.beans.*;
import dataBase.table.ITable;
import dataBase.connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import viewHelper.*;

public class OgrodModel extends DataBaseAccess implements ITable
{
	private static String dbTableName = "Ogrod"; 
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	String miasto; 
	String ulica; 
	String nrDomu; 
	String nrMieszkania; 
	String kodPocztowy; 
	public String GetNazwa(){return nazwa;}
	public int GetId(){return id;}
	public String GetOpis(){return opis;}
	public String GetMiasto(){return miasto;}
	public String GetUlica(){return ulica;}
	public String GetNrDomu(){return nrDomu;}
	public String GetNrMieszkania(){return nrMieszkania;}
	public String GetKodPocztowy(){return kodPocztowy;}
	public void SetNazwa(String nazwa){this.nazwa = nazwa;}
	public void SetOpis(String opis){this.opis = opis;}
	public void SetMiasto(String miasto){this.miasto = miasto;}
	public void SetUlica(String ulica){this.ulica = ulica;}
	public void SetNrDomu(String nrDomu){this.nrDomu = nrDomu;}
	public void SetNrMieszkania(String nrMieszkania){this.nrMieszkania = nrMieszkania;}
	public void SetKodPocztowy(String kodPocztowy){this.kodPocztowy = kodPocztowy;}
	public OgrodModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
	}
	private void Reset(){
		this.id=0;
		this.nazwa=" ";
		this.opis=" ";
		this.miasto=" ";
		this.ulica=" ";
		this.nrDomu=" ";
		this.nrMieszkania=" ";
		this.kodPocztowy=" ";
	}
	
	public boolean DeleteData() {
		if (this.id ==0)
			return true;
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
		if (this.nazwa.equals(""))
			return true;
		SetConnection();
		try{
			String insertOgrod = "INSERT INTO Ogrod (Name, Opis, Miasto, Ulica, nrDomu, nrMieszkania, KodPocztowy) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertOgrod,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,this.nazwa);
			ps.setString(2,this.opis);
			ps.setString(3,this.miasto);
			ps.setString(4,this.ulica);
			ps.setString(5,this.nrDomu);
			ps.setString(6,this.nrMieszkania);
			ps.setString(7,this.kodPocztowy);
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
				rs.updateString("Miasto", this.miasto);
				rs.updateString("Ulica", this.ulica);
				rs.updateString("nrDomu", this.nrDomu);
				rs.updateString("nrMieszkania", this.nrMieszkania);
				rs.updateString("KodPocztowy", this.kodPocztowy);
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
				this.miasto = rs.getString("Miasto");
				this.ulica = rs.getString("Ulica");
				this.nrDomu = rs.getString("nrDomu");
				this.nrMieszkania = rs.getString("nrMieszkania");
				this.kodPocztowy = rs.getString("KodPocztowy");
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
				"Opis VARCHAR(256), " +
				"Miasto VARCHAR(32), " +
				"Ulica VARCHAR(32), " +
				"nrDomu VARCHAR(32), " +
				"nrMieszkania VARCHAR(32), " +
				"KodPocztowy VARCHAR(32)) ";
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