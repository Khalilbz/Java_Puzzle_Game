import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu extends JMenuBar{
	
	public JMenu f = new JMenu("File");
	public JMenuItem newg = new JMenuItem("New Game"); 
	public JMenuItem sett = new JMenuItem("Settings"); 
	public JMenuItem hist = new JMenuItem("History"); 
	public JMenuItem exit = new JMenuItem("Exit"); 

	public Menu(){
		f.add(newg);
		f.add(sett);
		f.add(hist);
		f.add(exit);
		this.add(f);
	}
	
}
