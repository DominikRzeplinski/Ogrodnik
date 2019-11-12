package Gleba.controler;
import Gleba.model.GlebaModel;
import Gleba.view.GlebaView;
import java.awt.event.*;

public class GlebaControler{
	private GlebaModel model;
    private GlebaView view;
    
    public GlebaControler(GlebaModel model, GlebaView view){
        this.model = model;
        this.view = view;
		view.AddSaveActionListener(new SaveListener());
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
        }
    }
}