import javax.swing.*;
import Ogrod.view.*;
import Ogrod.model.OgrodModel;
import Gleba.model.*;
import Ogrod.controler.*;
import DataBase.Connection.*;

public class Hello extends JFrame
{
	
 public Hello()
 {
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
 setSize(400,100);
 setVisible(true);
 }
  public static void main(String[] args){
	  
	DataBaseAccess.CreateDataBaseAccess();
	
	OgrodModel model = new OgrodModel();
	
	OgrodView view = new OgrodView(model);
	
	OgrodControler controler = new OgrodControler(model,view);
  }
}
