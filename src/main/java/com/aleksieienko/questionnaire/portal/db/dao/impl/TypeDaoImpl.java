package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.Fields;
import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.TypeDao;
import com.aleksieienko.questionnaire.portal.db.entity.Type;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@ManagedBean
@ApplicationScoped
public class TypeDaoImpl implements TypeDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void add(Type type) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(type);
            transaction.commit();
        } catch (RuntimeException e) {
            type.setId(null);
            e.printStackTrace();
        }
    }

    @Override
    public List<Type> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Type> criteriaQuery = criteriaBuilder.createQuery(Type.class);
            Root<Type> root = criteriaQuery.from(Type.class);
            criteriaQuery.select(root);

            Query<Type> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Type getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Type> criteriaQuery = criteriaBuilder.createQuery(Type.class);
            Root<Type> root = criteriaQuery.from(Type.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.TYPE_ID), id));
            Query<Type> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Type getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Type> criteriaQuery = criteriaBuilder.createQuery(Type.class);
            Root<Type> root = criteriaQuery.from(Type.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.TYPE_NAME), name));
            Query<Type> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeType(Type type) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(type);
            transaction.commit();
        } catch (TransactionRequiredException e) {
            transaction.rollback();
            transaction.commit();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
