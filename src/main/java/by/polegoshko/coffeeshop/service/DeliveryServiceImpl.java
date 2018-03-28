package by.polegoshko.coffeeshop.service;

import java.util.List;

import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.dao.DeliveryDAOImpl;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeliveryServiceImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    private DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();

    public Delivery get(String name) {
        Session session = util.getSession();
        Delivery delivery = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            delivery = deliveryDAO.get(name);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return delivery;
    }

    public List<Delivery> getAll() {
        List<Delivery> deliveryList = null;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            deliveryList = deliveryDAO.getAll();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return deliveryList;
    }

}
