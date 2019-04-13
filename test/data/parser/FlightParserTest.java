/**
 * 
 */
package data.parser;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import data.WorkspaceFile;
import data.object.Flight;
import junit.framework.TestCase;

/**
 * @author alvaro
 *
 */
public class FlightParserTest extends TestCase implements Observer
{
    WorkspaceFile workspace;
    FlightParser flightParser;
    ArrayList<Flight> flights;

    protected void setUp() throws Exception
    {
        super.setUp();
        
        this.workspace = new WorkspaceFile();
        this.flightParser = new FlightParser(this.workspace.getFlightsPath());
        this.flightParser.addObserver(this);
        this.flights = new ArrayList<Flight>();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test method for {@link data.parser.FlightParser#parse()}.
     */
    public void testParse()
    {
        this.flightParser.parse();
        assertEquals(this.flights.size(), 89);
        assertEquals("CPH,FRA,IB2818,186.00", flights.get(0).toString());
        assertEquals("CPH,LHR,U23631,152.00", flights.get(1).toString());
        assertEquals("CDG,MAD,IB8482,295.00", flights.get(2).toString());
        assertEquals("BCN,FRA,FR7521,150.00", flights.get(3).toString());
        assertEquals("CPH,FCO,TK4667,137.00", flights.get(4).toString());
        assertEquals("CPH,FCO,U24631,268.00", flights.get(5).toString());
        assertEquals("FCO,CDG,VY4335,158.00", flights.get(6).toString());
        assertEquals("LHR,IST,TK8891,250.00", flights.get(7).toString());
        assertEquals("FRA,AMS,U24107,237.00", flights.get(8).toString());
        assertEquals("CPH,BCN,U22593,218.00", flights.get(9).toString());
        assertEquals("BCN,IST,VY9890,178.00", flights.get(10).toString());
        assertEquals("AMS,CPH,TK4927,290.00", flights.get(11).toString());
        assertEquals("FCO,MAD,BA1164,118.00", flights.get(12).toString());
        assertEquals("CPH,LHR,BA7710,138.00", flights.get(13).toString());
        assertEquals("BCN,AMS,U24985,191.00", flights.get(14).toString());
        assertEquals("MAD,CDG,IB9961,128.00", flights.get(15).toString());
        assertEquals("LHR,FRA,LH2118,165.00", flights.get(16).toString());
        assertEquals("IST,FRA,IB8911,180.00", flights.get(17).toString());
        assertEquals("AMS,FRA,TK2372,197.00", flights.get(18).toString());
        assertEquals("FRA,IST,LH4145,169.00", flights.get(19).toString());
        assertEquals("MAD,CDG,IB6112,112.00", flights.get(20).toString());
        assertEquals("CPH,FRA,LH1678,298.00", flights.get(21).toString());
        assertEquals("LHR,CPH,LH6620,217.00", flights.get(22).toString());
        assertEquals("MAD,LHR,TK4199,186.00", flights.get(23).toString());
        assertEquals("MAD,CDG,IB7403,253.00", flights.get(24).toString());
        assertEquals("FRA,CPH,BA4369,109.00", flights.get(25).toString());
        assertEquals("BCN,MAD,IB2171,259.00", flights.get(26).toString());
        assertEquals("IST,LHR,LH6412,197.00", flights.get(27).toString());
        assertEquals("IST,MAD,LH1115,160.00", flights.get(28).toString());
        assertEquals("LHR,LHR,VY8162,285.00", flights.get(29).toString());
        assertEquals("FRA,LHR,BA8162,205.00", flights.get(30).toString());
        assertEquals("AMS,FCO,BA7610,168.00", flights.get(31).toString());
        assertEquals("LHR,IST,LH1085,148.00", flights.get(32).toString());
        assertEquals("FCO,FRA,U21423,274.00", flights.get(33).toString());
        assertEquals("CPH,MAD,U23282,113.00", flights.get(34).toString());
        assertEquals("CDG,CPH,LH5778,263.00", flights.get(35).toString());
        assertEquals("CPH,CDG,BA2777,284.00", flights.get(36).toString());
        assertEquals("BCN,LHR,TK4375,208.00", flights.get(37).toString());
        assertEquals("MAD,FCO,LH8408,149.00", flights.get(38).toString());
        assertEquals("AMS,IST,IB4563,109.00", flights.get(39).toString());
        assertEquals("LHR,FCO,LH5174,251.00", flights.get(40).toString());
        assertEquals("MAD,BCN,BA9569,232.00", flights.get(41).toString());
        assertEquals("AMS,FRA,TK2659,248.00", flights.get(42).toString());
        assertEquals("LHR,CDG,IB2771,289.00", flights.get(43).toString());
        assertEquals("IST,MAD,IB8688,150.00", flights.get(44).toString());
        assertEquals("CPH,AMS,TK8355,137.00", flights.get(45).toString());
        assertEquals("FCO,CDG,VY2974,111.00", flights.get(46).toString());
        assertEquals("AMS,FRA,LH5909,113.00", flights.get(47).toString());
        assertEquals("CPH,BCN,FR7949,176.00", flights.get(48).toString());
        assertEquals("BCN,CPH,U27858,237.00", flights.get(49).toString());
        assertEquals("FRA,AMS,LH2320,288.00", flights.get(50).toString());
        assertEquals("LHR,BCN,VY4633,149.00", flights.get(51).toString());
        assertEquals("AMS,IST,IB7289,163.00", flights.get(52).toString());
        assertEquals("FRA,LHR,IB9443,254.00", flights.get(53).toString());
        assertEquals("IST,FCO,LH4948,176.00", flights.get(54).toString());
        assertEquals("IST,BCN,TK5558,211.00", flights.get(55).toString());
        assertEquals("BCN,BCN,BA9409,215.00", flights.get(56).toString());
        assertEquals("IST,AMS,FR9261,267.00", flights.get(57).toString());
        assertEquals("CDG,IST,IB7181,227.00", flights.get(58).toString());
        assertEquals("LHR,BCN,TK1446,217.00", flights.get(59).toString());
        assertEquals("FCO,FRA,TK2793,175.00", flights.get(60).toString());
        assertEquals("AMS,CPH,FR1491,284.00", flights.get(61).toString());
        assertEquals("IST,BCN,IB9219,279.00", flights.get(62).toString());
        assertEquals("MAD,AMS,TK7871,159.00", flights.get(63).toString());
        assertEquals("FCO,AMS,VY4840,260.00", flights.get(64).toString());
        assertEquals("MAD,FRA,BA8982,171.00", flights.get(65).toString());
        assertEquals("IST,LHR,U23526,254.00", flights.get(66).toString());
        assertEquals("FRA,MAD,BA6773,157.00", flights.get(67).toString());
        assertEquals("CDG,CPH,IB5257,299.00", flights.get(68).toString());
        assertEquals("CPH,CDG,LH8545,230.00", flights.get(69).toString());
        assertEquals("LHR,AMS,IB4737,110.00", flights.get(70).toString());
        assertEquals("BCN,MAD,LH5496,293.00", flights.get(71).toString());
        assertEquals("CDG,LHR,U29718,103.00", flights.get(72).toString());
        assertEquals("LHR,AMS,BA9561,253.00", flights.get(73).toString());
        assertEquals("FRA,LHR,TK3167,118.00", flights.get(74).toString());
        assertEquals("IST,FRA,FR4727,108.00", flights.get(75).toString());
        assertEquals("CPH,IST,LH6320,115.00", flights.get(76).toString());
        assertEquals("LHR,AMS,BA6657,122.00", flights.get(77).toString());
        assertEquals("LHR,FRA,TK5342,295.00", flights.get(78).toString());
        assertEquals("IST,LHR,IB4938,226.00", flights.get(79).toString());
        assertEquals("CDG,BCN,VY9791,289.00", flights.get(80).toString());
        assertEquals("MAD,LHR,IB4124,272.00", flights.get(81).toString());
        assertEquals("FRA,MAD,BA7842,121.00", flights.get(82).toString());
        assertEquals("AMS,FCO,VY5092,178.00", flights.get(83).toString());
        assertEquals("CDG,LHR,BA9813,171.00", flights.get(84).toString());
        assertEquals("FRA,IST,BA2421,226.00", flights.get(85).toString());
        assertEquals("IST,CPH,U28059,262.00", flights.get(86).toString());
        assertEquals("MAD,AMS,LH7260,191.00", flights.get(87).toString());
        assertEquals("CDG,CPH,TK2044,186.00", flights.get(88).toString());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (arg instanceof Flight)
        {
            Flight flight = (Flight) arg;
            this.flights.add(flight);
        }
    }

}
