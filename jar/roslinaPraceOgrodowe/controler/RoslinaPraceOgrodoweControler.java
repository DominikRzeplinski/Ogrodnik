package roslinaPraceOgrodowe.controler;
import roslinaPraceOgrodowe.model.RoslinaPraceOgrodoweModel;
import roslinaPraceOgrodowe.view.RoslinaPraceOgrodoweView;
import java.awt.event.*;

public class RoslinaPraceOgrodoweControler{
	private RoslinaPraceOgrodoweModel model;
    private RoslinaPraceOgrodoweView view;
    
    public RoslinaPraceOgrodoweControler(RoslinaPraceOgrodoweModel model, RoslinaPraceOgrodoweView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCancelActionListener(new CancelListener());
		view.AddCBPraceOgrodoweActionListener(new CBPraceOgrodoweListener());
		view.Show();
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.SetIdPraceOgrodowe(view.GetCBPracaOgrodowa());
				model.SetIlosc(view.GetTFIlosc());
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
	public class CBPraceOgrodoweListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
				model.praceOgrodowe.GetData(view.GetCBPracaOgrodowa());
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '");
            }
        }
    }
}