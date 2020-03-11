package billtracker.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname = "users_db";
    private static Integer portnumber = 3306;
    private static String password = "";

    private static final DataSource dataSource = new DataSource();
    private MysqlDataSource datasource = new MysqlDataSource();

    private DataSource() {
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
    }

    public static DataSource getInstance() {
        return dataSource;
    }

    private MysqlDataSource mysqlDataSource(){
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.mysqlDataSource().getConnection();
    }
}
