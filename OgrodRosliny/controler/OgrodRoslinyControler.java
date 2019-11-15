package OgrodRosliny.controler;
import OgrodRosliny.model.OgrodRoslinyModel;
import OgrodRosliny.view.OgrodRoslinyView;
import java.awt.event.*;

public class OgrodRoslinyControler{
	private OgrodRoslinyModel model;
    private OgrodRoslinyView view;
    
    public OgrodRoslinyControler(OgrodRoslinyModel model, OgrodRoslinyView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCBRoslinaActionListener(new CBRoslinaListener());
		view.Show();
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.SetIdRosliny(view.GetCBRoslina());
                model.Update();
                view.dispose();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '");
            }
        }
    }
	public class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                view.dispose();
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '");
            }
        }
    }
	public class CBRoslinaListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.roslina.GetData(view.GetCBRoslina());
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '");
            }
        }
    }
}