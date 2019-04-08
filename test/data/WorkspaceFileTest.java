package data;

import java.io.File;

import junit.framework.TestCase;

public class WorkspaceFileTest extends TestCase
{
    private WorkspaceFile workspace;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile(WorkspaceFile.DEFAULT_WORKSPACE_FILE);
    }

    public void testGetters() throws Exception
    {
        assertEquals(this.workspace.getAirlinesPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_AIRLINES.replace("/", File.separator));
        assertEquals(this.workspace.getAirportsPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_AIRPORTS.replace("/", File.separator));
        assertEquals(this.workspace.getDaysToDeparturePath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_DAYS_TO_DEPARTURE.replace("/", File.separator));
        assertEquals(this.workspace.getFlightsPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_FLIGHTS.replace("/", File.separator));
        assertEquals(this.workspace.getPassengersPath(),
                     WorkspaceFile.DEFAULT_ROOT + File.separator + "data" + File.separator + WorkspaceFile.DEFAULT_PASSENGERS.replace("/", File.separator));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

}
