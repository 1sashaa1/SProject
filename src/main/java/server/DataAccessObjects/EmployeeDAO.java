package server.DataAccessObjects;

import org.hibernate.Cache;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import server.Interfaces.DAO;
import server.Models.Entities.Client;
import server.Models.Entities.Employee;

import java.util.List;

public class EmployeeDAO implements DAO{
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
        Employee employee = session.get(Employee.class, id);
        session.close();
        return employee;
    }

    @Override
    public List findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Employee> employee = session.createQuery("FROM Employee", Employee.class).list();
        session.close();
        return employee;
    }
}