package Gleba.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;

public class GlebaModel extends DataBaseAccess implements ITable
{
	private static String dbTableName = "Gleba"; 
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public GlebaModel(){
		CreateTable();
        support = new PropertyChangeSupport(this);
	}
	public int SaveData(String nazwa, String opis) {
		String temp = this.nazwa;
		this.nazwa = nazwa;
		this.opis = opis;
		support.firePropertyChange("Gleba", temp, nazwa);
	}
	public int UpdateData(String nazwa, String opis) {
		if (id == 0){
			Save(nazwa,opis);
		}
		else{
			nazwa = "nazwa2";
			opis = "opis2";
		}
	}
	public static GlebaModel Get(int id) {
		GlebaModel gleba = new GlebaModel();
		gleba.nazwa = "nazwa";
		gleba.id =0;
		gleba.opis = "gleba testowa";
		return gleba;
	}
	
 
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
	
	public int CreateTable(){	
		SetConnection();
		int ret =0;
		try{
			Statement statement = connection.createStatement();
			String createGleba = "CREATE TABLE [Gleba] (Id COUNTER CONSTRAINT c_Id PRIMARY KEY, " +
				"Name VARCHAR(50) CONSTRAINT c_Name UNIQUE, " +
				"Opis VARCHAR(256))";
			ret = statement.executeUpdate(createGleba);
		}catch(SQLException exception) {
            // Output exception ClassNotFoundExceptions.
			System.out.print(exception.toString());
		}
		CloseConnection();
		return ret;
	}
}