/**
 * 
 */
package data.parser;

import java.util.HashMap;

import data.WorkspaceFile;
import data.object.Passenger;
import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class PassengerParserTest extends TestCase
{
    WorkspaceFile workspace;
    PassengerParser passengerParser;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
        this.passengerParser = new PassengerParser(this.workspace.getPassengersPath());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.parser.PassengerParser#Parse()}.
     */
    public void testParse()
    {
        HashMap<String, Passenger> passengers = this.passengerParser.Parse();
        assertEquals(passengers.size(), 3);
        assertEquals("adult,100", passengers.get("adult").toString());
        assertEquals("child,67", passengers.get("child").toString());
        assertEquals("infant,0", passengers.get("infant").toString());
    }

}
