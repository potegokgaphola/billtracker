package billtracker.model;

import lombok.Data;

@Data
public class BillModel {
    private int billId;
    private int billAmount;
    private String date;
    private String billType;

    public BillModel() {}

    public BillModel(int billId, int billAmount, String date, String billType) {
        this.billId = billId;
        this.billAmount = billAmount;
        this.date = date;
        this.billType = billType;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

