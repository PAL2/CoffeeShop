package by.polegoshko.coffeeshop.service;

import java.util.List;

import by.polegoshko.coffeeshop.dao.CoffeeOrderDAOImpl;
import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoffeeOrderServiceImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

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
        CoffeeOrder coffeeOrder = null;
        try {
            Session session = util.getSession();
            coffeeOrder = session.get(CoffeeOrder.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
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