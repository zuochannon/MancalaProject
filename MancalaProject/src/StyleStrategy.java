import javax.swing.ImageIcon;

public interface StyleStrategy {
	/**
	 *  Gets the proper image with correct number of stones displayed
	 * @param stones = stones to display
	 * @return corresponding imageicon
	 */
	public ImageIcon getImage(int stones);
}
