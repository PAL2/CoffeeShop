package by.polegoshko.coffeeshop.domain.delivery;

import java.util.List;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeliveryServiceImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    private DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();

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
