package es.softwareprocess.fillercreep;

import android.graphics.Color;

public class FundamentalStuffColorMap {
    static private int[] colors = null;

    public static int colorOf(FundamentalStuff stuff) {
        if (colors == null) {
            colors = new int[255];
            colors[new DarkEnergy().intID()] = Color.rgb(255, 0, 0);
            colors[new DarkMatter().intID()] = Color.rgb(0, 255, 0);
            colors[new Energy().intID()] = Color.rgb(0, 128, 255);
            colors[new Matter().intID()] = Color.rgb(255, 0, 255);
            colors[new Yin(0, 0).intID()] = Color.rgb(255, 255, 255);
            colors[new Yang(0, 0).intID()] = Color.rgb(64, 64, 64);
        }
        return colors[stuff.intID()];
    }
}
