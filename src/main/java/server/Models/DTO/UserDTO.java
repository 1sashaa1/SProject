package server.Models.DTO;

import com.google.gson.annotations.SerializedName;
import server.Models.Entities.Admin;
import server.Models.Entities.Client;
import server.Models.Entities.Employee;
import server.Models.Entities.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserDTO {

    private int id;
    private String login;
    private String password;
    private String role;
    private User user;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.password = user.getPassword();
    }

    public UserDTO(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public UserDTO(int id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}