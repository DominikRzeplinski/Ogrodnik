package Ogrod.view;
import javax.swing.*;
import java.awt.event.*;
import Ogrod.model.OgrodModel;
import java.beans.*;

public class OgrodView extends JFrame implements PropertyChangeListener
{
	private JTextField tFEdit;
	private JTextArea tADescription;
	private JButton bSave;
	private JButton bCancel;
	private OgrodModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	
	public OgrodView(OgrodModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		tFEdit = new JTextField(model.GetNazwa(),20);
		lNazwa = new JLabel("Nazwa");
		lOpis = new JLabel("Opis");
		bSave = new JButton("Zapisz");
		bCancel = new JButton("Anuluj");
		tADescription = new JTextArea(model.GetOpis(),4,20);
		JScrollPane SPDescription = new JScrollPane(tADescription);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tFEdit))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lOpis))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(SPDescription)
				.addGroup(layout.createSequentialGroup()
					.addComponent(bSave)
					.addComponent(bCancel))));
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lNazwa)
								.addComponent(tFEdit)
								.addComponent(lOpis)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								)))
					.addComponent(SPDescription))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(bSave)
					.addComponent(bCancel)));
				
		pack();
		setSize(550,250);
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
	
	public void AddCancelActionListener(ActionListener al){
		bCancel.addActionListener(al);
	}
	
    public void propertyChange(PropertyChangeEvent evt) {
		if ("Ogrod".equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
		}
    }
}