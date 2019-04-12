/**
 * 
 */
package search;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import data.WorkspaceFile;
import data.object.Airline;
import data.object.Airport;
import data.object.DaysToDeparture;
import data.object.Flight;
import data.object.Passenger;
import data.parser.AirlineParser;
import data.parser.AirportParser;
import data.parser.DaysToDepartureParser;
import data.parser.FlightParser;
import data.parser.PassengerParser;

/**
 * @author alvaro
 *
 */
public class ConnectionsManager implements Observer
{
    HashMap<String, Airport> airports;
    HashMap<String, Airline> airlines;
    HashMap<String, Passenger> passengers;
    ArrayList<DaysToDeparture> daysToDeparture;

    /**
     * @throws FileNotFoundException 
     * 
     */
    public ConnectionsManager(WorkspaceFile workspace) throws FileNotFoundException
    {
        AirlineParser airlineParser = new AirlineParser(workspace.getAirlinesPath());
        this.airlines = airlineParser.Parse();
        
        PassengerParser passengerParser = new PassengerParser(workspace.getPassengersPath());
        this.passengers = passengerParser.Parse();
        
        DaysToDepartureParser daysToDepartureParser = new DaysToDepartureParser(workspace.getDaysToDeparturePath());
        this.daysToDeparture = daysToDepartureParser.Parse();
        
        AirportParser airportParser = new AirportParser(workspace.getAirportsPath());
        this.airports = airportParser.Parse();
        
        FlightParser flightParser = new FlightParser(workspace.getFlightsPath());
        flightParser.addObserver(this);
        flightParser.Parse();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (arg instanceof Flight)
        {
            Flight flight = (Flight) arg;
            Airport airport = airports.get(flight.getOrigin());
            airport.addConnection(flight);
        }
    }

    public ArrayList<String> search(String query)
    {
        ArrayList<String> result = new ArrayList<String>();
        
        // TODO: do
        
        return result;
    }

}
