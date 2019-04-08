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
    private static final String DEFAULT_ROOT = System.getProperty("user.dir");
    public static final String DEFAULT_WORKSPACE_FILE = WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + "workspace.properties";
    private static String PASSENGERS = "passengers";
    private static String DEFAULT_PASSENGERS = "rules/passengers.csv";
    private static String DAYS_TO_DEPARTURE = "daystodeparture";
    private static String DEFAULT_DAYS_TO_DEPARTURE = "rules/days to departure date.csv";
    private static String AIRLINES = "airlines";
    private static String DEFAULT_AIRLINES = "static/airlines.csv";
    private static String AIRPORTS = "airports";
    private static String DEFAULT_AIRPORTS = "static/airports.csv";
    private static String FLIGHTS = "flights";
    private static String DEFAULT_FLIGHTS = "static/flights.csv";

    private String parentdir = "";
    private Properties prop = new Properties();
    
    public WorkspaceFile(String filepath) throws IOException
    {
        File file = new File(filepath);
        this.parentdir = file.getParent();
        
        InputStream input = new FileInputStream(filepath);
        this.prop.load(input);
    }

    public String getPassengersPath()
    {
        return this.parentdir +
               this.prop.getProperty(WorkspaceFile.PASSENGERS, WorkspaceFile.DEFAULT_PASSENGERS).replace("/", File.separator);
    }

    public String getDaysToDeparturePath()
    {
        return this.parentdir +
               this.prop.getProperty(WorkspaceFile.DAYS_TO_DEPARTURE, WorkspaceFile.DEFAULT_DAYS_TO_DEPARTURE).replace("/", File.separator);
    }

    public String getAirlinesPath()
    {
        return this.parentdir +
               this.prop.getProperty(WorkspaceFile.AIRLINES, WorkspaceFile.DEFAULT_AIRLINES).replace("/", File.separator);
    }

    public String getAirportsPath()
    {
        return this.parentdir +
               this.prop.getProperty(WorkspaceFile.AIRPORTS, WorkspaceFile.DEFAULT_AIRPORTS).replace("/", File.separator);
    }

    public String getFlightsPath()
    {
        return this.parentdir +
               this.prop.getProperty(WorkspaceFile.FLIGHTS, WorkspaceFile.DEFAULT_FLIGHTS).replace("/", File.separator);
    }
}
