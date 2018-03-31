package by.polegoshko.coffeeshop.service;

import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;

public abstract class AbstractService<T> {

    protected HibernateUtil util = HibernateUtil.getInstance();

    protected AbstractService() {
    }
}
