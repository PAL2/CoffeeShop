package by.polegoshko.coffeeshop.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order", schema = "public")
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name = "my_seq", sequenceName = "my_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "{validation.empty.amount}")
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "variety")
    private String variety;

    @NotNull(message = "{validation.empty.time.from}")
    @Column(name = "timeFrom")
    private Date timeFrom;

    @NotNull(message = "{validation.empty.time.to}")
    @Column(name = "timeTo")
    private Date timeTo;

    @NotNull(message = "{validation.empty.date}")
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "delivery")
    private String delivery;

    @Column(name = "cost")
    private Double cost;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String delivery, Double cost) {
        this.delivery = delivery;
        this.cost = cost;
    }

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
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