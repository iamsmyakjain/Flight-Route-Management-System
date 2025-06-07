package service;

import model.Airport;
import model.Flight;
import model.Route;
import java.util.*;

public class RouteService {
    private Map<Airport, List<Route>> routeMap;
    private FlightService flightService;
    private List<Airport> airports;
    private Random random;

    public RouteService(FlightService flightService) {
        this.routeMap = new HashMap<>();
        this.flightService = flightService;
        this.airports = new ArrayList<>();
        this.random = new Random();
        initializeAirports();
        initializeRoutes();
        ensureConnectivity();
    }

    private void initializeAirports() {
        // Major Indian airports with coordinates (latitude, longitude)
        airports.add(new Airport("DEL", "Delhi", 28.5562, 77.1000));
        airports.add(new Airport("BOM", "Mumbai", 19.0887, 72.8679));
        airports.add(new Airport("BLR", "Bengaluru", 13.1986, 77.7066));
        airports.add(new Airport("HYD", "Hyderabad", 17.2403, 78.4294));
        airports.add(new Airport("CCU", "Kolkata", 22.6547, 88.4467));
        airports.add(new Airport("MAA", "Chennai", 12.9941, 80.1707));
        airports.add(new Airport("GOI", "Goa", 15.3808, 73.8345));
        airports.add(new Airport("PNQ", "Pune", 18.5822, 73.9197));
        airports.add(new Airport("COK", "Kochi", 10.1520, 76.4019));
        airports.add(new Airport("TRV", "Thiruvananthapuram", 8.4821, 76.9204));
        airports.add(new Airport("AMD", "Ahmedabad", 23.0772, 72.6347));
        airports.add(new Airport("JAI", "Jaipur", 26.8242, 75.8122));
        airports.add(new Airport("LKO", "Lucknow", 26.7606, 80.8893));
        airports.add(new Airport("GAU", "Guwahati", 26.1061, 91.5859));
        airports.add(new Airport("PAT", "Patna", 25.5913, 85.0880));
        airports.add(new Airport("IXC", "Chandigarh", 30.6735, 76.7885));
        airports.add(new Airport("VNS", "Varanasi", 25.4524, 82.8593));
        airports.add(new Airport("IDR", "Indore", 22.7218, 75.8011));
        airports.add(new Airport("BDQ", "Vadodara", 22.3362, 73.2263));
        airports.add(new Airport("BHO", "Bhopal", 23.2875, 77.3374));
        airports.add(new Airport("NAG", "Nagpur", 21.0922, 79.0472));
        airports.add(new Airport("IXB", "Bagdogra", 26.6812, 88.3286));
        airports.add(new Airport("IXZ", "Port Blair", 11.6412, 92.7297));
        airports.add(new Airport("SXR", "Srinagar", 33.9871, 74.7743));
        airports.add(new Airport("IXJ", "Jammu", 32.6891, 74.8384));
        airports.add(new Airport("IXA", "Agartala", 23.8868, 91.2405));
        airports.add(new Airport("IXD", "Allahabad", 25.4401, 81.7339));
        airports.add(new Airport("IXR", "Ranchi", 23.3143, 85.3217));
        airports.add(new Airport("IXE", "Mangalore", 12.9613, 74.8901));
        airports.add(new Airport("IXM", "Madurai", 9.8345, 78.0936));
        airports.add(new Airport("IXU", "Aurangabad", 19.8632, 75.3959));
        airports.add(new Airport("JDH", "Jodhpur", 26.2511, 73.0489));
        airports.add(new Airport("UDR", "Udaipur", 24.6177, 73.8961));
        airports.add(new Airport("RPR", "Raipur", 21.1804, 81.7390));
        airports.add(new Airport("HBX", "Hubli", 15.3617, 75.0849));
        airports.add(new Airport("STV", "Surat", 21.1146, 72.7418));
        airports.add(new Airport("BBI", "Bhubaneswar", 20.2444, 85.8178));
        airports.add(new Airport("CJB", "Coimbatore", 11.0300, 77.0434));
        airports.add(new Airport("TIR", "Tirupati", 13.6325, 79.5433));
        airports.add(new Airport("VTZ", "Visakhapatnam", 17.7212, 83.2245));
    }

    private void initializeRoutes() {
        // First create direct routes between major hubs
        createHubRoutes();
        
        // Then connect other airports to nearest hubs
        connectRegionalAirports();
        
        // Finally add some random routes for variety
        addRandomRoutes();
    }
    
