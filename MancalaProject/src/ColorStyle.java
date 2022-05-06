import java.awt.Image;

import javax.swing.ImageIcon;

public class ColorStyle implements StyleStrategy {
	
	@Override
	public ImageIcon getImage(int stones) {
		ImageIcon pit;
		switch (stones) {
		default:
			pit = new ImageIcon("ColorStyle/EmptyPit.png");
			break;
		case (1):
			pit = new ImageIcon("ColorStyle/Color1.png");
			break;
		case (2):
			pit = new ImageIcon("ColorStyle/Color2.png");
			break;
		case (3):
			pit = new ImageIcon("ColorStyle/Color3.png");
			break;
		case (4):
			pit = new ImageIcon("ColorStyle/Color4.png");
			break;
		case (5):
			pit = new ImageIcon("ColorStyle/Color5.png");
			break;
		case (6):
			pit = new ImageIcon("ColorStyle/Color6.png");
			break;
		case (7):
			pit = new ImageIcon("ColorStyle/Color7.png");
			break;
		case (8):
			pit = new ImageIcon("ColorStyle/Color8.png");
			break;
		case (9):
			pit = new ImageIcon("ColorStyle/Color9.png");
			break;
		case (10):
			pit = new ImageIcon("ColorStyle/Color10.png");
			break;
		case (11):
			pit = new ImageIcon("ColorStyle/Color11.png");
			break;
		case (12):
			pit = new ImageIcon("ColorStyle/Color12.png");
			break;
		case (13):
			pit = new ImageIcon("ColorStyle/Color13.png");
			break;
		case (14):
			pit = new ImageIcon("ColorStyle/Color14.png");
			break;
		case (15):
			pit = new ImageIcon("ColorStyle/Color15.png");
			break;
		case (16):
			pit = new ImageIcon("ColorStyle/Color16.png");
			break;
		case (17):
			pit = new ImageIcon("ColorStyle/Color17.png");
			break;
		case (18):
			pit = new ImageIcon("ColorStyle/Color18.png");
			break;
		case (19):
			pit = new ImageIcon("ColorStyle/Color19.png");
			break;
		case (20):
			pit = new ImageIcon("ColorStyle/Color20.png");
			break;
		case (21):
			pit = new ImageIcon("ColorStyle/Color21.png");
			break;
		case (22):
			pit = new ImageIcon("ColorStyle/Color22.png");
			break;
		case (23):
			pit = new ImageIcon("ColorStyle/Color23.png");
			break;
		case (24):
			pit = new ImageIcon("ColorStyle/Color24.png");
			break;
		}
		Image image = pit.getImage(); // transform it 
		Image newimg = image.getScaledInstance(80, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		pit = new ImageIcon(newimg);  // transform it back
		
		return pit;
	}
}
