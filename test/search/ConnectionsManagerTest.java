package search;

import java.text.ParseException;

import data.WorkspaceFile;
import junit.framework.TestCase;
import search.arg.Arguments;
import search.exception.DestinationNotFoundException;
import search.exception.OriginNotFoundException;

public class ConnectionsManagerTest extends TestCase
{
    ConnectionsManager connectionsManager;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.connectionsManager = new ConnectionsManager(new WorkspaceFile());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link search.ConnectionsManager#search()}.
     */
    public void testSearch()
    {
        try
        {
            Arguments case1 = new Arguments("1 adult, 31 days to the departure date, flying AMS -> FRA");
            assertEquals(new String[] { "TK2372, 157.6 €",
                                        "TK2659, 198.4 €",
                                        "LH5909, 90.4 €" }, this.connectionsManager.search(case1).toArray());
        }
        catch (ParseException | OriginNotFoundException | DestinationNotFoundException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
        
        try
        {
            Arguments case2 = new Arguments("2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
            assertEquals(new String[] { "TK8891, 806 € (2 * (120% of 250) + 67% of (120% of 250) + 5)",
                                        "LH1085, 481.19 € (2 * (120% of 148) + 67% of (120% of 148) + 7)" }, this.connectionsManager.search(case2).toArray());
        }
        catch (ParseException | OriginNotFoundException | DestinationNotFoundException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
        
        try
        {
            Arguments case3 = new Arguments("1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
            assertEquals(new String[] { "IB2171, 909.09 € (150% of 259 + 2 * 67% of (150% of 259))",
                                        "LH5496, 1028.43 € (150% of 293 + 2 * 67% of (150% of 293))" }, this.connectionsManager.search(case3).toArray());
        }
        catch (ParseException | OriginNotFoundException | DestinationNotFoundException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
        
        try
        {
            Arguments case4 = new Arguments("CDG -> FRA");
            this.connectionsManager.search(case4);
        }
        catch (ParseException | OriginNotFoundException | DestinationNotFoundException e)
        {
            assertEquals("FRA is not an available destination from CDG", e.getMessage());
        }
    }

}
