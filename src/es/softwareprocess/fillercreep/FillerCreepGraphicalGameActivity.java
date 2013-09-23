package es.softwareprocess.fillercreep;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FillerCreepGraphicalGameActivity extends Activity implements
        FView<FillerCreep> {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphicalinterface);
        ImageButton button = (ImageButton) findViewById(R.id.gamegraphicalview);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v) {
                // finish();
            }
        };
        button.setOnClickListener(listener);
        
        Button autoButton = (Button) findViewById(R.id.autoplay);
        autoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		        GameController gc = FillerCreepApplication.getGameController();
		        gc.autoRound();				
			}
		});

        ImageButton debutton = (ImageButton) findViewById(R.id.gamedarkenergy);
        debutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                play(new DarkEnergy());
            }
        });
        ImageButton dmbutton = (ImageButton) findViewById(R.id.gamedarkmatter);
        dmbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                play(new DarkMatter());
            }
        });
        ImageButton ebutton = (ImageButton) findViewById(R.id.gameenergy);
        ebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                play(new Energy());
            }
        });
        ImageButton mbutton = (ImageButton) findViewById(R.id.gamematter);
        mbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                play(new Matter());
            }
        });

        updateMap();
        updateScores();

        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.addView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.deleteView(this);
    }

    public void update(FillerCreep fillerCreep) {
        updateMap();
        updateScores();
    }

    public void updateMap() {
        ImageButton button = (ImageButton) findViewById(R.id.gamegraphicalview);
        GameController gc = FillerCreepApplication.getGameController();
        Bitmap bitmap = gc.getMapBitmap();
        button.setImageBitmap(bitmap);
    }

    void play(FundamentalStuff choice) {
        GameController gc = FillerCreepApplication.getGameController();
        gc.playRound(choice);
    }

    void updateScores() {
        TextView score1 = (TextView) findViewById(R.id.gameyin);
        TextView score2 = (TextView) findViewById(R.id.gameyang);
        TextView[] tscores = new TextView[] { score1, score2 };

        GameController gc = FillerCreepApplication.getGameController();

        String[] scores = gc.getGameScoreStrings();
        for (int i = 0; i < tscores.length; i++) {
            tscores[i].setText(scores[i]);
        }
    }
}
