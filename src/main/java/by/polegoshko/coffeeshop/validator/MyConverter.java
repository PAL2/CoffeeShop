package by.polegoshko.coffeeshop.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import by.polegoshko.coffeeshop.domain.variety.CoffeeVarietyServiceImpl;

@FacesConverter("converter")
public class MyConverter implements Converter {

    private CoffeeVarietyServiceImpl varietyService = new CoffeeVarietyServiceImpl();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return varietyService.get("Каши");
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
