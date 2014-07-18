package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.inz.gra1.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AdminActivity extends Activity implements OnClickListener {

	ListView list;
	EditText loginUser, nameUser, surnameUser;
	TextView information;
	ArrayList<String> listItems = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Map<String, User> usersMap = new HashMap<String, User>();
	View bAddUser, bRemoveUser, bLoginUser;
	Bundle bundle;
	Intent cel;
	boolean newfl = true;
	
	DatabaseManager db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		cel = new Intent(this, MainActivity.class);
		
		loginUser = (EditText) this.findViewById(R.id.etLogin);
		nameUser = (EditText) this.findViewById(R.id.etName);
		surnameUser = (EditText) this.findViewById(R.id.etSurname);
		bAddUser = this.findViewById(R.id.buttonAdd);		
		bRemoveUser = this.findViewById(R.id.buttonRemove);
		bLoginUser = this.findViewById(R.id.buttonLogin);
		list = (ListView) this.findViewById(R.id.lvUsers);
		information = (TextView) this.findViewById(R.id.tvInformation);
		
		bAddUser.setOnClickListener(this);
		bRemoveUser.setOnClickListener(this);
		bLoginUser.setOnClickListener(this);
		loginUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		nameUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		surnameUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		            activeText(false);
		        }
		    }
		});
		list.setOnItemClickListener(list.getOnItemClickListener());
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				selected(item);
				activeText(true);
			}
		});
		
		db = new DatabaseManager(this);
		if (db.dbReturnUsers().isEmpty()) {
			User guest = new User("Gość", "Profil", "domyślny");
			db.dbAddUser(guest);
		}
		for (User u: db.dbReturnUsers()) {
			listItems.add(u.getLogin());
			usersMap.put(u.getLogin(), u);
			adapter.add(u.getLogin());
			list.setAdapter(adapter);
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonAdd:
			addUser(view);
			break;
		case R.id.buttonRemove:
			removeUser(view);
			break;
		case R.id.buttonLogin:
			loginUser(view);
			break;
		}
	}

	public void addUser(View view) {
		
		if ((loginUser.length() != 0) && (nameUser.length() != 0) && (surnameUser.length() != 0)) {
		
			User user = new User();
			user.setLogin(loginUser.getText().toString());
			user.setName(nameUser.getText().toString());
			user.setSurname(surnameUser.getText().toString());
			usersMap.put(user.getLogin(), user);
			adapter.add(user.getLogin());
			list.setAdapter(adapter);
			cleanEditText();
			
			information.setText("Dodano użytkownika "+user.getLogin());
			information.setTextColor(Color.GREEN);
			
			db.dbAddUser(user);

		} else {
			information.setText("Aby dodać nowego użytkownika wypełnij wszystkie pola");
			information.setTextColor(Color.RED);
		}
		

	}
	
	public void removeUser(View view) {
		User user = new User();
		user.setLogin(loginUser.getText().toString());
		user.setName(nameUser.getText().toString());
		user.setSurname(surnameUser.getText().toString());
		
		usersMap.remove(user.getLogin());
		adapter.remove(user.getLogin());
		list.setAdapter(adapter);
		information.setText("Usunięto użytkownika "+user.getLogin());
		information.setTextColor(Color.GREEN);
		cleanEditText();
		
		db.dbRemoveUser(user);
	}
	
	private void selected(String login) {
		User user = usersMap.get(login);
		loginUser.setText(user.getLogin());
		nameUser.setText(user.getName());
		surnameUser.setText(user.getSurname());
		
		activeText(true);
				
		information.setText("");
	}
	
	public void loginUser(View view) {	
		if ((loginUser.length() != 0) && (nameUser.length() != 0) && (surnameUser.length() != 0)) {
			cel.putExtra("login", loginUser.getText().toString());
			cel.putExtra("name", nameUser.getText().toString());
			cel.putExtra("surname", surnameUser.getText().toString());
			finish();
			startActivity(cel);
		} else {
			information.setText("Wybierz użytkownika");
			information.setTextColor(Color.RED);
		}
	}
	
	private void cleanEditText() {
		loginUser.setText("");
		nameUser.setText("");
		surnameUser.setText("");
	}
	
	private void activeText(boolean fl) {
		if (fl) {
			bAddUser.setVisibility(View.INVISIBLE);
			bLoginUser.setVisibility(View.VISIBLE);
			bRemoveUser.setVisibility(View.VISIBLE);
			newfl = true;
		} else {
			if (newfl) {
				loginUser.setText("");
				nameUser.setText("");
				surnameUser.setText("");
				newfl = false;
			}
			bAddUser.setVisibility(View.VISIBLE);
			bLoginUser.setVisibility(View.INVISIBLE);
			bRemoveUser.setVisibility(View.INVISIBLE);
		}
	}
}