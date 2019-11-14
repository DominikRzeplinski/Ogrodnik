package Roslina.view;
import javax.swing.*;
import java.awt.event.*;
import Roslina.model.RoslinaModel;
import java.beans.*;
import ViewHelper.*;
import java.awt.Dialog.*;
import java.awt.*;

public class RoslinaView extends JDialog implements PropertyChangeListener
{
	private JTextField tFEdit;
	private JTextArea tADescription;
	private JButton bSave;
	private JButton bCancel;
	private RoslinaModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	private JLabel lGleba;
	private JLabel lRodzaj;
	private JLabel lPoraSadzenia;
	private JComboBox cbGleba;
	private JComboBox cbRodzaj;
	private JComboBox cbPoraSadzenia;
	
	public RoslinaView(RoslinaModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		tFEdit = new JTextField(model.GetNazwa(),20);
		tFEdit.setInputVerifier(new InputVerifierLength(50));
		lNazwa = new JLabel("Nazwa");
		lOpis = new JLabel("Opis");
		bSave = new JButton("Zapisz");
		bSave.setVerifyInputWhenFocusTarget(true);
		bCancel = new JButton("Anuluj");
		lGleba = new JLabel("Gleba");
		lRodzaj = new JLabel("Rodzaj");
		lPoraSadzenia = new JLabel("PoraSadzenia");
		tADescription = new JTextArea(model.GetOpis(),4,20);
		tADescription.setInputVerifier(new InputVerifierLength(256));
		cbGleba = new JComboBox(model.gleba.GetDataList());
		cbRodzaj = new JComboBox(model.rodzajeRoslin.GetDataList());
		cbPoraSadzenia = new JComboBox(model.poraSadzenia.GetDataList());
		SetCBGleba();
		SetCBRodzaj();
		SetCBPoraSadzenia();
		JScrollPane SPDescription = new JScrollPane(tADescription);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(lGleba)
					.addComponent(lRodzaj)
					.addComponent(lPoraSadzenia))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tFEdit)
					.addComponent(cbGleba)
					.addComponent(cbRodzaj)
					.addComponent(cbPoraSadzenia))
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
								.addComponent(lGleba)
								.addComponent(cbGleba))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lRodzaj)
								.addComponent(cbRodzaj))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lPoraSadzenia)
								.addComponent(cbPoraSadzenia))))
					.addComponent(SPDescription))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(bSave)
					.addComponent(bCancel)));
				
		pack();
		setSize(550,250);
		setResizable(false);
		if(model.IsNew())
			setTitle(model.GetTableName() + " - Dodaj");
		else
			setTitle(model.GetTableName() + " - Edytuj");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	};
	
	public void Show(){
		setVisible(true);
	}
	
	private void SetCBGleba(){ 
		for (int i=0; i < cbGleba.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbGleba.getItemAt(i);
			if (item.getKey() == model.GetIdGleby())
				cbGleba.setSelectedIndex(i);
		}
	} 
	private void SetCBRodzaj(){ 
		for (int i=0; i < cbGleba.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbRodzaj.getItemAt(i);
			if (item.getKey() == model.GetIdRodzaj())
				cbRodzaj.setSelectedIndex(i);
		}
	} 
	private void SetCBPoraSadzenia(){ 
		for (int i=0; i < cbGleba.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbPoraSadzenia.getItemAt(i);
			if (item.getKey() == model.GetIdPoraSadzenia())
				cbPoraSadzenia.setSelectedIndex(i);
		}
	} 
	
	public int GetCBGleba(){ 
		Object item = cbGleba.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
	
	public int GetCBRodzaj(){ 
		Object item = cbRodzaj.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
	
	public int GetCBPoraSadzenia(){ 
		Object item = cbPoraSadzenia.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
	
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
		if ("Roslina".equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
			SetCBGleba();
			SetCBRodzaj();
			SetCBPoraSadzenia();
		}
    }
}