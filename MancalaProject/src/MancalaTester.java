import java.awt.Dimension;

import javax.swing.JFrame;


public class MancalaTester {
	public static void main(String[] args) {
		JFrame frame = new JFrame(); // borderlayout is default
        
		Controller c = new Controller(frame);
		frame.add(c.startScreen());
		
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
}
