/**
 * 
 */
package search;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import search.arg.Arguments;
import search.exception.DestinationNotFoundException;
import search.exception.OriginNotFoundException;

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

    public ArrayList<String> search(Arguments arguments) throws OriginNotFoundException, DestinationNotFoundException
    {
        ArrayList<String> result = new ArrayList<String>();
        
        if (this.airports.containsKey(arguments.getOrigin()))
        {
            Airport origin = this.airports.get(arguments.getOrigin());
            ArrayList<Flight> connections = origin.getConnections(arguments.getDestination());
            
            if (!connections.isEmpty())
            {
                for (Flight flight : connections)
                {
                    result.add(this.calculatePrice(flight, arguments));
                }
            }
            else
            {
                throw new DestinationNotFoundException(arguments.getOrigin(), arguments.getDestination());
            }
        }
        else
        {
            throw new OriginNotFoundException(arguments.getOrigin());
        }
        
        return result;
    }

    private String calculatePrice(Flight flight, Arguments arguments)
    {
        String result = flight.getNumber();
        String moreInfo = "";
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(custom);
        
        // The numbers
        int generalPercentaje = getPercentajeBasedOnDaysToDeparture(arguments.getDaysToDeparture());
        int childrenPercentaje = this.passengers.get(Passenger.CHILD).getPercentage();
        double infantPrice = this.airlines.get(flight.getIataCode()).getInfantPrice();
        
        // The price
        double price = (arguments.getAdults()   * (generalPercentaje / 100.0)                                * flight.getPrice())
                     + (arguments.getChildren() * (generalPercentaje / 100.0) * (childrenPercentaje / 100.0) * flight.getPrice())
                     + (arguments.getInfants()                                                               * infantPrice);
        
        // The more info
        if ((arguments.getAdults() > 1) || (arguments.getChildren() > 0) || (arguments.getInfants() > 0))
        {
            String grossPrice = df.format(flight.getPrice());
            
            if (arguments.getAdults() == 1)
            {
                moreInfo += generalPercentaje + "% of " + grossPrice;
            }
            else if (arguments.getAdults() > 1)
            {
                moreInfo += arguments.getAdults() + " * (" + generalPercentaje + "% of " + grossPrice + ")";
            }

            if (arguments.getChildren() == 1)
            {
                moreInfo += " + " + childrenPercentaje  + "% of (" + generalPercentaje + "% of " + grossPrice + ")";
            }
            else if (arguments.getChildren() > 1)
            {
                moreInfo += " + " + arguments.getChildren() + " * " + childrenPercentaje  + "% of (" + generalPercentaje + "% of " + grossPrice + ")";
            }

            if (arguments.getInfants() == 1)
            {
                moreInfo += " + " + df.format(infantPrice);
            }
            else if (arguments.getInfants() > 1)
            {
                moreInfo += " + " + arguments.getInfants() + " * " + df.format(infantPrice);
            }
        }
        
        // Append price
        result += ", " + df.format(price) + " €";
        
        // Append more info
        if (!moreInfo.isEmpty())
        {
            result += " (" + moreInfo + ")";
        }
        return result;
    }

    private int getPercentajeBasedOnDaysToDeparture(int days)
    {
        int result = 0;
        
        boolean found = false;
        Iterator<DaysToDeparture> it = this.daysToDeparture.iterator();
        while (it.hasNext() && !found)
        {
            DaysToDeparture daysToDeparture = (DaysToDeparture) it.next();
            if (daysToDeparture.getMinDays() <= days)
            {
                found = true;
                result = daysToDeparture.getPercentage();
            }
        }
        
        return result;
    }

}
