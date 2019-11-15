package OgrodRosliny.view;
import javax.swing.*;
import java.awt.event.*;
import OgrodRosliny.model.OgrodRoslinyModel;
import java.beans.*;
import ViewHelper.*;
import java.awt.Dialog.*;
import java.awt.*;

/** klasa widoku prezentująca atrybuty rosliny w ogrodzie
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
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
	private JLabel lIlosc;
	private JTextField tfGleba;
	private JTextField tfRodzaj;
	private JTextField tfPoraSadzenia;
	private JTextField tfIlosc;
	
/** konstruktor widoku Ogrodu
  * Okno jest wysiwetlane w trybie modalnym
  *@param model - renderowana roslina
 */
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
		lIlosc = new JLabel("Ilosc");
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
		tfIlosc = new JTextField(Integer.toString(this.model.GetIlosc()),5);
		tfIlosc.setInputVerifier(new InputVerifierLength(4));
		JScrollPane SPDescription = new JScrollPane(tADescription);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(lGleba)
					.addComponent(lRodzaj)
					.addComponent(lPoraSadzenia)
					.addComponent(lIlosc))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(cbRoslina)
					.addComponent(tfGleba)
					.addComponent(tfRodzaj)
					.addComponent(tfPoraSadzenia)
					.addComponent(tfIlosc))
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
								.addComponent(tfPoraSadzenia))
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lIlosc)
								.addComponent(tfIlosc))))
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
	
/** metoda wyswietlajaca dialog
 */
	public void Show(){
		setVisible(true);
	}
	
/** metoda ustawia wybrana rosline z listy roslin
 */
	private void SetCBRoslina(){ 
		for (int i=0; i < cbRoslina.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbRoslina.getItemAt(i);
			if (item.getKey() == model.GetIdRosliny())
				cbRoslina.setSelectedIndex(i);
		}
	} 
	
/** metoda pobiera id wybranej rosliny z listy roslin
 *@return idRosliny
 */
	public int GetCBRoslina(){ 
		Object item = cbRoslina.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
/** metoda pobiera ilosc wybranej rosliny
 *@return ilosc
 */
	public int GetTFIlosc(){ 
		String sIlosc = tfIlosc.getText();
		return Integer.parseInt(sIlosc.trim());
	} 
	
/** metoda wyswitla komunikat jako dialog
 *@param errMessage String
 */
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
	
/** metoda dodaje klasę nasłuchującą dla przycisku Zapisz
 *@param al ActionListener
 */
	public void AddSaveActionListener(ActionListener al){
		bSave.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla przycisku Anuluj
 *@param al ActionListener
 */
	public void AddCancelActionListener(ActionListener al){
		bCancel.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla zmiany wybranej rosliny
 *@param al ActionListener
 */
	public void AddCBRoslinaActionListener(ActionListener al){
		cbRoslina.addActionListener(al);
	} 
	
/** metoda jest implementacja PropertyChangeListener.
 * Za jej pomocą widok jest informowany o zmianach w modelu.  
 *@param evt PropertyChangeEvent
 */
    public void propertyChange(PropertyChangeEvent evt) {
		
		tfGleba.setText(this.model.roslina.gleba.GetNazwa());
		tfRodzaj.setText(this.model.roslina.rodzajeRoslin.GetNazwa());
		tfPoraSadzenia.setText(this.model.roslina.poraSadzenia.GetNazwa());
		tADescription.setText(this.model.roslina.GetOpis());
		tfIlosc.setText(Integer.toString(this.model.GetIlosc()));
    }
}