package Gleba.model;
import java.beans.*;

public class GlebaModel{
	private PropertyChangeSupport support;
	int id;
	String nazwa; 
	String opis; 
	public String GetNazwa(){return nazwa;}
	public String GetOpis(){return opis;}
	public GlebaModel(){
        support = new PropertyChangeSupport(this);
	}
	public void Save(String nazwa, String opis) {
		String temp = this.nazwa;
		this.nazwa = nazwa;
		this.opis = opis;
		support.firePropertyChange("Gleba", temp, nazwa);
	}
	public void Update(String nazwa, String opis) {
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
}