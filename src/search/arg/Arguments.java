/**
 * 
 */
package search.arg;

/**
 * @author AGZ3
 *
 */
public class Arguments
{
    int adults;
    int children;
    int infants;
    int daysToDeparture;
    String origin;
    String destination;
    
    // This constructor accepts queries like:
    // 1 adult, 31 days to the departure date, flying AMS -> FRA
    // 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
    // 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
    public Arguments(String query)
    {
        
    }

    public int getAdults()
    {
        return adults;
    }

    public int getChildren()
    {
        return children;
    }

    public int getInfants()
    {
        return infants;
    }

    public int getDaysToDeparture()
    {
        return daysToDeparture;
    }

    public String getOrigin()
    {
        return origin;
    }

    public String getDestination()
    {
        return destination;
    }
}
