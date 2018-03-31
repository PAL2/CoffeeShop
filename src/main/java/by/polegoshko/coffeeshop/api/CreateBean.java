package by.polegoshko.coffeeshop.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import by.polegoshko.coffeeshop.domain.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.Delivery;
import by.polegoshko.coffeeshop.service.impl.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.service.impl.CoffeeVarietyServiceImpl;
import by.polegoshko.coffeeshop.service.impl.DeliveryServiceImpl;

@ManagedBean(name = "createBean")
@RequestScoped
public class CreateBean {

    private CoffeeOrderServiceImpl orderService = new CoffeeOrderServiceImpl();

    private CoffeeVarietyServiceImpl varietyService = new CoffeeVarietyServiceImpl();

    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

    private CoffeeOrder order = new CoffeeOrder("Самовывоз", 0.0);

    public CreateBean() {
    }

    CreateBean(
        CoffeeOrderServiceImpl orderService,
        CoffeeVarietyServiceImpl varietyService,
        DeliveryServiceImpl deliveryService,
        CoffeeOrder order
    ) {
        this.orderService = orderService;
        this.varietyService = varietyService;
        this.deliveryService = deliveryService;
        this.order = order;
    }

    public String saveOrder(CoffeeOrder order) throws IOException {
        changeCost(order);
        orderService.save(order);
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

    public String get(Integer id) {
        order = orderService.get(id);
        return "order.xhtml";
    }

    public void validateTimeFrom(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            order.setTimeFrom((Date) value);
        }
    }

    public void validateTimeTo(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            order.setTimeTo((Date) value);
            if (order.getTimeFrom().after(order.getTimeTo())) {
                ResourceBundle resourceBundle =
                    ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
                String message = resourceBundle.getString("incorrect.date");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
                throw new ValidatorException(msg);
            }
        }
    }

    public double changeCost(CoffeeOrder order) {
        if (order.getVariety() != null && order.getDelivery() != null && order.getAmount() != null) {
            CoffeeVariety variety = varietyService.findByName(order.getVariety());
            Delivery delivery = deliveryService.findByName(order.getDelivery());
            order.setCost(order.getAmount() * variety.getPrice() / 1000 + delivery.getCost());
        }
        return order.getCost();
    }

    public List<CoffeeOrder> getCoffeeOrders() {
        return orderService.getAll();
    }

    public CoffeeOrder getOrder() {
        return order;
    }

    public void setOrder(CoffeeOrder order) {
        this.order = order;
    }
}