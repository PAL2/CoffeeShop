package by.polegoshko.coffeeshop.domain.order;

import java.util.List;

import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CoffeeOrderDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public void save(CoffeeOrder entity) {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<CoffeeOrder> getAll() {
        List<CoffeeOrder> results = null;
        try {
            Session session = util.getSession();
            Query query  = session.createNativeQuery("SELECT t.* FROM public.\"order\" t")
                .addEntity(CoffeeOrder.class);
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}
