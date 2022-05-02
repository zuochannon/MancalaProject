
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

public class Controller { //
	private Model data;
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
		
		JButton button4 = new JButton("4 Stones");
		button4.addActionListener(l -> { 
			data = new Model(4); 
			View v = new View(data, panel);
			data.attach(v);
			data.update();
			});
		
		JButton button5 = new JButton("5 Stones");
		button5.addActionListener(l -> { 
			data = new Model(5);
			//attach listener here
			
			});
		
		p.add(button4);
		p.add(button5);
		panel.add(p, "StartScreen");
		
	}
	
	public JButton createMancalaPit(String key) {
		JButton button = new JButton(data.getMancalaPits().get(key) + "");
		button.addActionListener((l -> 
				{
						String player = data.getPlayer();
						int currentIndex = pitKeys.indexOf(key);
						int numOfStones = data.getMancalaPits().get(key);
						data.clearPit(key);
						while(numOfStones > 0) {
							currentIndex++;
							if((currentIndex == 5 && player.equals("B")) || (currentIndex == 13 && player.equals("A"))) { //skips opponent's mancala
								currentIndex++;
							}
							if(currentIndex  == 14) {
								currentIndex = 0;
							}
							String currentPit = pitKeys.get(currentIndex);
							data.incPit(currentPit);
							numOfStones--;
						}
						int lastPitStones = data.getMancalaPits().get(pitKeys.get(currentIndex));
						String endingSide = pitKeys.get(currentIndex).substring(0, 1); //A or B
						String endNumber = pitKeys.get(currentIndex).substring(1); //1 to 6
						if(lastPitStones == 1 && endingSide.equals(player)) { //pit was empty and is on current player's side = take stones from opponent
							String opponentPit;
							switch(endNumber) { //gets the correct pit to take stones from
							case("1"):
								opponentPit = getOpponentSide() + "6";
								break;
							case("2"):
								opponentPit = getOpponentSide() + "5";
								break;
							case("3"):
								opponentPit = getOpponentSide() + "4";
								break;
							case("4"):
								opponentPit = getOpponentSide() + "3";
								break;
							case("5"):
								opponentPit = getOpponentSide() + "2";
								break;
							default:
								opponentPit = getOpponentSide() + "1";
								break;
							}
							int takenStones = data.getMancalaPits().get(opponentPit);
							data.clearPit(opponentPit);
							String currentMancala = player + "M";
							data.incPit(currentMancala, takenStones);
						}
						if((pitKeys.get(currentIndex).equals("AM") && player.equals("A")) || (pitKeys.get(currentIndex).equals("BM") && player.equals("BM"))) {
							data.update();
							//player gets another turn
						}
						else {
							data.changePlayer();
							data.update();
						}
				}));
		return button;
	}
	
	private String getOpponentSide() {
		String currentPlayer = data.getPlayer();
		if(currentPlayer.equals("A")) {
			return "B";
		}
		else {
			return "A";
		}
	}
	
	public JPanel test() {
		JPanel panel = new JPanel(new FlowLayout());
		for(String key : pitKeys) {
			panel.add(createMancalaPit(key));
		}
		return panel;
		
	}
	
	public static ArrayList<String> getPitKeys() {
		return pitKeys;
	}
	
}
