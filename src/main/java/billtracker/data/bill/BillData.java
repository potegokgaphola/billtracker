package billtracker.data.bill;

import billtracker.data.DataOperation;

public class BillData {
    private DataOperation dataOperation = new DataOperation();

    public void addBill(AddBillModel addBillModel) {
        dataOperation.insert("INSERT INTO `bills`(`bill_amount`, `date`, `bill_type`, `user_id`) VALUE(?, ?, ?, ?)",
                new Object[] {addBillModel.getamount, ...});
    }
}
