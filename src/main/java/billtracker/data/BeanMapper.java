package billtracker.data;

import java.sql.ResultSet;

public interface BeanMapper {
    Object map(ResultSet rs);
}
