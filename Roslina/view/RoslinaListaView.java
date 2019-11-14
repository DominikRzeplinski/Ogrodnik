package Roslina.view;
import javax.swing.*;
import java.awt.event.*;
import Roslina.model.*;
import java.beans.*;
import java.awt.*;
import ViewHelper.*;
import javax.swing.table.*;
public class RoslinaListaView extends JFrame implements PropertyChangeListener
{
	private JList lList;
	private JTable tTable;
	private ListSelectionModel lsmTable;
	private RoslinaModel model;
	private JButton bAdd; 
	private JButton bEdit;
	private JButton bRemove;
	private JButton bCancel;
	
	public RoslinaListaView(RoslinaModel model){
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
		
		tTable = new JTable(model.GetDataTableList(),model.GetDataTableColumnsNames());
		tTable.removeColumn(tTable.getColumnModel().getColumn(0));
		tTable.setDefaultEditor(Object.class, null);
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
	
	public int GetSelectedId(){
		String id = (String)tTable.getModel().getValueAt(tTable.getSelectedRow(),0);
		return Integer.parseInt(id);
	}
	
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
	
	public void AddAddActionListener(ActionListener al){
		bAdd.addActionListener(al);
	}
	
	public void AddEditActionListener(ActionListener al){
		bEdit.addActionListener(al);
	}
	
	public void AddRemoveActionListener(ActionListener al){
		bRemove.addActionListener(al);
	}
	
	public void AddCancelActionListener(ActionListener al){
		bCancel.addActionListener(al);
	}
	
    public void propertyChange(PropertyChangeEvent evt) {
		if (model.GetTableName().equals(evt.getPropertyName()))
		{
			DefaultTableModel tModel = new DefaultTableModel(model.GetDataTableList(),model.GetDataTableColumnsNames());
			tTable.setModel(tModel);
			tTable.removeColumn(tTable.getColumnModel().getColumn(0));
			((AbstractTableModel)tTable.getModel()).fireTableDataChanged();
		}
    }
}