package pl.edu.agh.inz.gra1;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
 
public class PaintView extends View{
 
	private final Kolo kolo;
	ArrayList<RectF> obrecze;
	ArrayList<RectF> kolorowe;
	ArrayList<RectF> biale;
	RectF oval1;
	private int promien;
	private float srY;
	private float srX;
	private boolean fl = true;
    Paint paint = new Paint();
    Paint paintObrecz = new Paint();
    Paint paintTlo = new Paint();
    int count;
    

    
    
 //, AttributeSet attrs
    public PaintView(Context context) {
    	super(context);
    	this.kolo = (Kolo) context;
        obrecze = new ArrayList<RectF>();
        kolorowe = new ArrayList<RectF>();
        biale = new ArrayList<RectF>();
        paint = new Paint();
        paintObrecz = new Paint();
        paintTlo = new Paint();
        oval1 = new RectF();
     //   fl = false;
     //   promienie(80, 1);    
        setFocusable(true);
		setFocusableInTouchMode(true);
        
    }
 
    private float sqr(float x) {
    	return x*x;
    }
    
    private RectF drawCircle(float x, float y, float r) {
    	return new RectF(x-r, y-r, x+r, y+r);
    }
    
    private void promienie(int odleglosc, int grubosc) {
    	
    	Log.d("promienie", "Wykonywanie promieni");
    	
    
    	RectF obrecz;
    	RectF tlo;
    	
    	srY = getHeight() / 2;
        srX = getWidth() / 2;
        
        float r = ((getHeight()/2)/odleglosc)*odleglosc;
    	    	
    	while(r>0) {
    		obrecz = drawCircle(srX, srY, r);
    		kolorowe.add(obrecz);
    		r -= grubosc;
    		tlo = drawCircle(srX, srY, r);
    		kolorowe.add(tlo);
    		r -= odleglosc;
    	}    
    	//=================
    /*  	
    	float r = ((getHeight()/2)/odleglosc)*odleglosc;
    	float x, y;
    	
    	double kat;
    	double delta;
    	srY = this.getHeight() / 2;
        srX = this.getWidth() / 2;
    	
        while (r > 0) {        	
        	delta = 10/Math.pow(r, 1.3);
        	kat = 0;
	    	do {
	    		x = (float) (srX + r*Math.cos(kat));
	            y = (float) (srY + r*Math.sin(kat));
	            obrecze.add(drawCircle(x, y, grubosc));
	            kat += delta;
	        } while (kat < (2*Math.PI));
	    	
	    	r -= odleglosc;
        }    	
        */
    }
       
    @Override
	protected void onSizeChanged(int s, int w, int staras, int staraw){
    	
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	    	
    	Log.d("dotyk", event.getX()+" "+event.getY());
    	
    	if (fl) {
    		promienie(80,4);
    		fl = false;
    	}
    	srY = this.getHeight() / 2;
        srX = this.getWidth() / 2;
    //	oval1 = new RectF(event.getX()-promien, event.getY()-promien, event.getX() + promien, event.getY() + promien);
    	promien = (int) Math.sqrt(sqr(srX-event.getX())+sqr(srY-event.getY()));
    	oval1 = drawCircle(srX, srY, promien); //new RectF(srX-promien, srY-promien, srX+promien, srY+promien);
        invalidate();
        return true;
    }
 
    
    @Override
    protected void onDraw(Canvas canvas) {
    	paint.setColor(Color.RED);
    	paintObrecz.setColor(Color.YELLOW);
    	paintTlo.setColor(Color.WHITE);
    	/*
	        for (RectF obrecz : obrecze) {
	        	canvas.drawOval(obrecz, paintObrecz);
	        }
        */
    	count = 0;
    	for (RectF kolor : kolorowe) {
    		if (count % 2 == 0){
    			canvas.drawOval(kolor, paintObrecz);
    		} else {
    			canvas.drawOval(kolor, paintTlo);
    		}
    		count++;
        }
    	
        canvas.drawOval(oval1, paint);

    }
 
}
