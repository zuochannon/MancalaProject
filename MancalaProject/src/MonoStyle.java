import javax.swing.ImageIcon;
import java.awt.Image;
public class MonoStyle implements StyleStrategy {
	
	/**
	 * Method required by StyleStrategy interface
	 */
	@Override
	public ImageIcon getImage(int stones) {
		ImageIcon pit;
		switch (stones) {
		default:
			pit = new ImageIcon("MonoStyle/EmptyPit.png");
			break;
		case (1):
			pit = new ImageIcon("MonoStyle/Mono1.png");
			break;
		case (2):
			pit = new ImageIcon("MonoStyle/Mono2.png");
			break;
		case (3):
			pit = new ImageIcon("MonoStyle/Mono3.png");
			break;
		case (4):
			pit = new ImageIcon("MonoStyle/Mono4.png");
			break;
		case (5):
			pit = new ImageIcon("MonoStyle/Mono5.png");
			break;
		case (6):
			pit = new ImageIcon("MonoStyle/Mono6.png");
			break;
		case (7):
			pit = new ImageIcon("MonoStyle/Mono7.png");
			break;
		case (8):
			pit = new ImageIcon("MonoStyle/Mono8.png");
			break;
		case (9):
			pit = new ImageIcon("MonoStyle/Mono9.png");
			break;
		case (10):
			pit = new ImageIcon("MonoStyle/Mono10.png");
			break;
		case (11):
			pit = new ImageIcon("MonoStyle/Mono11.png");
			break;
		case (12):
			pit = new ImageIcon("MonoStyle/Mono12.png");
			break;
		case (13):
			pit = new ImageIcon("MonoStyle/Mono13.png");
			break;
		case (14):
			pit = new ImageIcon("MonoStyle/Mono14.png");
			break;
		case (15):
			pit = new ImageIcon("MonoStyle/Mono15.png");
			break;
		case (16):
			pit = new ImageIcon("MonoStyle/Mono16.png");
			break;
		case (17):
			pit = new ImageIcon("MonoStyle/Mono17.png");
			break;
		case (18):
			pit = new ImageIcon("MonoStyle/Mono18.png");
			break;
		case (19):
			pit = new ImageIcon("MonoStyle/Mono19.png");
			break;
		case (20):
			pit = new ImageIcon("MonoStyle/Mono20.png");
			break;
		case (21):
			pit = new ImageIcon("MonoStyle/Mono21.png");
			break;
		case (22):
			pit = new ImageIcon("MonoStyle/Mono22.png");
			break;
		case (23):
			pit = new ImageIcon("MonoStyle/Mono23.png");
			break;
		case (24):
			pit = new ImageIcon("MonoStyle/Mono24.png");
			break;
		}
		Image image = pit.getImage(); // transform it 
		Image newimg = image.getScaledInstance(80, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		pit = new ImageIcon(newimg);  // transform it back
		
		return pit;
	}

}
