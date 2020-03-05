package billtracker.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataOperation {

	public List<Object> getResults(String sqlQuery, Object[] params, BeanMapper mapper) throws SQLException {
		List<Object> results = new ArrayList<>();
		try (Connection con = DataSource.getInstance().getConnection();
			 PreparedStatement stat = con.prepareStatement(sqlQuery)) {
			for (int i = 0; i < params.length; i++) {
				stat.setObject(i + 1, params[i]);
			}
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				results.add( mapper.map(rs) );
			}
		}
		return results;
	}

}