    private void createHubRoutes() {
        // Connect major hubs (Delhi, Mumbai, Bangalore, Kolkata, Chennai)
        Airport delhi = findAirportByCode("DEL");
        Airport mumbai = findAirportByCode("BOM");
        Airport bangalore = findAirportByCode("BLR");
        Airport kolkata = findAirportByCode("CCU");
        Airport chennai = findAirportByCode("MAA");
        Airport hyderabad = findAirportByCode("HYD");
        
        // Delhi to other hubs
        createRoute(delhi, mumbai);
        createRoute(delhi, bangalore);
        createRoute(delhi, kolkata);
        createRoute(delhi, chennai);
        createRoute(delhi, hyderabad);
        
        // Mumbai to other hubs
        createRoute(mumbai, bangalore);
        createRoute(mumbai, kolkata);
        createRoute(mumbai, chennai);
        createRoute(mumbai, hyderabad);
        
        // Bangalore to other hubs
        createRoute(bangalore, kolkata);
        createRoute(bangalore, chennai);
        createRoute(bangalore, hyderabad);
        
        // Kolkata to other hubs
        createRoute(kolkata, chennai);
        createRoute(kolkata, hyderabad);
        
        // Chennai to Hyderabad
        createRoute(chennai, hyderabad);
    }
    
    private void connectRegionalAirports() {
        // Connect regional airports to their nearest hub
        Map<String, String> regionalConnections = new HashMap<>();
        regionalConnections.put("GOI", "BOM"); // Goa to Mumbai
        regionalConnections.put("PNQ", "BOM"); // Pune to Mumbai
        regionalConnections.put("COK", "BLR"); // Kochi to Bangalore
        regionalConnections.put("TRV", "BLR"); // Trivandrum to Bangalore
        regionalConnections.put("AMD", "DEL"); // Ahmedabad to Delhi
        regionalConnections.put("JAI", "DEL"); // Jaipur to Delhi
        regionalConnections.put("LKO", "DEL"); // Lucknow to Delhi
        regionalConnections.put("GAU", "CCU"); // Guwahati to Kolkata
        regionalConnections.put("PAT", "DEL"); // Patna to Delhi
        regionalConnections.put("IXC", "DEL"); // Chandigarh to Delhi
        regionalConnections.put("VNS", "DEL"); // Varanasi to Delhi
        regionalConnections.put("IDR", "DEL"); // Indore to Delhi
        regionalConnections.put("BDQ", "BOM"); // Vadodara to Mumbai
        regionalConnections.put("BHO", "DEL"); // Bhopal to Delhi
        regionalConnections.put("NAG", "BOM"); // Nagpur to Mumbai
        regionalConnections.put("IXB", "CCU"); // Bagdogra to Kolkata
        regionalConnections.put("IXZ", "CCU"); // Port Blair to Kolkata
        regionalConnections.put("SXR", "DEL"); // Srinagar to Delhi
        regionalConnections.put("IXJ", "DEL"); // Jammu to Delhi
        regionalConnections.put("IXA", "CCU"); // Agartala to Kolkata
        regionalConnections.put("IXD", "DEL"); // Allahabad to Delhi
        regionalConnections.put("IXR", "CCU"); // Ranchi to Kolkata
        regionalConnections.put("IXE", "BLR"); // Mangalore to Bangalore
        regionalConnections.put("IXM", "MAA"); // Madurai to Chennai
        regionalConnections.put("IXU", "BOM"); // Aurangabad to Mumbai
        regionalConnections.put("JDH", "DEL"); // Jodhpur to Delhi
        regionalConnections.put("UDR", "DEL"); // Udaipur to Delhi
        regionalConnections.put("RPR", "HYD"); // Raipur to Hyderabad
        regionalConnections.put("HBX", "BLR"); // Hubli to Bangalore
        regionalConnections.put("STV", "BOM"); // Surat to Mumbai
        regionalConnections.put("BBI", "CCU"); // Bhubaneswar to Kolkata
        regionalConnections.put("CJB", "MAA"); // Coimbatore to Chennai
        regionalConnections.put("TIR", "MAA"); // Tirupati to Chennai
        regionalConnections.put("VTZ", "HYD"); // Visakhapatnam to Hyderabad
        
        for (Map.Entry<String, String> entry : regionalConnections.entrySet()) {
            Airport regional = findAirportByCode(entry.getKey());
            Airport hub = findAirportByCode(entry.getValue());
            if (regional != null && hub != null) {
                createRoute(regional, hub);
                createRoute(hub, regional);
            }
        }
    }
    
