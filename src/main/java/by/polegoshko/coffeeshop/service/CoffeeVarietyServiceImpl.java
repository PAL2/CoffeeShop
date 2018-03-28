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

    public CoffeeVariety get(String id) {
        Session session = util.getSession();
        CoffeeVariety coffeeVariety = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeVariety = coffeeVarietyDAO.get(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return coffeeVariety;
    }

    public void save(CoffeeVariety entity) {
        Session session = util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            coffeeVarietyDAO.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void update(CoffeeVariety entity) {
        coffeeVarietyDAO.update(entity);
    }

    public void delete(int id) {
        coffeeVarietyDAO.delete(id);
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