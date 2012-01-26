package es.softwareprocess.fillercreep;

import android.graphics.Bitmap;

public interface FController {
    public boolean isGameOver();

    public int[] getScores();

    public int whichPlayerNumberWins();

    public Player[] getPlayers();

    public Bitmap getMapBitmap();

    public void playRound(FundamentalStuff choice);

    public String[] getGameScoreStrings();
}
