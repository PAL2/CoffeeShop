package by.polegoshko.coffeeshop.api;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import by.polegoshko.coffeeshop.domain.variety.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.variety.CoffeeVarietyServiceImpl;

@ManagedBean(name = "coffeeVariety")
@RequestScoped
public class CoffeeVarietyController {

    private CoffeeVarietyServiceImpl service = new CoffeeVarietyServiceImpl();

    public List<CoffeeVariety> getCoffeeVarieties() {
        return service.getAll();
    }

    public String goOrders() {
        return "orders.xhtml";
    }
}