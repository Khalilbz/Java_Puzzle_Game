import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class GameFrame extends JInternalFrame implements MouseListener{

	public BufferedImage[][] mat ;
	public JLabel[][] lbs ;
	public BufferedImage img ;
	public Container container ;
	public int width = 300;
	public int height = 300;
	public int size = 0;
	int pwidth;
	String pic = "";
	public int clics = 0;
	public boolean winner = false;
	public MainFrame mf;
	
	public GameFrame(MainFrame mf,int size, int width, int height,String pic){
		//Frame conf
		this.mf = mf;
		this.pic = pic;
		this.size = size;
		this.width = width;
		this.height = height;
		this.pwidth = width / size;
		this.setVisible(true);
		this.setTitle("Click to start ^_^");
		int xsize = size;
		if(size > 20)xsize = size /2 ;
		this.setSize(width+xsize*5, height+xsize*5+20);
		container = this.getContentPane();
		container.setLayout( new GridLayout(size,size));
		JLabel x = new JLabel();
		setClosable(true);
		
		//Getting the picture
		try {
			img = ImageIO.read(this.getClass().getResource(this.pic));
			img = imageToBufferedImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Cutting the picture to tiles in mat[][]
		cutpics();

		//Creating all the JLables
		createlabels();
		//Clearing the first tile
		mat[1][1] = makeItWhite();
		//lbs[1][1].setName("E");
		//Copying the tiles in the labels
		mattolabels();
		if(mf.setting.chk.isSelected())shuffle();
		this.validate();
	}

	public void cutpics(){
		mat = new BufferedImage[100][100];
		for(int i = 1 ; i<= size ; i++){
			mat[i] = new BufferedImage[100];
			for(int j = 1 ; j<= size ; j++){
				//System.out.println("i = "+i+", j = "+j+", size = "+size+", img.getSubimage("+(i-1)*pwidth+", "+(j-1)*pwidth+", "+pwidth+", "+pwidth+")");
				mat[i][j] = imageToBufferedImage(img.getSubimage((j-1)*pwidth, (i-1)*pwidth, pwidth, pwidth));
				/* To save the pictures
				File outputfile = new File("/home/ixi/Desktop/pics/"+i+"-"+j+".png");
				System.out.println(i+"-"+j+".png");
				try {
					ImageIO.write(mat[i][j], "png", outputfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
		}
	}

	public void createlabels() {
		lbs = new JLabel[100][];
		for(int i = 1 ; i<= size ; i++){
			lbs[i] = new JLabel[100];
			for(int j = 1 ; j<= size ; j++){
				lbs[i][j] = new JLabel();
				lbs[i][j].setName(i+"-"+j);
				lbs[i][j].setText(i+"-"+j);
				lbs[i][j].addMouseListener(this);
				container.add(lbs[i][j]);
			}
			
		}
	}
	
	public void mattolabels() {
		for(int i = 1 ; i<= size ; i++){
			for(int j = 1 ; j<= size ; j++){
				lbs[i][j].setIcon(new ImageIcon(mat[i][j]));
			}
			
		}
	}
	
	public void shuffle(){
		for(int i = 1 ; i<= size*size ; i++){
			for(int j = 1 ; j<= size*size ; j++){
				Random rnd = new Random();
				int x1 = rnd.nextInt(this.size-1)+2;
				int y1 = rnd.nextInt(this.size-1)+2;
				int x2 = rnd.nextInt(this.size-1)+2;
				int y2 = rnd.nextInt(this.size-1)+2;
				Point p1 = new Point();p1.x = x1;p1.y = y1;
				Point p2 = new Point();p2.x = x1;p2.y = y2;
				
				if( (x1!=1 && y1 != 1))if( (x2!=1 && y2 != 1) )changeto(p1,p2);
			}
		}
	}
	
	

	public BufferedImage makeItWhite(){
		BufferedImage anImage = new BufferedImage ( width, height, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = anImage.createGraphics();
		g.setColor( new Color ( 0, 0, 0, 0 ));
		g.fillRect(0, 0, pwidth, pwidth);
		g.dispose();
	    return anImage;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!this.winner){
			this.clics++;
			this.setTitle("New Game (Picture:"+mf.pic()+") (Grid:"+this.size+") (Clics:"+this.clics+") ");
			//System.out.println( ((JLabel)e.getSource()).getName() );
			String str = ((JLabel)e.getSource()).getText();
			Point p = get(str);
			
			//System.out.println("["+i+"]-["+j+"]");
	
			Point pup = new Point();pup.x = p.x;pup.y = p.y-1;
			Point pdown = new Point();pdown.x = p.x;pdown.y = p.y+1;
			Point pright = new Point();pright.x = p.x+1;pright.y = p.y;
			Point pleft = new Point();pleft.x = p.x-1;pleft.y = p.y;
			
			//if()//make the condition and to make sure that it's inside the matrice test if it's null or not :) :)
			if(isempty(pup)){ changeto(p,pup); }
			if(isempty(pdown)){ changeto(p,pdown); }
			if(isempty(pright)){ changeto(p,pright); }
			if(isempty(pleft)){ changeto(p,pleft); }
			/*if(lbs[0][0] == null){
				System.out.println("Null");
			}else{
				System.out.println("Not null");
			}*/
			win();
		}
	}
	
	public void changeto(Point a,Point b){
		//System.out.print(a.x+","+a.y+" <-> "+b.x+","+b.y);
		String name = lbs[a.x][a.y].getName();
		Icon icon = lbs[a.x][a.y].getIcon();
		lbs[a.x][a.y].setName(lbs[b.x][b.y].getName());
		lbs[a.x][a.y].setIcon(lbs[b.x][b.y].getIcon());
		lbs[b.x][b.y].setName(name);
		lbs[b.x][b.y].setIcon(icon);
	}
	
	public boolean isempty(Point pup){
		return check_point(pup) && get(lbs[pup.x][pup.y].getName()).x==1 && get(lbs[pup.x][pup.y].getName()).y==1;
	}
	
	public Point get(String str){
		Point p = new Point();
		p.x = Integer.parseInt(str.substring(0,str.indexOf("-")));
		p.y = Integer.parseInt(str.substring(str.indexOf("-")+1,str.length()));
		return p;
	}
	
	public boolean check_point(Point p){
		return (p.x<=size && p.x>=1 && p.y<=size && p.y>=1);
	}
	
	public void win(){
		boolean win = true;
		for(int i = 1 ; i<= size ; i++){
			for(int j = 1 ; j<= size ; j++){
				if(!lbs[i][j].getName().equals(i+"-"+j)){
					win = false;
				}
			}
			
		}
		if(win){
			String str = JOptionPane.showInputDialog("Enter your name please");
			String msg = "Hi "+str+" You have won ^_^ your score is ( "+((this.size*this.size)-this.clics)+" ) \n";
			this.setTitle(msg);
			mf.history += msg;
			this.winner = true;
		}/*else{
			System.out.println("Loser");
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
