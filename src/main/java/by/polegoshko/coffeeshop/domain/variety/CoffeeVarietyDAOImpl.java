package by.polegoshko.coffeeshop.domain.variety;

import java.util.List;

import org.hibernate.query.Query;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CoffeeVarietyDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public CoffeeVariety get(String name) {
        CoffeeVariety coffeeVariety = null;
        try {
            Session session = util.getSession();
            Query query  = session.createQuery("FROM CoffeeVariety WHERE name=:name");
            query.setParameter("name", name);
            coffeeVariety = (CoffeeVariety) query.uniqueResult();
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
            Query query  = session.createQuery("FROM CoffeeVariety");
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}
