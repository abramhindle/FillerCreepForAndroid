package es.softwareprocess.fillercreep;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class FillerCreepGraphicalViewActivity extends Activity implements
        FView<FillerCreep> {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphicalview);
        ImageButton button = (ImageButton) findViewById(R.id.maingraphicalview);
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        };
        button.setOnClickListener(listener);

        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.addView(this);

        updateMap();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FillerCreep fc = FillerCreepApplication.getFillerCreep();
        fc.deleteView(this);
    }

    public void update(FillerCreep fillerCreep) {
        updateMap();
    }

    public void updateMap() {
        ImageButton button = (ImageButton) findViewById(R.id.maingraphicalview);
        // FillerCreep fillerCreep = FillerCreepApplication.getFillerCreep();
        GameController gc = FillerCreepApplication.getGameController();
        Bitmap bitmap = gc.getMapBitmap();
        button.setImageBitmap(bitmap);
        // GraphicalFillerCreepView gfcv = new
        // GraphicalFillerCreepView(fillerCreep);
        // Bitmap bitmap = gfcv.getBitmapOfFillerCreep();
        // button.setImageBitmap(bitmap);
    }
}
