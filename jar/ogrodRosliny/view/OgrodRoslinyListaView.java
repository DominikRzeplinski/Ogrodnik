package ogrodRosliny.view;
import javax.swing.*;
import java.awt.event.*;
import ogrodRosliny.model.*;
import java.beans.*;
import java.awt.*;
import viewHelper.*;
import javax.swing.table.*;

/** klasa widoku prezentująca liste roslin w danym ogrodzie
 *@author Dominik Rzepliński
 *@version 1.0
 *@since v1.0
 */
public class OgrodRoslinyListaView extends JFrame implements PropertyChangeListener
{
	private JList lList;
	public JTable tTable;
	private ListSelectionModel lsmTable;
	private OgrodRoslinyModel model;
	private JButton bAdd; 
	private JButton bEdit;
	private JButton bRemove;
	private JButton bCancel;
	
/** konstruktor widoku Ogrodu
  *@param model - renderowany Ogrod
 */
	public OgrodRoslinyListaView(OgrodRoslinyModel model){
		this.model = model;
		model.addPropertyChangeListener(this);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		bAdd = new JButton("Dodaj");
		bAdd.setSize(40,40);
		bAdd.setPreferredSize(new Dimension(80, 30));
		bEdit = new JButton("Edytuj");
		bEdit.setSize(40,40);
		bEdit.setPreferredSize(new Dimension(80, 30));
		bRemove = new JButton("Usun");
		bRemove.setSize(40,40);
		bRemove.setPreferredSize(new Dimension(80, 30));
		bCancel = new JButton("Zamknij");
		bCancel.setSize(40,40);
		bCancel.setPreferredSize(new Dimension(80, 30));
		
		tTable = new JTable();
		tTable.setDefaultEditor(Object.class, null);
		SetTableData();
		JScrollPane tablePane = new JScrollPane(tTable);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
				.addComponent(tablePane)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(bAdd,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(bEdit,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(bRemove,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(bCancel,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
						
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(tablePane)
					.addGroup(layout.createSequentialGroup()
						.addComponent(bAdd,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bEdit,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bRemove,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addComponent(bCancel,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		
		pack();
		setTitle(model.GetTableName() + " - Lista");
		setSize(400,400);
		setResizable(false);
		setVisible(true);
	};
	
/** metoda odswierza dane w tabeli
 */
	public void SetTableData(){
		DefaultTableModel tModel = new DefaultTableModel(model.GetDataTableList(),model.GetDataTableColumnsNames());
		tTable.setModel(tModel);
		tTable.removeColumn(tTable.getColumnModel().getColumn(0));
		((AbstractTableModel)tTable.getModel()).fireTableDataChanged();
	}
/** metoda zwraca id wybranej rosliny w ogrodzie
 *@return  idRoslinyWOgrodzie
 */
	public int GetSelectedId(){
		String id = (String)tTable.getModel().getValueAt(tTable.getSelectedRow(),0);
		return Integer.parseInt(id);
	}
	
/** metoda wyswitla komunikat jako dialog
 *@param errMessage String
 */
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
	
/** metoda dodaje klasę nasłuchującą dla przycisku Dodaj
 *@param al ActionListener
 */
	public void AddAddActionListener(ActionListener al){
		bAdd.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla przycisku Edytuj
 *@param al ActionListener
 */
	public void AddEditActionListener(ActionListener al){
		bEdit.addActionListener(al);
	}
	
/** metoda dodaje klasę nasłuchującą dla przycisku Usun
 *@param al ActionListener
 */
	public void AddRemoveActionListener(ActionListener al){
		bRemove.addActionListener(al);
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
			SetTableData();
		}
    }
}