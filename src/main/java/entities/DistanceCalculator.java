package entities;

/**
 * This class calculates the distance between a point of reference
 * and another point on GPS coordinates
 *
 * It defines two methods to calculate distance, using the Haversine
 * formula and the Vincenty formula
 *
 * Created by sofia on 22/02/17.
 */
public class DistanceCalculator {

    private final int earthRadius;

    private final double latitudeRef;
    private final double latitudeRefRads;

    private final double longitudeRef;
    private final double longitudeRefRads;


    /**
     * Creates a distance calculator
     * @param latitudeRef reference point latitude
     * @param longitudeRef reference point longtitude
     */
    public DistanceCalculator(double latitudeRef, double longitudeRef) {
        this.latitudeRef = latitudeRef;
        this.latitudeRefRads = Math.toRadians(latitudeRef);

        this.longitudeRef = longitudeRef;
        this.longitudeRefRads = Math.toRadians(longitudeRef);

        // Radius of earth in meters
        earthRadius = 6371000;
    }

    /**
     * Calculates the distance using the Vincenty algorithm
     * @param latitude point latitude
     * @param longtitude point longtitude
     * @return distance in km
     */
    public double calculateVincentyDistance(double latitude, double longtitude) {

        Double dLat = Math.toRadians(latitude-this.latitudeRef);
        Double dLong = Math.toRadians(longtitude-this.longitudeRef);

        // calculate coordinates in radians
        latitude = Math.toRadians(latitude);
        longtitude = Math.toRadians(longtitude);

        // calculate nominator
        double nominator = Math.pow((Math.cos(latitude) * Math.sin(dLong)),2);

        nominator += Math.pow((Math.cos(this.latitudeRefRads) * Math.sin(latitude)
                        - Math.sin(latitudeRefRads) * Math.cos(latitude) * Math.cos(dLong)),2);

        nominator = Math.sqrt(nominator);

        // calculate denominator
        double denominator = Math.sin(this.latitudeRefRads) * Math.sin(latitude)
                + Math.cos(this.latitudeRefRads) * Math.cos(latitude) * Math.cos(dLong);

        return earthRadius * Math.atan(nominator / denominator);
    }

    /**
     * Calculates the distance using the Haversine algorithm
     * @param latitude point latitude
     * @param longtitude point longtitude
     * @return distance in km
     */
    public double calculateHaversineDistance(double latitude, double longtitude) {

        Double dLat = Math.toRadians(latitude-this.latitudeRef);
        Double dLong = Math.toRadians(longtitude-this.longitudeRef);

        latitude = Math.toRadians(latitude);
        longtitude = Math.toRadians(longtitude);

        double a = haversin(dLat) + Math.cos(this.latitudeRefRads)
                * Math.cos(latitude) * haversin(dLong);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }

    /**
     * Calculates the Haversin number
     * @param val number
     * @return Haversin number
     */
    private static double haversin(double val) {
        return Math.pow(Math.sin((val / (double)2)), 2);
    }
}
