package billtracker.data.bill;

import billtracker.data.BeanMapper;
import billtracker.data.DataOperation;
import billtracker.model.BillModel;
import billtracker.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public class BillData {
    private BeanMapper beanMapper = rs -> {
        int billId = rs.getInt("bill_id");
        int billAmount = rs.getInt("bill_amount");
        String billDate = rs.getString("date");
        String billType = rs.getString("bill_type");
        return new BillModel(billId, billAmount, billDate, billType);
    };
    private DataOperation dataOperation = new DataOperation();

    public int addBill(int billAmount, String billDate, String billType) throws SQLException {
        return dataOperation.insert("INSERT INTO `bills`(`bill_amount`, `date`, `bill_type`, `user_id`) VALUE(?, ?, ?, ?)", new Object[] {billAmount, billDate, billType, UserModel.getId()});
    }

    public List<Object> getBills(String billDate) throws SQLException {
        return dataOperation.getResults("SELECT * FROM `bills` WHERE `user_id`=? AND `date`=?", new Object[] {UserModel.getId(), billDate}, beanMapper);
    }

    public List<Object> getGraphData(String startDate, String endDate, String billType) throws SQLException {
        return dataOperation.getResults("SELECT * FROM `bills` WHERE `date` BETWEEN ? AND ? AND `bill_type`=? AND `user_id`=? ORDER BY `date` ASC", new Object[]{startDate, endDate, billType, UserModel.getId()}, beanMapper);
    }
}
