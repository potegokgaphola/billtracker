package billtracker.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;

public class DataSource {
    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname = "users_db";
    private static Integer portnumber = 3306;
    private static String password = "manager";

    private static final DataSource dataSource = new DataSource();

    private DataSource() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
    }

    public static DataSource getInstance() {
        return dataSource;
    }

    public Connection getConnection() {
        return dataSource.getConnection();
    }
}
