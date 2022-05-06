
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

public class Controller { //
	private static Model data;
	private JPanel panel;
	private static ArrayList<String> pitKeys = new ArrayList<String>();
	/**
	 * Constructor
	 * @param panel
	 */
	public Controller(JPanel panel) {
		this.panel = panel;
		for(int i = 1; i < 7; i++) {
			pitKeys.add("A" + i);
		}
		pitKeys.add("AM"); //index 6
		for(int i = 1; i < 7; i++) {
			pitKeys.add("B" + i);
		}
		pitKeys.add("BM"); //index 13
		
	}
	
	/**
	 * Displays the starting screen, which allows user to select style and starting stones.
	 */
	public void startScreen() {
		JPanel p = new JPanel();
		String[] styles = new String[] {"Black and White", "Rainbow" };
		JComboBox<String> option = new JComboBox<String>(styles);
		
		JButton button4 = new JButton("3 Stones");
		button4.addActionListener(l -> { 
			data = new Model(3); 
			StyleStrategy style = new MonoStyle();
			View v = new View(data, panel, style);
			data.attach(v);
			data.update();
			});
		
		JButton button5 = new JButton("4 Stones");
		button5.addActionListener(l -> { 
			data = new Model(4);
			StyleStrategy style = new ColorStyle();
			View v = new View(data, panel, style);
			data.attach(v);
			data.update();
			});
		
		p.add(button4);
		p.add(button5);
		p.add(option);
		
		
		
		panel.add(p, "StartScreen");
	}
	
	/**
	 * Creates the undo button
	 * @return undo button
	 */
	public static JButton undoButton() {
		JButton button = new JButton("Undos left: " + data.getRemainingUndos());
		if(data.hasUsedUndo()) {
			button.setEnabled(false);
		}
		button.addActionListener(( l -> {
			data.undo();
		}));
		return button;
	}
	/**
	 * Gets the arraylist of pitkeys
	 * @return pitkeys
	 */
	public static ArrayList<String> getPitKeys() {
		return pitKeys;
	}
	
}
