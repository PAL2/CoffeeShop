package by.polegoshko.coffeeshop.dao;

import java.util.List;

import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CoffeeVarietyDAOImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    public CoffeeVariety findByName(String name) {
        CoffeeVariety coffeeVariety = null;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM CoffeeVariety WHERE name=:name");
            query.setParameter("name", name);
            coffeeVariety = (CoffeeVariety) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return coffeeVariety;
    }

    public List<CoffeeVariety> getAll() {
        List<CoffeeVariety> results = null;
        try {
            Session session = util.getSession();
            Query query = session.createQuery("FROM CoffeeVariety");
            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return results;
    }
}