package pl.edu.agh.inz.reactive;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.inz.gra1.R;
import pl.edu.agh.inz.reactive.games.Game;
import pl.edu.agh.inz.reactive.games.Sea;
import pl.edu.agh.inz.reactive.games.Three;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Context ctx;
	RadioGroup radioGroup;
	String labelUser;
	EditText etPassword;
	
	private static final int TILES_HEIGHT = 300;

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

		GridView gallery = (GridView) this.findViewById(R.id.gamesList);
		
		ImageView seaGame    = createGameImage(Sea.class, R.drawable.gra1);
		ImageView three1Game = createGameImage(Three.class, R.drawable.gra2);
		ImageView three2Game = createGameImage(Three.class, R.drawable.gra2);
		ImageView three3Game = createGameImage(Three.class, R.drawable.gra2);
		ImageView three4Game = createGameImage(Three.class, R.drawable.gra2);
		ImageView three5Game = createGameImage(Three.class, R.drawable.gra2);
		
		gallery.setNumColumns(3);
		
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		float allPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
		gallery.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300));//size.y - (int) allPx));
		
		List<ImageView> games = Arrays.asList(new ImageView[] { seaGame, three1Game, three2Game, three3Game, three4Game, three5Game  });
		
		ImageArrayAdapter adapter = new ImageArrayAdapter(this, android.R.layout.simple_list_item_1, games);
		gallery.setAdapter(adapter);
		
		View showGraph = this.findViewById(R.id.button3);
		showGraph.setOnClickListener(this);
		View logout = this.findViewById(R.id.button5);
		logout.setOnClickListener(this);
		TextView labelTextView = (TextView) this.findViewById(R.id.tvLoginUser);
		labelTextView.setText(labelUser);
	}
	
	private ImageView createGameImage(Class<? extends Game> gameClass, int imgSrc) {
		ImageView game = new ImageView(this);
		game.setImageResource(imgSrc);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		Resources r = getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size.y, r.getDisplayMetrics());
		
		game.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, (int) (px - 60) / 2));
		
		game.setOnClickListener(new GameSelectionListener(this, gameClass));
		return game;
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
