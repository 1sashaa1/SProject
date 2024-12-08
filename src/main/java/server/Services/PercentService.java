package server.Services;

import server.DataAccessObjects.PercentDAO;
import server.Interfaces.Service;
import server.Models.Entities.Percent;

import java.util.List;

public class PercentService implements Service<Percent> {

    private PercentDAO daoService;

    public PercentService(PercentDAO daoService) {
        this.daoService = daoService;
    }

    public PercentService() {
        this.daoService = new PercentDAO();
    }

    @Override
    public Percent findEntity(int id) {
        Percent entity = (Percent) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Percent entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Percent entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Percent entity) {
        daoService.update(entity);
    }

    @Override
    public List<Percent> findAllEntities() {
        return daoService.findAll();
    }
}
