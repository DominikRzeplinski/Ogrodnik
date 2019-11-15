package roslinaPraceOgrodowe.model;
import java.beans.*;
import dataBase.table.ITable;
import dataBase.connection.*;
import java.sql.Statement;
import java.sql.*;
import roslina.model.*;
import praceOgrodowe.model.*;
import java.util.*;
import viewHelper.*;

public class RoslinaPraceOgrodoweModel extends DataBaseAccess implements ITable
{
	public PraceOgrodoweModel praceOgrodowe; 
	public String GetTableName(){return "RoslinaPraceOgrodowe";} 
	private PropertyChangeSupport support;
	int id;
	int idRosliny;
	int idPraceOgrodowe;
	int ilosc;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public int GetIlosc(){return ilosc;}
	public String GetOpis(){return opis;}
	public int GetIdPraceOgrodowe(){return idPraceOgrodowe;}
	public void SetIdPraceOgrodowe(int idPraceOgrodowe){this.idPraceOgrodowe = idPraceOgrodowe;}
	public void SetIlosc(int ilosc){this.ilosc = ilosc;}
	public RoslinaPraceOgrodoweModel(int idRosliny){
		this.idRosliny = idRosliny;
		praceOgrodowe = new PraceOgrodoweModel();
		CreateTable();
        support = new PropertyChangeSupport(this);
		Reset();
	}
	
	public boolean IsNew(){
		return (this.id ==0);
	}
	
	private void Reset(){
		this.id=0;
		this.nazwa=" ";
		this.opis=" ";
		this.idPraceOgrodowe=0;
		this.ilosc=0;
	}
	
	public boolean DeleteData() {
		SetConnection();
		try{
			String deleteRoslina = "DELETE FROM RoslinaPraceOgrodowe WHERE id = ?";
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
		support.firePropertyChange("RoslinaPraceOgrodowe", "A", "B");
		return true; 
	}
	public boolean SaveData() {
		SetConnection();
		try{
			String insertOgrodRosliny = "INSERT INTO RoslinaPraceOgrodowe (idRosliny, idPraceOgrodowe, ilosc) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertOgrodRosliny,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,this.idRosliny);
			ps.setInt(2,this.idPraceOgrodowe);
			ps.setInt(3,this.ilosc);
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
		support.firePropertyChange("RoslinaPraceOgrodowe", "A", "B");
	}
	
	public boolean UpdateData() 
	{
		SetConnection();
		try{
			String query = "SELECT * FROM RoslinaPraceOgrodowe WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1,this.id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				rs.updateInt("idPraceOgrodowe", this.idPraceOgrodowe);
				rs.updateInt("ilosc", this.ilosc);
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
			String query = "SELECT ogr.Id, ogr.idPraceOgrodowe, ogr.ilosc, po.Name, po.Opis  FROM RoslinaPraceOgrodowe ogr " +
			" INNER JOIN PraceOgrodowe po ON po.id = ogr.idPraceOgrodowe WHERE ogr.id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				this.nazwa = rs.getString("Name");
				this.opis = rs.getString("Opis");
				this.id = rs.getInt("Id");
				this.idPraceOgrodowe = rs.getInt("idPraceOgrodowe");
				this.ilosc = rs.getInt("ilosc");
			}
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		this.praceOgrodowe.GetData(this.idPraceOgrodowe);
		
		CloseConnection();
		support.firePropertyChange("RoslinaPraceOgrodowe", "A", "B");
		return true;
	}
 
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
		praceOgrodowe.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
		praceOgrodowe.removePropertyChangeListener(pcl);
    }
	
	public boolean CreateTable(){	
		SetConnection();
		int ret =0;
		try{
			Statement statement = connection.createStatement();
			String createRoslina = "CREATE TABLE [RoslinaPraceOgrodowe] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
				"idRosliny INT NULL CONSTRAINT fk_Rosliny REFERENCES [Roslina](Id), "+
				"idPraceOgrodowe INT NULL CONSTRAINT fk_PraceOgrodowe REFERENCES [PraceOgrodowe](Id), "+
				"ilosc INT NULL) ";
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
			String query = "SELECT ogr.Id, ogr.idRosliny, ogr.ilosc, r.Name, r.Opis  FROM RoslinaPraceOgrodowe ogr INNER JOIN PraceOgrodowe r ON r.id = ogr.idPraceOgrodowe WHERE ogr.idRosliny = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,idRosliny);
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
		names.add("Ilosc");
		return names;
	}
	
	public Vector<Vector<String>> GetDataTableList()
	{
		Vector<Vector<String>> list = new Vector<Vector<String>>();
		SetConnection();
		try{
			String query = "SELECT ogr.Id, ogr.idRosliny, ogr.ilosc, r.Name, r.Opis  FROM RoslinaPraceOgrodowe ogr INNER JOIN PraceOgrodowe r ON r.id = ogr.idPraceOgrodowe WHERE ogr.idRosliny = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,idRosliny);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Vector<String> row = new Vector<String>();
				row.add(String.valueOf(rs.getInt("Id")));
				row.add(rs.getString("Name"));
				row.add(rs.getString("Opis"));
				row.add(rs.getString("ilosc"));
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