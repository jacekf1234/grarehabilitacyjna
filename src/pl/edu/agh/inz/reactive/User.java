package pl.edu.agh.inz.reactive;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "users")
public class User {
	
	//nazwy kolumn
	public static final String ID_USER = "login";
    public static final String NAME_USER = "name";
    public static final String SURNAME_USER = "surname";
	
    @DatabaseField(id = true, useGetSet = true, columnName = ID_USER)
	String login;
	
	@DatabaseField(useGetSet = true, columnName = NAME_USER)
	String name;
	
	@DatabaseField(useGetSet = true, columnName = SURNAME_USER)
	String surname;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
