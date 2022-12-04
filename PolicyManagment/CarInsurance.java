package PolicyManagment;

import java.util.Date;

public class CarInsurance extends Insurance{
    public CarInsurance(String insuranceName, double insuranceFee, Date startEndDate, User user) {
        super(insuranceName, insuranceFee, startEndDate, user);
    }

    @Override
    public void calculate() {

    }
}
