package pl.edu.agh.inz.gra1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Login_menuActivity extends Activity implements OnClickListener {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_menu);
		
		View registration = this.findViewById(R.id.buttonRegistration);
		registration.setOnClickListener(this);
		View login = this.findViewById(R.id.buttonLogin);
		login.setOnClickListener(this);
        View koniec = this.findViewById(R.id.buttonKoniec);
        koniec.setOnClickListener(this);		
	}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.buttonRegistration:			
			showRegistration();
			break;
		case R.id.buttonLogin:
			showLogin();
			break;
		case R.id.buttonKoniec:
			System.exit(0);
			break;
	}

}

	private void showLogin() {
		Intent intent = new Intent(Login_menuActivity.this, Login.class);
		startActivity(intent);
		
	}

	private void showRegistration() {
		Intent intent = new Intent(Login_menuActivity.this, Registration.class);
		startActivity(intent);
		
	}
}