package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Sea extends Activity {
	
	private Woda woda;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		woda = new Woda(this);
		setContentView(woda);
//		
//		ImageView object = new ImageView(this);
//		object.setImageResource(R.drawable.lodka);
//		
//		
//	//	setContentView(R.layout.activity_sea);
//		
//		ImageView image = new ImageView(this);
//		image.setImageResource(R.drawable.morze);
//		image.setMinimumWidth(1280);
//		setContentView(image);
		
		
		
		
	}

}
