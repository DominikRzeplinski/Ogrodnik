import javax.swing.*;
import Roslina.view.*;
import Roslina.model.*;
import Roslina.controler.*;
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
	RoslinaModel model = new RoslinaModel();
	RoslinaView view = new RoslinaView(model);
	RoslinaControler controler = new RoslinaControler(model,view);
  }
}
