package by.polegoshko.coffeeshop.api;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "order")
public class OrderController {

    private Double amount;

    private String variety;

    private String delivery;

    private Date date;

    private String timeFrom;

    private String timeTo;

    public String goOrders() {
        return "orders.xhtml";
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}