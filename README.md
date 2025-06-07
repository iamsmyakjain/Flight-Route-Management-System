A Flight Route Management System built purely in Java using Data Structures & Algorithms (DSA) to find optimal flight paths between Indian cities, including details like flight duration, fare, and stopovers.

Features:- Graph-Based Routing – Airports as nodes, flights as edges.
           Direct & Connecting Flights – Finds all possible routes.
           Flight Details – Displays fare, duration, airline, and distance.
           Shortest Path – Uses Dijkstra’s algorithm for optimal routes.
           Terminal-Based UI – Simple console interface.

Project Structure:

          FlightRouteManagementSystem/  
          ├── src/  
          │   ├── model/  
          │   │   ├── Airport.java  
          │   │   ├── Flight.java  
          │   │   └── Route.java  
          │   ├── service/  
          │   │   ├── FlightService.java  
          │   │   └── RouteService.java  
          │   │   ├── DistanceCalculator.java  
          │   ├── util/  
          │   │   ├── FlightRouteManager.java  
          ├── README.md  

How It Works:
Graph Representation
Airports → Nodes
Flight routes → Weighted Edges (distance, fare, duration)

User Input
Enter source and destination airport codes (e.g., DEL for Delhi).


Algorithm Execution
Dijkstra’s Algorithm → Finds cheapest/fastest route.
BFS → Lists all possible connecting flights.


Sample Input & Output:

Enter source airport code: DEL  
Enter destination airport code: BOM  

Output

DIRECT FLIGHTS (1 found):  
---------------------------------  
Air India (AI101) | DEL → BOM  
Distance: 1150 km | Duration: 2h 15m | Fare: ₹6500  

CONNECTING FLIGHTS (1 option):  
---------------------------------  
Option 1 via GOI:  
  IndiGo (6E202) | DEL → GOI | 2h | ₹4500  
  SpiceJet (SG301) | GOI → BOM | 1h | ₹3500  
  Total: 3h | ₹8000  


Algorithms Used:
Dijkstra’s	Shortest path (min fare/duration)
BFS	Explore all possible routes
HashMap Lookup	O(1) airport/flight searches

Contributing:
Fork the repo.
Create a branch (git checkout -b improve-feature).
Commit changes (git commit -m "Add new airports").
Push (git push origin improve-feature).
Open a Pull Request.
