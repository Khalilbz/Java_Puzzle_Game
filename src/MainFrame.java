import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class MainFrame extends JFrame implements ActionListener{
	
	public JDesktopPane dp = new JDesktopPane();
	public Menu m = new Menu();
	public Settings setting = new Settings(this);
	public String history = "History of wins : \n";

	public MainFrame(){
		//System.out.print(this.setting.box.getSelectedItem().toString());
		//MainFrame conf
		this.setVisible(true);
		this.setSize(500, 500);
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		this.setTitle("Puzzle Game");
		
		//Menu Bar conf
		this.setJMenuBar(m);
		m.newg.addActionListener(this);
		m.sett.addActionListener(this);
		m.hist.addActionListener(this);
		m.exit.addActionListener(this);
		setting.bpic.addActionListener(this);
		setting.bclose.addActionListener(this);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Desktop Pane conf
		this.setContentPane(dp);
		dp.add(setting);
		
	}
	
	public String pic(){
		return this.setting.box.getSelectedItem().toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == m.newg){
			dp.add(new GameFrame(this,Integer.parseInt(setting.size.getText()),Integer.parseInt(setting.width.getText()),Integer.parseInt(setting.height.getText()),this.pic()));
		}
		if(e.getSource() == m.sett){
			setting.setVisible(true);
		}
		if(e.getSource() == m.hist){
			JOptionPane.showMessageDialog(null, this.history,"HISTORY", JOptionPane.INFORMATION_MESSAGE);
		}

		if(e.getSource() == m.exit){
			
		}
		if(e.getSource() == setting.bpic){
			dp.add(new showpic(this));
		}
		if(e.getSource() == setting.bclose){
			setting.setVisible(false);
		}
		
	}
}
