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

import data.object.Airline;

/**
 * Parser of a .csv file that contains the airlines data.
 * @author alvaro
 *
 */
public class AirlineParser extends AbstractParser
{

    /**
     * @param path
     * @throws FileNotFoundException
     */
    public AirlineParser(String path) throws FileNotFoundException
    {
        super(path);
    }

    /**
     * Parses the .csv file.
     * @return A hashmap of Airline instances
     */
    public HashMap<String, Airline> parse()
    {
        HashMap<String, Airline> result = new HashMap<String, Airline>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path)))
        {
            while ((line = br.readLine()) != null)
            {
                try
                {
                    Airline airline = new Airline(line);
                    result.put(airline.getIataCode(), airline);
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
