package server.Models.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notifications implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "client_id", length = 45)
    private int clientId;
    @Column(name = "message", length = 45)
    private String message;
    @Column(name = "is_read", length = 45)
    private boolean isRead;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "notification_clients", // Таблица для связи
            joinColumns = @JoinColumn(name = "notification_id"), // Уведомление
            inverseJoinColumns = @JoinColumn(name = "client_id") // Клиенты
    )
    private List<User> clients;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public List<User> getClients() {
        return clients;
    }
}
