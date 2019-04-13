/**
 * 
 */
package data.parser;

import java.util.ArrayList;

import data.WorkspaceFile;
import data.object.DaysToDeparture;
import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class DaysToDepartureParserTest extends TestCase
{
    WorkspaceFile workspace;
    DaysToDepartureParser daysToDepartureParser;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
        this.daysToDepartureParser = new DaysToDepartureParser(this.workspace.getDaysToDeparturePath());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.parser.DaysToDepartureParser#parse()}.
     */
    public void testParse()
    {
        ArrayList<DaysToDeparture> days = this.daysToDepartureParser.parse();
        assertEquals(days.size(), 4);
        assertEquals("31,80", days.get(0).toString());
        assertEquals("16,100", days.get(1).toString());
        assertEquals("3,120", days.get(2).toString());
        assertEquals("0,150", days.get(3).toString());
    }

}
