package roslinaPraceOgrodowe.controler;
import roslinaPraceOgrodowe.model.*;
import roslinaPraceOgrodowe.view.*;
import roslinaPraceOgrodowe.controler.*;
import java.awt.event.*;

public class RoslinaPraceOgrodoweListaControler{
	private RoslinaPraceOgrodoweModel model;
    private RoslinaPraceOgrodoweListaView view;
    
    public RoslinaPraceOgrodoweListaControler(RoslinaPraceOgrodoweModel model, RoslinaPraceOgrodoweListaView view){
        this.model = model;
        this.view = view;
		view.AddCancelActionListener(new CancelListener());
		view.AddAddActionListener(new AddListener());
		view.AddEditActionListener(new EditListener());
		view.AddRemoveActionListener(new RemoveListener());
		view.Show();
    }
    
	public class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.GetData(0);
                RoslinaPraceOgrodoweView sView = new RoslinaPraceOgrodoweView(model);
				RoslinaPraceOgrodoweControler sControler = new RoslinaPraceOgrodoweControler(model,sView);
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
                RoslinaPraceOgrodoweView sView = new RoslinaPraceOgrodoweView(model);
				RoslinaPraceOgrodoweControler sControler = new RoslinaPraceOgrodoweControler(model,sView);
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