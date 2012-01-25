package es.softwareprocess.fillercreep;

import java.util.Stack;

//Our Model

public class FillerCreep extends FModel<FView> {
	static private FundamentalStuff[] stuffArray = null;
	static FundamentalStuff[] getStuffArray() {
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
		super();
		init();
	}
	FillerCreep(int width, int height) {
		super();
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
		
		players = new Player[nPlayers];
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
	// This blows the stack on android ;_;
	static public int stackfulFillFlood(FundamentalStuff[][] universe, int x, int y, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		if (toReplace.isSame(replaceWith)) {
			throw new RuntimeException("Why is toReplace == replaceWith " + toReplace.getClass().toString() + " " + replaceWith.getClass().toString());
		}
		if (!inBounds(universe,x,y)) { 
				return 0; 
		}
		FundamentalStuff currentSpace = universe[x][y];		
		if (currentSpace.isSame(toReplace)) {
			universe[x][y] = replaceWith; // should we copy?
			return 1 + 
					fillFlood(universe, x+1, y  , toReplace, replaceWith) +
					fillFlood(universe, x-1, y  , toReplace, replaceWith) +
					fillFlood(universe, x  , y+1, toReplace, replaceWith) +
					fillFlood(universe, x  , y-1, toReplace, replaceWith);
		} else {
			
		}
		return 0;
	}
	
	static public int stacklessFillFlood(FundamentalStuff[][] universe, int ix, int iy, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		if (toReplace.isSame(replaceWith)) {
			throw new RuntimeException("Why is toReplace == replaceWith " + toReplace.getClass().toString() + " " + replaceWith.getClass().toString());
		}
		Stack<IntPoint> floods = new Stack<IntPoint>();
		floods.push(new IntPoint(ix, iy));
		int score = 0;
		while(!floods.isEmpty()) {
			IntPoint flood = floods.pop();
			int x = flood.x;
			int y = flood.y;
			if (inBounds(universe,x,y)) { 
				FundamentalStuff currentSpace = universe[x][y];
				if (currentSpace.isSame(toReplace)) {
					universe[x][y] = replaceWith; // should we copy?
					score += 1;
					floods.push(new IntPoint(x+1, y));
					floods.push(new IntPoint(x-1, y));
					floods.push(new IntPoint(x, y+1));
					floods.push(new IntPoint(x, y-1));
				}
			}			
		}
		return score;
	}
	static public int fillFlood(FundamentalStuff[][] universe, int ix, int iy, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		return stacklessFillFlood(universe, ix, iy, toReplace, replaceWith);
	}

	
	// method for the class
	private int fillFlood(int x, int y, FundamentalStuff toReplace, FundamentalStuff replaceWith) {
		// call the static version of the fill flood code
		return fillFlood(universe, x, y, toReplace, replaceWith);
	}
	
	public int playPlayer(int playerNumber, FundamentalStuff choiceOfStuff) {
		Player player = players[playerNumber];
		return playPlayer( player, choiceOfStuff );
	}
	private void updateScore(Player player, int newScore) {
		for (int i = 0; i < players.length; i++) {
			if (players[i]==player) {
				scores[i] = newScore;
			}
		}
	}
	public int playPlayer(Player player, FundamentalStuff choiceOfStuff) {
		//System.err.println("Player "+player.getName()+" plays "+choiceOfStuff.getName());
		// First we fill from that player's corner
		// With the STUFF we want
		int scoreBefore = fillFlood(player.getX(), player.getY(), player.stuff(), choiceOfStuff);
		// Then we fill that STUFF we want
		// With the Player's STUFF
		int scoreAfter  = fillFlood(player.getX(), player.getY(), choiceOfStuff, player.stuff());
		int changeInScore = scoreAfter - scoreBefore;
		updateScore(player, scoreAfter);
		return scoreAfter;
	}
	private FundamentalStuff[][] cloneUniverse() {
		FundamentalStuff[][] newUniverse = new FundamentalStuff[universe.length][];
		for (int i = 0 ; i < universe.length; i++) {
			newUniverse[i] = universe[i].clone();
		}
		return newUniverse;
	}
	// For AIs or interfaces to test a possible play
	public int testPlayerPlay(Player player, FundamentalStuff choiceOfStuff) {
		FundamentalStuff[][] newUniverse = cloneUniverse();
		// First we fill from that player's corner
		// With the STUFF we want
		int scoreBefore = FillerCreep.fillFlood(newUniverse, player.getX(), player.getY(), player.stuff(), choiceOfStuff);
		// Then we fill that STUFF we want
		// With the Player's STUFF
		int scoreAfter  = FillerCreep.fillFlood(newUniverse, player.getX(), player.getY(), choiceOfStuff, player.stuff());
		int changeInScore = scoreAfter - scoreBefore;		
		return scoreAfter;
	}
	public int testPlayerPlay(int playerID, FundamentalStuff choiceOfStuff) {
		return testPlayerPlay(players[playerID], choiceOfStuff);
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
		notifyViews();
	}
	public int[] getScores() {
		return scores;
	}
	public Player[] getPlayers() {
		return players;
	}
	public int playAIPlayer(int i) {
		AI playerAI = players[i].getAI();
		FundamentalStuff choice = playerAI.evaluate(i,this);
		return playPlayer(i, choice);
	}
	public void playRoundWithAI(int playerID, FundamentalStuff choice) {
		int otherPlayer = playerID;
		for (int i = 0; i < players.length ; i++) {
			if (i != playerID) {
				otherPlayer = i;
			}
		}
		int you  = playPlayer(playerID, choice);
		int them = playAIPlayer(otherPlayer);
		//System.err.println("You:"+playerID+""+you + " Them:"+otherPlayer+" "+them+" Your Choice was "+choice.getClass().getName());
		notifyViews();
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public FundamentalStuff[][] getUniverse() {
		return universe;
	}

}
