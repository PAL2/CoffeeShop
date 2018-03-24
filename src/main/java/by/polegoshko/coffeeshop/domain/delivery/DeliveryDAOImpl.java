package by.polegoshko.coffeeshop.domain.delivery;

import java.util.List;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DeliveryDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public List<Delivery> getAll() {
        List<Delivery> results = null;
        try {
            Session session = util.getSession();
            Query query  = session.createNativeQuery("SELECT t.* FROM public.\"delivery\" t")
                .addEntity(Delivery.class);
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}