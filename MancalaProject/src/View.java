import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View implements ChangeListener{
	private Model data;
	private JFrame frame;
	
	public View(Model m, JFrame f) {
		data = m;
		frame = f;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		frame.removeAll();
		frame.add(Controller.test());
		frame.repaint();
		
	}

}
