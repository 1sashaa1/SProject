package server.Models.DTO;

import java.util.Date;

public class JoinMyDepositsDTO {
    private int idDeposit;
    private String nameDeposit;
    private String type;
    private double interestRate;
    private double term;
    private boolean isProlongation;
    private double firstAmount;
    private Date openingDate;

    public JoinMyDepositsDTO() {
    }

    public JoinMyDepositsDTO(int idDeposit, String nameDeposit, String type, double interestRate, double term,
                             boolean isProlongation, double firstAmount, Date openingDate) {
        this.idDeposit = idDeposit;
        this.nameDeposit = nameDeposit;
        this.type = type;
        this.interestRate = interestRate;
        this.term = term;
        this.isProlongation = isProlongation;
        this.firstAmount = firstAmount;
        this.openingDate = openingDate;
    }

    public String getNameDeposit() {
        return nameDeposit;
    }

    public void setNameDeposit(String nameDeposit) {
        this.nameDeposit = nameDeposit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public boolean isProlongation() {
        return isProlongation;
    }

    public void setProlongation(boolean prolongation) {
        isProlongation = prolongation;
    }

    public double getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(double firstAmount) {
        this.firstAmount = firstAmount;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
}
