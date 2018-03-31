package by.polegoshko.coffeeshop.dao.impl;

import by.polegoshko.coffeeshop.domain.CoffeeVariety;
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
public class CoffeeVarietyDAOImplTest {

    private CoffeeVarietyDAOImpl dao;

    private CoffeeVariety defVariety;

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
        dao = new CoffeeVarietyDAOImpl(util);

        defVariety = new CoffeeVariety();
        defVariety.setId(1);
        defVariety.setName("Сорт");
        defVariety.setPrice(100);
    }

    @Test
    public void findByName() throws Exception {
        when(util.getSession()).thenReturn(session);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(defVariety);

        CoffeeVariety actualVariety = dao.findByName("Сорт");
        assertEquals(defVariety.getId(), actualVariety.getId());
        assertEquals(defVariety.getName(), actualVariety.getName());
        assertEquals(defVariety.getPrice(), actualVariety.getPrice());
    }

    @Test
    public void findByName_HibernateException() throws Exception {
        doThrow(exception).when(util).getSession();
        dao.findByName("Сорт");
        verify(exception, times(1)).printStackTrace();
    }
}