    private void addRandomRoutes() {
        // Add some additional random routes for more connectivity
        int additionalRoutes = airports.size() * 2;
        for (int i = 0; i < additionalRoutes; i++) {
            Airport source = airports.get(random.nextInt(airports.size()));
            Airport destination;
            do {
                destination = airports.get(random.nextInt(airports.size()));
            } while (destination.equals(source) || routeExists(source, destination));
            
            createRoute(source, destination);
        }
    }
    
    private void createRoute(Airport source, Airport destination) {
        String flightNum = generateFlightNumber();
        Flight flight = flightService.getFlight(flightNum);
        if (flight == null) {
            flight = new Flight(flightNum, getRandomAirline());
            flightService.addFlight(flightNum, flight.getAirlineName());
        }
        
        double distance = DistanceCalculator.calculateDistance(source, destination);
        int duration = (int)(distance / 800 * 60) + 30; // Approx flight time based on distance (800 km/h) + 30 min buffer
        double fare = calculateFare(distance);
        
        Route route = new Route(source, destination, flight, duration, fare, distance);
        addRouteToMap(route);
    }
    
    private double calculateFare(double distance) {
        // Base fare + distance-based fare (₹10 per km) + random variation
        return 1000 + (distance * 10) + (random.nextInt(2000) - 1000);
    }
    
    private void addRouteToMap(Route route) {
        routeMap.computeIfAbsent(route.getSource(), k -> new ArrayList<>()).add(route);
    }
    
    private void ensureConnectivity() {
        // Make sure every airport is reachable from every other airport
        // Using a minimum spanning tree approach
        Set<Airport> connected = new HashSet<>();
        Queue<Airport> toProcess = new LinkedList<>();
        
        // Start with Delhi as the central hub
        Airport delhi = findAirportByCode("DEL");
        connected.add(delhi);
        toProcess.add(delhi);
        
        while (!toProcess.isEmpty()) {
            Airport current = toProcess.poll();
            for (Airport airport : airports) {
                if (!connected.contains(airport)) {
                    if (!routeExists(current, airport)) {
                        createRoute(current, airport);
                    }
                    if (!routeExists(airport, current)) {
                        createRoute(airport, current);
                    }
                    connected.add(airport);
                    toProcess.add(airport);
                }
            }
        }
    }

    public Airport findAirportByCode(String code) {
        for (Airport airport : airports) {
            if (airport.getCityCode().equalsIgnoreCase(code)) {
                return airport;
            }
        }
        return null;
    }

    public boolean routeExists(Airport source, Airport destination) {
        if (!routeMap.containsKey(source)) return false;
        for (Route route : routeMap.get(source)) {
            if (route.getDestination().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public List<Route> getDirectRoutes(Airport source, Airport destination) {
        List<Route> directRoutes = new ArrayList<>();
        if (routeMap.containsKey(source)) {
            for (Route route : routeMap.get(source)) {
                if (route.getDestination().equals(destination)) {
                    directRoutes.add(route);
                }
            }
        }
        return directRoutes;
    }

    public List<List<Route>> findConnectingRoutes(Airport source, Airport destination) {
        List<List<Route>> connectingRoutes = new ArrayList<>();
        
        if (!routeMap.containsKey(source)) {
            return connectingRoutes;
        }
        
        // Check for one-stop connections
        for (Route firstLeg : routeMap.get(source)) {
            if (firstLeg.getDestination().equals(destination)) {
                continue; // Skip direct flights
            }
            
            for (Route secondLeg : routeMap.getOrDefault(firstLeg.getDestination(), Collections.emptyList())) {
                if (secondLeg.getDestination().equals(destination)) {
                    List<Route> connection = new ArrayList<>();
                    connection.add(firstLeg);
                    connection.add(secondLeg);
                    connectingRoutes.add(connection);
                }
            }
        }
        
        return connectingRoutes;
    }

    public List<Airport> getAllAirports() {
        return new ArrayList<>(airports);
    }

    private String generateFlightNumber() {
        String[] prefixes = {"AI", "6E", "SG", "UK", "G8"};
        return prefixes[random.nextInt(prefixes.length)] + (100 + random.nextInt(900));
    }

    private String getRandomAirline() {
        String[] airlines = {"Air India", "IndiGo", "SpiceJet", "Vistara", "GoAir"};
        return airlines[random.nextInt(airlines.length)];
    }
}
