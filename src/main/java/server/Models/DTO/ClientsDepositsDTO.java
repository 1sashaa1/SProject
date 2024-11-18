package server.Models.DTO;

import server.Models.Entities.Client;
import server.Models.Entities.ClientsDeposits;
import server.Models.Entities.Deposit;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

public class ClientsDepositsDTO {
    private int idDeposit;
    private boolean isOpen;
    private double firstAmount;
    private Date openingDate;

    public ClientsDepositsDTO() {
    }

    public ClientsDepositsDTO(ClientsDeposits clientsDeposits) {
        this.idDeposit = clientsDeposits.getIdDeposit();
        this.isOpen = clientsDeposits.isOpen();
        this.firstAmount = clientsDeposits.getFirstAmount();
        this.openingDate = clientsDeposits.getOpeningDate();
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
}
