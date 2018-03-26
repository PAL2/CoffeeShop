package by.polegoshko.coffeeshop.domain.order;

import java.util.List;

import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            transaction.rollback();
        }
        return coffeeOrders;
    }
}
