package slowniki.controler;
import slowniki.model.*;
import slowniki.view.*;
import slowniki.controler.*;
import java.awt.event.*;

public class SlownikListaControler{
	private SlownikModel model;
    private SlownikListaView view;
    
    public SlownikListaControler(SlownikModel model, SlownikListaView view){
        this.model = model;
        this.view = view;
		view.AddCancelActionListener(new CancelListener());
		view.AddAddActionListener(new AddListener());
		view.AddEditActionListener(new EditListener());
		view.AddRemoveActionListener(new RemoveListener());
    }
    
	public class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.GetData(0);
                SlownikView sView = new SlownikView(model);
				SlownikControler sControler = new SlownikControler(model,sView);
            } catch (NumberFormatException nfex) {
                view.showError("Bad input:'");
            }
        }
    }
    
	public class EditListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.GetData(view.GetSelectedId());
                SlownikView sView = new SlownikView(model);
				SlownikControler sControler = new SlownikControler(model,sView);
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: ");
            }
        }
    }
    
	public class RemoveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.GetData(view.GetSelectedId());
                model.DeleteData();
            } catch (NumberFormatException nfex) {
                view.showError("Bad input:'");
            }
        }
    }
	
	public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				view.dispose();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: ");
            }
        }
    }
}