package PolicyManagment;

import java.util.Date;

public class TravelInsurance extends Insurance{
    public TravelInsurance(String insuranceName, double insuranceFee, Date startEndDate, User user) {
        super(insuranceName, insuranceFee, startEndDate, user);
    }

    @Override
    public void calculate() {
        //işlem
    }
}
