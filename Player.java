
import java.util.*;

/**
 * CS 463 Homework #7 Game of Imperative Thrones
 * 
 * This class represent a player of the Musical Chairs Game.
 * @author Hailey Lee
 */
public class Player extends Thread{
	private String name;			//name of the player 
	public MusicalChairsAlg mca;	//musical chairs algorithm
	
	/**
	 * Constructor
	 * @param name		name of the player
	 * @param mca		musical chairs algorithm
	 */
	public Player(String name, MusicalChairsAlg mca) {
		this.name = name;
		setName(this.name);
		this.mca = mca;
	}
	
	/**
	 * Get ready for the game
	 * - wait for all players to be ready
	 */
	public void getReady() {
		try {
			this.mca.waitForPlayers();
		} catch (Exception e) {
			System.err.println("Players Interrupted: " + e.toString());
		}
	}

	/**
	 * Obtain a chair and update status accordingly
	 */
	public synchronized Chair obtainChair() {
		Iterator<Chair> i = this.mca.chairs.iterator();
		boolean found = false;
		Chair c;
		
		//look for a chair
		while (i.hasNext() && !found && (this.mca.numPlayersDone < this.mca.numPlayers - 1)) {
			c = i.next();
			
			//for (int j = 0; j < this.mca.chairs.size(); j++) {
			//	System.out.println(this.mca.chairs.get(j).getName() + " available for " + this.name);
			//}
			
			//a chair found
			if (!c.isTaken() && (this.mca.numPlayersDone < this.mca.numPlayers - 1)) {
				c.changeStatus(true);
				this.mca.winner = this.name;
				this.mca.numPlayersDone++;
				return c;
			}
		}

		//lost	
		this.mca.loser = this.name;
		return null;
	}

	/**
	 * Play the game.
	 * 1) Get ready for the game.
	 * 2) Obtain a chair.
	 * 3) Print the status of the player.
	 * 		-> done when lost or won the game
	 * 		-> get ready for the next round
	 */
	@Override
	public void run() {
		Chair found;
		while (true) {
			//beginning of the round
			getReady();
			
			//get the chair
			found = obtainChair();
			
			if (found != null) {
				System.out.println(this.name + " sat in " + found.getName() + ".");
			} else
				break;
			
			//end of the round
			getReady();
		}
	}

} //end of class
