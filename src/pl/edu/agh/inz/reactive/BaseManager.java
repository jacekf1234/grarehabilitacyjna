package pl.edu.agh.inz.reactive;

import java.sql.SQLException;
import java.util.List;

public interface BaseManager {
	public void addNewUser(User user) throws SQLException;
	public void deleteUser(User user) throws SQLException;
	public List<User> getAvailableUsers() throws SQLException;
	

}
