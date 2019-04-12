/**
 * 
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author alvaro
 *
 */
public class WorkspaceFile
{
            static final String DEFAULT_ROOT = System.getProperty("user.dir");
    public  static final String DEFAULT_WORKSPACE_FILE = WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + "workspace.properties";
    private static final String PASSENGERS = "passengers";
            static final String DEFAULT_PASSENGERS = "rules/passengers.csv";
    private static final String DAYS_TO_DEPARTURE = "daystodeparture";
            static final String DEFAULT_DAYS_TO_DEPARTURE = "rules/days to departure date.csv";
    private static final String AIRLINES = "airlines";
            static final String DEFAULT_AIRLINES = "static/airlines.csv";
    private static final String AIRPORTS = "airports";
            static final String DEFAULT_AIRPORTS = "static/airports.csv";
    private static final String FLIGHTS = "flights";
            static final String DEFAULT_FLIGHTS = "static/flights.csv";

    private String parentdir = "";
    private Properties prop = new Properties();
    
    public WorkspaceFile() throws IOException
    {
        this(WorkspaceFile.DEFAULT_WORKSPACE_FILE);
    }
    
    public WorkspaceFile(String filepath) throws IOException
    {
        File file = new File(filepath);
        this.parentdir = file.getParent();
        
        InputStream input = new FileInputStream(filepath);
        this.prop.load(input);
    }

    public String getPassengersPath()
    {
        return this.parentdir + File.separator +
               this.prop.getProperty(WorkspaceFile.PASSENGERS, WorkspaceFile.DEFAULT_PASSENGERS).replace("/", File.separator);
    }

    public String getDaysToDeparturePath()
    {
        return this.parentdir + File.separator +
               this.prop.getProperty(WorkspaceFile.DAYS_TO_DEPARTURE, WorkspaceFile.DEFAULT_DAYS_TO_DEPARTURE).replace("/", File.separator);
    }

    public String getAirlinesPath()
    {
        return this.parentdir + File.separator +
               this.prop.getProperty(WorkspaceFile.AIRLINES, WorkspaceFile.DEFAULT_AIRLINES).replace("/", File.separator);
    }

    public String getAirportsPath()
    {
        return this.parentdir + File.separator +
               this.prop.getProperty(WorkspaceFile.AIRPORTS, WorkspaceFile.DEFAULT_AIRPORTS).replace("/", File.separator);
    }

    public String getFlightsPath()
    {
        return this.parentdir + File.separator +
               this.prop.getProperty(WorkspaceFile.FLIGHTS, WorkspaceFile.DEFAULT_FLIGHTS).replace("/", File.separator);
    }
}
