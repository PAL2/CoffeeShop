package by.polegoshko.coffeeshop.service.impl;

import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.CoffeeOrderDAOImpl;
import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.service.AbstractService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoffeeOrderServiceImpl extends AbstractService {

    private CoffeeOrderDAOImpl coffeeOrderDAO = new CoffeeOrderDAOImpl();

    public void save(CoffeeOrder entity) {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeOrderDAO.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    public List<CoffeeOrder> getAll() {
        List<CoffeeOrder> coffeeOrders = null;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeOrders = coffeeOrderDAO.getAll();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return coffeeOrders;
    }

    public CoffeeOrder get(Integer id) {
        Session session = util.getSession();
        CoffeeOrder coffeeOrder = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeOrder = coffeeOrderDAO.get(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return coffeeOrder;
    }

    public void delete(int id) {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeOrderDAO.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}