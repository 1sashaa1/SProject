package server.Services;

import server.DataAccessObjects.ClientsDepositsDAO;
import server.DataAccessObjects.DepositDAO;
import server.Interfaces.Service;
import server.Models.Entities.ClientsDeposits;
import server.Models.Entities.Deposit;

import java.util.List;

public class ClientsDepositsService implements Service<ClientsDeposits> {

    ClientsDepositsDAO daoService = new ClientsDepositsDAO();

    @Override
    public ClientsDeposits findEntity(int id) {
        ClientsDeposits entity = (ClientsDeposits) daoService.findById(id);
        if (entity.getDeposit() != null)  {

        }
        return entity;
    }

    @Override
    public void saveEntity(ClientsDeposits entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(ClientsDeposits entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(ClientsDeposits entity) {
        daoService.update(entity);
    }

    @Override
    public List<ClientsDeposits> findAllEntities() {
        return  daoService.findAll();
    }
}
