package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.dao.AbstractDAO;
import by.polegoshko.coffeeshop.dao.DAO;
import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CoffeeOrderDAOImpl extends AbstractDAO implements DAO {

    public CoffeeOrderDAOImpl() {
        super(CoffeeOrder.class);
    }

    public void save(CoffeeOrder entity) {
        try {
            Session session = util.getSession();
            session.clear();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
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