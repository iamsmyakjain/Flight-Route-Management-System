package service;

import model.Airport;

public class DistanceCalculator {
    // Haversine formula to calculate distance between two points on Earth
    public static double calculateDistance(Airport a1, Airport a2) {
        double lat1 = a1.getLatitude();
        double lon1 = a1.getLongitude();
        double lat2 = a2.getLatitude();
        double lon2 = a2.getLongitude();
        
        final int R = 6371; // Radius of the earth in km
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return R * c; // Distance in km
    }
}
