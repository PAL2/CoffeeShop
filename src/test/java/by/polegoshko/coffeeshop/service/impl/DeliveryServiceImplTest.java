package by.polegoshko.coffeeshop.service.impl;

import java.util.Collections;
import java.util.List;

import by.polegoshko.coffeeshop.dao.impl.DeliveryDAOImpl;
import by.polegoshko.coffeeshop.domain.Delivery;
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
public class DeliveryServiceImplTest {

    private DeliveryServiceImpl service;

    private Delivery defDelivery;

    private List<Delivery> defDeliveries;

    @Mock
    private DeliveryDAOImpl deliveryDAO;

    @Mock
    private HibernateException hibernateException;

    @Before
    public void setUp() throws Exception {
        service = new DeliveryServiceImpl(deliveryDAO);

        defDelivery = new Delivery();
        defDelivery.setId(1);
        defDelivery.setName("Доставка");
        defDelivery.setCost(100);

        defDeliveries = Collections.singletonList(defDelivery);
    }

    @Test
    public void findByName() throws Exception {
        when(deliveryDAO.findByName(anyString())).thenReturn(defDelivery);
        Delivery actualDelivery = service.findByName("Сорт");
        assertEquals(defDelivery.getId(), actualDelivery.getId());
        assertEquals(defDelivery.getName(), actualDelivery.getName());
        assertEquals(defDelivery.getCost(), actualDelivery.getCost());
    }

    @Test
    public void findByName_HibernateException() throws Exception {
        doThrow(hibernateException).when(deliveryDAO).findByName(anyString());
        service.findByName("Доставка");
        verify(hibernateException, times(1)).printStackTrace();
    }

    @Test
    public void getAll() throws Exception {
        when(deliveryDAO.getAll()).thenReturn(defDeliveries);

        List<Delivery> deliveries = service.getAll();
        Delivery actualDelivery = deliveries.get(0);
        assertEquals(defDelivery.getId(), actualDelivery.getId());
        assertEquals(defDelivery.getName(), actualDelivery.getName());
        assertEquals(defDelivery.getCost(), actualDelivery.getCost());
    }

    @Test
    public void getAll_HibernateException() throws Exception {
        doThrow(hibernateException).when(deliveryDAO).getAll();
        service.getAll();
        verify(hibernateException, times(1)).printStackTrace();
    }

}