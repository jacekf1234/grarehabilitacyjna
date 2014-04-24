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
/*<pl.edu.agh.inz.gra1.PaintView
       android:id="@+id/paintView"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:background="#ffffff" />
   
   <pl.edu.agh.inz.gra1.Okienka
       android:id="@+id/okienka"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:background="#ffffff" />*/
