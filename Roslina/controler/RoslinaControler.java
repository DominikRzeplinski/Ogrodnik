package roslina.controler;
import roslina.model.RoslinaModel;
import roslina.view.RoslinaView;
import java.awt.event.*;
import roslinaPraceOgrodowe.model.*;
import roslinaPraceOgrodowe.view.*;
import roslinaPraceOgrodowe.controler.*;

public class RoslinaControler{
	private RoslinaModel model;
    private RoslinaView view;
    
    public RoslinaControler(RoslinaModel model, RoslinaView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddPraceOgrodoweActionListener(new PraceOgrodoweListener());
		view.Show();
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                model.SetNazwa(view.GetTFEdit());
                model.SetOpis(view.GetTADescription());
				model.SetIdGleby(view.GetCBGleba());
				model.SetIdRodzaj(view.GetCBRodzaj());
				model.SetIdPoraSadzenia(view.GetCBPoraSadzenia());
                model.Update();
                view.dispose();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFEdit() + "'");
            }
        }
    }
	public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                view.dispose();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFEdit() + "'");
            }
        }
    }
	public class PraceOgrodoweListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                if (model.GetId() ==0)
					return; 
                RoslinaPraceOgrodoweModel m = new RoslinaPraceOgrodoweModel(model.GetId());
                RoslinaPraceOgrodoweListaView v = new RoslinaPraceOgrodoweListaView(m);
                RoslinaPraceOgrodoweListaControler c = new RoslinaPraceOgrodoweListaControler(m,v);
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFEdit() + "'");
            }
        }
    }
}