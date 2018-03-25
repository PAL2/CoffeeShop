package by.polegoshko.coffeeshop.api;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "order")
public class OrderController {

    private Double amount;

    private int price;

    private String variety;

    private int deliveryCost;

    private Date date;

    private Date timeFrom;

    private Date timeTo;

    private double cost;

    public String goOrders() {
        return "orders.xhtml";
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
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

    public void changeCost(){
        this.cost = amount * price/1000 + deliveryCost;
    }
}