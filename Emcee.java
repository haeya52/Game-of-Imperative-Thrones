
import java.util.*;

/**
 * CS 463 Homework #7 Game of Imperative Thrones
 * 
 * This class represents an emcee of the Musical Chairs Game.
 * @author Hailey Lee
 */
public class Emcee extends Thread{
	
	public MusicalChairsAlg mca;	//Musical Chairs Algorithm
	public int waitTime; 			//wait time of the game
	
	/**
	 * Constructor
	 * @param mca		musical chairs algorithm
	 */
	public Emcee(MusicalChairsAlg mca) {
		this.mca = mca;
		this.waitTime = 1500;
		this.mca.rounds = this.mca.numPlayers - 1;
	}
	
	/**
	 * Print the message "BEGIN N player" and set the players (threads).
	 */
	public void startGame() {
		System.out.println("BEGIN " + this.mca.numPlayers + " players");
		
		for (Player p: this.mca.players)
			p.start();
	}
	
	/**
	 * Print the message "round N".
	 */
	public void startRound() {
		System.out.println();
		System.out.println("round " + this.mca.currentRound);
	}
	
	/**
	 * Wait while the music is on.
	 * @throws Exception
	 */
	public void turnMusicOn(){
		//System.out.println("music on");
		try {
			sleep(waitTime);
		} catch (Exception e) {
			System.err.println("Music Interrupted: " + e.toString());
		}	
	}
	
	/**
	 * Turn off the music and wait for players to find chairs.
	 */
	public void endRound() {
		System.out.println("music off");
		
		//wake up all players to find a chair
		this.mca.wakeUpPlayers();
		
		//wait until all players find a chair
		try {
			Thread.sleep(waitTime);
		} catch (Exception e) {
			System.err.println("Emcee Interrupted: " + e.toString());
		}
	}
	
	/**
	 * Announce the loser/winner at the end of each round.
	 */
	public void announceLoser() {
		System.out.println(this.mca.loser + " lost");
	}
	
	public void announceWinner() {
		System.out.println("\n");
		System.out.println(this.mca.winner + " wins!");
	}
	
	/**
	 * Reset the round.
	 */
	public synchronized void resetRound() {
		//reset chairs & remove the last chair
		for (Chair c : this.mca.chairs)
			c.changeStatus(false);
		this.mca.chairs.remove(this.mca.chairs.size() - 1);

		//Decrement the loser & wake up all players for the next round
		this.mca.numPlayers--;	
		this.mca.numPlayersDone = 0;
		this.mca.wakeUpPlayers();
	}
	
	/**
	 * End the game.
	 */
	public void endGame() {
		System.out.println("END\n");	
	}
	
	/**
	 * Run the game
	 * 1) Start the game
	 * 2) For each round
	 * 		-> start the round
	 * 		-> end the round
	 * 		-> reset the round
	 * 3) End the game
	 */
	@Override
	public void run() {
		//beginning of the game
		startGame();
		
		//round 1 ~ (N - 1)
		for (this.mca.currentRound = 1; this.mca.currentRound <= this.mca.rounds; this.mca.currentRound++) {
			//beginning of the round
			startRound();
			
			try {
				turnMusicOn();
			} catch (Exception e) {
				System.err.println("Music Interrupted: " + e.toString());
			}
			
			//end of the round & announce the loser
			endRound();
			announceLoser();
			
			//reset the round
			resetRound();
			
		}
		
		//announce winner & end of the game
		announceWinner();
		endGame();
	}
	
}
