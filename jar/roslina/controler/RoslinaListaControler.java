package roslina.controler;
import roslina.model.*;
import roslina.view.*;
import roslina.controler.*;
import java.awt.event.*;

public class RoslinaListaControler{
	private RoslinaModel model;
    private RoslinaListaView view;
    
    public RoslinaListaControler(RoslinaModel model, RoslinaListaView view){
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
                RoslinaView sView = new RoslinaView(model);
				RoslinaControler sControler = new RoslinaControler(model,sView);
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
                RoslinaView sView = new RoslinaView(model);
				RoslinaControler sControler = new RoslinaControler(model,sView);
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