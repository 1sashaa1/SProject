package server.Services;

import server.DataAccessObjects.DepositDAO;
import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.Entities.Deposit;
import server.Models.Entities.User;

import java.util.List;

public class DepositService implements Service<Deposit> {

    DepositDAO daoService = new DepositDAO(); // добавить работу с базой данных

    @Override
    public Deposit findEntity(int id) {
        Deposit entity = (Deposit) daoService.findById(id);
        if (entity.getName() != null)  {

        }
        return entity;
    }

    @Override
    public void saveEntity(Deposit entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Deposit entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Deposit entity) {
        daoService.update(entity);
    }

    @Override
    public List<Deposit> findAllEntities() {
        return  daoService.findAll();
    }
}
