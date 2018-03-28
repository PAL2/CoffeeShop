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

import by.polegoshko.coffeeshop.domain.delivery.Delivery;
import by.polegoshko.coffeeshop.domain.delivery.DeliveryServiceImpl;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrder;
import by.polegoshko.coffeeshop.domain.order.CoffeeOrderServiceImpl;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVarietyServiceImpl;

@ManagedBean(name = "createBean")
@RequestScoped
public class CreateBean {

    private CoffeeOrderServiceImpl orderService = new CoffeeOrderServiceImpl();

    private CoffeeVarietyServiceImpl varietyService = new CoffeeVarietyServiceImpl();

    private DeliveryServiceImpl deliveryService = new DeliveryServiceImpl();

    private CoffeeOrder coffeeOrder = new CoffeeOrder("Самовывоз");

    public String saveOrder() throws IOException {
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
        return "order.xhtml";
    }

    public void validateTimeFrom(FacesContext context, UIComponent component, Object value) {
        coffeeOrder.setTimeFrom((Date) value);
    }

    public void validateTimeTo(FacesContext context, UIComponent component, Object value) {
        coffeeOrder.setTimeTo((Date) value);
        if (coffeeOrder.getTimeFrom().after(coffeeOrder.getTimeTo())) {
            ResourceBundle resourceBundle =
                ResourceBundle.getBundle("messages", context.getViewRoot().getLocale());
            String message = resourceBundle.getString("incorrect.date");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            throw new ValidatorException(msg);
        }
    }

    public double changeCost() {
        if (coffeeOrder.getVariety() != null && coffeeOrder.getDelivery() != null
                && coffeeOrder.getAmount() != null) {
            CoffeeVariety coffeeVariety = varietyService.get(coffeeOrder.getVariety());
            Delivery deliveryTmp = deliveryService.get(coffeeOrder.getDelivery());
            coffeeOrder.setCost(coffeeOrder.getAmount() * coffeeVariety.getPrice()
                / 1000 + deliveryTmp.getCost());
        }
        return coffeeOrder.getCost();
    }

    public List<CoffeeOrder> getCoffeeOrders(){
        return orderService.getAll();
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }
}