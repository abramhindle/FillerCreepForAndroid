package es.softwareprocess.fillercreep;

import android.app.Application;

public class FillerCreepApplication extends Application {
    // Singleton
    transient private static FillerCreep fillerCreep = null;

    static FillerCreep getFillerCreep() {
        if (fillerCreep == null) {
            fillerCreep = new FillerCreep();
        }
        return fillerCreep;
    }

    // Singleton
    transient private static GameController gameController = null;

    public static GameController getGameController() {
        if (gameController == null) {
            gameController = new GameController(getFillerCreep());
        }
        return gameController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
