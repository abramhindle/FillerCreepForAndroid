package es.softwareprocess.fillercreep;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FillerCreepGraphicalGameActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphicalinterface);
        ImageButton button = (ImageButton)findViewById(R.id.gamegraphicalview);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v) {                
            	//finish();
            }
        };
        button.setOnClickListener(listener);
        updateMap();
                               
        
        ImageButton debutton = (ImageButton)findViewById(R.id.gamedarkenergy);
        debutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new DarkEnergy());
        	}
        });
        ImageButton dmbutton = (ImageButton)findViewById(R.id.gamedarkmatter);
        dmbutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new DarkMatter());
        	}
        });
        ImageButton ebutton = (ImageButton)findViewById(R.id.gameenergy);
        ebutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new Energy());
        	}
        });
        ImageButton mbutton = (ImageButton)findViewById(R.id.gamematter);
        mbutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new Matter());
        	}
        });
        updateScores();
        
        
    }
    public void updateMap() {
    	ImageButton button = (ImageButton)findViewById(R.id.gamegraphicalview);
        FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
        GraphicalFillerCreepView gfcv = new GraphicalFillerCreepView(fillerCreep);
        Bitmap bitmap = gfcv.getBitmapOfFillerCreep();
        button.setImageBitmap(bitmap);
    }
    void play(FundamentalStuff choice) {
		FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
		fillerCreep.playRoundWithAI(0, choice);
		updateMap();
		updateScores();    	
    }
    void updateScores() {
    	TextView score1 = (TextView)findViewById(R.id.gameyin);
    	TextView score2 = (TextView)findViewById(R.id.gameyang);
    	TextView [] tscores = new TextView[]{ score1, score2 }; 
		FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
		int winner = -1;
		if (fillerCreep.gameOver()) {
			winner = fillerCreep.whichPlayerNumberWins();
		}
		int[] scores = fillerCreep.getScores();
		Player[] players = fillerCreep.getPlayers();
		String wins = " wins and ";
		for (int i = 0; i < scores.length; i++) {
			tscores[i].setText(players[i].getName() +(winner==i?wins:"") + " has " + scores[i]);
		}			
    }
}
