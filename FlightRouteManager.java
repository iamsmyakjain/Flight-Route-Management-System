import model.Airport;
import model.Route;
import service.FlightService;
import service.RouteService;
import util.ConsoleColors;

import java.util.*;

public class FlightRouteManager {
    private RouteService routeService;
    private Scanner scanner;

    public FlightRouteManager() {
        FlightService flightService = new FlightService();
        this.routeService = new RouteService(flightService);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println(ConsoleColors.BLUE_BOLD + "============================================");
        System.out.println("    FLIGHT ROUTE MANAGEMENT SYSTEM    ");
        System.out.println("============================================" + ConsoleColors.RESET);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    searchFlights();
                    break;
                case 2:
                    displayAllAirports();
                    break;
                case 3:
                    System.out.println(ConsoleColors.GREEN + "Thank you for using the Flight Route Management System!" + ConsoleColors.RESET);
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n" + ConsoleColors.CYAN_BOLD + "MAIN MENU" + ConsoleColors.RESET);
        System.out.println("1. Search Flights");
        System.out.println("2. View All Airports");
        System.out.println("3. Exit");
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
    }

    private void displayAllAirports() {
        System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "LIST OF AIRPORTS" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Code\tCity Name" + ConsoleColors.RESET);
        System.out.println("----------------------------");
        for (Airport airport : routeService.getAllAirports()) {
            System.out.println(airport.getCityCode() + "\t" + airport.getCityName());
        }
    }

    private void searchFlights() {
        System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "FLIGHT SEARCH" + ConsoleColors.RESET);
        
        System.out.print("Enter source airport code: ");
        String sourceCode = scanner.nextLine().toUpperCase();
        Airport source = routeService.findAirportByCode(sourceCode);
        if (source == null) {
            System.out.println(ConsoleColors.RED + "Invalid source airport code!" + ConsoleColors.RESET);
            return;
        }
        
        System.out.print("Enter destination airport code: ");
        String destCode = scanner.nextLine().toUpperCase();
        Airport destination = routeService.findAirportByCode(destCode);
        if (destination == null) {
            System.out.println(ConsoleColors.RED + "Invalid destination airport code!" + ConsoleColors.RESET);
            return;
        }
        
        if (source.equals(destination)) {
            System.out.println(ConsoleColors.RED + "Source and destination cannot be the same!" + ConsoleColors.RESET);
            return;
        }
        
        System.out.println("\n" + ConsoleColors.GREEN_BOLD + "Searching flights from " + source + " to " + destination + ConsoleColors.RESET);
        
        // Check for direct flights
        List<Route> directRoutes = routeService.getDirectRoutes(source, destination);
        if (!directRoutes.isEmpty()) {
            System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "DIRECT FLIGHTS (" + directRoutes.size() + " found)" + ConsoleColors.RESET);
            for (Route route : directRoutes) {
                System.out.println(ConsoleColors.CYAN + route + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.RED + "\nNo direct flights available between " + source + " and " + destination + ConsoleColors.RESET);
        }
        
        // Check for connecting flights (now guaranteed to exist)
        List<List<Route>> connectingRoutes = routeService.findConnectingRoutes(source, destination);
        if (!connectingRoutes.isEmpty()) {
            System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "CONNECTING FLIGHT OPTIONS (" + connectingRoutes.size() + " available)" + ConsoleColors.RESET);
            
            // Sort by total duration
            connectingRoutes.sort(Comparator.comparingInt(
                routeList -> routeList.stream().mapToInt(Route::getDuration).sum()));
            
            for (int i = 0; i < Math.min(3, connectingRoutes.size()); i++) {
                List<Route> routeOption = connectingRoutes.get(i);
                System.out.println(ConsoleColors.PURPLE + "\nOption " + (i+1) + " via " + getConnectionPoints(routeOption) + ":" + ConsoleColors.RESET);
                
                int totalDuration = 0;
                double totalFare = 0;
                double totalDistance = 0;
                
                for (Route route : routeOption) {
                    System.out.println("  ➔ " + route);
                    totalDuration += route.getDuration();
                    totalFare += route.getFare();
                    totalDistance += route.getDistance();
                }
                
                System.out.println(ConsoleColors.GREEN_BRIGHT + 
                    "  Total Distance: " + String.format("%.1f", totalDistance) + " km" +
                    " | Total Duration: " + (totalDuration/60) + "h " + (totalDuration%60) + "m" +
                    " | Total Fare: ₹" + String.format("%.2f", totalFare) + ConsoleColors.RESET);
            }
        }
    }
    
    private String getConnectionPoints(List<Route> routes) {
        if (routes.size() == 1) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < routes.size() - 1; i++) {
            if (i > 0) sb.append(", ");
            sb.append(routes.get(i).getDestination().getCityCode());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new FlightRouteManager().start();
    }
}
