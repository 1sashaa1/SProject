package server.Models.DTO;

import java.util.Date;

public class EmployeeClientDTO {
    private String email;

    private String name;

    private String surname;

    private String patronymic;

    private Date dob;

    private String citizenship;

    private String documentType;

    private String idNumber;

    private String documentNumber;

    private int IdE;

    private String seat;

    public EmployeeClientDTO(String email, String name, String surname, String patronymic, Date dob, String citizenship, String documentType, String idNumber, String documentNumber, int idE, String seat) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dob = dob;
        this.citizenship = citizenship;
        this.documentType = documentType;
        this.idNumber = idNumber;
        this.documentNumber = documentNumber;
        IdE = idE;
        this.seat = seat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getIdE() {
        return IdE;
    }

    public void setIdE(int idE) {
        IdE = idE;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
