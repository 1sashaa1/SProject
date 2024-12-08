package server.Services;

import server.DataAccessObjects.NotificationsDAO;
import server.DataAccessObjects.OperationDAO;
import server.Interfaces.Service;
import server.Models.Entities.Notifications;
import server.Models.Entities.Operation;

import java.util.List;

public class OperationService implements Service<Operation> {

    private OperationDAO daoService;

    public OperationService(OperationDAO daoService) {
        this.daoService = daoService;
    }

    @Override
    public Operation findEntity(int id) {
        Operation operation = (Operation) daoService.findById(id);
        return operation;
    }

    @Override
    public void saveEntity(Operation entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Operation entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Operation entity) {
        daoService.update(entity);
    }

    @Override
    public List<Operation> findAllEntities() {
        return daoService.findAll();
    }

    public OperationService() {
        this.daoService = new OperationDAO();
    }
}
