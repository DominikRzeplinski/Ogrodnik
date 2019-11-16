package ogrod.view;
import javax.swing.*;
import java.awt.event.*;
import ogrod.model.OgrodModel;
import java.beans.*;
import viewHelper.*;


/** klasa widoku prezentująca informacje dotyczące Ogrodu
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class OgrodView extends JFrame implements PropertyChangeListener
{ 
	private JTextField tFNazwa;
	private JTextField tFMiasto;
	private JTextField tFUlica;
	private JTextField tFNrDomu;
	private JTextField tFNrMieszkania;
	private JTextField tFKodPocztowy;
	private JTextArea tADescription;
	private JButton bSave; 
	private JButton bCancel;
	private JButton bDelete;
	private JButton bDetails;
	private OgrodModel model;
	private JLabel lNazwa;
	private JLabel lOpis;
	private JLabel lOgrod;
	private JLabel lMiasto;
	private JLabel lUlica;
	private JLabel lNrDomu;
	private JLabel lNrMieszkania;
	private JLabel lKodPocztowy;
	private JComboBox cbOgrod; 
	private JMenuBar mb;
	private JMenu mSlowniki;
	private JMenu mRosliny;
	private JMenuItem mIGleby;
	private JMenuItem mIRodzaje;
	private JMenuItem mIPoraSadzenia;
	private JMenuItem mIListaRosliny;
	private JMenuItem mIListaPracOgrodowych;
	
/** konstruktor widoku Ogrodu
  *@param model - renderowany Ogrod
 */
	public OgrodView(OgrodModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		tFNazwa = new JTextField(model.GetNazwa(),20);
		lNazwa = new JLabel("Nazwa");
		tFNazwa.setInputVerifier(new InputVerifierLength(50));
		lOpis = new JLabel("Opis");
		bSave = new JButton("Zapisz");
		bSave.setVerifyInputWhenFocusTarget(true);;
		bCancel = new JButton("Anuluj");
		bDelete = new JButton("Usun");
		bDetails = new JButton("Szczegoly");
		tADescription = new JTextArea(model.GetOpis(),4,20);
		tADescription.setInputVerifier(new InputVerifierLength(256));
		JScrollPane SPDescription = new JScrollPane(tADescription);
		cbOgrod = new JComboBox(model.GetDataList());
		lOgrod = new JLabel("Ogrod");
		lMiasto = new JLabel("Miasto");
		tFMiasto = new JTextField(model.GetMiasto(),20);
		tFMiasto.setInputVerifier(new InputVerifierLength(32));
		lNrDomu = new JLabel("Nr domu");
		tFNrDomu = new JTextField(model.GetNrDomu(),5);
		tFNrDomu.setInputVerifier(new InputVerifierLength(32));
		lNrMieszkania = new JLabel("Nr mieszkania");
		tFNrMieszkania = new JTextField(model.GetNrMieszkania(),5);
		tFNrMieszkania.setInputVerifier(new InputVerifierLength(32));
		lUlica = new JLabel("Ulica");
		tFUlica = new JTextField(model.GetUlica(),20);
		tFUlica.setInputVerifier(new InputVerifierLength(32));
		lKodPocztowy = new JLabel("Kod pocztowy");
		tFKodPocztowy = new JTextField(model.GetKodPocztowy(),5);
		tFKodPocztowy.setInputVerifier(new InputVerifierLength(32));
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addComponent(lOgrod)
				.addComponent(cbOgrod))
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lNazwa)
					.addComponent(lMiasto)
					.addComponent(lUlica)
					.addComponent(lNrDomu)
					.addComponent(lNrMieszkania)
					.addComponent(lKodPocztowy)
					.addComponent(bDetails))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tFNazwa,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(tFMiasto,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(tFUlica,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(tFNrDomu,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(tFNrMieszkania,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(tFKodPocztowy,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(tFNazwa)
								.addComponent(lOpis)))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lMiasto)
								.addComponent(tFMiasto)
								))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lUlica)
								.addComponent(tFUlica)
								))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lNrDomu)
								.addComponent(tFNrDomu)
								))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lNrMieszkania)
								.addComponent(tFNrMieszkania)
								))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lKodPocztowy)
								.addComponent(tFKodPocztowy)
								)))
					.addComponent(SPDescription))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(bDetails)
					.addComponent(bSave)
					.addComponent(bCancel)
					.addComponent(bDelete)));
				
		pack();
		setSize(620,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createJMenu();
		setTitle("Ogrodnik");
		setVisible(true);
	};
	
/** metoda tworząca górne menu programu
 */
	private void createJMenu()
	{
		mb = new JMenuBar();
		setJMenuBar(mb);
		
		mSlowniki = new JMenu("Slowniki");
		mb.add(mSlowniki);
		mIGleby = new JMenuItem("Gleby");
		mSlowniki.add(mIGleby);
		mSlowniki.addSeparator();
		mIRodzaje = new JMenuItem("Rodzaje");
		mSlowniki.add(mIRodzaje);
		mSlowniki.addSeparator();
		mIPoraSadzenia = new JMenuItem("Pora sadzenia");
		mSlowniki.add(mIPoraSadzenia);
		mSlowniki.addSeparator();
		mIListaPracOgrodowych = new JMenuItem("Prace ogrodowe");
		mSlowniki.add(mIListaPracOgrodowych);
		mSlowniki.addSeparator();
		
		mRosliny = new JMenu("Rosliny");
		mb.add(mRosliny);
		mIListaRosliny = new JMenuItem("Lista");
		mRosliny.add(mIListaRosliny);
		
	}
	
