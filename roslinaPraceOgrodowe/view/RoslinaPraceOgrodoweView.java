package roslinaPraceOgrodowe.view;
import javax.swing.*;
import java.awt.event.*;
import roslinaPraceOgrodowe.model.RoslinaPraceOgrodoweModel;
import java.beans.*;
import viewHelper.*;
import java.awt.Dialog.*;
import java.awt.*;

/** klasa widoku prezentująca atrybuty prac ogrodowych przypisanych do roslin
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class RoslinaPraceOgrodoweView extends JDialog implements PropertyChangeListener
{
	private JComboBox cbPraceOgrodowe;
	private JTextArea tADescription;
	private JButton bSave;
	private JButton bCancel;
	private RoslinaPraceOgrodoweModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	private JLabel lIlosc;
	private JTextField tfIlosc;
	
/** konstruktor widoku prac ogrodowych
  * Okno jest wysiwetlane w trybie modalnym
  *@param model - renderowana pracaOgrodowe
 */
	public RoslinaPraceOgrodoweView(RoslinaPraceOgrodoweModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		cbPraceOgrodowe = new JComboBox(model.praceOgrodowe.GetDataList());
		SetCBPraceOgrodowe();
		lNazwa = new JLabel("Nazwa");
		lOpis = new JLabel("Opis");
		lIlosc = new JLabel("Ilosc");
		bSave = new JButton("Zapisz");
		bSave.setVerifyInputWhenFocusTarget(true);
		bCancel = new JButton("Anuluj");
		tADescription = new JTextArea(model.GetOpis(),4,20);
		tADescription.setInputVerifier(new InputVerifierLength(256));
		tfIlosc = new JTextField(Integer.toString(this.model.GetIlosc()),5);
		tfIlosc.setInputVerifier(new InputVerifierLength(4));
		JScrollPane SPDescription = new JScrollPane(tADescription);
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(lIlosc))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(cbPraceOgrodowe)
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
								.addComponent(cbPraceOgrodowe)
								.addComponent(lOpis)))
						.addGroup(layout.createSequentialGroup()
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
	
/** metoda ustawia wybrana prace z listy prac ogrodowych
 */
	private void SetCBPraceOgrodowe(){ 
		for (int i=0; i < cbPraceOgrodowe.getItemCount(); i++)
		{
			ComboBoxItem item = (ComboBoxItem)cbPraceOgrodowe.getItemAt(i);
			if (item.getKey() == model.GetIdPraceOgrodowe())
				cbPraceOgrodowe.setSelectedIndex(i);
		}
	} 
	
/** metoda pobiera id wybranej pracy z listy prac ogrodowych
 *@return idPracyOgrodowe
 */
	public int GetCBPracaOgrodowa(){ 
		Object item = cbPraceOgrodowe.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
/** metoda pobiera ilosc wybranej pracy ogrodowej
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
	
/** metoda dodaje klasę nasłuchującą dla zmiany wybranej pracy ogrodowej
 *@param al ActionListener
 */
	public void AddCBPraceOgrodoweActionListener(ActionListener al){
		cbPraceOgrodowe.addActionListener(al);
	} 
	
/** metoda jest implementacja PropertyChangeListener.
 * Za jej pomocą widok jest informowany o zmianach w modelu.  
 *@param evt PropertyChangeEvent
 */
    public void propertyChange(PropertyChangeEvent evt) {
		
		tADescription.setText(this.model.praceOgrodowe.GetOpis());
		tfIlosc.setText(Integer.toString(this.model.GetIlosc()));
    }
}