package es.softwareprocess.fillercreep;

//Our Model

public class FillerCreep implements Model {
	static private FundamentalStuff[] stuffArray = null;
	static private FundamentalStuff[] getStuffArray() {
		if (stuffArray == null) {
			stuffArray = new FundamentalStuff[]{
					new DarkEnergy(),
					new DarkMatter(),
					new Energy(),
					new Matter()					
			};	
		}
		return stuffArray;
	}
	// Really it is space
	protected FundamentalStuff[][] universe = null;
	static int nPlayers = 2;
	protected Player[] players = null;
	// score of players
	protected int[] scores = null;
	int width = 32;
	int height = 32;
	FillerCreep() {
		init();
	}
	FillerCreep(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	// Called by constructors
	private void init() {
		FundamentalStuff[] stuff = getStuffArray(); 
		universe = new FundamentalStuff[width][height];
		// Fill the universe with FundamentalStuff;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				universe[x][y] = stuff[(int)(Math.random()*stuff.length)].copy();
			}
		}
		// Player 1
		Yin player1 = new Yin(0,0);
		players[0] = player1;
		universe[0][0] = player1;
		// Player 2
		Yang player2 = new Yang(width-1,height-1);
		players[1] = player2;
		universe[width-1][height-1] = player2;
		
		//init game score
		scores = new int[nPlayers]; 		
		for (int i = 0 ; i < scores.length; i++) { scores[i] = 1; }
	}
	static private boolean inBounds(FundamentalStuff[][] universe, int x, int y) {
		return (x >= 0 && x < universe.length && y >= 0 && y < universe[0].length );
	}

	private boolean inBounds(int x, int y) {
		return (x >= 0 && x < width && y >= 0 && y < height );
	}
	// Mutates the board universe
	// counts the fills
	// note the shadowing
	static public int fillFlood(FundamentalStuff[][] universe, int x, int y, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		if (!inBounds(universe,x,y)) { 
				return 0; 
		}
		FundamentalStuff currentSpace = universe[x][y];
		if (currentSpace.isa(toReplace)) {
			universe[x][y] = replaceWith; // should we copy?
			return 1 + 
					fillFlood(universe, x+1, y  , toReplace, replaceWith) +
					fillFlood(universe, x-1, y  , toReplace, replaceWith) +
					fillFlood(universe, x  , y+1, toReplace, replaceWith) +
					fillFlood(universe, x  , y-1, toReplace, replaceWith);
		}
		return 0;
	}
	// method for the class
	private int fillFlood(int x, int y, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		// call the static version of the fill flood code
		return fillFlood(universe, x, y, toReplace, replaceWith);
	}
	
	public void playPlayer(int playerNumber, FundamentalStuff choiceOfStuff) {
		Player player = players[playerNumber];
		playPlayer( player, choiceOfStuff );
	}
	public void playPlayer(Player player, FundamentalStuff choiceOfStuff) {
		// First we fill from that player's corner
		// With the STUFF we want
		int scoreBefore = fillFlood(player.getX(), player.getY(), player.stuff(), choiceOfStuff);
		// Then we fill that STUFF we want
		// With the Player's STUFF
		int scoreAfter  = fillFlood(player.getX(), player.getY(), choiceOfStuff, player.stuff());
		int changeInScore = scoreAfter - scoreBefore;		
	}
	// For AIs or interfaces to test a possible play
	public int testPlayerPlay(Player player, FundamentalStuff choiceOfStuff) {
		FundamentalStuff[][] newUniverse = universe.clone();
		// First we fill from that player's corner
		// With the STUFF we want
		int scoreBefore = fillFlood(newUniverse, player.getX(), player.getY(), player.stuff(), choiceOfStuff);
		// Then we fill that STUFF we want
		// With the Player's STUFF
		int scoreAfter  = fillFlood(newUniverse, player.getX(), player.getY(), choiceOfStuff, player.stuff());
		int changeInScore = scoreAfter - scoreBefore;
		return scoreAfter;
	}
	public boolean gameOver() {
		int sum = 0;
		for (int i = 0 ; i < scores.length; i++ ){
			sum += scores[i];
		}
		return (sum == width*height);
	}
	public int whichPlayerNumberWins() {
		int max = scores[0];
		int winner = 0;
		for (int i = 0 ; i < scores.length; i++ ){
			if (scores[i] > max) {
				max = scores[i];
				winner = i;
			}
		}
		return winner;		
	}
	public Player whichPlayerWins() {
		return players[whichPlayerNumberWins()];	
	}
	public void resetGame() {
		init();
	}
}
