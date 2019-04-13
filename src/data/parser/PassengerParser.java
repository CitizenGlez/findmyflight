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

import data.object.Passenger;

/**
 * Parser of a .csv file that contains the passengers data.
 * @author alvaro
 *
 */
public class PassengerParser extends AbstractParser
{

    /**
     * @param path
     * @throws FileNotFoundException
     */
    public PassengerParser(String path) throws FileNotFoundException
    {
        super(path);
    }

    /**
     * Parses the .csv file.
     * @return A hashmap of Airline instances
     */
    public HashMap<String, Passenger> parse()
    {
        HashMap<String, Passenger> result = new HashMap<String, Passenger>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path)))
        {
            while ((line = br.readLine()) != null)
            {
                try
                {
                    Passenger passenger = new Passenger(line);
                    result.put(passenger.getType(), passenger);
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
