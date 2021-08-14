package co.edu.utp.misiontic2022.c2.reto4;

import co.edu.utp.misiontic2022.c2.reto4.view.ConsultaFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

			ConsultaFrame view = new ConsultaFrame();
			view.pack();
			view.setVisible(true);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
