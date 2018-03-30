package by.polegoshko.coffeeshop.api;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
        List<CoffeeOrder> ordersToDelete = null;
        if (orderId != null) {
            ordersToDelete = new ArrayList<>();
            ordersToDelete.add(orderService.get(orderId));
        }
        return ordersToDelete;
    }

    public String delete() {
        orderService.delete(orderId);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        ResourceBundle resourceBundle =
            ResourceBundle.getBundle("messages", facesContext.getViewRoot().getLocale());
        String message = resourceBundle.getString("deleted.success");
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        facesContext.addMessage("deleted", msg);
        externalContext.getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
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