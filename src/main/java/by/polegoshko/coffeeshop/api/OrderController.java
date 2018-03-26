package by.polegoshko.coffeeshop.api;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import by.polegoshko.coffeeshop.domain.delivery.Delivery;
import by.polegoshko.coffeeshop.domain.delivery.DeliveryServiceImpl;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVarietyServiceImpl;

@ManagedBean(name = "order")
public class OrderController {

    private CoffeeVarietyServiceImpl service = new CoffeeVarietyServiceImpl();

    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

    private CoffeeOrderServiceImpl orderService = new CoffeeOrderServiceImpl();

    private CoffeeOrder coffeeOrder = new CoffeeOrder();

    private int varietyId;

    private int deliveryId = 1;

    private Double amount;

    private Date date;

    private Date timeFrom;

    private Date timeTo;

    private double cost;

    public String goOrders() {
        coffeeOrder.setAmount(amount);
        coffeeOrder.setTimeFrom(timeFrom);
        coffeeOrder.setTimeTo(timeTo);
        coffeeOrder.setDate(date);
        coffeeOrder.setCost(changeCost());
        orderService.save(coffeeOrder);
        List<CoffeeOrder> coffeeOrders = orderService.getAll();
        return "orders.xhtml";
    }

    public void validateTimeFrom(FacesContext context, UIComponent component, Object value) {
        this.timeFrom = (Date) value;
    }

    public void validateTimeTo(FacesContext context, UIComponent component, Object value) {
        this.timeTo = (Date) value;
        if(this.timeFrom.after(timeTo)) {
            ResourceBundle resourceBundle =
                ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
            String message = resourceBundle.getString("incorrect.date");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            throw new ValidatorException(msg);
        }
    }

    public double changeCost(){
        CoffeeVariety coffeeVariety = service.get(varietyId);
        Delivery delivery = deliveryService.get(deliveryId);
        coffeeOrder.setVariety(coffeeVariety.getName());
        coffeeOrder.setDelivery(delivery.getName());
        cost = amount * coffeeVariety.getPrice()/1000 + delivery.getCost();
        return cost;
    }

    public int getVarietyId() {
        return varietyId;
    }

    public void setVarietyId(int varietyId) {
        this.varietyId = varietyId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}