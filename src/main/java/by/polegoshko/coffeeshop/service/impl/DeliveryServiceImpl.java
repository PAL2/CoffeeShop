package by.polegoshko.coffeeshop.service.impl;

import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.DeliveryDAOImpl;
import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.service.AbstractService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeliveryServiceImpl extends AbstractService {

    private DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();

    public Delivery findByName(String name) {
        Session session = util.getSession();
        Delivery delivery = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            delivery = deliveryDAO.findByName(name);
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