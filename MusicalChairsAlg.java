
import java.util.*;

/**
 * CS 463 Homework #7 Game of Imperative Thrones
 * 
 * This class represents an algorithm of the Musical Chairs Game.
 * @author Hailey Lee
 */
public class MusicalChairsAlg {
	public ArrayList<Player> players;		//players
	public ArrayList<Chair> chairs;			//chairs
	
	public Emcee emcee;						//emcee of the game
	
	public int numPlayers;					//the number of players
	public int numPlayersDone;				//the number of players done at each round
	public int currentRound;				//current round
	public int rounds;						//total number of rounds
	
	public String winner;					//winner of the each round - game at the end
	public String loser;					//loser of each round
	
	/**
	 * Constructor
	 * @param numPlayers	the number of players in the game
	 */
	public MusicalChairsAlg(int numPlayers) {
		
		this.numPlayers = numPlayers;
		this.numPlayersDone = 0; 
		this.currentRound = 0;
		this.rounds = numPlayers - 1;
		
		this.winner = "";
		this.loser = "";
		
		this.emcee = new Emcee(this);
		
		this.chairs = new ArrayList<>();
		for (int i = 1; i < numPlayers; i++) {
			Chair c = new Chair("C" + i);
			chairs.add(c);
		}
		
		this.players = new ArrayList<>();
		for (int i = 1; i <= numPlayers; i++) {
			Player p = new Player("P" + i, this);
			players.add(p);
		}
	}
	
	/*
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public ArrayList<Chair> getChairs() {
		return this.chairs;
	}
	*/
	
	/**
	 * Wait for all players to be ready.
	 */
	public synchronized void waitForPlayers(){
		try {
			wait();
		} catch (Exception e) {
			System.err.println("Waiting Interrupted: " + e.toString());
		}
	}
	
	/**
	 * Wake up all players.
	 */
	public synchronized void wakeUpPlayers() {
		notifyAll();

	}

}
