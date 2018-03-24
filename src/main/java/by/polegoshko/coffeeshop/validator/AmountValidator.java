package by.polegoshko.coffeeshop.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("amountValidator")
public class AmountValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (Double.valueOf(o.toString()) < 100) {
            ResourceBundle resourceBundle =
                ResourceBundle.getBundle("messages", fc.getViewRoot().getLocale());
            String message = resourceBundle.getString("small.amount");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
            throw new ValidatorException(msg);
        }
    }
}