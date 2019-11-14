package Ogrod.controler;
import Ogrod.model.OgrodModel;
import Ogrod.view.OgrodView;
import java.awt.event.*;

public class OgrodControler{
	private OgrodModel model;
    private OgrodView view;
    
    public OgrodControler(OgrodModel model, OgrodView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCBOgrodActionListener(new CBOgrodListener());
		view.AddDeleteActionListener(new AddDeleteActionListener());
		view.AddDetailsActionListener(new AddDetailsActionListener());
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
	public class AddDeleteActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                
				model.DeleteData();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
	public class AddDetailsActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFNazwa() + "'");
            }
        }
    }
}