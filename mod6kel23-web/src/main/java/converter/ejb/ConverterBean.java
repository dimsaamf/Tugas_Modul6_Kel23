package converter.ejb;

import java.text.DecimalFormat;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;

@Stateless
@LocalBean
public class ConverterBean implements ConverterBeanLocal {
    
    private DecimalFormat twoDigits = new DecimalFormat("0.00");
    
    public String ctof(double c) {
        String result = twoDigits.format((c * 0.8));
        return result;
    }
    
    public String ftoc(double r) {
        String result = twoDigits.format((r * 1.25));
        return result;
    }
}
