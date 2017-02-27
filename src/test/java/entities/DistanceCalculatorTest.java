package entities;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for Distance Calculator
 *
 * Created by Sofia on 2/24/17.
 */
public class DistanceCalculatorTest {

    private DistanceCalculator distanceCalculator;
    private static final double DELTA = 1e-15;

    private static double latitudeRef = 56.02391;
    private static double longtitudeRef = 8.72631;

    private static double latitude1 = 87.019293;
    private static double longtitude1 = -9.029341;
    private static double expectedDistance1Vincenty = 3463616.574134322;
    private static double expectedDistance1Haversine= 3463616.574134322;

    private static double latitude2 = -15.12332;
    private static double longtitude2 = -50.0000;
    private static double expectedDistance2Vincenty = 9601371.313523019;
    private static double expectedDistance2Haversine= 9601371.313523019;

    private static double latitude3 = 12.304934;
    private static double longtitude3 = -12.32132;
    private static double expectedDistance3Vincenty = 5188499.595114667;
    private static double expectedDistance3Haversine= 5188499.595114667;

    private static double latitude4 = 65.32121;
    private static double longtitude4 = -27.32123;
    private static double expectedDistance4Vincenty = 2176972.051253267;
    private static double expectedDistance4Haversine= 2176972.051253267;


    @Before
    public void initCalculator(){
        try {
            distanceCalculator = new DistanceCalculator(latitudeRef,longtitudeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calculateVincentyDistance1() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude1,longtitude1);
        assertEquals (expectedDistance1Vincenty, distance, DELTA );
    }

    @Test
    public void calculateHaversineDistance1() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude1,longtitude1);
        assertEquals (expectedDistance1Haversine, distance, DELTA );
    }

    @Test
    public void calculateVincentyDistance2() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude2,longtitude2);
        assertEquals (expectedDistance2Vincenty, distance, DELTA );
    }

    @Test
    public void calculateHaversineDistance2() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude2,longtitude2);
        assertEquals (expectedDistance2Haversine, distance, DELTA );
    }

    @Test
    public void calculateVincentyDistance3() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude3,longtitude3);
        assertEquals (expectedDistance3Vincenty ,distance , DELTA );
    }

    @Test
    public void calculateHaversineDistance3() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude3,longtitude3);
        assertEquals (expectedDistance3Haversine, distance, DELTA );
    }

    @Test
    public void calculateVincentyDistance4() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude4,longtitude4);
        assertEquals (expectedDistance4Vincenty ,distance , DELTA );
    }

    @Test
    public void calculateHaversineDistance4() throws Exception {
        Assume.assumeNotNull(distanceCalculator);
        double distance = distanceCalculator.calculateVincentyDistance(latitude4,longtitude4);
        assertEquals (expectedDistance4Haversine, distance, DELTA );
    }
}