import java.util.ArrayList;
import javax.swing.JButton;

public class Pit {
	private Model data;
	private ArrayList<String> pitKeys;
	
	public Pit(Model data) {
		this.data = data;
		pitKeys = new ArrayList<String>();
		for(int i = 1; i < 7; i++) {
			pitKeys.add("A" + i);
		}
		pitKeys.add("AM"); //index 6
		for(int i = 1; i < 7; i++) {
			pitKeys.add("B" + i);
		}
		pitKeys.add("BM"); //index 13
		
	}
	
	public JButton createMancalaPit(String key) {
		//get icon from style strategy?
		JButton button = new JButton(data.getMancalaPits().get(key) + ""); //add icon here later
		if(!key.substring(0,1).equals(data.getPlayer()) || key.substring(1, 2).equals("M") || data.getMancalaPits().get(key) == 0) {  //button does not belong to current player or button is mancala or mancala has no stones
			button.setEnabled(false);
		}
		button.addActionListener((l -> 
		{
			if(!data.hasUsedUndo()) {
				data.restoreUndoCount();
			}
			data.setUsedUndo(false);
			data.saveState();
			String player = data.getPlayer();
			int currentIndex = pitKeys.indexOf(key);
			int numOfStones = data.getMancalaPits().get(key);
			data.clearPit(key);
			while (numOfStones > 0) {
				currentIndex++;
				if ((currentIndex == 5 && player.equals("B")) || (currentIndex == 13 && player.equals("A"))) { // skips opponent's mancala																									
					currentIndex++;
				}
				if (currentIndex == 14) {
					currentIndex = 0;
				}
				String currentPit = pitKeys.get(currentIndex);
				data.incPit(currentPit);
				numOfStones--;
			}
			int lastPitStones = data.getMancalaPits().get(pitKeys.get(currentIndex));
			String endingSide = pitKeys.get(currentIndex).substring(0, 1); // A or B
			String endNumber = pitKeys.get(currentIndex).substring(1); // 1 to 6
			if (lastPitStones == 1 && endingSide.equals(player)
					&& !(pitKeys.get(currentIndex).equals("AM") || pitKeys.get(currentIndex).equals("BM"))) {
				// pit was empty and is on current player's side and is not a mancala = take
				// stones from opponent
				String opponentPit;
				switch (endNumber) { // gets the correct pit to take stones from
				case ("1"):
					opponentPit = getOpponentSide() + "6";
					break;
				case ("2"):
					opponentPit = getOpponentSide() + "5";
					break;
				case ("3"):
					opponentPit = getOpponentSide() + "4";
					break;
				case ("4"):
					opponentPit = getOpponentSide() + "3";
					break;
				case ("5"):
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
				takenStones = data.getMancalaPits().get(pitKeys.get(currentIndex));
				data.clearPit(pitKeys.get(currentIndex));
				data.incPit(currentMancala, takenStones);
			}
			if ((pitKeys.get(currentIndex).equals("AM") && player.equals("A"))
					|| (pitKeys.get(currentIndex).equals("BM") && player.equals("BM"))) {
				data.update();
				// player gets another turn
			} else {
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
}