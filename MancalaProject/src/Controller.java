import javax.swing.*;

public class Controller {
	private Model data;
	private JFrame frame;
	
	/**
	 * Constructor
	 * @param frame
	 */
	public Controller(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Returns a JPanel containing start buttons, which initialize the model.
	 * @return
	 */
	public JPanel startScreen() {
		JPanel p = new JPanel();
		
		JButton button4 = new JButton("4 Stones");
		button4.addActionListener(l -> { 
			data = new Model(4); 
			//attach listener here?
			frame.remove(p);
			frame.repaint();
			});
		
		JButton button5 = new JButton("5 Stones");
		button5.addActionListener(l -> { 
			data = new Model(5); 
			//attach listener here?
			frame.remove(p);
			frame.repaint();
			});
		
		p.add(button4);
		p.add(button5);
		return p;
	}
	
	
}
