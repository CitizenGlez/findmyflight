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
 * This class contains all the data contained within the files referenced in the workspace file, and provides a flight search interface.
 * @author alvaro
 */
public class ConnectionsManager implements Observer
{
    HashMap<String, Airport> airports;
    HashMap<String, Airline> airlines;
    HashMap<String, Passenger> passengers;
    ArrayList<DaysToDeparture> daysToDeparture;

    /**
     * Parses all the contents of the .csv files referenced in the workspace file.
     * @param workspace
     * @throws FileNotFoundException
     */
    public ConnectionsManager(WorkspaceFile workspace) throws FileNotFoundException
    {
        AirlineParser airlineParser = new AirlineParser(workspace.getAirlinesPath());
        this.airlines = airlineParser.parse();
        
        PassengerParser passengerParser = new PassengerParser(workspace.getPassengersPath());
        this.passengers = passengerParser.parse();
        
        DaysToDepartureParser daysToDepartureParser = new DaysToDepartureParser(workspace.getDaysToDeparturePath());
        this.daysToDeparture = daysToDepartureParser.parse();
        
        AirportParser airportParser = new AirportParser(workspace.getAirportsPath());
        this.airports = airportParser.parse();
        
        FlightParser flightParser = new FlightParser(workspace.getFlightsPath());
        flightParser.addObserver(this);  // We observer the flight parser
        flightParser.parse();
    }

    /**
     * This is what we do when we receive an update notification from the flight parser.
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if (arg instanceof Flight)
        {
            // A new flight has been discovered, and we add it to the airport where it departs from
            Flight flight = (Flight) arg;
            Airport airport = airports.get(flight.getOrigin());
            airport.addConnection(flight);
        }
    }

    /**
     * Searches all the flights between two given airports, for the amount of adults, children and infants to fly within a given number of days prior to the departure date.
     * @param arguments
     * @return A list of strings, each of them describing a flight search result
     * @throws OriginNotFoundException
     * @throws DestinationNotFoundException
     */
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

    /**
     * Calculates the price of a flight according to the given arguments and the pricing rules.
     * @param flight
     * @param arguments
     * @return A string containing the flight number, price in euros and description of how the price has been calculated according to the pricing rules
     */
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

    /**
     * Calculates the percentaje of the base price to pay, according to the days to departure date rule.
     * @param days
     * @return Percentaje of the base price to pay, according to the days to departure date rule
     */
    private int getPercentajeBasedOnDaysToDeparture(int days)
    {
        int result = 0;
        
        // The list is already sorted from the highest to the lowest days, so we just iterate
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
