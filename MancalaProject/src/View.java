import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View implements ChangeListener{
	private Model data;
	private JPanel panel;
	private String style;
	
	public View(Model model, JPanel pit, String style) {
		data = model;
		panel = pit;
		this.style = style;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(data.isGameOver()) {
			JPanel nextCard = new JPanel();
			JLabel winner = new JLabel(data.getWinner());
			nextCard.add(winner);
			panel.add(nextCard);
		}
		else {
			JPanel nextCard = new JPanel();
			Pit pit = new Pit(data);
			GridBagLayout g = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			nextCard.setLayout(g);
			
			boolean forward = true;
			c.gridx = 1;
			c.gridy = 1;
			for(String key : Controller.getPitKeys()) {
				if(key.equals("AM")) { //modifications to place A mancala in correct spot
					c.gridx = 7;
					c.gridy = 0;
					c.gridheight = 2;
					c.fill = GridBagConstraints.VERTICAL;
					nextCard.add(pit.createMancalaPit(key), c);
					c.gridx = 6;
					c.gridy = 0;
					c.gridheight = 1;
					c.fill = GridBagConstraints.NONE;
					forward = false;
				}
				
				else if(key.equals("BM")) {//modifications to place B mancala in correct spot
					c.gridx = 0;
					c.gridy = 0;
					c.gridheight = 2;
					c.fill = GridBagConstraints.VERTICAL;
					nextCard.add(pit.createMancalaPit(key), c);
				}
				
				else if(forward) { //going along bottom row (player a's pits) (left to right)
					nextCard.add(pit.createMancalaPit(key), c);
					c.gridx++;
				}
				else { // going along top row (player b's pits) (right to left)
					nextCard.add(pit.createMancalaPit(key), c);
					c.gridx--;
				}
			}
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 2;
			nextCard.add(Controller.undoButton(), c);
			panel.add(nextCard);
		}
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.next(panel);
	}

}
