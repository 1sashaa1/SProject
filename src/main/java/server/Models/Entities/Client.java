package server.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "patronymic", length = 45)
    private String patronymic;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "citizenship", length = 45)
    private String citizenship;

    @Column(name = "document_type", length = 45)
    private String documentType;

    @Column(name = "ID_number", length = 45)
    private String idNumber;

    @Column(name = "document_number", length = 45)
    private String documentNumber;

    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ClientsDeposits> clientsDeposits;

    @OneToMany(mappedBy = "client", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Question> questions;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Operation operation;

    public Client() {
    }

    public Client(int clientId, String name, String surname, String patronymic, Date dob, String citizenship, String documentType, String idNumber, String documentNumber, Set<User> users) {
        this.id = clientId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dob = dob;
        this.citizenship = citizenship;
        this.documentType = documentType;
        this.idNumber = idNumber;
        this.documentNumber = documentNumber;
        this.users = users;
    }

    public Client(int clientId) {
        this.id = clientId;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ClientsDeposits> getClientsDeposits() {
        return clientsDeposits;
    }

    public void setClientsDeposits(Set<ClientsDeposits> clientsDeposits) {
        this.clientsDeposits = clientsDeposits;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
