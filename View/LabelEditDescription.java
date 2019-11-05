import javax.swing.*;
import ../Model.Gleba;

public class LabelEditDescription extends JFrame
{
	public LabelEditDescription(int id){
		Gleba gleba = Gleba.Get(id);
		getContentPane().add(new JLabel("Nazwa", JLabel.LEFT));
		JTextArea TAEdit = new JTextArea(gleba.nazwa);
		getContentPane().add(TAEdit);
		JTextArea TADescription = new JTextArea(gleba.opis);
		JScrollPane SPDescription = new JScrollPane(TADescription);
		getContentPane().add(SPDescription);
		
		pack();
		setSize(400,100);
		setVisible(true);
	};
}