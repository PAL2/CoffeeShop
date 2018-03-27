package by.polegoshko.coffeeshop.domain.delivery;

import java.util.List;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DeliveryDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public Delivery get(String name) {
        Delivery delivery = null;
        try {
            Session session = util.getSession();
            Query query  = session.createQuery("FROM Delivery WHERE name=:name");
            query.setParameter("name", name);
            delivery = (Delivery) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return delivery;
    }

    public List<Delivery> getAll() {
        List<Delivery> results = null;
        try {
            Session session = util.getSession();
            Query query  = session.createQuery("FROM Delivery");
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}