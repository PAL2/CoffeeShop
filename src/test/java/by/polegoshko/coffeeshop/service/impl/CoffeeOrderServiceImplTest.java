package by.polegoshko.coffeeshop.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.CoffeeOrderDAOImpl;
import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import org.hibernate.HibernateException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeOrderServiceImplTest {

    private CoffeeOrderServiceImpl service;

    private CoffeeOrder defOrder;

    private List<CoffeeOrder> defCoffeeOrders;

    @Mock
    private CoffeeOrderDAOImpl coffeeOrderDAO;

    @Mock
    private HibernateException hibernateException;

    @Before
    public void setUp() throws Exception {
        service = new CoffeeOrderServiceImpl(coffeeOrderDAO);

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

        defCoffeeOrders = Collections.singletonList(defOrder);
    }

    @Test
    public void save() throws Exception {
        doNothing().when(coffeeOrderDAO).save(defOrder);
        service.save(defOrder);

        ArgumentCaptor<CoffeeOrder> orderCaptor = ArgumentCaptor.forClass(CoffeeOrder.class);
        verify(coffeeOrderDAO, times(1)).save(orderCaptor.capture());
        CoffeeOrder actualOrder = orderCaptor.getValue();
        assertEquals(defOrder.getId(), actualOrder.getId());
        assertEquals(defOrder.getAmount(), actualOrder.getAmount());
        assertEquals(defOrder.getVariety(), actualOrder.getVariety());
        assertEquals(defOrder.getTimeFrom(), actualOrder.getTimeFrom());
        assertEquals(defOrder.getTimeTo(), actualOrder.getTimeTo());
        assertEquals(defOrder.getDate(), actualOrder.getDate());
        assertEquals(defOrder.getDelivery(), actualOrder.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder.getCost());
        assertEquals(defOrder.toString(), actualOrder.toString());
    }

    @Test
    public void save_HibernateException() throws Exception {
        doThrow(hibernateException).when(coffeeOrderDAO).save(defOrder);
        service.save(defOrder);
        verify(hibernateException, times(1)).printStackTrace();
    }

    @Test
    public void getAll() throws Exception {
        when(coffeeOrderDAO.getAll()).thenReturn(defCoffeeOrders);

        List<CoffeeOrder> coffeeOrders = service.getAll();
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
        doThrow(hibernateException).when(coffeeOrderDAO).getAll();
        service.getAll();
        verify(hibernateException, times(1)).printStackTrace();
    }

    @Test
    public void get() throws Exception {
        when(coffeeOrderDAO.get(1)).thenReturn(defOrder);

        CoffeeOrder actualOrder = service.get(1);
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
        doThrow(hibernateException).when(coffeeOrderDAO).get(1);
        service.get(1);
        verify(hibernateException, times(1)).printStackTrace();
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(coffeeOrderDAO).delete(1);
        service.delete(1);
        verify(coffeeOrderDAO, times(1)).delete(1);
    }

    @Test
    public void delete_HibernateException() throws Exception {
        doThrow(hibernateException).when(coffeeOrderDAO).delete(1);
        service.delete(1);
        verify(hibernateException, times(1)).printStackTrace();
    }
}