package by.polegoshko.coffeeshop.domain.order;

import java.util.List;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
            Query query  = session.createQuery("FROM CoffeeOrder");
            results = query.list();
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
}