package by.polegoshko.coffeeshop.validator;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore( {"javax.xml.*", "org.xml.sax.*", "org.w3c.*"})
public class AmountValidatorTest {

    private AmountValidator validator;

    @Mock
    private FacesContext facesContext;

    @Mock
    private UIComponent uiComponent;

    @Mock
    private UIViewRoot uiViewRoot;

    @Before
    public void setUp() throws Exception {
        validator = new AmountValidator();
    }

    @PrepareForTest( {ResourceBundle.class})
    @Test(expected = ValidatorException.class)
    public void validate_ValidatorException() throws Exception {
        when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
        when(uiViewRoot.getLocale()).thenReturn(new Locale("ru"));

        PowerMockito.mockStatic(ResourceBundle.class);
        ResourceBundle resourceBundle = PowerMockito.mock(ResourceBundle.class);
        when(ResourceBundle.getBundle(anyString(), any(Locale.class)))
            .thenReturn(resourceBundle);

        validator.validate(facesContext, uiComponent, 50);
    }

    @Test
    public void validate() throws Exception {
        validator.validate(facesContext, uiComponent, 200);
    }
}