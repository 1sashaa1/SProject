package server.Models.DTO;

import server.Models.Entities.Client;

public class ClientDTO {

    private int id;
    private String name;

    private String surname;

    private String patronymic;

    private String dob;

    private String citizenship;

    private String documentType;

    private String idNumber;

    private String documentNumber;

    private String email;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
this.surname = client.getSurname();
this.patronymic = client.getPatronymic();
this.dob = String.valueOf(client.getDob());
this.citizenship = client.getCitizenship();
this.documentNumber = client.getDocumentNumber();
this.documentType = client.getDocumentType();
this.idNumber = client.getIdNumber();
this.email = client.getEmail();
    }

    public ClientDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
}