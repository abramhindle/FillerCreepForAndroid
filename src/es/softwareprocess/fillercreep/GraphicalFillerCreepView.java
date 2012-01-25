package es.softwareprocess.fillercreep;

import android.graphics.Bitmap;

public class GraphicalFillerCreepView {
	Bitmap bitmap = null;
	FillerCreep fillerCreep = null;
	int blockSize=16;
	public GraphicalFillerCreepView(FillerCreep fillerCreep) {
		this.fillerCreep = fillerCreep;
	}
	Bitmap getBitmapOfFillerCreep() {
		if (bitmap == null) {
			bitmap = Bitmap.createBitmap(fillerCreep.getWidth()*blockSize, 
												fillerCreep.getHeight()*blockSize, 
												Bitmap.Config.ARGB_8888 );
			
		}
		// if it updated update it
		// but we're naive
		FundamentalStuff[][] universe = fillerCreep.getUniverse();
		int width = fillerCreep.getWidth();
		int height = fillerCreep.getHeight());
		for (int x = 0; x < width ; x++) {
			for (int y = 0; y < height ; y++) {
				drawBlock(x*blockSize,y*blockSize,getColorOf(universe[x][y]));
			}
		}
	}
	int getColorOf(FundamentalStuff stuff) {		
		return FundamentalStuffColorMap.colorOf(stuff);
	}
}
