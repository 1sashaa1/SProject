package server.Models.Entities;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee")
    private int idemployee;

    @Column(name = "seat", length = 45)
    private String seat;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    private Client client;


    public Employee(){}

    public Employee(int idemployee, String seat, String name, String surname, String patronymic, Client client) {
        this.idemployee = idemployee;
        this.seat = seat;
        this.client = client;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
