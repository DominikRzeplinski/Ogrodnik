import javax.swing.*;
import Gleba.view.*;

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
	//JFrame.setDefaultLookAndFeelDecorated(true);
	new LabelEditDescription(0);
  }
}
