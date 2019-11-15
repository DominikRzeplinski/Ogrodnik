package ogrodRosliny.controler;
import ogrodRosliny.model.*;
import ogrodRosliny.view.*;
import ogrodRosliny.controler.*;
import java.awt.event.*;

public class OgrodRoslinyListaControler{
	private OgrodRoslinyModel model;
    private OgrodRoslinyListaView view;
    
    public OgrodRoslinyListaControler(OgrodRoslinyModel model, OgrodRoslinyListaView view){
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
                OgrodRoslinyView sView = new OgrodRoslinyView(model);
				OgrodRoslinyControler sControler = new OgrodRoslinyControler(model,sView);
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
                OgrodRoslinyView sView = new OgrodRoslinyView(model);
				OgrodRoslinyControler sControler = new OgrodRoslinyControler(model,sView);
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