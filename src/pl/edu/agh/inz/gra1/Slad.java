package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.os.Bundle;

public class Slad extends Activity {
	
	private Okienka okienka;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		okienka = new Okienka(this);
		setContentView(okienka);
	}

}
