package PolicyManagment;

import java.util.Date;

public abstract class Insurance {
    private String insuranceName;
    private double insuranceFee;//ücreti
    private Date startEndDate;
    private User user;

    public Insurance(String insuranceName, double insuranceFee, Date startEndDate,User user) {
        this.insuranceName = insuranceName;
        this.insuranceFee = insuranceFee;
        this.startEndDate = startEndDate;
        this.user=user;
    }

    public abstract void calculate();

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public Date getStartEndDate() {
        return startEndDate;
    }

    public void setStartEndDate(Date startEndDate) {
        this.startEndDate = startEndDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
