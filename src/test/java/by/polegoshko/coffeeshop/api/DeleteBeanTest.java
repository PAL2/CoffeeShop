package by.polegoshko.coffeeshop.api;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.service.impl.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.service.impl.CoffeeVarietyServiceImpl;
import by.polegoshko.coffeeshop.service.impl.DeliveryServiceImpl;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore( {"javax.xml.*", "org.xml.sax.*", "org.w3c.*"})
public class DeleteBeanTest {

    private DeleteBean bean;

    private List<CoffeeVariety> coffeeVarieties;

    private List<Delivery> deliveries;

    private CoffeeOrder defOrder;

    @Mock
    private CoffeeOrderServiceImpl coffeeOrderService;

    @Mock
    private CoffeeVarietyServiceImpl coffeeVarietyService;

    @Mock
    private DeliveryServiceImpl deliveryService;

    @Mock
    private UIViewRoot uiViewRoot;

    @Mock
    private ExternalContext externalContext;

    @Mock
    private Flash flash;

    @Before
    public void setUp() throws Exception {

        Delivery delivery = new Delivery();
        delivery.setId(1);
        delivery.setName("Доставка");
        delivery.setCost(100);

        CoffeeVariety coffeeVariety = new CoffeeVariety();
        coffeeVariety.setId(1);
        coffeeVariety.setName("Сорт");
        coffeeVariety.setPrice(10);

        coffeeVarieties = Collections.singletonList(coffeeVariety);
        deliveries = Collections.singletonList(delivery);

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

        bean = new DeleteBean(coffeeOrderService, coffeeVarietyService, deliveryService,
            coffeeVarieties, deliveries, defOrder, 1);
    }

    @Test
    public void goToDelete() throws Exception {
        assertEquals("delete.xhtml", bean.goToDelete());
    }

    @Test
    public void deleteOrder() throws Exception {
        when(coffeeOrderService.get(1)).thenReturn(defOrder);
        List<CoffeeOrder> orderToDelete = bean.deleteOrder();
        CoffeeOrder actualOrder = orderToDelete.get(0);
        assertEquals(defOrder.getId(), actualOrder.getId());
        assertEquals(defOrder.getAmount(), actualOrder.getAmount());
        assertEquals(defOrder.getVariety(), actualOrder.getVariety());
        assertEquals(defOrder.getTimeFrom(), actualOrder.getTimeFrom());
        assertEquals(defOrder.getTimeTo(), actualOrder.getTimeTo());
        assertEquals(defOrder.getDate(), actualOrder.getDate());
        assertEquals(defOrder.getDelivery(), actualOrder.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder.getCost());
    }

    @PrepareForTest( {FacesContext.class, ResourceBundle.class})
    @Test
    public void delete() throws Exception {
        doNothing().when(coffeeOrderService).delete(1);
        PowerMockito.mockStatic(FacesContext.class);
        FacesContext facesContext = PowerMockito.mock(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
        when(uiViewRoot.getLocale()).thenReturn(new Locale("ru"));
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(externalContext.getFlash()).thenReturn(flash);

        PowerMockito.mockStatic(ResourceBundle.class);
        ResourceBundle resourceBundle = PowerMockito.mock(ResourceBundle.class);
        when(ResourceBundle.getBundle(anyString(), any(Locale.class)))
            .thenReturn(resourceBundle);

        assertEquals("index.xhtml?faces-redirect=true", bean.delete());

        ArgumentCaptor<FacesMessage> argumentCaptor = ArgumentCaptor.forClass(FacesMessage.class);
        verify(facesContext, times(1)).addMessage(eq("deleted"), argumentCaptor.capture());
        assertEquals("Заказ успешно удален", argumentCaptor.getValue().getDetail());
    }

    @Test
    public void gettersSetters() throws Exception {
        bean = new DeleteBean();
        CoffeeOrder defOrder = new CoffeeOrder("Почта", 10.0);
        bean.setOrder(defOrder);
        CoffeeOrder actualOrder2 = bean.getOrder();
        bean.setOrderId(2);
        bean.setCoffeeVarieties(coffeeVarieties);
        bean.setDeliveries(deliveries);
        assertEquals(defOrder.getDelivery(), actualOrder2.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder2.getCost());
        assertEquals(2, (int) bean.getOrderId());
        assertEquals(coffeeVarieties, bean.getCoffeeVarieties());
        assertEquals(deliveries, bean.getDeliveries());
    }
}