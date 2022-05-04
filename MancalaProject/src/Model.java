import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private static int 	NUM_PITS = 14;
	
	private HashMap<String, Integer> 	mancalaPits;	// <name of pit, number of stones in the pit>
	private HashMap<String, Integer>    previousState;  //remembers the previous values of the mancalaPits
	private ArrayList<ChangeListener> 	listeners;		
	private String 						currentPlayer;
	
	/** 
	 * Constructor initializes the data model with the correct amount of stones in each pit.
	 * Mancala pits start out with zero stones.
	 * 
	 * @param numStones		defines the # of stones each pit will start out with (excluding mancala pits)
	 */
	public Model(int numStones) {
		//if there's a better way to initialize this, lmk lolol
		mancalaPits = new HashMap<String, Integer>(NUM_PITS);
		mancalaPits.put("A1", numStones);
		mancalaPits.put("A2", numStones);
		mancalaPits.put("A3", numStones);
		mancalaPits.put("A4", numStones);
		mancalaPits.put("A5", numStones);
		mancalaPits.put("A6", numStones);
		mancalaPits.put("AM", 0);
		mancalaPits.put("B1", numStones);
		mancalaPits.put("B2", numStones);
		mancalaPits.put("B3", numStones);
		mancalaPits.put("B4", numStones);
		mancalaPits.put("B5", numStones);
		mancalaPits.put("B6", numStones);
		mancalaPits.put("BM", 0);
		currentPlayer = "A";
		listeners = new ArrayList<ChangeListener>();
		System.out.println("Successfully initialized with " + numStones + " stones.");
	}
	
	/** 
	 * Simple add method that finds key and increments its value by one
	 * 
	 * Usage: User clicks on a pit and subsequent pits are given one extra stone
	 * 
	 * @param key	name of key
	 * @return		if key is successfully incremented, return true. otherwise false
	 */
	public boolean incPit(String key) {
		if(mancalaPits.containsKey(key)) {
			mancalaPits.replace(key, mancalaPits.get(key) + 1);
			return true;
		}
		return false;
	}
	
	/** 
	 * Add method that finds key and increments its value by the given amount
	 * 
	 * Usage: User clicks on a pit and subsequent pits are given one extra stone
	 * 
	 * @param key	name of key
	 * @return		if key is successfully incremented, return true. otherwise false
	 */
	public boolean incPit(String key, int amount) {
		if(mancalaPits.containsKey(key)) {
			mancalaPits.replace(key, mancalaPits.get(key) + amount);
			return true;
		}
		return false;
	}
	
	/** 
	 * Simple decrease method that finds key and decrements its value by one.
	 * 
	 * Usage: User undo last turn
	 * 
	 * @param key	name of key
	 * @return		if key is successfully decremented, return true. otherwise false
	 */
	public boolean decPit(String key) {
		if(mancalaPits.containsKey(key)) {
			mancalaPits.replace(key, mancalaPits.get(key) - 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Undos the last move made by a player
	 */
	public void undo() {
		mancalaPits = previousState;
		changePlayer();
		update();
	}
	
	/** 
	 * Sets the value of the specified key to zero. Used to "empty" a pit after it is clicked on.
	 * 
	 * @param key	name of key
	 */
	public boolean clearPit(String key) {
		if(mancalaPits.containsKey(key)) {
			mancalaPits.replace(key, 0);
			return true;
		}
		return false;
		
	}
	
	/** 
	 * Extensively checks integrity of the data structure; should be called after every value update
	 * 
	 * MAINLY FOR DEBUGGING PURPOSES; we can probably remove this later??? idk
	 * 
	 * Passes if:
	 * 		- Contains exactly 14 pits
	 * 		- No keys map to a null value
	 * 
	 * @return 	true if it passes all integrity tests, and false otherwise
	 */
	public boolean checkIntegrity() {
		if(mancalaPits.size() != NUM_PITS)
			return false;
		for(Integer value : mancalaPits.values()) {
			if(value == null)
				return false;
		}			
		return true;
	}
	
	/**
	 * Attaches the provided listener to the model by adding it to the array list.
	 * 
	 * @param l = listener to be added
	 */
	public void attach(ChangeListener l) {
		listeners.add(l);
	}
	
	/**
	 * Notifies attached listeners of any changes done to the model.
	 */
	public void update() {
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	
	}
	
	/**
	 * Swaps the current player
	 */
	public void changePlayer() {
		if(currentPlayer.equals("A")) {
			currentPlayer = "B";
		}
		else {
			currentPlayer = "A";
		}
	}
	
	/**
	 * Checks the pits to see if either side is empty, thus signaling a game over
	 * @return true if pits are empty, false otherwise
	 */
	public boolean isGameOver() {
		ArrayList<String> pitKeys = new ArrayList<String>();
		for(int i = 1; i < 7; i++) {
			pitKeys.add("A" + i);
		}
		pitKeys.add("AM"); //index 6
		for(int i = 1; i < 7; i++) {
			pitKeys.add("B" + i);
		}
		pitKeys.add("BM"); //index 13
		
		int count = 0;
		for(int i = 0; i < 6; i++) {
			count += mancalaPits.get(pitKeys.get(i));
		}
		if(count == 0) {
			return true;
		}
		
		count = 0;
		for(int i = 7; i < 13 ; i++) {
			count += mancalaPits.get(pitKeys.get(i));
		}
		if(count == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Compares the players mancalas and determines who has the most stones, therefore winning
	 * @return player a or b victory string
	 */
	public String getWinner() {
		int aScore = mancalaPits.get("AM");
		int bScore = mancalaPits.get("BM");
		
		if(aScore > bScore) {
			return "The winner is Player A";
		}
		else {
			return "The winner is Player B";
		}
	}
	
	/**
	 * Copys the current state of the pits and stores it in the previous state instance variable.
	 */
	public void saveState() {
		previousState = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : mancalaPits.entrySet())
	    {
	        previousState.put(entry.getKey(), entry.getValue());
	         
	    }
	}
	
	public HashMap<String, Integer> getMancalaPits() {
		return mancalaPits;
	}
	
	public String getPlayer() {
		return currentPlayer;
	}
}
