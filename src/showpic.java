import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class showpic extends JInternalFrame{
	
	public MainFrame mf;
	public Container container ;
	
	public showpic(MainFrame mf){
		this.mf = mf;
		this.setVisible(true);
		this.setTitle("Picture Viewer");
		this.setSize(200, 170);
		container = this.getContentPane();
		container.setLayout(new FlowLayout());
		JLabel pic = new JLabel();
		try {
			Icon ic = new ImageIcon(imageToBufferedImage(ImageIO.read(mf.getClass().getResource(mf.pic()))));
			pic.setIcon(ic);
			this.setSize(ic.getIconWidth(), ic.getIconHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		container.add(pic);
		setClosable(true);
		this.setResizable(true);
		
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
	
	public static BufferedImage imageToBufferedImage(final Image image)
	{
		final BufferedImage bufferedImage =
		         new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g2 = bufferedImage.createGraphics();
	      g2.drawImage(image, 0, 0, null);
	      g2.dispose();
	      return bufferedImage;
	}

}