package server.Services;

import server.DataAccessObjects.NotificationsDAO;
import server.Interfaces.Service;
import server.Models.Entities.Notifications;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService implements Service<Notifications> {

    private NotificationsDAO daoService;

    public NotificationService(NotificationsDAO daoService) {
        this.daoService = daoService;
    }

    @Override
    public Notifications findEntity(int id) {
        Notifications entity = (Notifications) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Notifications entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Notifications entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Notifications entity) {
        daoService.update(entity);
    }

    @Override
    public List<Notifications> findAllEntities() {
        return daoService.findAll();
    }

    public NotificationService() {
        this.daoService = new NotificationsDAO();
    }

    public List<Notifications> findNotificationsByClientId(int clientId) {
        System.out.println("ID КЛИЕНТА ДЛЯ ПОИСКА РАССЫЛОК " + clientId);

        List<Notifications> allNotifications = findAllEntities();

        List<Notifications> filteredNotifications = allNotifications.stream()
                .filter(notification -> notification.getClientId() == clientId) // Фильтрация по client_id
                .collect(Collectors.toList());

        System.out.println("Отфильтрованные уведомления: " + filteredNotifications);

        return filteredNotifications;
    }
}
