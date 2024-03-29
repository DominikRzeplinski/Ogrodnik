package slowniki.view;
import javax.swing.*;
import java.awt.event.*;
import slowniki.model.*;
import java.beans.*;
import viewHelper.*;
import java.awt.Dialog.*;
import java.awt.*;

/** klasa widoku prezentująca informacje dotyczące Slownika
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class SlownikView extends JDialog implements PropertyChangeListener
{
	private JTextField tFEdit;
	private JTextArea tADescription;
	private JButton bSave;
	private JButton bCancel;
	private SlownikModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	
/** konstruktor widoku Slownika
  * Okno jest wysiwetlane w trybie modalnym
  *@param model - renderowany Slownik
 */
	public SlownikView(SlownikModel model){
		
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		lNazwa = new JLabel("Nazwa");
		lOpis = new JLabel("Opis");
		tFEdit = new JTextField(model.GetNazwa(),20);
		tFEdit.setInputVerifier(new InputVerifierLength(50));
		tADescription = new JTextArea(model.GetOpis(),4,20);
		tADescription.setInputVerifier(new InputVerifierLength(256));
		JScrollPane SPDescription = new JScrollPane(tADescription);
		bSave = new JButton("Zapisz");
		bCancel = new JButton("Anuluj");
		bSave.setSize(40,40);
		bCancel.setSize(40,40);
		bSave.setVerifyInputWhenFocusTarget(true);
		
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(tFEdit,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(lOpis)
					.addComponent(SPDescription,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(layout.createSequentialGroup()
						.addComponent(bSave)
						.addComponent(bCancel))));
						
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addComponent(lNazwa)
				.addComponent(tFEdit,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(lOpis)
				.addComponent(SPDescription)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(bSave)
					.addComponent(bCancel)));
		
		
		pack();
		if(model.IsNew())
			setTitle(model.GetTableName() + " - Dodaj");
		else
			setTitle(model.GetTableName() + " - Edytuj");
		setSize(260,300);
		setResizable(false);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	};
/** metoda wyswietlajaca dialog
 */
	public void Show(){
		setVisible(true);
	}
	
/** metoda pobiera nazwe slownikowa
 *@return nazwa
 */
	public String GetTFEdit(){
		return tFEdit.getText();
	}
	
/** metoda pobiera opis
 *@return opis
 */
	public String GetTADescription(){
		return tADescription.getText();
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
	
/** metoda jest implementacja PropertyChangeListener.
 * Za jej pomocą widok jest informowany o zmianach w modelu.  
 *@param evt PropertyChangeEvent
 */
    public void propertyChange(PropertyChangeEvent evt) {
		if (model.GetTableName().equals(evt.getPropertyName()))
		{
			tFEdit.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
		}
    }
}