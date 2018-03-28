package by.polegoshko.coffeeshop.service;

import java.util.List;

import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.dao.CoffeeVarietyDAOImpl;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoffeeVarietyServiceImpl {

    private HibernateUtil util = HibernateUtil.getInstance();

    private CoffeeVarietyDAOImpl coffeeVarietyDAO = new CoffeeVarietyDAOImpl();

    public CoffeeVariety findByName(String name) {
        Session session = util.getSession();
        CoffeeVariety coffeeVariety = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeVariety = coffeeVarietyDAO.findByName(name);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return coffeeVariety;
    }

    public List<CoffeeVariety> getAll() {
        List<CoffeeVariety> coffeeVarieties = null;
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeVarieties = coffeeVarietyDAO.getAll();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return coffeeVarieties;
    }
}