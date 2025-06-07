package model;

public class Route {
    private Airport source;
    private Airport destination;
    private Flight flight;
    private int duration; // in minutes
    private double fare; // in INR
    private double distance; // in km

    public Route(Airport source, Airport destination, Flight flight, int duration, double fare, double distance) {
        this.source = source;
        this.destination = destination;
        this.flight = flight;
        this.duration = duration;
        this.fare = fare;
        this.distance = distance;
    }

    public Airport getSource() {
        return source;
    }

    public Airport getDestination() {
        return destination;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getDuration() {
        return duration;
    }

    public double getFare() {
        return fare;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return flight + " from " + source.getCityCode() + " to " + destination.getCityCode() + 
               " | Distance: " + String.format("%.1f", distance) + " km" +
               " | Duration: " + duration/60 + "h " + duration%60 + "m | Fare: ₹" + fare;
    }
}
