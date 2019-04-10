/**
 * 
 */
package data.object;

import java.security.InvalidParameterException;

/**
 * @author alvaro
 *
 */
public class Flight extends AbstractObject
{
    private String origin;
    private String destination;
    private String iataCode;
    private String number;
    private double price;

    public Flight(String line)
    {
        super(line);
        
        String[] parts = this.line.split(AbstractObject.CSV_DELIMITER);
        this.origin = parts[0];
        this.destination = parts[1];
        
        String code = parts[2];
        int alphabeticPos = 0;
        while ((alphabeticPos < code.length()) && Character.isAlphabetic(code.charAt(alphabeticPos)))
        {
            alphabeticPos++;
        }
        if (alphabeticPos > 0)
        {
            this.iataCode = code.substring(0, alphabeticPos - 1);
        }
        else
        {
            throw new InvalidParameterException("Wrong IATA code found");
        }
        
        int digitPos = alphabeticPos;
        while ((digitPos < code.length()) && Character.isDigit(code.charAt(digitPos)))
        {
            digitPos++;
        }
        if (digitPos > alphabeticPos)
        {
            this.number = code.substring(alphabeticPos, digitPos - 1);
        }
        else
        {
            throw new InvalidParameterException("Wrong flight number found");
        }
        
        this.price = Double.parseDouble(parts[3]);
    }

    public String getOrigin()
    {
        return origin;
    }

    public String getDestination()
    {
        return destination;
    }

    public String getIataCode()
    {
        return iataCode;
    }

    public String getNumber()
    {
        return iataCode + number;
    }

    public double getPrice()
    {
        return price;
    }

}
