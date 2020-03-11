package billtracker.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BeanMapper {

    Object map(ResultSet rs) throws SQLException;
}
