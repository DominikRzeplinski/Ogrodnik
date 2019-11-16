package ogrod.controler;
import ogrod.model.OgrodModel;
import ogrod.view.OgrodView;
import java.awt.event.*;
import slowniki.model.*;
import slowniki.view.*;
import slowniki.controler.*;
import gleba.model.*;
import rodzajeRoslin.model.*;
import poraSadzenia.model.*;
import roslina.model.*;
import roslina.view.*;
import roslina.controler.*;
import ogrodRosliny.model.*;
import ogrodRosliny.view.*;
import ogrodRosliny.controler.*;
import praceOgrodowe.model.*;

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
		view.AddMenuPraceOgrodoweActionListener(new MenuPraceOgrodoweActionListener());
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
				if (view.GetCBOgrod() ==0)
					return;
                OgrodRoslinyModel model = new OgrodRoslinyModel(view.GetCBOgrod());   
				OgrodRoslinyListaView view = new OgrodRoslinyListaView(model);
				OgrodRoslinyListaControler controler = new OgrodRoslinyListaControler(model,view);           
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
	public class MenuPraceOgrodoweActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				SlownikModel model = new PraceOgrodoweModel();
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