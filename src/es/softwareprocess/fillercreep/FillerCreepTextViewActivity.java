package es.softwareprocess.fillercreep;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FillerCreepTextViewActivity extends Activity implements FView<FillerCreep> {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textinterface);
        
        
        
        Button button = (Button)findViewById(R.id.textdarkenergy);
        button.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new DarkEnergy());
        	}
        });
        Button dmbutton = (Button)findViewById(R.id.textdarkmatter);
        dmbutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new DarkMatter());
        	}
        });
        Button ebutton = (Button)findViewById(R.id.textenergy);
        ebutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new Energy());
        	}
        });
        Button mbutton = (Button)findViewById(R.id.textmatter);
        mbutton.setOnClickListener(new OnClickListener() {			
        	@Override
        	public void onClick(View arg0) {
        		play(new Matter());
        	}
        });
        updateScores();
        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.addView(this);
    }
    
    public void update(FillerCreep fillerCreep) {
    	updateScores();
    }
    @Override
    public void onDestroy() {
    	super.onDestroy();
        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.deleteView(this);    	
    }

    void play(FundamentalStuff choice) {
    	GameController gc = FillerCreepApplication.getGameController();
    	gc.playRound(choice);
    }
    
    void updateScores() {
    	
      	TextView score1 = (TextView)findViewById(R.id.textyin);
    	TextView score2 = (TextView)findViewById(R.id.textyang);
    	TextView [] tscores = new TextView[]{ score1, score2 };
    	
    	GameController gc = FillerCreepApplication.getGameController();
    	
    	String [] scores = gc.getGameScoreStrings();
    	for (int i = 0 ; i < tscores.length; i++) {
    		tscores[i].setText(scores[i]);
    	}
    }
}
