import javax.swing.*;
import Ogrod.view.*;
import Ogrod.model.OgrodModel;
import Gleba.model.*;
import Ogrod.controler.*;
import DataBase.Connection.*;

/** klasa uruchamiająca aplikację Ogrodnik
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class Ogrodnik
{
	
/** Kontruktor klasy. 
 * Nie jest zaimplementowany
 */
 public Ogrodnik()
 {}
/** Metoda startowa programu.
 *@param args nie uzywany
 */
  public static void main(String[] args){
	  
	DataBaseAccess.CreateDataBaseAccess();
	/** Model */
	OgrodModel model = new OgrodModel();
	
	/** Widok */
	OgrodView view = new OgrodView(model);
	
	/** Kontroler */
	OgrodControler controler = new OgrodControler(model,view);
  }
}
