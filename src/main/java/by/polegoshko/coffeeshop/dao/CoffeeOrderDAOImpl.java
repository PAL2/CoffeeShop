package by.polegoshko.coffeeshop.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CoffeeOrderDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public void save(CoffeeOrder entity) {
        try {
            Session session = util.getSession();
            session.clear();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<CoffeeOrder> getAll() {
        List<CoffeeOrder> results = null;
        try {
            Session session = util.getSession();
            session.clear();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CoffeeOrder> criteria = criteriaBuilder.createQuery(CoffeeOrder.class);
            Root<CoffeeOrder> root = criteria.from(CoffeeOrder.class);
            criteria.select(root);
            results = session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }

    public CoffeeOrder get(Integer id) {
        CoffeeOrder coffeeOrder = null;
        try {
            Session session = util.getSession();
            session.clear();
            coffeeOrder = session.get(CoffeeOrder.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return coffeeOrder;
    }

    public void delete(int id) {
        try {
            Session session = util.getSession();
            CoffeeOrder account = session.get(CoffeeOrder.class, id);
            session.delete(account);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}