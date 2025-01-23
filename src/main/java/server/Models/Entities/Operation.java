package server.Models.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoperation")
    private int idoperation;
    @Column(name = "date", length = 45)
    private Date dateSend;
    @Column(name = "type", length = 45)
    private String type;
    @Column(name = "sum", length = 45)
    private double sum;
    @Column(name = "done", length = 45)
    private boolean done;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = false)
    @JoinColumn(name = "depositdata_iddeposit", nullable = true)
    private Deposit deposit;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = false)
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = false)
    @JoinColumn(name = "clientdeposit", nullable = true)
    private ClientsDeposits clientsDeposits;


    public ClientsDeposits getClientsDeposits() {
        return clientsDeposits;
    }

    public void setClientsDeposits(ClientsDeposits clientsDeposits) {
        this.clientsDeposits = clientsDeposits;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(int idoperation) {
        this.idoperation = idoperation;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}