package by.polegoshko.coffeeshop.domain.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order", schema = "public")
public class CoffeeOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "variety")
    private String variety;

    @Column(name = "timeFrom")
    private Date timeFrom;

    @Column(name = "timeTo")
    private Date timeTo;

    @Column(name = "date")
    private Date date;

    @Column(name = "delivery")
    private String delivery;

    @Column(name = "cost")
    private double cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return amount + " гр. " + variety + " с " + timeFormat(timeFrom) + " по "
            + timeFormat(timeTo) + " " + dateFormat(date) + " " + delivery + " - " + cost + " руб.";
    }

    private String timeFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    private String dateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }
}