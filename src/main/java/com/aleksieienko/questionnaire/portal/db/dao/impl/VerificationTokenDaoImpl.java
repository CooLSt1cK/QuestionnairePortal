package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.Fields;
import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.VerificationTokenDao;
import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.VerificationToken;
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
public class VerificationTokenDaoImpl implements VerificationTokenDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void createVerificationToken(User user, String token) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println(user);
            VerificationToken verificationToken = new VerificationToken(token, user);
            Transaction transaction = session.beginTransaction();
            System.out.println(verificationToken);
            session.save(verificationToken);
            transaction.commit();
        }
    }

    @Override
    public VerificationToken getTokenById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<VerificationToken> criteriaQuery = criteriaBuilder.createQuery(VerificationToken.class);
            Root<VerificationToken> root = criteriaQuery.from(VerificationToken.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.VERIFICATION_TOKEN_ID), id));
            Query<VerificationToken> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public VerificationToken getTokenByToken(String token) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<VerificationToken> criteriaQuery = criteriaBuilder.createQuery(VerificationToken.class);
            Root<VerificationToken> root = criteriaQuery.from(VerificationToken.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Fields.VERIFICATION_TOKEN_TOKEN), token));
            Query<VerificationToken> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(VerificationToken verificationToken) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(verificationToken);
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
