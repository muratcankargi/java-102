package PolicyManagment;

import java.util.Date;

public class ResidenceInsurance extends Insurance{
    public ResidenceInsurance(String insuranceName, double insuranceFee, Date startEndDate, User user) {
        super(insuranceName, insuranceFee, startEndDate, user);
    }

    @Override
    public void calculate() {
        //işlem
    }
}
