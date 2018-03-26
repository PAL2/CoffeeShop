package by.polegoshko.coffeeshop.domain.variety;

import java.util.List;

import org.hibernate.query.Query;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CoffeeVarietyDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public CoffeeVariety get(Integer id) {
        CoffeeVariety coffeeVariety = null;
        try {
            Session session = util.getSession();
            coffeeVariety = session.get(CoffeeVariety.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return coffeeVariety;
    }

    public void save(CoffeeVariety entity) {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void update(CoffeeVariety entity) {

    }

    public void delete(int id) {

    }

    public List<CoffeeVariety> getAll() {
        List<CoffeeVariety> results = null;
        try {
            Session session = util.getSession();
            Query query  = session.createNativeQuery("SELECT t.* FROM public.\"variety\" t")
                .addEntity(CoffeeVariety.class);
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}
