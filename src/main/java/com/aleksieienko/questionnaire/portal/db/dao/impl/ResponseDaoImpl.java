package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.ResponseDao;
import com.aleksieienko.questionnaire.portal.db.entity.Response;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@ManagedBean
@ApplicationScoped
public class ResponseDaoImpl implements ResponseDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void add(Response response) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            response.setId((Integer) session.save(response));
            transaction.commit();
        } catch (RuntimeException e) {
            response.setId(null);
            e.printStackTrace();
        }
    }

    @Override
    public List<Response> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Response> criteriaQuery = criteriaBuilder.createQuery(Response.class);
            Root<Response> root = criteriaQuery.from(Response.class);
            root.fetch("questionnaires", JoinType.INNER);
            criteriaQuery.select(root).distinct(true);
            Query<Response> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
