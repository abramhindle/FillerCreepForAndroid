package es.softwareprocess.fillercreep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FillerCreepActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.newgame);
        button.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {				
        		FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
        		fillerCreep.resetGame();
        		Toast.makeText(FillerCreepActivity.this, "FillerCreep game initialized!", Toast.LENGTH_SHORT).show();
        	}
        });
        Button scoreButton = (Button)findViewById(R.id.scoreview);
        scoreButton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {				
        		//Intent intent = new Intent(MoleFinderActivity.this, PhotoCollectionsActivity.class);
        		//startActivity(intent);
        		FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
        		int[] scores = fillerCreep.getScores();
        		Player[] players = fillerCreep.getPlayers();
        		String score = "Current Score: ";
        		for (int i = 0 ; i < scores.length ; i++ ) {
        			score += (i==0?"":" and ") + "Player " + players[i].getName() + " has " + scores[i]; 
        		}
        		Toast.makeText(FillerCreepActivity.this, score, Toast.LENGTH_SHORT).show();

        	}		
        });
        Button textviewButton = (Button)findViewById(R.id.textonlyview);
        textviewButton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {				
        		Intent intent = new Intent(FillerCreepActivity.this, FillerCreepTextViewActivity.class);
        		startActivity(intent);
        	}
        });
        Button gvButton = (Button)findViewById(R.id.seemap);
        gvButton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {				
        		Intent intent = new Intent(FillerCreepActivity.this, FillerCreepGraphicalViewActivity.class);
        		startActivity(intent);
        	}
        });
        Button ggButton = (Button)findViewById(R.id.graphicalview);
        ggButton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {				
        		Intent intent = new Intent(FillerCreepActivity.this, FillerCreepGraphicalGameActivity.class);
        		startActivity(intent);
        	}
        });

    }
}