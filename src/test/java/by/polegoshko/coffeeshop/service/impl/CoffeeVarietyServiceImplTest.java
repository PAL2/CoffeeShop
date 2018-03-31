package by.polegoshko.coffeeshop.service.impl;

import java.util.Collections;
import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.CoffeeVarietyDAOImpl;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import org.hibernate.HibernateException;
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
public class CoffeeVarietyServiceImplTest {

    private CoffeeVarietyServiceImpl service;

    private CoffeeVariety defCoffeeVariety;

    private List<CoffeeVariety> defCoffeeVarieties;

    @Mock
    private CoffeeVarietyDAOImpl coffeeVarietyDAO;

    @Mock
    private HibernateException hibernateException;

    @Before
    public void setUp() throws Exception {
        service = new CoffeeVarietyServiceImpl(coffeeVarietyDAO);

        defCoffeeVariety = new CoffeeVariety();
        defCoffeeVariety.setId(1);
        defCoffeeVariety.setName("Сорт");
        defCoffeeVariety.setPrice(100);

        defCoffeeVarieties = Collections.singletonList(defCoffeeVariety);
    }

    @Test
    public void findByName() throws Exception {
        when(coffeeVarietyDAO.findByName(anyString())).thenReturn(defCoffeeVariety);
        CoffeeVariety actualVariety = service.findByName("Сорт");
        assertEquals(defCoffeeVariety.getId(), actualVariety.getId());
        assertEquals(defCoffeeVariety.getName(), actualVariety.getName());
        assertEquals(defCoffeeVariety.getPrice(), actualVariety.getPrice());
    }

    @Test
    public void findByName_HibernateException() throws Exception {
        doThrow(hibernateException).when(coffeeVarietyDAO).findByName(anyString());
        service.findByName("Сорт");
        verify(hibernateException, times(1)).printStackTrace();
    }

    @Test
    public void getAll() throws Exception {
        when(coffeeVarietyDAO.getAll()).thenReturn(defCoffeeVarieties);

        List<CoffeeVariety> coffeeVarieties = service.getAll();
        CoffeeVariety actualVariety = coffeeVarieties.get(0);
        assertEquals(defCoffeeVariety.getId(), actualVariety.getId());
        assertEquals(defCoffeeVariety.getName(), actualVariety.getName());
        assertEquals(defCoffeeVariety.getPrice(), actualVariety.getPrice());
    }

    @Test
    public void getAll_HibernateException() throws Exception {
        doThrow(hibernateException).when(coffeeVarietyDAO).getAll();
        service.getAll();
        verify(hibernateException, times(1)).printStackTrace();
    }
}