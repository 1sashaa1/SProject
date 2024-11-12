package server.Models.Entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "datadeposit")
public class Deposit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "interestRate", length = 45)
    private double interestRate;

    @Column(name = "minAmount", length = 45)
    private double minAmount;

    @Column(name = "term", length = 45)
    private double term;

    @Column(name = "isReplenishment", length = 45)
    private boolean isReplenishment;

    @Column(name = "isTransactions", length = 45)
    private boolean isTransactions;

    @Column(name = "isProlongation", length = 45)
    private boolean isProlongation;

    public Deposit() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setTerm(int term) {
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
