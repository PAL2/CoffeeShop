package by.polegoshko.coffeeshop.api;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.polegoshko.coffeeshop.domain.delivery.Delivery;
import by.polegoshko.coffeeshop.domain.delivery.DeliveryServiceImpl;

@ManagedBean(name = "delivery")
@RequestScoped
public class DeliveryController {

    private Delivery delivery;

    private DeliveryServiceImpl service = new DeliveryServiceImpl();

    public List<Delivery> getDeliveries() {
        return service.getAll();
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
