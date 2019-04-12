/**
 * 
 */
package data.parser;

import java.util.HashMap;

import data.WorkspaceFile;
import data.object.Airport;
import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class AirportParserTest extends TestCase
{
    WorkspaceFile workspace;
    AirportParser airportParser;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
        this.airportParser = new AirportParser(this.workspace.getAirportsPath());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.parser.AirportParser#Parse()}.
     */
    public void testParse()
    {
        HashMap<String, Airport> airports = this.airportParser.Parse();
        assertEquals(airports.size(), 9);
        assertEquals("MAD,Madrid", airports.get("MAD").toString());
        assertEquals("BCN,Barcelona", airports.get("BCN").toString());
        assertEquals("LHR,London", airports.get("LHR").toString());
        assertEquals("CDG,Paris", airports.get("CDG").toString());
        assertEquals("FRA,Frankfurt", airports.get("FRA").toString());
        assertEquals("IST,Istanbul", airports.get("IST").toString());
        assertEquals("AMS,Amsterdam", airports.get("AMS").toString());
        assertEquals("FCO,Rome", airports.get("FCO").toString());
        assertEquals("CPH,Copenhagen", airports.get("CPH").toString());
    }

}
