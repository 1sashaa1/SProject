package server.Models.DTO;

import server.Models.Entities.Client;

public class ClientDTO {

    private int id;
    private String name;
    private String surname;
    private String patronymic;


    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();

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
}