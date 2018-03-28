package by.polegoshko.coffeeshop.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.service.impl.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.service.impl.CoffeeVarietyServiceImpl;
import by.polegoshko.coffeeshop.service.impl.DeliveryServiceImpl;

@ManagedBean(name = "deleteBean")
@SessionScoped
public class DeleteBean {

    private CoffeeOrderServiceImpl orderService = new CoffeeOrderServiceImpl();

    private CoffeeVarietyServiceImpl varietyService = new CoffeeVarietyServiceImpl();

    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

    private List<CoffeeVariety> coffeeVarieties;

    private List<Delivery> deliveries;

    private CoffeeOrder coffeeOrder;

    private Integer orderId;

    @PostConstruct
    private void init() {
        coffeeVarieties = varietyService.getAll();
        deliveries = deliveryService.getAll();
    }

    public String goToDelete() {
        return "delete.xhtml";
    }

    public List<CoffeeOrder> deleteOrder() {
        List<CoffeeOrder> ordersToDelete = new ArrayList<>();
        if (orderId != null) {
            ordersToDelete.add(orderService.get(orderId));
        }
        return ordersToDelete;
    }

    public String delete() {
        orderService.delete(orderId);
        return "index.xhtml";
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
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
}