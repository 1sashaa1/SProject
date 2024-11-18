package server.Models.DTO;

import java.util.Date;
import java.util.List;

public class JoinClientsDepositsDTO {
    private int idDeposit;
    private String nameDeposit;

    private boolean isOpen;
    private double firstAmount;
    private Date openingDate;

    private String name;
    private String surname;
    private String patronymic;

    public JoinClientsDepositsDTO(int idDeposit, String nameDeposit, boolean isOpen, double firstAmount, Date openingDate, String name, String surname, String patronymic) {
        this.idDeposit = idDeposit;
        this.nameDeposit = nameDeposit;
        this.isOpen = isOpen;
        this.firstAmount = firstAmount;
        this.openingDate = openingDate;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public int getIdDeposit() {
        return idDeposit;
    }

    public void setIdDeposit(int idDeposit) {
        this.idDeposit = idDeposit;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public double getFirstAmount() {
        return firstAmount;
    }

    public void setFirstAmount(double firstAmount) {
        this.firstAmount = firstAmount;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getNameDeposit() {
        return nameDeposit;
    }

    public void setNameDeposit(String nameDeposit) {
        this.nameDeposit = nameDeposit;
    }
}
