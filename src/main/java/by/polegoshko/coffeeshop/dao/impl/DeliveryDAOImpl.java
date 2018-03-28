package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.dao.AbstractDAO;
import by.polegoshko.coffeeshop.domain.Delivery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DeliveryDAOImpl extends AbstractDAO {

    public DeliveryDAOImpl() {
        super(Delivery.class);
    }

    public Delivery findByName(String name) {
        Delivery delivery = null;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM Delivery WHERE name=:name");
            query.setParameter("name", name);
            delivery = (Delivery) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return delivery;
    }
}