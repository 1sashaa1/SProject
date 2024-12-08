package server.Models.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "percent")
public class Percent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpercent")
    private int idpercent;
    @Column(name = "period", length = 45)
    private int period;
    @Column(name = "sum", length = 45)
    private double sum;
    @Column(name = "date", length = 45)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "deposit_iddeposit", nullable = true)
    private ClientsDeposits clientsDeposits;

    public ClientsDeposits getClientsDeposits() {
        return clientsDeposits;
    }

    public void setClientsDeposits(ClientsDeposits clientsDeposits) {
        this.clientsDeposits = clientsDeposits;
    }

    public int getIdpercent() {
        return idpercent;
    }

    public void setIdpercent(int idercent) {
        this.idpercent = idercent;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
