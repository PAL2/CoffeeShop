package by.polegoshko.coffeeshop.api;

import java.util.List;

import javax.faces.bean.ManagedBean;

import by.polegoshko.coffeeshop.domain.coffeeVariety.CoffeeVariety;
import by.polegoshko.coffeeshop.domain.coffeeVariety.CoffeeVarietyServiceImpl;

@ManagedBean(name = "coffeeVariety")
public class CoffeeVarietyController {

    private CoffeeVariety coffeeVarietyList;

    private CoffeeVarietyServiceImpl service = new CoffeeVarietyServiceImpl();

    public CoffeeVarietyServiceImpl getService() {
        return service;
    }

    public void setService(CoffeeVarietyServiceImpl service) {
        this.service = service;
    }

    public List<CoffeeVariety> getCoffeeVarietyList() {
        List<CoffeeVariety> varieties = service.getAll();
        return varieties;
    }

    public String goOrders(){
        return "orders.xhtml";
    }
}