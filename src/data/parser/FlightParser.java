/**
 * 
 */
package data.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Observable;

import data.object.Flight;

/**
 * Parser of a .csv file that contains the flights data.
 * @author alvaro
 *
 */
public class FlightParser extends Observable
{
    protected String path;

    /**
     * @param path
     * @throws FileNotFoundException
     */
    public FlightParser(String path) throws FileNotFoundException
    {
        File file = new File(path);
        if (!file.exists())
        {
            throw new FileNotFoundException();
        }
        
        this.path = path;
    }
    
    /**
     * Parses the .csv file.
     */
    public void parse()
    {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path)))
        {
            while ((line = br.readLine()) != null)
            {
                try
                {
                    Flight flight = new Flight(line);

                    // To whom it may concern
                    this.setChanged();
                    this.notifyObservers(flight);
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
    }

}
