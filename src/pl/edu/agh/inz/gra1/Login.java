package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.os.Bundle;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void exit() {
		System.exit(0);
	}
}
