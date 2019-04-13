/**
 * 
 */
package data.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;

import data.object.DaysToDeparture;

/**
 * Parser of a .csv file that contains the days to departure date data.
 * @author alvaro
 *
 */
public class DaysToDepartureParser extends AbstractParser
{

    /**
     * @param path
     * @throws FileNotFoundException
     */
    public DaysToDepartureParser(String path) throws FileNotFoundException
    {
        super(path);
    }
    
    /**
     * Parses the .csv file.
     * @return A list of DaysToDeparture instances
     */
    public ArrayList<DaysToDeparture> parse()
    {
        ArrayList<DaysToDeparture> result = new ArrayList<DaysToDeparture>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(this.path)))
        {
            while ((line = br.readLine()) != null)
            {
                try
                {
                    DaysToDeparture daysToDeparture = new DaysToDeparture(line);
                    result.add(daysToDeparture);
                }
                catch (InvalidParameterException e)
                {
                    // Comment or empty line, ignore
                }
            }

            // Sort in descending ordering
            result.sort(new Comparator<DaysToDeparture>()
            {
                @Override
                public int compare(DaysToDeparture o1, DaysToDeparture o2)
                {
                    return o2.getMinDays() - o1.getMinDays();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
