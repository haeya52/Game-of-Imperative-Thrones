
/**
 * CS 463 Homework #7 Game of Imperative Thrones
 * @author Hailey Lee
 */
public class H7 {
	public static void main (String[] args) {
		MusicalChairsAlg mca;		//musical chairs game algorithm
		int N;						//the number of players in the game
		
		/*
		 * "the number of players is the first command-line argument 
		 * to the whole program. When not present, your code must default to
		 * N = 10."
		 */
		if (args.length >= 1)
			N = Integer.parseInt(args[0]);
		else
			N = 10;
		
		/*
		 *Initialize & run the game
		 */
		mca = new MusicalChairsAlg(N);
		mca.emcee.run();	
		
		System.exit(0);
	}	
}//end of game
