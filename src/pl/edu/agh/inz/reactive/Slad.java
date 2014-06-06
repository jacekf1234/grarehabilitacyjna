package pl.edu.agh.inz.reactive;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Slad extends Activity {
	
	private Okienka okienka;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		okienka = new Okienka(this);
		setContentView(okienka);
		
		Toast toast = Toast.makeText(this, "Runda 1\nWynik: 157", Toast.LENGTH_SHORT);
		toast.show();
		
	}

}
