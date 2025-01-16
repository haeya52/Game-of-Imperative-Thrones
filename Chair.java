
/**
 * CS 463 Homework #7 Game of Imperative Thrones
 * 
 * This class represents a chair of the Musical Chairs Game.
 * @author Hailey Lee
 */
public class Chair {
	private String name;				//name of the chair C1, C2, ... C(n - 1)
	private volatile boolean taken;		//status of the chair
	
	/**
	 * Constructor
	 * @param name		name of the chair
	 */
	public Chair(String name) {
		this.name = name;
		this.taken = false;
	}
	
	/**
	 * Getter for the name of the chair.
	 * @return			name of the chair
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for the status of the chair.
	 * @return			status of the chair
	 */
	public boolean isTaken() {
		return this.taken;
	}
	
	/**
	 * Setter for the status of the chair.
	 * @param taken		status of the chair
	 */
	public void changeStatus(boolean taken) {
		this.taken = taken;
	}
}
