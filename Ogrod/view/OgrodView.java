package Ogrod.view;
import javax.swing.*;
import java.awt.event.*;
import Ogrod.model.OgrodModel;
import java.beans.*;
import ViewHelper.*;

public class OgrodView extends JFrame implements PropertyChangeListener
{ 
	private JTextField tFEdit;
	private JTextArea tADescription;
	private JButton bSave; 
	private JButton bCancel;
	private JButton bDelete;
	private OgrodModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	private JLabel lOgrod;
	private JComboBox cbOgrod; 
	
	
	public OgrodView(OgrodModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		tFEdit = new JTextField(model.GetNazwa(),20);
		lNazwa = new JLabel("Nazwa");
		tFEdit.setInputVerifier(new InputVerifierLength(50));
		lOpis = new JLabel("Opis");
		bSave = new JButton("Zapisz");
		bSave.setVerifyInputWhenFocusTarget(true);;
		bCancel = new JButton("Anuluj");
		bDelete = new JButton("Usun");
		tADescription = new JTextArea(model.GetOpis(),4,20);
		tADescription.setInputVerifier(new InputVerifierLength(256));
		JScrollPane SPDescription = new JScrollPane(tADescription);
		cbOgrod = new JComboBox(model.GetDataList());
		lOgrod = new JLabel("Ogrod");
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(lOgrod)
				.addComponent(cbOgrod))
			.addGroup(layout.createSequentialGroup()
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
					.addComponent(bCancel)
					.addComponent(bDelete)))));
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lOgrod)
								.addComponent(cbOgrod))))
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
					.addComponent(bCancel)
					.addComponent(bDelete)));
				
		pack();
		setSize(600,250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	};
	
	public String GetTFEdit(){
		return tFEdit.getText();
	}
	
	public String GetTADescription(){
		return tADescription.getText();
	} 
	
	public int GetCBOgrod(){ 
		Object item = cbOgrod.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
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
	
	public void AddDeleteActionListener(ActionListener al){
		bDelete.addActionListener(al);
	}
	
	public void AddCBOgrodActionListener(ActionListener al){
		cbOgrod.addActionListener(al);
	} 
	
    public void propertyChange(PropertyChangeEvent evt) {
		if ("OgrodGetData".equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
		}else if("Ogrod".equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
			cbOgrod.setModel(new DefaultComboBoxModel<ComboBoxItem>(model.GetDataList()));
			for (int i=0; i < cbOgrod.getItemCount(); i++)
			{
				ComboBoxItem item = (ComboBoxItem)cbOgrod.getItemAt(i);
				if (item.getKey() == model.GetId())
					cbOgrod.setSelectedIndex(i);
			}
		}
    }
}