package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements OnClickListener {
	 
	    PaintView paintView;
	    Okienka okienka;
	    Context ctx;
	    RadioGroup radioGroup;
	 /*
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        okienka = (Okienka) findViewById(R.id.okienka);
	    //    paintView = (PaintView) findViewById(R.id.paintView);
	        ctx = getApplicationContext();
	        
	        
	    }*/
	    
	    private void uruchomSlad() {
	    //	okienka = new Okienka(this);
			Intent intencja = new Intent(MainActivity.this, Slad.class);
			startActivity(intencja);
		}
	    
	    private void uruchomKolo() {
			Intent intencja = new Intent(MainActivity.this, Kolo.class);
			startActivity(intencja);
		}
	    
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			View nowaGra = this.findViewById(R.id.button1);
			nowaGra.setOnClickListener(this);
			View zapiszWyniki = this.findViewById(R.id.button2);
			zapiszWyniki.setOnClickListener(this);
	        View wczytajWyniki = this.findViewById(R.id.button3);
	        wczytajWyniki.setOnClickListener(this);		
		}

		public void onClick(View v){
			switch(v.getId()){
			case R.id.button1:			
				uruchomSlad();
				break;
			case R.id.button2:
				uruchomKolo();
				break;
			case R.id.button3:
				finish();
				break;
		}
}}
