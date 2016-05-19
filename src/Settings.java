import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Settings extends JInternalFrame{
	
	public Container container ;
	public JTextField width;
	public JTextField size;
	public JTextField height;
	public JComboBox box;
	public MainFrame mf;
	public JButton bpic;
	public JButton bclose;
	public JCheckBox chk;
	
	public Settings(MainFrame mf){
		this.mf = mf;
		this.setVisible(false);
		this.setTitle("Game Settings");
		this.setSize(200, 230);
		container = this.getContentPane();
		container.setLayout(new FlowLayout());
		JLabel xsize = new JLabel("Size : ");
		size = new JTextField(10);size.setText("6");
		JLabel xwidth = new JLabel("width : ");
		width = new JTextField(10);width.setText("500");
		JLabel xheight = new JLabel("height : ");
		height = new JTextField(10);height.setText("500");
		JLabel xbox = new JLabel("Picture : ");
		JLabel xchk = new JLabel("Shuffle : ");
		chk = new JCheckBox();
		box = new JComboBox();//baby.png  banana.png  dizert.png  dragon.png  flower.png  shark.png
		box.addItem("dragon.png");
		box.addItem("baby.png");
		box.addItem("banana.png");
		box.addItem("dizert.png");
		box.addItem("flower.png");
		box.addItem("shark.png");
		bpic = new JButton("Show The Picture");
		JButton b = new JButton("Save Settings");
		bclose = new JButton("Close");
		container.add(xsize);
		container.add(size);
		container.add(xwidth);
		container.add(width);
		container.add(xheight);
		container.add(height);
		container.add(xbox);
		container.add(box);
		container.add(xchk);
		container.add(chk);
		container.add(bpic);
		container.add(bclose);
		//container.add(b);
		//setClosable(true);
		
		
		//Getting the picture
		/**
		try {
			img = ImageIO.read(this.getClass().getResource("dizert.png"));
			img = imageToBufferedImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**/
		
		this.validate();
	}

}
