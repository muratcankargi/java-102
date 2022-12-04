package PolicyManagment;

import java.util.Date;

public class HealthInsurance extends Insurance{
    public HealthInsurance(String insuranceName, double insuranceFee, Date startEndDate, User user) {
        super(insuranceName, insuranceFee, startEndDate, user);
    }

    @Override
    public void calculate() {
        //işlemler
    }
}
