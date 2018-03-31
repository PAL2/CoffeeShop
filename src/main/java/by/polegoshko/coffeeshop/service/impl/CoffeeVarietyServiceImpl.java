package by.polegoshko.coffeeshop.service.impl;

import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.CoffeeVarietyDAOImpl;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.service.AbstractService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoffeeVarietyServiceImpl extends AbstractService {

    private CoffeeVarietyDAOImpl coffeeVarietyDAO = new CoffeeVarietyDAOImpl();

    public CoffeeVarietyServiceImpl() {
    }

    CoffeeVarietyServiceImpl(CoffeeVarietyDAOImpl coffeeVarietyDAO) {
        this.coffeeVarietyDAO = coffeeVarietyDAO;
    }

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return coffeeVarieties;
    }
}