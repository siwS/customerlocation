package entities;

/**
 * This class calculates the distance between a point of reference
 * and another point on GPS coordinates
 *
 * Created by sofia on 22/02/17.
 */
public class DistanceCalculator {

    private double latitudeReference;
    private double latitudeReferenceRadians;

    private double longitudeReference;
    private double longitudeReferenceRadians;

    int earthRadius;

    /**
     * Creates a distance calculator
     * @param latitudeReference reference point latitude
     * @param longitudeReference reference point longtitude
     */
    public DistanceCalculator(double latitudeReference, double longitudeReference) {
        this.latitudeReference = latitudeReference;
        this.latitudeReferenceRadians = Math.toRadians(latitudeReference);

        this.longitudeReference = longitudeReference;
        this.longitudeReferenceRadians = Math.toRadians(longitudeReference);
        earthRadius = 6371;
    }

    /**
     * Calculates the distance using the Vincenty algorithm
     * @param latitude point latitude
     * @param longtitude point longtitude
     * @return distance in km
     */
    public double calculateVincentyDistance(double latitude, double longtitude) {

        double dLat  = Math.toRadians((this.latitudeReference - latitude));
        double dLong = Math.toRadians((this.longitudeReference - longtitude));

        // calculate coordinates in radians
        latitude = Math.toRadians(latitude);
        longtitude = Math.toRadians(longtitude);

        // calculate nominator
        double nominator = Math.pow((Math.cos(latitude) * Math.sin(dLong)),2);

        nominator +=
                Math.pow((Math.cos(this.latitudeReferenceRadians) * Math.sin(latitude)
                        - Math.sin(latitude) * Math.cos(latitude) * Math.cos(dLong)),2);

        nominator = Math.sqrt(nominator);

        // calculate denominator
        double denominator = Math.sin(this.latitudeReferenceRadians) * Math.sin(latitude)
                + Math.cos(this.latitudeReferenceRadians) * Math.cos(latitude) * Math.cos(dLong);

        return earthRadius * Math.atan(nominator / denominator);
    }

    /**
     * Calculates the distance using the Haversine algorithm
     * @param latitude point latitude
     * @param longtitude point longtitude
     * @return distance in km
     */
    public double calculateHaversineDistance(double latitude, double longtitude) {

        double dLat  = Math.toRadians(this.latitudeReference - latitude);
        double dLong = Math.toRadians(this.longitudeReference - longtitude);

        latitude = Math.toRadians(latitude);
        longtitude = Math.toRadians(longtitude);

        double a = haversin(dLat) + Math.cos(latitude) * Math.cos(longtitude) * haversin(dLong);
        double c = 2 * Math.asin(Math.sqrt(a));

        return earthRadius * c;
    }

    /**
     * Calculates the haversin number
     * @param val number
     * @return haversin number
     */
    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
