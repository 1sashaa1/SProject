package server.Models.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "deposit")
public class ClientsDeposits implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddeposit")
    private int idDeposit;
    @Column(name = "isOpen", length = 45)
    private boolean isOpen;
    @Column(name = "first_amount", length = 45)
    private double firstAmount;
    @Column(name = "opening_date", length = 45)
    private Date openingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_client_id",nullable = true)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dep_data", nullable = true)
    private Deposit deposit;

    @OneToOne(mappedBy = "deposit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public ClientsDeposits() {
    }

    public int getIdDeposit() {
        return idDeposit;
    }

    public void setIdDeposit(int idDeposit) {
        this.idDeposit = idDeposit;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
}
