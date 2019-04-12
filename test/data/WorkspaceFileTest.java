/**
 * 
 */
package data;

import java.io.File;

import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class WorkspaceFileTest extends TestCase
{
    WorkspaceFile workspace;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.WorkspaceFile#getPassengersPath()}.
     */
    public void testGetPassengersPath()
    {
        assertEquals(this.workspace.getPassengersPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_PASSENGERS.replace("/", File.separator));
    }

    /**
     * Test method for {@link data.WorkspaceFile#getDaysToDeparturePath()}.
     */
    public void testGetDaysToDeparturePath()
    {
        assertEquals(this.workspace.getDaysToDeparturePath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_DAYS_TO_DEPARTURE.replace("/", File.separator));
    }

    /**
     * Test method for {@link data.WorkspaceFile#getAirlinesPath()}.
     */
    public void testGetAirlinesPath()
    {
        assertEquals(this.workspace.getAirlinesPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_AIRLINES.replace("/", File.separator));
    }

    /**
     * Test method for {@link data.WorkspaceFile#getAirportsPath()}.
     */
    public void testGetAirportsPath()
    {
        assertEquals(this.workspace.getAirportsPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_AIRPORTS.replace("/", File.separator));
    }

    /**
     * Test method for {@link data.WorkspaceFile#getFlightsPath()}.
     */
    public void testGetFlightsPath()
    {
        assertEquals(this.workspace.getFlightsPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_FLIGHTS.replace("/", File.separator));
    }

}
