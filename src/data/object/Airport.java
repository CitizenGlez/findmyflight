/**
 * 
 */
package data.object;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * @author alvaro
 *
 */
public class Airport extends AbstractObject
{
    private String iataCode;
    private String city;
    private ArrayList<Flight> connections;

    public Airport(String line) throws InvalidParameterException
    {
        super(line);
        
        String[] parts = line.trim().split(AbstractObject.CSV_DELIMITER);
        this.iataCode = parts[0];
        this.city = parts[1];
        
        this.connections = new ArrayList<Flight>();
    }

    public String getIataCode()
    {
        return iataCode;
    }

    public String getCity()
    {
        return city;
    }

    public void addConnection(Flight flight)
    {
        this.connections.add(flight);
    }

    public ArrayList<Flight> getConnections(String destination)
    {
        ArrayList<Flight> result = new ArrayList<Flight>();
        for (Flight flight : this.connections)
        {
            if (flight.getDestination() == destination)
            {
                result.add(flight);
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        return iataCode + "," + city;
    }
}
