package billtracker.data.user;

import billtracker.data.BeanMapper;
import billtracker.data.DataOperation;
import billtracker.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public class UserData {
    BeanMapper beanMapper = rs -> {
        int userId = rs.getInt("user_id");
        String username = rs.getString("username");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String password = rs.getString("password");
        return new UserModel(userId, username, firstname, lastname, password);
    };
    private DataOperation dataOperation = new DataOperation();

    public int addUser(String username, String firstname, String lastname, String password) throws SQLException {
        return dataOperation.insert("INSERT INTO `users`(`username`, `firstname`, `lastname`, `password`) VALUES (?, ?, ?, ?)", new Object[]{username, firstname, lastname, password});
    }

    public List<Object> getUser(String username, String password) throws SQLException {
        return dataOperation.getResults("SELECT * FROM `users` WHERE `username` = ? AND `password` =  ?", new Object[]{username, password}, beanMapper);
    }
}
