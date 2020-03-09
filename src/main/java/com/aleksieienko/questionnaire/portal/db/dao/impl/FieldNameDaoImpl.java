package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.Fields;
import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.FieldNameDao;
import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@ManagedBean
@ApplicationScoped
public class FieldNameDaoImpl implements FieldNameDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void add(FieldName fieldName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            fieldName.setId((Integer) session.save(fieldName));
            transaction.commit();
        } catch (RuntimeException e) {
            fieldName.setId(null);
            e.printStackTrace();
        }
    }

    @Override
    public List<FieldName> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<FieldName> criteriaQuery = criteriaBuilder.createQuery(FieldName.class);
            Root<FieldName> root = criteriaQuery.from(FieldName.class);
            criteriaQuery.select(root);

            Query<FieldName> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FieldName getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<FieldName> criteriaQuery = criteriaBuilder.createQuery(FieldName.class);
            Root<FieldName> root = criteriaQuery.from(FieldName.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.FIELD_NAMES_NAME), name));

            Query<FieldName> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
