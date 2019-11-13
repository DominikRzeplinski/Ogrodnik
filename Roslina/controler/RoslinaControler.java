package Roslina.controler;
import Roslina.model.RoslinaModel;
import Roslina.view.RoslinaView;
import java.awt.event.*;

public class RoslinaControler{
	private RoslinaModel model;
    private RoslinaView view;
    
    public RoslinaControler(RoslinaModel model, RoslinaView view){
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