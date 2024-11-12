package server.Services;

import server.DataAccessObjects.DepositDAO;
import server.DataAccessObjects.NotificationsDAO;
import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.Entities.Deposit;
import server.Models.Entities.Notifications;
import server.Models.Entities.User;

import java.util.List;

public class NotificationService implements Service<Notifications> {

    NotificationsDAO daoService = new NotificationsDAO(); // добавить работу с базой данных

    @Override
    public Notifications findEntity(int id) {
        Notifications entity = (Notifications) daoService.findById(id);
        if (entity.getMessage() != null)  {

        }
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
        return  daoService.findAll();
    }
}
