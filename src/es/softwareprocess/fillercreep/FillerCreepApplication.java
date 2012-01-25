package es.softwareprocess.fillercreep;

import android.app.Application;

public class FillerCreepApplication extends Application {
	transient private static FillerCreep fillerCreep = null;
	static FillerCreep getFillerCreep() {		
		if (fillerCreep == null) {
			fillerCreep = new FillerCreep();
		}
		return fillerCreep;
	}
	
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
