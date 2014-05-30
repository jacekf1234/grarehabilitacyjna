package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.os.Bundle;

public class Registration extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
	}
	
	public void exit() {
		System.exit(0);
	}
	
}
