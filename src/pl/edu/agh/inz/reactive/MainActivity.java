package pl.edu.agh.inz.reactive;

import pl.edu.agh.inz.gra1.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	 
	    PaintView paintView;
	    Okienka okienka;
	    Context ctx;
	    RadioGroup radioGroup;
	    String labelUser;
	    EditText etPassword;
	    

	    
	    
	    private void runSea() {
	    	Intent intent = new Intent(MainActivity.this, Sea.class);
			startActivity(intent);
	    }
	    
	    private void uruchomSlad() {
			Intent intencja = new Intent(MainActivity.this, Slad.class);
			startActivity(intencja);
		}
	    
	    private void uruchomKolo() {
			Intent intencja = new Intent(MainActivity.this, Kolo.class);
			startActivity(intencja);
		}
	    
	    private void wyswietlWykres(View view) {
	    	LineGraph line = new LineGraph();
	    	Intent intencja = line.getIntent(this);
			startActivity(intencja);
	    }
	    
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			if (getIntent().getStringExtra("login") != null) {
				labelUser = getIntent().getStringExtra("login") + " (" + getIntent().getStringExtra("name") + " " + getIntent().getStringExtra("surname") + ")";
			} else {
				labelUser = "Gość";
			}
			
			View nowaGra = this.findViewById(R.id.button1);
			nowaGra.setOnClickListener(this);
			View zapiszWyniki = this.findViewById(R.id.button2);
			zapiszWyniki.setOnClickListener(this);
	        View wczytajWyniki = this.findViewById(R.id.button3);
	        wczytajWyniki.setOnClickListener(this);		
	        View polparaliz = this.findViewById(R.id.button4);
	        polparaliz.setOnClickListener(this);
	        View wyloguj = this.findViewById(R.id.button5);
	        wyloguj.setOnClickListener(this);
	        TextView labelTextView = (TextView) this.findViewById(R.id.tvLoginUser);
	        labelTextView.setText("Zalogowano jako: " + labelUser);
	        
	        
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
				wyswietlWykres(v);
				break;
			case R.id.button4:
				runSea();
				break;
			case R.id.button5:
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				LayoutInflater inflater = this.getLayoutInflater();
				builder.setView(inflater.inflate(R.layout.dialog_password, null)).setPositiveButton("OK", new DialogInterface.OnClickListener() {		              
		               public void onClick(DialogInterface dialog, int id) {
		            	   if (etPassword.getText().toString().equals("admin")) {
		            		   	goLogin();
		            	   }                
		               }
		           });
				AlertDialog dialog = builder.create();
				dialog.show();	
				etPassword = (EditText) dialog.findViewById(R.id.password);				
				break;
		}
}

		private void goLogin() {
			Intent intencja = new Intent(this, AdminActivity.class);
			finish();
			startActivity(intencja);
			
		}}
