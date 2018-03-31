package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.dao.AbstractDAO;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CoffeeVarietyDAOImpl extends AbstractDAO {

    public CoffeeVarietyDAOImpl() {
        super(CoffeeVariety.class);
    }

    CoffeeVarietyDAOImpl(HibernateUtil util) {
        super(util);
    }

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
}