/**
 * 
 */
package data.parser;

import java.util.HashMap;

import data.WorkspaceFile;
import data.object.Airline;
import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class AirlineParserTest extends TestCase
{
    WorkspaceFile workspace;
    AirlineParser airlineParser;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
        this.airlineParser = new AirlineParser(this.workspace.getAirlinesPath());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.parser.AirlineParser#Parse()}.
     */
    public void testParse()
    {
        HashMap<String, Airline> airlines = this.airlineParser.Parse();
        assertEquals(7, airlines.size());
        assertEquals("IB,Iberia,10.00", airlines.get("IB").toString());
        assertEquals("BA,British Airways,15.00", airlines.get("BA").toString());
        assertEquals("LH,Lufthansa,7.00", airlines.get("LH").toString());
        assertEquals("FR,Ryanair,20.00", airlines.get("FR").toString());
        assertEquals("VY,Vueling,10.00", airlines.get("VY").toString());
        assertEquals("TK,Turkish Airlines,5.00", airlines.get("TK").toString());
        assertEquals("U2,Easyjet,19.90", airlines.get("U2").toString());
    }

}
