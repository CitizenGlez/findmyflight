/**
 * 
 */
package search.arg;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author AGZ3
 *
 */
public class Arguments
{
    // All of them singular and plural friendly
    private static final String ADULT = "adult";
    private static final String CHILD = "child";
    private static final String INFANT = "infant";
    private static final String DAY = "to the departure date";
    private static final String FLYING = "flying";
    
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
    public Arguments(String query) throws ParseException, InvalidParameterException
    {
        // Split the query into comma separated elements
        String[] tokens = query.split(",");
        
        for (String token : tokens)
        {
            try (Scanner sc = new Scanner(token))
            {
                int tokenpos = query.indexOf(token);
                
                if (token.toLowerCase().contains(Arguments.ADULT))
                {
                    this.adults = sc.nextInt();
                    if (this.adults < 0)
                    {
                        throw new InvalidParameterException("The number of adults may not be negative");
                    }
                }
                else if (token.toLowerCase().contains(Arguments.CHILD))
                {
                    this.children = sc.nextInt();
                    if (this.children < 0)
                    {
                        throw new InvalidParameterException("The number of children may not be negative");
                    }
                }
                else if (token.toLowerCase().contains(Arguments.INFANT))
                {
                    this.infants = sc.nextInt();
                    if (this.infants < 0)
                    {
                        throw new InvalidParameterException("The number of infants may not be negative");
                    }
                }
                else if (token.toLowerCase().contains(Arguments.DAY))
                {
                    this.daysToDeparture = sc.nextInt();
                    if (this.daysToDeparture < 0)
                    {
                        throw new InvalidParameterException("The number of days to departure date may not be negative");
                    }
                }
                else if (token.toLowerCase().contains(Arguments.FLYING))
                {
                    String trimmedToken = token.trim();
                    if (trimmedToken.toLowerCase().startsWith(Arguments.FLYING))
                    {
                        String trip = trimmedToken.substring(Arguments.FLYING.length()).trim();
                        String[] airports = trip.split("->");
                        if (airports.length == 2)
                        {
                            this.origin = airports[0].trim().toUpperCase();
                            this.destination = airports[1].trim().toUpperCase();
                        }
                        else
                        {
                            throw new ParseException("Wrong token has been found: \"" + token + "\"", tokenpos);
                        }
                    }
                    else
                    {
                        throw new ParseException("Wrong token has been found: \"" + token + "\"", tokenpos);
                    }
                }
                else
                {
                    throw new ParseException("Wrong token has been found: \"" + token + "\"", tokenpos);
                }
            }
            catch (Exception e)
            {
                throw e;  // We rethrow in order to avoid leaking the Scanner
            }
        }
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
