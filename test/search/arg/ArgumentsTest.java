/**
 * 
 */
package search.arg;

import junit.framework.TestCase;

/**
 * @author AGZ3
 *
 */
public class ArgumentsTest extends TestCase
{
    Arguments case1, case2, case3;

    protected void setUp() throws Exception
    {
        super.setUp();

        this.case1 = new Arguments("1 adult, 31 days to the departure date, flying AMS -> FRA");
        this.case2 = new Arguments("2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
        this.case3 = new Arguments("1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link search.arg.Arguments#getAdults()}.
     */
    public void testGetAdults()
    {
        assertEquals(1, this.case1.getAdults());
        assertEquals(2, this.case2.getAdults());
        assertEquals(1, this.case3.getAdults());
    }

    /**
     * Test method for {@link search.arg.Arguments#getChildren()}.
     */
    public void testGetChildren()
    {
        assertEquals(0, this.case1.getChildren());
        assertEquals(1, this.case2.getChildren());
        assertEquals(2, this.case3.getChildren());
    }

    /**
     * Test method for {@link search.arg.Arguments#getInfants()}.
     */
    public void testGetInfants()
    {
        assertEquals(0, this.case1.getInfants());
        assertEquals(1, this.case2.getInfants());
        assertEquals(0, this.case3.getInfants());
    }

    /**
     * Test method for {@link search.arg.Arguments#getDaysToDeparture()}.
     */
    public void testGetDaysToDeparture()
    {
        assertEquals(31, this.case1.getDaysToDeparture());
        assertEquals(15, this.case2.getDaysToDeparture());
        assertEquals(2, this.case3.getDaysToDeparture());
    }

    /**
     * Test method for {@link search.arg.Arguments#getOrigin()}.
     */
    public void testGetOrigin()
    {
        assertEquals("AMS", this.case1.getOrigin());
        assertEquals("LHR", this.case2.getOrigin());
        assertEquals("BCN", this.case3.getOrigin());
    }

    /**
     * Test method for {@link search.arg.Arguments#getDestination()}.
     */
    public void testGetDestination()
    {
        assertEquals("FRA", this.case1.getDestination());
        assertEquals("IST", this.case2.getDestination());
        assertEquals("MAD", this.case3.getDestination());
    }

}
