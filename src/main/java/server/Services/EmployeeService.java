package server.Services;
import server.DataAccessObjects.ClientDAO;
import server.DataAccessObjects.EmployeeDAO;
import server.DataAccessObjects.UserDAO;
import server.Interfaces.DAO;
import server.Interfaces.Service;
import server.Models.Entities.Client;
import server.Models.Entities.Employee;
import server.Models.Entities.User;
import java.util.List;
public class EmployeeService implements Service<Employee>{
    EmployeeDAO daoService = new EmployeeDAO(); // добавить работу с базой данных

    @Override
    public Employee findEntity(int id) {
        Employee entity = (Employee) daoService.findById(id);
        if (entity.getUsers() != null) {
            // что-нибудь ещё добавить
            //////entity.getUsers().;
            /////////////
        }
        return null;
    }

    @Override
    public void saveEntity(Employee entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Employee entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Employee entity) {
        daoService.update(entity);
    }

    @Override
    public List<Employee> findAllEntities() {
        return daoService.findAll();
    }
}

