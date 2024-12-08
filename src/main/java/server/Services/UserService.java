package server.Services;

import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.DTO.ClientDTO;
import server.Models.Entities.Client;
import server.Models.Entities.User;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {

    UserDAO daoService = new UserDAO(); // добавить работу с базой данных

    @Override
    public User findEntity(int id) {
        User entity = (User) daoService.findById(id);
        if (entity.getClient() != null)  {
            entity.getClient().setUsers(null);
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

    public ClientDTO findClientByClientId(int clientId) {
        Optional<User> optionalUser = findAllEntities().stream()
                .filter(u -> u.getClient() != null && u.getId() == clientId)
                .findFirst();

        return optionalUser.map(user -> convertToClientDTO(user.getClient())).orElse(null);
    }

    private ClientDTO convertToClientDTO(Client client) {
        return new ClientDTO(client.getId());
    }

}
