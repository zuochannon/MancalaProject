import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class MancalaTester {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel masterPanel = new JPanel(new CardLayout());
		
		frame.add(masterPanel);
		Controller c = new Controller(masterPanel);
		c.startScreen();
		
		
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
}
