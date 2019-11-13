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
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                model.SetNazwa(view.GetTFEdit());
                model.SetOpis(view.GetTADescription());
                model.Update();
                
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
}