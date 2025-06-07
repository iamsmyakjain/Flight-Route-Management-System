package model;

public class Airport {
    private String cityCode;
    private String cityName;
    private double latitude;
    private double longitude;

    public Airport(String cityCode, String cityName, double latitude, double longitude) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return cityName + " (" + cityCode + ")";
    }
}
