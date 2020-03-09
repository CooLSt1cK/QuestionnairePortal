package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.Fields;
import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.FieldDao;
import com.aleksieienko.questionnaire.portal.db.entity.Field;
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
public class FieldDaoImpl implements FieldDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void add(Field field) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            field.setId((Integer) session.save(field));
            transaction.commit();
        } catch (RuntimeException e) {
            field.setId(null);
            e.printStackTrace();
        }
    }

    @Override
    public List<Field> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Field> criteriaQuery = criteriaBuilder.createQuery(Field.class);
            Root<Field> root = criteriaQuery.from(Field.class);
            criteriaQuery.select(root);

            Query<Field> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Field> getActive() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Field> criteriaQuery = criteriaBuilder.createQuery(Field.class);
            Root<Field> root = criteriaQuery.from(Field.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.FIELD_ACTIVE), true));

            Query<Field> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Field> criteriaQuery = criteriaBuilder.createQuery(Field.class);
            Root<Field> root = criteriaQuery.from(Field.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.FIELD_ID), id));
            Query<Field> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field getByLabel(String label) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Field> criteriaQuery = criteriaBuilder.createQuery(Field.class);
            Root<Field> root = criteriaQuery.from(Field.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.FIELD_LABEL), label));
            Query<Field> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Field field) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(field);
            transaction.commit();
        } catch (TransactionRequiredException e) {
            transaction.rollback();
            transaction.commit();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Field field) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(field);
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
