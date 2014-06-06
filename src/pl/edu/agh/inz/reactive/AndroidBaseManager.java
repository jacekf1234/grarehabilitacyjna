package pl.edu.agh.inz.reactive;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.j256.ormlite.dao.Dao;

public class AndroidBaseManager implements BaseManager {

	private Logger logger = Logger.getLogger(AndroidBaseManager.class.getSimpleName());

    private Dao<User, String> userDao;

    public AndroidBaseManager(Dao<User, String> userDao){
        this.userDao = userDao;
    }

    public void addNewUser(User user) throws SQLException {
        try {
            userDao.create(user);
            logger.info("New user is added to the database");
        } catch (SQLException e) {
            logger.info("Cannot add new user to the database");
            throw new RuntimeException(e);
        }
    }
    public void deleteUser(User user) throws SQLException {
        try {
            userDao.delete(user);
            logger.info("User is deleted from the database");
        } catch (SQLException e) {
            logger.info("Cannot delete user from the database");
            throw new RuntimeException(e);
        }
    }
    public List<User> getAvailableUsers() throws SQLException {
        try {
            List<User> u = userDao.queryForAll();
            logger.info("Users getted from the database");
            return u;
        } catch (SQLException e) {
            logger.info("Cannot gezt users from the database");
            throw new RuntimeException(e);
        }
    }
}
