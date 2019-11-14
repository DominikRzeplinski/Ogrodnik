package Ogrod.controler;
import Ogrod.model.OgrodModel;
import Ogrod.view.OgrodView;
import java.awt.event.*;
import Slowniki.model.*;
import Slowniki.view.*;
import Slowniki.controler.*;
import Gleba.model.*;
import RodzajeRoslin.model.*;
import PoraSadzenia.model.*;
import Roslina.model.*;
import Roslina.view.*;
import Roslina.controler.*;

public class OgrodControler{
	private OgrodModel model;
    private OgrodView view;
    
    public OgrodControler(OgrodModel model, OgrodView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCBOgrodActionListener(new CBOgrodListener());
		view.AddDeleteActionListener(new DeleteActionListener());
		view.AddDetailsActionListener(new DetailsActionListener());
		view.AddMenuGlebyActionListener(new MenuGlebyActionListener());
		view.AddMenuRodzajeRoslinActionListener(new MenuRodzajeRoslinActionListener());
		view.AddMenuPoraSadzeniaActionListener(new MenuPoraSadzeniaActionListener());
		view.AddMenuListaRoslinActionListener(new MenuListaRoslinActionListener());
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                model.SetNazwa(view.GetTFNazwa());
                model.SetOpis(view.GetTADescription());
                model.SetMiasto(view.GetTFMiasto());
                model.SetUlica(view.GetTFUlica());
                model.SetNrDomu(view.GetTFNrDomu());
                model.SetNrMieszkania(view.GetTFNrMieszkania());
                model.SetKodPocztowy(view.GetTFKodPocztowy());
                model.Update();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        } 
    }
	public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.GetData(view.GetCBOgrod());
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class CBOgrodListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                
				model.GetData(view.GetCBOgrod());
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class DeleteActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                
				model.DeleteData();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class DetailsActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	
	public class MenuGlebyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				SlownikModel model = new GlebaModel();
				SlownikListaView view = new SlownikListaView(model);
				SlownikListaControler controler = new SlownikListaControler(model,view); 
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class MenuRodzajeRoslinActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				SlownikModel model = new RodzajeRoslinModel();
				SlownikListaView view = new SlownikListaView(model);
				SlownikListaControler controler = new SlownikListaControler(model,view); 
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class MenuPoraSadzeniaActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				SlownikModel model = new PoraSadzeniaModel();
				SlownikListaView view = new SlownikListaView(model);
				SlownikListaControler controler = new SlownikListaControler(model,view); 
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class MenuListaRoslinActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				RoslinaModel model = new RoslinaModel();
				RoslinaListaView view = new RoslinaListaView(model);
				RoslinaListaControler controler = new RoslinaListaControler(model,view); 
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
}