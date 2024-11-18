package server.Models.DTO;

import server.Models.Entities.ClientsDeposits;
import server.Models.Entities.Deposit;
import server.Models.Entities.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class DepositDTO {
    private int id;

    private String nameDeposit;

    private String type;

    private double interestRate;

    private double minAmount;

    private double term;

    private boolean isReplenishment;

    private boolean isTransactions;

    private boolean isProlongation;

    public DepositDTO (Deposit deposit) {
        this.id = deposit.getId();
        this.nameDeposit = deposit.getName();
        this.type = deposit.getType();
        this.interestRate = deposit.getInterestRate();
        this.minAmount = deposit.getMinAmount();
        this.term = deposit.getTerm();
        this.isReplenishment = deposit.isReplenishment();
        this.isTransactions = deposit.isTransactions();
        this.isProlongation = deposit.isProlongation();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nameDeposit;
    }

    public void setName(String name) {
        this.nameDeposit = name;
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

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public boolean isReplenishment() {
        return isReplenishment;
    }

    public void setReplenishment(boolean replenishment) {
        isReplenishment = replenishment;
    }

    public boolean isTransactions() {
        return isTransactions;
    }

    public void setTransactions(boolean transactions) {
        isTransactions = transactions;
    }

    public boolean isProlongation() {
        return isProlongation;
    }

    public void setProlongation(boolean prolongation) {
        isProlongation = prolongation;
    }
}
