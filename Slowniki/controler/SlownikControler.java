package Slowniki.controler;
import Slowniki.model.SlownikModel;
import Slowniki.view.SlownikView;
import java.awt.event.*;

public class SlownikControler{
	private SlownikModel model;
    private SlownikView view;
    
    public SlownikControler(SlownikModel model, SlownikView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
		view.AddCancelActionListener(new CancelListener());
		view.Show();
    }
	public class SaveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            try {
                String nazwa = view.GetTFEdit();
                String opis = view.GetTADescription();
                model.Update(nazwa,opis);
                
            } catch (NumberFormatException nfex) {
                view.showError("Bad input: '" + view.GetTFEdit() + "'");
            }
			view.dispose();
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