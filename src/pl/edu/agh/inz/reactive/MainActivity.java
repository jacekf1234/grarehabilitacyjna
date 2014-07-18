package pl.edu.agh.inz.reactive;

import pl.edu.agh.inz.gra1.R;
import pl.edu.agh.inz.reactive.games.Sea;
import pl.edu.agh.inz.reactive.games.Three;
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

	Context ctx;
	RadioGroup radioGroup;
	String labelUser;
	EditText etPassword;

	private void wyswietlWykres(View view) {
		LineGraph line = new LineGraph();
		Intent intencja = line.getIntent(this);
		startActivity(intencja);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (getIntent().getStringExtra("login") != null) {
			labelUser = getIntent().getStringExtra("login") + " ("
					+ getIntent().getStringExtra("name") + " "
					+ getIntent().getStringExtra("surname") + ")";
		} else {
			labelUser = "Gość";
		}

		View seaGame = this.findViewById(R.id.imgvGra1);
		seaGame.setOnClickListener(new GameSelectionListener(this, Sea.class));
		View threeGame = this.findViewById(R.id.imgvGra2);
		threeGame.setOnClickListener(new GameSelectionListener(this, Three.class));
		
		View showGraph = this.findViewById(R.id.button3);
		showGraph.setOnClickListener(this);
		View logout = this.findViewById(R.id.button5);
		logout.setOnClickListener(this);
		TextView labelTextView = (TextView) this.findViewById(R.id.tvLoginUser);
		labelTextView.setText(labelUser);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button3:
			wyswietlWykres(v);
			break;
		case R.id.button5:
			passwordPopup();
			break;
		}
	}
	
	public void passwordPopup() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.dialog_password, null))
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								if (etPassword.getText().toString()
										.equals("admin")) {
									goLogin();
								}
							}
						});
		AlertDialog dialog = builder.create();
		dialog.show();
		etPassword = (EditText) dialog.findViewById(R.id.password);
	}

	private void goLogin() {
		Intent intent = new Intent(this, AdminActivity.class);
		finish();
		startActivity(intent);
	}
}
