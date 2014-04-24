package pl.edu.agh.inz.gra1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class Okienka extends View {

	int promien = 20;
	RectF slad;
	Paint paint;
	
	public Okienka (Context context) {
		super(context);
		slad = new RectF();
		paint = new Paint();
	}	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		slad = new RectF(event.getX()-promien, event.getY()-promien, event.getX() + promien, event.getY() + promien);
		invalidate();
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
    	paint.setColor(Color.RED);

    	canvas.drawOval(slad, paint);
    	
	}
}
