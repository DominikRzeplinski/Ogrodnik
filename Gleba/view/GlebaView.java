package Gleba.view;
import javax.swing.*;
import java.awt.event.*;
import Gleba.model.GlebaModel;
import java.beans.*;

public class GlebaView extends JFrame implements PropertyChangeListener
{
	private JPanel panel;
	private JTextField tFEdit;
	private JTextArea tADescription;
	private JButton bSave;
	private GlebaModel model;
	
	public GlebaView(GlebaModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		panel = new JPanel(); 
		
		panel.add(new JLabel("Nazwa", JLabel.LEFT));
		tFEdit = new JTextField(model.GetNazwa(),20);
		panel.add(tFEdit);
		panel.add(new JLabel("Opis", JLabel.LEFT));
		tADescription = new JTextArea(model.GetOpis(),4,20);
		JScrollPane SPDescription = new JScrollPane(tADescription);
		panel.add(SPDescription);
		getContentPane().add(panel);
		bSave = new JButton("Zapisz");
		bSave.setSize(40,40);
		getContentPane().add(bSave);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pack();
		setSize(250,250);
		setResizable(false);
		setVisible(true);
	};
	
	public String GetTFEdit(){
		return tFEdit.getText();
	}
	
	public String GetTADescription(){
		return tADescription.getText();
	}
	
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
	
	public void AddSaveActionListener(ActionListener al){
		bSave.addActionListener(al);
	}
	
    public void propertyChange(PropertyChangeEvent evt) {
		if ("Gleba".equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
		}
    }
}