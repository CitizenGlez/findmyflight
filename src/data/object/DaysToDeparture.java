/**
 * 
 */
package data.object;

/**
 * @author alvaro
 *
 */
public class DaysToDeparture extends AbstractObject
{
    private int minDays;
    private int percentage;
    
    public DaysToDeparture(String line)
    {
        super(line);
        
        String[] parts = this.line.split(AbstractObject.CSV_DELIMITER);
        this.minDays = Integer.parseInt(parts[0]);
        this.percentage = Integer.parseInt(parts[1]);
    }

    public int getMinDays()
    {
        return minDays;
    }

    public int getPercentage()
    {
        return percentage;
    }
}
