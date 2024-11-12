package server.Services;

import server.DataAccessObjects.ClientDAO;
import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.Entities.Client;
import server.Models.Entities.User;

import java.util.List;

public class ClientService implements Service<Client> {

    ClientDAO daoService = new ClientDAO(); // добавить работу с базой данных

    @Override
    public Client findEntity(int id) {
        Client entity = (Client) daoService.findById(id);
        if (entity.getUsers() != null) {
            // что-нибудь ещё добавить
            //////entity.getUsers().;
            /////////////
        }
        return null;
    }

    @Override
    public void saveEntity(Client entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Client entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Client entity) {
        daoService.update(entity);
    }

    @Override
    public List<Client> findAllEntities() {
        return daoService.findAll();
    }
}
