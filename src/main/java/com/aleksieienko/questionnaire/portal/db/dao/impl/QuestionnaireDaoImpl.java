package com.aleksieienko.questionnaire.portal.db.dao.impl;

import com.aleksieienko.questionnaire.portal.db.HibernateUtil;
import com.aleksieienko.questionnaire.portal.db.dao.QuestionnaireDao;
import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@ManagedBean
@ApplicationScoped
public class QuestionnaireDaoImpl implements QuestionnaireDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void add(Questionnaire questionnaire) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            questionnaire.setId((Integer) session.save(questionnaire));
            transaction.commit();
        } catch (RuntimeException e) {
            questionnaire.setId(null);
            e.printStackTrace();
        }
    }

    @Override
    public boolean addList(List<Questionnaire> list) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            for (Questionnaire e : list) {
                session.save(e);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
