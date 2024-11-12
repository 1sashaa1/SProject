package server.Models.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int id;
    @Column(name = "login", length = 45)
    private String login;
    @Column(name = "password", length = 45)
    private String password;
    @Column(name = "role", length = 45)
    private String role;
    @Column(name = "client_client_id", length = 45)
    private Client client;
    @Column(name = "employee_idemployee", length = 45)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "admin_admin_id")
    private Admin admin;

    public User() {
    }

    public User(int id, String login, String password, String role, Client client) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.client = client;
    }

    public User(int id, String login, String password, String role, Employee employee) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    public int getId() {
        return id;
    }

@Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password" )
    public String getPassword() {
        return password;
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_client_id", nullable = true) // Это внешняя ссылка на client_id в таблице Client
    public Client getClient() {
        return client;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_idemployee", nullable = true)
    public Employee getEmployee() {
        return employee;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setClient(Client client) {
        this.client = client;
        if (client != null) {
            this.employee = null;
        }
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (this.client == null) {
            this.employee = employee;
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_admin_id", nullable = true)
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}