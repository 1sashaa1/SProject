package server.Models.DTO;

import server.Models.Entities.Notifications;

import java.util.List;
import java.util.stream.Collectors;
import server.Models.Entities.Client;
import server.Models.Entities.User;

import javax.management.Notification;

public class NotificationDTO {
    private int id;
    private String message;
    private List<Integer> clientIds;
    private boolean read;


    public NotificationDTO() {
    }

    public NotificationDTO(Notifications notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.clientIds = notification.getClients().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }


    public NotificationDTO(int id, String message, List<Integer> clientIds) {
        this.id = id;
        this.message = message;
        this.clientIds = clientIds;
    }

    public NotificationDTO(int id, String message, boolean Read) {
        this.message = message;
        this.id = id;
        this.read = Read;
    }

    public NotificationDTO(Notification notification) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getClientIds() {
        return clientIds;
    }

    public void setClientIds(List<Integer> clientIds) {
        this.clientIds = clientIds;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
