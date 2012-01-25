package es.softwareprocess.fillercreep;

public class AIMaximum implements AI {
	
	public FundamentalStuff evaluate(int playernumber, FillerCreep fillerCreep) {
		FundamentalStuff[] stuff = FillerCreep.getStuffArray();
		int maxscore = -1;
		int maxstuff = -1;
		for (int i = 0 ; i < stuff.length; i++) {
			int score = fillerCreep.testPlayerPlay(playernumber, stuff[i]);
			if (score > maxscore) {
				maxscore = score;
				maxstuff = i;
			}
		}
		return stuff[maxstuff];
	}

}
