/**
 * 
 */
package data.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;

import data.object.Airport;

/**
 * Parser of a .csv file that contains the airports data.
 * @author alvaro
 *
 */
public class AirportParser extends AbstractParser
{

    /**
     * @param path
     * @throws FileNotFoundException 
     */
    public AirportParser(String path) throws FileNotFoundException
    {
        super(path);
    }

    /**
     * Parses the .csv file.
     * @return A hashmap of Airport instances
     */
    public HashMap<String, Airport> parse()
    {
        HashMap<String, Airport> result = new HashMap<String, Airport>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path)))
        {
            while ((line = br.readLine()) != null)
            {
                try
                {
                    Airport airport = new Airport(line);
                    result.put(airport.getIataCode(), airport);
                }
                catch (InvalidParameterException e)
                {
                    // Comment or empty line, ignore
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
