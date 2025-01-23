package server.Services;

import server.DataAccessObjects.ClientsDepositsDAO;
import server.Interfaces.Service;
import server.Models.DTO.JoinClientsDepositsDTO;
import server.Models.DTO.JoinMyDepositsDTO;
import server.Models.Entities.ClientsDeposits;
import org.hibernate.Session;
import org.hibernate.query.Query;
import server.Utility.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.Collections;
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

    public List<JoinClientsDepositsDTO> getCombinedData() {
        String hql = "SELECT new server.Models.DTO.JoinClientsDepositsDTO(" +
                "cd.idDeposit, d.nameDeposit, cd.isOpen, cd.firstAmount, cd.openingDate, " +
                "c.id, c.name, c.surname, c.patronymic) " +
                "FROM Client c " +
                "JOIN c.clientsDeposits cd JOIN cd.deposit d";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<JoinClientsDepositsDTO> query = session.createQuery(hql, JoinClientsDepositsDTO.class);
            return query.getResultList();
        }
    }
    public List<JoinMyDepositsDTO> getMyDeposits(int idUser) {
        System.out.println("idUser: " + idUser);
        String hql = "SELECT new server.Models.DTO.JoinMyDepositsDTO(" +
                "cd.idDeposit, d.nameDeposit, d.type, d.interestRate, d.term, " +
                "d.isProlongation, cd.firstAmount, cd.openingDate) " +
                "FROM Client c " +
                "JOIN c.users u " +
                "JOIN c.clientsDeposits cd " +
                "JOIN cd.deposit d " +
                "WHERE u.id = :idUser AND cd.isOpen = true";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Executing HQL: " + hql);
            Query<JoinMyDepositsDTO> query = session.createQuery(hql, JoinMyDepositsDTO.class);
            query.setParameter("idUser", idUser);
            System.out.println("Parameter idUser: " + idUser);

            List<JoinMyDepositsDTO> results = query.getResultList();
            System.out.println("Query results: " + results);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Возвращаем пустой список в случае ошибки
        }
    }



    public ClientsDeposits findByIdDeposit(int idDeposit, String depositName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM ClientsDeposits cd " +
                                    "WHERE cd.idDeposit = :idDeposit " +
                                    "AND cd.deposit.nameDeposit = :depositName",
                            ClientsDeposits.class
                    )
                    .setParameter("idDeposit", idDeposit)
                    .setParameter("depositName", depositName)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
