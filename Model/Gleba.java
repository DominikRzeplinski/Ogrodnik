package Model;

public class Gleba{
	int id;
	public String nazwa; 
	public String opis; 
	public Gleba(){
	}
	public void Save() {
		
	}
	public static Gleba Get(int id) {
		Gleba gleba = new Gleba();
		gleba.nazwa = "nazwa";
		gleba.id =0;
		gleba.opis = "gleba testowa";
		return gleba;
	}
}