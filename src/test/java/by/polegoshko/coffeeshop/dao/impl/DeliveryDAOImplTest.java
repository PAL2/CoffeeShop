package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.infrastructure.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryDAOImplTest {

    private DeliveryDAOImpl dao;

    private Delivery defDelivery;

    @Mock
    private HibernateUtil util;

    @Mock
    private Session session;

    @Mock
    private Query query;

    @Mock
    private HibernateException exception;

    @Before
    public void setUp() throws Exception {
        dao = new DeliveryDAOImpl(util);

        defDelivery = new Delivery();
        defDelivery.setId(1);
        defDelivery.setName("Сорт");
        defDelivery.setCost(100);
    }

    @Test
    public void findByName() throws Exception {
        when(util.getSession()).thenReturn(session);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(defDelivery);

        Delivery actualDelivery = dao.findByName("Сорт");
        assertEquals(defDelivery.getId(), actualDelivery.getId());
        assertEquals(defDelivery.getName(), actualDelivery.getName());
        assertEquals(defDelivery.getCost(), actualDelivery.getCost());
    }

    @Test
    public void findByName_HibernateException() throws Exception {
        doThrow(exception).when(util).getSession();
        dao.findByName("Сорт");
        verify(exception, times(1)).printStackTrace();
    }
}