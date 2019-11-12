import javax.swing.*;
import Gleba.view.*;
import Gleba.model.*;
import Gleba.controler.*;
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
    System.out.print("Hello World");
	DataBaseAccess.CreateDataBaseAccess();
	GlebaModel model = GlebaModel.Get(0);
	GlebaView view = new GlebaView(model);
	GlebaControler controler = new GlebaControler(model,view);
  }
}
