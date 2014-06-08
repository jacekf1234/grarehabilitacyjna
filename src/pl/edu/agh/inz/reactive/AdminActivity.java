package pl.edu.agh.inz.reactive;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.inz.gra1.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

public class AdminActivity extends Activity implements OnClickListener {

	ListView list;
	EditText loginUser, nameUser, surnameUser;
	ArrayList<String> listItems = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Map<String, User> usersMap = new HashMap<String, User>();
	View bAddUser, bRemoveUser, bLoginUser;
	Bundle bundle;
	Intent cel;
	
	Dao<User, String> userDao;
	DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);
	AndroidBaseManager baseManager = new AndroidBaseManager(userDao);

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
		
		bAddUser.setOnClickListener(this);
		bRemoveUser.setOnClickListener(this);
		bLoginUser.setOnClickListener(this);
		list.setOnItemClickListener(list.getOnItemClickListener());
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				selected(item);
								
			}
		});
		
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
		//	finish();
		// System.exit(0);
			break;
		}
	}

	public void addUser(View view) {
		User user = new User();
		user.setLogin(loginUser.getText().toString());
		user.setName(nameUser.getText().toString());
		user.setSurname(surnameUser.getText().toString());
		usersMap.put(user.getLogin(), user);
		adapter.add(user.getLogin());
		list.setAdapter(adapter);
		cleanEditText();
		
//		try {
//			baseManager.addNewUser(user);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void removeUser(View view) {
		usersMap.remove(loginUser.getText().toString());
		adapter.remove(loginUser.getText().toString());
		list.setAdapter(adapter);
		cleanEditText();
	}
	
	private void selected(String login) {
		User user = usersMap.get(login);
		loginUser.setText(user.getLogin());
		nameUser.setText(user.getName());
		surnameUser.setText(user.getSurname());
		
	
		//when user can't set 
//		loginUser.setEnabled(false);
//		nameUser.setEnabled(false);
//		surnameUser.setEnabled(false);
//		bAddUser.setClickable(false);		
	}
	
	public void loginUser(View view) {		
		cel.putExtra("login", loginUser.getText().toString());
		cel.putExtra("name", nameUser.getText().toString());
		cel.putExtra("surname", surnameUser.getText().toString());
		finish();
		startActivity(cel);
	}
	
	private void cleanEditText() {
		loginUser.setText("");
		nameUser.setText("");
		surnameUser.setText("");
	}
}