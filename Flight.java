package model;

public class Flight {
    private String flightNumber;
    private String airlineName;

    public Flight(String flightNumber, String airlineName) {
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    @Override
    public String toString() {
        return airlineName + " (" + flightNumber + ")";
    }
}
