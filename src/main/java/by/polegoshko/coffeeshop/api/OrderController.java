package by.polegoshko.coffeeshop.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import by.polegoshko.coffeeshop.domain.delivery.Delivery;
import by.polegoshko.coffeeshop.domain.delivery.DeliveryServiceImpl;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVarietyServiceImpl;

@ManagedBean(name = "order")
@RequestScoped
public class OrderController {

    private CoffeeVarietyServiceImpl varietyService = new CoffeeVarietyServiceImpl();

    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

    private CoffeeOrderServiceImpl orderService = new CoffeeOrderServiceImpl();

    private CoffeeOrder coffeeOrder = new CoffeeOrder();

    private List<CoffeeVariety> coffeeVarieties;

    private List<Delivery> deliveries;

    private String variety;

    public String getVariety() {
        return variety;
    }

    private String delivery = "Самовывоз";

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    private Double amount;

    private Date date;

    private Date timeFrom;

    private Date timeTo;

    private double cost;

    @PostConstruct
    private void init(){
        coffeeVarieties = varietyService.getAll();
        deliveries = deliveryService.getAll();
    }

    public String goOrders() throws IOException {
        coffeeOrder.setAmount(amount);
        coffeeOrder.setTimeFrom(timeFrom);
        coffeeOrder.setTimeTo(timeTo);
        coffeeOrder.setDate(date);
        coffeeOrder.setCost(changeCost());
        orderService.save(coffeeOrder);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        ResourceBundle resourceBundle =
            ResourceBundle.getBundle("messages", facesContext.getViewRoot().getLocale());
        String message = resourceBundle.getString("order.success");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        facesContext.addMessage("success", msg);
        externalContext.getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
    }

    public String get(Integer id){
        coffeeOrder = orderService.get(id);
        delivery = coffeeOrder.getDelivery();
        variety = coffeeOrder.getVariety();
        amount = coffeeOrder.getAmount();
        date = coffeeOrder.getDate();
        timeFrom = coffeeOrder.getTimeFrom();
        timeTo = coffeeOrder.getTimeTo();
        cost = coffeeOrder.getCost();
        return "order.xhtml";
    }

    public void validateTimeFrom(FacesContext context, UIComponent component, Object value) {
        this.timeFrom = (Date) value;
    }

    public void validateTimeTo(FacesContext context, UIComponent component, Object value) {
        this.timeTo = (Date) value;
        if (this.timeFrom.after(timeTo)) {
            ResourceBundle resourceBundle =
                ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
            String message = resourceBundle.getString("incorrect.date");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            throw new ValidatorException(msg);
        }
    }

    public double changeCost() {
        CoffeeVariety coffeeVariety = varietyService.get(variety);
        Delivery deliveryTmp = deliveryService.get(delivery);
        coffeeOrder.setVariety(variety);
        coffeeOrder.setDelivery(delivery);
        cost = amount * coffeeVariety.getPrice() / 1000 + deliveryTmp.getCost();
        return cost;
    }

    public List<CoffeeOrder> getCoffeeOrders(){
        return orderService.getAll();
    }

    public List<CoffeeVariety> getCoffeeVarieties() {
        return coffeeVarieties;
    }

    public void setCoffeeVarieties(List<CoffeeVariety> coffeeVarieties) {
        this.coffeeVarieties = coffeeVarieties;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
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