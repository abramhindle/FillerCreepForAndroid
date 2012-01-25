package es.softwareprocess.fillercreep;

import android.graphics.Bitmap;

public class GraphicalFillerCreepView {
	Bitmap bitmap = null;
	FillerCreep fillerCreep = null;
	int blockSize=12;
	public GraphicalFillerCreepView(FillerCreep fillerCreep) {
		this.fillerCreep = fillerCreep;
	}
	private Bitmap getBitmap() {
		if (bitmap == null) {
			bitmap = Bitmap.createBitmap(fillerCreep.getWidth()*blockSize, 
												fillerCreep.getHeight()*blockSize, 
												Bitmap.Config.ARGB_8888 );
			
		}
		return bitmap;
	}
	Bitmap getBitmapOfFillerCreep() {
		Bitmap bitmap = getBitmap();
		// if it updated update it
		// but we're naive
		FundamentalStuff[][] universe = fillerCreep.getUniverse();
		int width = fillerCreep.getWidth();
		int height = fillerCreep.getHeight();
		int nPixels = width * height*blockSize*blockSize;
		int [] pixels = new int[nPixels];//*blockSize];
		System.err.println("N Pixels allocated :" + nPixels);
		for (int x = 0; x < width ; x++) {
			for (int y = 0; y < height ; y++) {
				int color = getColorOf(universe[x][y]);
				drawBlock(pixels,x,y,color, width);
			}
		}
		
		bitmap.setPixels(pixels, 0, width*blockSize, 0, 0, width*blockSize, height*blockSize);
		return bitmap;
	}
	// xi and yi are creep locations
	private void drawBlock(int [] pixels, int xi, int yi, int colorOf, int creepWidth) {		
		for (int y = 0; y < blockSize; y++) {
			for (int x = 0; x < blockSize; x++) {
				int i = (xi * blockSize + x) + creepWidth * blockSize * blockSize * yi + y * creepWidth * blockSize ;
				
				pixels[i] = colorOf;
			}
		}
		//System.err.println("Done");
	}
	int getColorOf(FundamentalStuff stuff) {		
		return FundamentalStuffColorMap.colorOf(stuff);
	}
}
