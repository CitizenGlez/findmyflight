/**
 * 
 */
package data.object;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author alvaro
 *
 */
public class Airline extends AbstractObject
{
    private String iataCode;
    private String name;
    private double infantPrice;

    public Airline(String line)
    {
        super(line);
        
        String[] parts = line.trim().split(AbstractObject.CSV_DELIMITER);
        this.iataCode = parts[0];
        this.name = parts[1];
        this.infantPrice = Double.parseDouble(parts[2]);
    }

    public String getIataCode()
    {
        return iataCode;
    }

    public String getName()
    {
        return name;
    }

    public double getInfantPrice()
    {
        return infantPrice;
    }

    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(custom);
        return iataCode + "," + name + "," + df.format(infantPrice);
    }

}
