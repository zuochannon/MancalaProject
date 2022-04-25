import java.util.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	private static int 	NUM_PITS = 14;
	
	private HashMap<String, Integer> mancalaPits;	// <name of pit, number of stones in the pit>
	private String					lastKey;		// remembers the last key whose value was changed
	private int 					previousValue;	// remembers the last value of lastKey before value was changed
	private ArrayList<ChangeListener> listeners;
	
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
		mancalaPits.put("MA", 0);
		mancalaPits.put("B1", numStones);
		mancalaPits.put("B2", numStones);
		mancalaPits.put("B3", numStones);
		mancalaPits.put("B4", numStones);
		mancalaPits.put("B5", numStones);
		mancalaPits.put("B6", numStones);
		mancalaPits.put("BM", 0);
		
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
			update();
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
			update();
			return true;
		}
		return false;
	}
	
	public boolean reversePit(String key) {
		if(key == lastKey) {
			mancalaPits.replace(key, previousValue);
			update();
			return true;
		}
		return false;
	}
	
	/** 
	 * Sets the value of the specified key to zero. Used to "empty" a pit after it is clicked on.
	 * 
	 * @param key	name of key
	 */
	public boolean clearPit(String key) {
		if(mancalaPits.containsKey(key)) {
			lastKey = key;
			previousValue = mancalaPits.replace(key, 0);
			update();
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
	 * Helper method that notifies attached listeners of any changes done to the model.
	 */
	private void update() {
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	
	}
}
