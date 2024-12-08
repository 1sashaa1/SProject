package server.Models.DTO;

import server.Models.Entities.Client;
import server.Models.Entities.Deposit;
import server.Models.Entities.Operation;

import javax.persistence.*;
import java.sql.Date;

public class OperationDTO {

    private int idoperation;

    private Date dateSend;

    private String type;

    private double sum;

    private boolean done;

    public OperationDTO() {
    }

    public OperationDTO(Operation operation){
        this.idoperation = operation.getIdoperation();
        this.dateSend = operation.getDateSend();
        this.sum = operation.getSum();
        this.type = operation.getType();
        this.done = operation.isDone();
    }

    public int getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(int idoperation) {
        this.idoperation = idoperation;
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