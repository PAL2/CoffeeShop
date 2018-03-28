package by.polegoshko.coffeeshop.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected HibernateUtil util = HibernateUtil.getInstance();

    private Class aClass;

    protected AbstractDAO(Class aClass) {
        this.aClass = aClass;
    }

    public List<T> getAll() {
        List<T> results = null;
        try {
            Session session = util.getSession();
            session.clear();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = criteriaBuilder.createQuery(aClass);
            Root<T> root = criteria.from(aClass);
            criteria.select(root);
            results = session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}
