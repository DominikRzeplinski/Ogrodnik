package Gleba.view;
import javax.swing.*;
import Gleba.model.Gleba;

public class LabelEditDescription extends JFrame
{
	public LabelEditDescription(int id){
		JPanel panel = new JPanel(); 
		Gleba gleba = Gleba.Get(id);
		panel.add(new JLabel("Nazwa", JLabel.LEFT));
		JTextArea TAEdit = new JTextArea(gleba.nazwa);
		panel.add(TAEdit);
		panel.add(new JLabel("Opis", JLabel.LEFT));
		JTextArea TADescription = new JTextArea(gleba.opis);
		JScrollPane SPDescription = new JScrollPane(TADescription);
		panel.add(SPDescription);
		getContentPane().add(panel);
		
		pack();
		setSize(400,100);
		setVisible(true);
	};
}