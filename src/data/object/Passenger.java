/**
 * 
 */
package data.object;

/**
 * @author alvaro
 *
 */
public class Passenger extends AbstractObject
{
    private String type;
    private int percentage;
    
    public Passenger(String line)
    {
        super(line);
        
        String[] parts = this.line.split(AbstractObject.CSV_DELIMITER);
        this.type = parts[0];
        this.percentage = Integer.parseInt(parts[1]);
    }

    public String getType()
    {
        return type;
    }

    public int getPercentage()
    {
        return percentage;
    }
}
