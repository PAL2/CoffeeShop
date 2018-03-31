package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.dao.AbstractDAO;
import by.polegoshko.coffeeshop.dao.DAO;
import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CoffeeOrderDAOImpl extends AbstractDAO implements DAO {

    public CoffeeOrderDAOImpl() {
        super(CoffeeOrder.class);
    }

    CoffeeOrderDAOImpl(HibernateUtil util) {
        super(util);
    }

    public void save(CoffeeOrder order) {
        try {
            Session session = util.getSession();
            session.clear();
            session.saveOrUpdate(order);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public CoffeeOrder get(Integer id) {
        CoffeeOrder order = null;
        try {
            Session session = util.getSession();
            session.clear();
            order = session.get(CoffeeOrder.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void delete(int id) {
        try {
            Session session = util.getSession();
            CoffeeOrder order = session.get(CoffeeOrder.class, id);
            session.delete(order);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}