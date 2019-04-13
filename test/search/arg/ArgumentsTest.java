/**
 * 
 */
package search.arg;

import java.security.InvalidParameterException;
import java.text.ParseException;

import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class ArgumentsTest extends TestCase
{
    Arguments case1, case2, case3, case4;

    protected void setUp() throws Exception
    {
        super.setUp();

        this.case1 = new Arguments("1 adult, 31 days to the departure date, flying AMS -> FRA");
        this.case2 = new Arguments("2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
        this.case3 = new Arguments("1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
        this.case4 = new Arguments("CDG -> FRA");
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
        assertEquals(1, this.case4.getAdults());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("-1 adults");
            fail("This test case should have thrown an exception");
        }
        catch (InvalidParameterException e)
        {
            assertEquals("The number of adults may not be negative", e.getMessage());
        }
        catch (ParseException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("1 child, AMS -> FRA");
            fail("This test case should have thrown an exception");
        }
        catch (InvalidParameterException e)
        {
            assertEquals("Children and infants cannot travel without at least one adult", e.getMessage());
        }
        catch (ParseException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link search.arg.Arguments#getChildren()}.
     */
    public void testGetChildren()
    {
        assertEquals(0, this.case1.getChildren());
        assertEquals(1, this.case2.getChildren());
        assertEquals(2, this.case3.getChildren());
        assertEquals(0, this.case4.getChildren());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("-1 children");
            fail("This test case should have thrown an exception");
        }
        catch (InvalidParameterException e)
        {
            assertEquals("The number of children may not be negative", e.getMessage());
        }
        catch (ParseException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link search.arg.Arguments#getInfants()}.
     */
    public void testGetInfants()
    {
        assertEquals(0, this.case1.getInfants());
        assertEquals(1, this.case2.getInfants());
        assertEquals(0, this.case3.getInfants());
        assertEquals(0, this.case4.getInfants());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("-1 infants");
            fail("This test case should have thrown an exception");
        }
        catch (InvalidParameterException e)
        {
            assertEquals("The number of infants may not be negative", e.getMessage());
        }
        catch (ParseException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link search.arg.Arguments#getDaysToDeparture()}.
     */
    public void testGetDaysToDeparture()
    {
        assertEquals(31, this.case1.getDaysToDeparture());
        assertEquals(15, this.case2.getDaysToDeparture());
        assertEquals(2, this.case3.getDaysToDeparture());
        assertEquals(0, this.case4.getDaysToDeparture());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("-1 days to the departure date");
            fail("This test case should have thrown an exception");
        }
        catch (InvalidParameterException e)
        {
            assertEquals("The number of days to departure date may not be negative", e.getMessage());
        }
        catch (ParseException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link search.arg.Arguments#getOrigin()}.
     */
    public void testGetOrigin()
    {
        assertEquals("AMS", this.case1.getOrigin());
        assertEquals("LHR", this.case2.getOrigin());
        assertEquals("BCN", this.case3.getOrigin());
        assertEquals("CDG", this.case4.getOrigin());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("ABC -> DEF -> GHI");
            fail("This test case should have thrown an exception");
        }
        catch (ParseException e)
        {
            assertEquals("Wrong token has been found: \"ABC -> DEF -> GHI\"", e.getMessage());
        }
        catch (InvalidParameterException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("1 adult");
            fail("This test case should have thrown an exception");
        }
        catch (ParseException e)
        {
            assertEquals("No origin or destination information has been provided", e.getMessage());
        }
        catch (InvalidParameterException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

    /**
     * Test method for {@link search.arg.Arguments#getDestination()}.
     */
    public void testGetDestination()
    {
        assertEquals("FRA", this.case1.getDestination());
        assertEquals("IST", this.case2.getDestination());
        assertEquals("MAD", this.case3.getDestination());
        assertEquals("FRA", this.case4.getDestination());
        
        try
        {
            @SuppressWarnings("unused")
            Arguments negativeCase = new Arguments("nothing");
            fail("This test case should have thrown an exception");
        }
        catch (ParseException e)
        {
            assertEquals("Wrong token has been found: \"nothing\"", e.getMessage());
        }
        catch (InvalidParameterException e)
        {
            fail("An exception has been thrown: " + e.getMessage());
        }
    }

}
