package server.Services;

import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.Entities.User;

import java.util.List;

public class UserService implements Service<User> {

    UserDAO daoService = new UserDAO(); // добавить работу с базой данных

    @Override
    public User findEntity(int id) {
        User entity = (User) daoService.findById(id);
        if (entity.getClient() != null)  {
            entity.getClient().setUsers(null);
            /////////////
        }
        return entity;
    }

    @Override
    public void saveEntity(User entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(User entity) {
        daoService.update(entity);
    }

    @Override
    public List<User> findAllEntities() {
        return  daoService.findAll();
    }
}
