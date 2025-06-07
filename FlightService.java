package service;

import model.Flight;
import java.util.HashMap;
import java.util.Map;

public class FlightService {
    private Map<String, Flight> flights;

    public FlightService() {
        flights = new HashMap<>();
        initializeFlights();
    }

    private void initializeFlights() {
        // Domestic flights in India
        addFlight("AI101", "Air India");
        addFlight("AI102", "Air India");
        addFlight("6E201", "IndiGo");
        addFlight("6E202", "IndiGo");
        addFlight("SG301", "SpiceJet");
        addFlight("SG302", "SpiceJet");
        addFlight("UK401", "Vistara");
        addFlight("UK402", "Vistara");
        addFlight("G8051", "GoAir");
        addFlight("G8052", "GoAir");
        addFlight("AI111", "Air India");
        addFlight("AI112", "Air India");
        addFlight("6E211", "IndiGo");
        addFlight("6E212", "IndiGo");
        addFlight("SG311", "SpiceJet");
        addFlight("SG312", "SpiceJet");
    }

    public void addFlight(String flightNumber, String airlineName) {
        flights.put(flightNumber, new Flight(flightNumber, airlineName));
    }

    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }
}
