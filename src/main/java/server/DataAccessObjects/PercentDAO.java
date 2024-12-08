package server.DataAccessObjects;

import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Interfaces.DAO;
import server.Models.Entities.Percent;

import java.util.List;

public class PercentDAO implements DAO {
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
        Percent percent = session.get(Percent.class, id);
        session.close();
        return percent;
    }

    @Override
    public List findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Percent> percent = session.createQuery("From Percent", Percent.class).list();
        session.close();
        return percent;
    }
}
