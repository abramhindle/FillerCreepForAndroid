package es.softwareprocess.fillercreep;

import android.graphics.Bitmap;

public class GameController implements FController {
    FillerCreep fc = null;

    public GameController(FillerCreep fc) {
        this.fc = fc;
    }

    public boolean isGameOver() {
        return fc.gameOver();
    }

    public int[] getScores() {
        return fc.getScores();
    }

    public int whichPlayerNumberWins() {
        return fc.whichPlayerNumberWins();
    }

    public Player[] getPlayers() {
        return fc.getPlayers();
    }

    public Bitmap getMapBitmap() {
        FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
        GraphicalFillerCreepView gfcv = new GraphicalFillerCreepView(
                fillerCreep);
        Bitmap bitmap = gfcv.getBitmapOfFillerCreep();
        return bitmap;
    }

    // Let the actual player play against the AI
    public void playRound(FundamentalStuff choice) {
        fc.playRoundWithAI(0, choice);
    }

    public String[] getGameScoreStrings() {
        GameController gc = this;
        int[] scores = gc.getScores();
        Player[] players = gc.getPlayers();
        int winner = -1;
        if (gc.isGameOver()) {
            winner = gc.whichPlayerNumberWins();
        }
        String wins = " wins and ";
        String[] tscores = new String[scores.length];
        for (int i = 0; i < scores.length; i++) {
            tscores[i] = players[i].getName() + (winner == i ? wins : "")
                    + " has " + scores[i];
        }
        return tscores;
    }

    public void resetGame() {
        fc.resetGame();
    }
}
