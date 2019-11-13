package ViewHelper;
import javax.swing.InputVerifier;
import javax.swing.text.JTextComponent;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class InputVerifierLength extends InputVerifier{
	private int lenght;
	public InputVerifierLength(int lenght){
		super();
		this.lenght = lenght;
	}
	@Override
	public boolean verify(JComponent input) {
        final JTextComponent source = (JTextComponent)input;
        String text = source.getText();
        if ((text.length() != 0) && (text.length() > lenght)) 
          return false;
        return true;
    }
	
	@Override
	public boolean shouldYieldFocus(JComponent input)
	{
		input.setForeground(Color.black);
		boolean valid = verify(input);
		if(!valid)
		{
			input.setForeground(Color.red);
			Toolkit.getDefaultToolkit().beep();
		}
		return valid;
	}
}

