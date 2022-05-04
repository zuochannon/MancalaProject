
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
	 *
	 */
	public void startScreen() {
		JPanel p = new JPanel();
		String[] styles = new String[] {"Style 1", "Style 2" };
		JComboBox<String> option = new JComboBox<String>(styles);
		
		JButton button4 = new JButton("4 Stones");
		button4.addActionListener(l -> { 
			data = new Model(4); 
			View v = new View(data, panel, option.getSelectedItem().toString());
			data.attach(v);
			data.update();
			});
		
		JButton button5 = new JButton("5 Stones");
		button5.addActionListener(l -> { 
			data = new Model(5);
			View v = new View(data, panel, option.getSelectedItem().toString());
			data.attach(v);
			data.update();
			});
		
		p.add(button4);
		p.add(button5);
		p.add(option);
		
		
		
		panel.add(p, "StartScreen");
	}
	
	public static JButton undoButton() {
		JButton button = new JButton("Undo");
		button.addActionListener(( l -> {
			data.undo();
		}));
		return button;
	}
	public static ArrayList<String> getPitKeys() {
		return pitKeys;
	}
	
}