/** metoda pobiera wpisaną nazwę ogrodu
 *@return String Nazwa
 */
	public String GetTFNazwa(){
		return tFNazwa.getText();
	}
/** metoda pobiera wpisaną nazwę miasta
 *@return String Nazwa
 */
	public String GetTFMiasto(){
		return tFMiasto.getText();
	}
/** metoda pobiera wpisaną nazwę ulicy
 *@return String Nazwa
 */
	public String GetTFUlica(){
		return tFUlica.getText();
	}
/** metoda pobiera wpisany nr domu
 *@return String NrDomu
 */
	public String GetTFNrDomu(){
		return tFNrDomu.getText();
	}
/** metoda pobiera wpisany nr mieszkania
 *@return String nrMieszkania
 */
	public String GetTFNrMieszkania(){
		return tFNrMieszkania.getText();
	}
/** metoda pobiera wpisany kod pocztowy
 *@return String kodPocztowy
 */
	public String GetTFKodPocztowy(){
		return tFKodPocztowy.getText();
	}
	
/** metoda pobiera wpisany opis Ogrodu
 *@return String Opis
 */
	public String GetTADescription(){
		return tADescription.getText();
	} 
	
/** metoda pobiera identyfikator wybranego ogrodu
 *@return Int idOgrodu
 */
	public int GetCBOgrod(){ 
		Object item = cbOgrod.getSelectedItem();
		int key = ((ComboBoxItem)item).getKey();
		return key;
	} 
	
/** metoda wyswitla komunikat jako dialog
 *@param errMessage String
 */
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
	
/** metoda dodaje klasę nasłuchującą dla Menu.PraceOgrodowe.lista
 *@param al ActionListener
 */
	public void AddMenuPraceOgrodoweActionListener(ActionListener al){
		mIListaPracOgrodowych.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla Menu.Rosliny.lista
 *@param al ActionListener
 */
	public void AddMenuListaRoslinActionListener(ActionListener al){
		mIListaRosliny.addActionListener(al);
	}
/** metoda dodaje klasę nasłuchującą dla Menu.Slowniki.poraSadzenia
 *@param al ActionListener
 */
	public void AddMenuPoraSadzeniaActionListener(ActionListener al){
		mIPoraSadzenia.addActionListener(al);
	}
/** metoda dodaje klasę nasłuchującą dla Menu.Slowniki.rodzajeRoslin
 *@param al ActionListener
 */
	public void AddMenuRodzajeRoslinActionListener(ActionListener al){
		mIRodzaje.addActionListener(al);
	}
/** metoda dodaje klasę nasłuchującą dla Menu.Slowniki.gleby
 *@param al ActionListener
 */
	public void AddMenuGlebyActionListener(ActionListener al){
		mIGleby.addActionListener(al);
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
	
/** metoda dodaje klasę nasłuchującą dla przycisku Usun
 *@param al ActionListener
 */
	public void AddDeleteActionListener(ActionListener al){
		bDelete.addActionListener(al);
	}
/** metoda dodaje klasę nasłuchującą dla przycisku Szczegoly
 *@param al ActionListener
 */
	public void AddDetailsActionListener(ActionListener al){
		bDetails.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla wyboru comboBoxa Ogrod
 *@param al ActionListener
 */
	public void AddCBOgrodActionListener(ActionListener al){
		cbOgrod.addActionListener(al);
	} 
	
/** metoda jest implementacja PropertyChangeListener.
 * Za jej pomocą widok jest informowany o zmianach w modelu.  
 *@param evt PropertyChangeEvent
 */
    public void propertyChange(PropertyChangeEvent evt) {
		if ("OgrodGetData".equals(evt.getPropertyName()))
		{
			tFNazwa.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
			tFMiasto.setText(this.model.GetMiasto());
			tFUlica.setText(this.model.GetUlica());
			tFNrDomu.setText(this.model.GetNrDomu());
			tFNrMieszkania.setText(this.model.GetNrMieszkania());
			tFKodPocztowy.setText(this.model.GetKodPocztowy());
		}else if("Ogrod".equals(evt.getPropertyName()))
		{
			tFNazwa.setText(this.model.GetNazwa());
			tADescription.setText(this.model.GetOpis());
			tFMiasto.setText(this.model.GetMiasto());
			tFUlica.setText(this.model.GetUlica());
			tFNrDomu.setText(this.model.GetNrDomu());
			tFNrMieszkania.setText(this.model.GetNrMieszkania());
			tFKodPocztowy.setText(this.model.GetKodPocztowy());
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