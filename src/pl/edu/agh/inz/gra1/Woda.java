package pl.edu.agh.inz.gra1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Woda extends View {

	int promien = 20;
	RectF slad;
	Paint paint;
	ImageView image;
	
	public Woda (Context context) {
		super(context);
		image = new ImageView(context);
		image.setImageResource(R.drawable.morze);
		image.setMinimumWidth(1280);
		paint = new Paint();
	}	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
    	this.addFocusables(new ArrayList<View>(), 0);
    	
	}
}