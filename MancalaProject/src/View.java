import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View implements ChangeListener{
	private Model data;
	private JPanel panel;
	
	public View(Model m, JPanel p) {
		data = m;
		panel = p;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JPanel nextCard = new JPanel();
		Pit p = new Pit(data);
		for(String key : Controller.getPitKeys()) {
			nextCard.add(p.createMancalaPit(key));
		}
		panel.add(nextCard);
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.next(panel);
	}

}
