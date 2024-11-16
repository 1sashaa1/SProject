package server.DataAccessObjects;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Interfaces.DAO;
import server.Models.Entities.ClientsDeposits;
import server.Models.Entities.Deposit;

import java.util.List;

public class ClientsDepositsDAO implements DAO {
    @Override
    public void save(Object obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        System.out.println("Объект " + obj);
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Object obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Object obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Object findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ClientsDeposits deposit = session.get(ClientsDeposits.class, id);
        session.close();
        return deposit;
    }

    @Override
    public List findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<ClientsDeposits> deposit = session.createQuery("From ClientsDeposits", ClientsDeposits.class).list();
        session.close();
        return deposit;
    }
}
