package by.polegoshko.coffeeshop.api;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;

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
public class CreateBeanTest {

    private CreateBean bean;

    private CoffeeOrder defOrder;

    private CoffeeVariety coffeeVariety;

    private Delivery delivery;

    private Date timeTo;

    private Date date;

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

    @Mock
    private CoffeeOrder mockOrder;

    @Mock
    private FacesContext facesContext;

    @Mock
    private UIComponent uiComponent;

    @Before
    public void setUp() throws Exception {

        defOrder = new CoffeeOrder();
        defOrder.setId(1);
        defOrder.setAmount(100.0);
        defOrder.setVariety("Сорт");
        Date timeFrom = new GregorianCalendar(2018, 2, 20, 13, 45).getTime();
        defOrder.setTimeFrom(timeFrom);
        timeTo = new GregorianCalendar(2018, 2, 20, 16, 0).getTime();
        defOrder.setTimeTo(timeTo);
        date = new GregorianCalendar(2018, 2, 20).getTime();
        defOrder.setDate(date);
        defOrder.setDelivery("Доставка");
        defOrder.setCost(5.5);

        bean = new CreateBean(coffeeOrderService, coffeeVarietyService, deliveryService, defOrder);

        delivery = new Delivery();
        delivery.setId(1);
        delivery.setName("Доставка");
        delivery.setCost(100);

        coffeeVariety = new CoffeeVariety();
        coffeeVariety.setId(1);
        coffeeVariety.setName("Сорт");
        coffeeVariety.setPrice(10);
    }

    @PrepareForTest( {FacesContext.class, ResourceBundle.class})
    @Test
    public void saveOrder() throws Exception {
        doNothing().when(coffeeOrderService).save(defOrder);
        when(coffeeVarietyService.findByName(defOrder.getVariety())).thenReturn(coffeeVariety);
        when(deliveryService.findByName(any())).thenReturn(delivery);

        PowerMockito.mockStatic(FacesContext.class);
        facesContext = PowerMockito.mock(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(facesContext);
        when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
        when(uiViewRoot.getLocale()).thenReturn(new Locale("ru"));
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(externalContext.getFlash()).thenReturn(flash);

        PowerMockito.mockStatic(ResourceBundle.class);
        ResourceBundle resourceBundle = PowerMockito.mock(ResourceBundle.class);
        when(ResourceBundle.getBundle(anyString(), any(Locale.class)))
            .thenReturn(resourceBundle);

        assertEquals("index.xhtml?faces-redirect=true", bean.saveOrder(defOrder));

        ArgumentCaptor<FacesMessage> argumentCaptor = ArgumentCaptor.forClass(FacesMessage.class);
        verify(facesContext, times(1)).addMessage(eq("success"), argumentCaptor.capture());
        assertEquals("Заказ принят в обработку", argumentCaptor.getValue().getDetail());

        ArgumentCaptor<CoffeeOrder> orderCaptor = ArgumentCaptor.forClass(CoffeeOrder.class);
        verify(coffeeOrderService, times(1)).save(orderCaptor.capture());
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
    public void get() throws Exception {
        when(coffeeOrderService.get(1)).thenReturn(defOrder);

        assertEquals("order.xhtml", bean.get(1));

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(coffeeOrderService, times(1)).get(argumentCaptor.capture());
        Integer value = argumentCaptor.getValue();
        assertEquals(defOrder.getId(), value);
    }

    @Test
    public void validateTimeFrom() throws Exception {
        bean.validateTimeFrom(facesContext, uiComponent, timeTo);
        assertEquals(defOrder.getTimeFrom(), timeTo);
    }

    @Test
    public void validateTimeTo() throws Exception {
        bean.validateTimeTo(facesContext, uiComponent, timeTo);
    }

    @PrepareForTest( {ResourceBundle.class})
    @Test(expected = ValidatorException.class)
    public void validateTimeTo_ValidatorException() throws Exception {
        when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
        when(uiViewRoot.getLocale()).thenReturn(new Locale("ru"));

        PowerMockito.mockStatic(ResourceBundle.class);
        ResourceBundle resourceBundle = PowerMockito.mock(ResourceBundle.class);
        when(ResourceBundle.getBundle(anyString(), any(Locale.class)))
            .thenReturn(resourceBundle);

        bean.validateTimeTo(facesContext, uiComponent, date);
    }

    @Test
    public void changeCost() throws Exception {
        when(coffeeVarietyService.findByName(defOrder.getVariety())).thenReturn(coffeeVariety);
        when(deliveryService.findByName(defOrder.getDelivery())).thenReturn(delivery);

        assertEquals(101.0, bean.changeCost(defOrder), 0);
    }

    @Test
    public void getCoffeeOrders() throws Exception {
        when(coffeeOrderService.getAll()).thenReturn(Collections.singletonList(defOrder));

        List<CoffeeOrder> orderList = bean.getCoffeeOrders();
        CoffeeOrder actualOrder = orderList.get(0);
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
    public void gettersSetters() throws Exception {
        bean = new CreateBean();
        CoffeeOrder defOrder = new CoffeeOrder("Почта", 10.0);
        bean.setOrder(defOrder);
        CoffeeOrder actualOrder2 = bean.getOrder();
        assertEquals(defOrder.getDelivery(), actualOrder2.getDelivery());
        assertEquals(defOrder.getCost(), actualOrder2.getCost());
    }
}