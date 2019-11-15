package OgrodRosliny.view;
import javax.swing.*;
import java.awt.event.*;
import OgrodRosliny.model.OgrodRoslinyModel;
import java.beans.*;
import ViewHelper.*;
import java.awt.Dialog.*;
import java.awt.*;

public class OgrodRoslinyView extends JDialog implements PropertyChangeListener
{
	private JComboBox cbRoslina;
	private JTextArea tADescription;
	private JButton bSave;
	private JButton bCancel;
	private OgrodRoslinyModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	private JLabel lGleba;
	private JLabel lRodzaj;
	private JLabel lPoraSadzenia;
	private JTextField tfGleba;
	private JTextField tfRodzaj;
	private JTextField tfPoraSadzenia;
	
	public OgrodRoslinyView(OgrodRoslinyModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		cbRoslina = new JComboBox(model.roslina.GetDataList());
		SetCBRoslina();
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
		tfGleba = new JTextField(model.roslina.gleba.GetNazwa(),20);
		tfRodzaj = new JTextField(model.roslina.rodzajeRoslin.GetNazwa(),20);
		tfPoraSadzenia = new JTextField(model.roslina.poraSadzenia.GetNazwa(),20);
		JScrollPane SPDescription = new JScrollPane(tADescription);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(lGleba)
					.addComponent(lRodzaj)
					.addComponent(lPoraSadzenia))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(cbRoslina)
					.addComponent(tfGleba)
					.addComponent(tfRodzaj)
					.addComponent(tfPoraSadzenia))
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
								.addComponent(cbRoslina)
								.addComponent(lOpis)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lGleba)
								.addComponent(tfGleba))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lRodzaj)
								.addComponent(tfRodzaj))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lPoraSadzenia)
								.addComponent(tfPoraSadzenia))))
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
	
	private void SetCBRoslina(){ 
		for (int i=0; i < cbRoslina.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbRoslina.getItemAt(i);
			if (item.getKey() == model.GetIdRosliny())
				cbRoslina.setSelectedIndex(i);
		}
	} 
	
	public int GetCBRoslina(){ 
		Object item = cbRoslina.getSelectedItem();
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
	
	public void AddCBRoslinaActionListener(ActionListener al){
		cbRoslina.addActionListener(al);
	} 
	
    public void propertyChange(PropertyChangeEvent evt) {
		
		tfGleba.setText(this.model.roslina.gleba.GetNazwa());
		tfRodzaj.setText(this.model.roslina.rodzajeRoslin.GetNazwa());
		tfPoraSadzenia.setText(this.model.roslina.poraSadzenia.GetNazwa());
		tADescription.setText(this.model.roslina.GetOpis());
    }
}