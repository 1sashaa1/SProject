package server.Services;

import org.hibernate.Session;
import org.hibernate.query.Query;
import server.DataAccessObjects.NotificationsDAO;
import server.DataAccessObjects.QuestionDAO;
import server.Interfaces.Service;
import server.Models.DTO.JoinMyDepositsDTO;
import server.Models.DTO.QuestionDTO;
import server.Models.Entities.Question;
import server.Utility.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionService implements Service<Question> {
    private QuestionDAO daoService;

    public QuestionService(QuestionDAO daoService) {
        this.daoService = daoService;
    }

    @Override
    public Question findEntity(int id) {
        Question entity = (Question) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Question entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Question entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Question entity) {
        daoService.update(entity);
    }

    @Override
    public List<Question> findAllEntities() {
        return daoService.findAll();
    }

    public QuestionService() {
        this.daoService = new QuestionDAO();
    }

    public List<QuestionDTO> getMyQuestion(int idUser) {
        System.out.println("idUser: " + idUser);
        String hql = "SELECT new server.Models.DTO.QuestionDTO(" +
                "q.id, q.text, q.answer) " +
                "FROM Question q " +
                "JOIN q.client cl " +
                "JOIN cl.users u " +
                "WHERE u.id = :idUser";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Executing HQL: " + hql);
            Query<QuestionDTO> query = session.createQuery(hql, QuestionDTO.class);
            query.setParameter("idUser", idUser);
            System.out.println("Parameter idUser: " + idUser);

            List<QuestionDTO> results = query.getResultList();
            System.out.println("Query results: " + results);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Возвращаем пустой список в случае ошибки
        }
    }

}
