package pl.edu.agh.inz.reactive;

import android.app.Activity;
import android.os.Bundle;

public class Kolo extends Activity {
	
private PaintView paintView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		paintView = new PaintView(this);		
		setContentView(paintView);
	}

}
