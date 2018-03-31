package by.polegoshko.coffeeshop.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeOrderDAOImplTest {

    private CoffeeOrderDAOImpl dao;

    private CoffeeOrder defOrder;

    @Mock
    private Session session;

    @Mock
    private HibernateUtil util;

    @Mock
    private HibernateException exception;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery criteria;

    @Mock
    private Root root;

    @Mock
    private Query query;

    @Mock
    private CoffeeOrder mockOrder;

    @Before
    public void setUp() throws Exception {
        dao = new CoffeeOrderDAOImpl(util);

        defOrder = new CoffeeOrder();
        defOrder.setId(1);
        defOrder.setAmount(100.0);
        defOrder.setVariety("Сорт");
        Date timeFrom = new GregorianCalendar(2018, 2, 20, 13, 45).getTime();
        defOrder.setTimeFrom(timeFrom);
        Date timeTo = new GregorianCalendar(2018, 2, 20, 16, 0).getTime();
        defOrder.setTimeTo(timeTo);
        Date date = new GregorianCalendar(2018, 2, 20).getTime();
        defOrder.setDate(date);
        defOrder.setDelivery("Доставка");
        defOrder.setCost(5.5);

        when(util.getSession()).thenReturn(session);
    }

    @Test
    public void save() throws Exception {
        doNothing().when(session).saveOrUpdate(defOrder);
        dao.save(defOrder);
    }

    @Test
    public void save_HibernateException() throws Exception {
        doThrow(exception).when(session).saveOrUpdate(defOrder);
        dao.save(defOrder);
        verify(exception, times(1)).printStackTrace();
    }

    @Test
    public void get() throws Exception {
        when(session.get(CoffeeOrder.class, 1)).thenReturn(defOrder);
        CoffeeOrder actualOrder = dao.get(1);
        assertEquals(defOrder.getId(), actualOrder.getId());
        assertEquals(defOrder.getAmount(), actualOrder.getAmount());
        assertEquals(defOrder.getVariety(), actualOrder.getVariety());
        assertEquals(defOrder.getTimeFrom(), actualOrder.getTimeFrom());
        assertEquals(defOrder.getTimeTo(), actualOrder.getTimeTo());
        assertEquals(defOrder.getDate(), actualOrder.getDate());
        assertEquals(defOrder.getDelivery(), actualOrder.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder.getCost());
    }

    @Test
    public void get_HibernateException() throws Exception {
        doThrow(exception).when(session).get(CoffeeOrder.class, 1);
        dao.get(1);
        verify(exception, times(1)).printStackTrace();
    }

    @Test
    public void delete() throws Exception {
        when(session.get(CoffeeOrder.class, 1)).thenReturn(defOrder);
        doNothing().when(session).delete(defOrder);
        dao.delete(1);
    }

    @Test
    public void delete_HibernateException() throws Exception {
        doThrow(exception).when(session).get(CoffeeOrder.class, 1);
        dao.delete(1);
        verify(exception, times(1)).printStackTrace();
    }

    @Test
    public void getAll() throws Exception {
        List<CoffeeOrder> list = Collections.singletonList(defOrder);

        when(session.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(any())).thenReturn(criteria);
        when(session.createQuery(criteria)).thenReturn(query);
        when(query.getResultList()).thenReturn(list);

        List<CoffeeOrder> coffeeOrders = dao.getAll();
        CoffeeOrder actualOrder = coffeeOrders.get(0);
        assertEquals(defOrder.getId(), actualOrder.getId());
        assertEquals(defOrder.getAmount(), actualOrder.getAmount());
        assertEquals(defOrder.getVariety(), actualOrder.getVariety());
        assertEquals(defOrder.getTimeFrom(), actualOrder.getTimeFrom());
        assertEquals(defOrder.getTimeTo(), actualOrder.getTimeTo());
        assertEquals(defOrder.getDate(), actualOrder.getDate());
        assertEquals(defOrder.getDelivery(), actualOrder.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder.getCost());
    }

    @Test
    public void getAll_HibernateException() throws Exception {
        doThrow(exception).when(session).getCriteriaBuilder();
        dao.getAll();
        verify(exception, times(1)).printStackTrace();
    }
}