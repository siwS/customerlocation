package runtime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Config;
import entities.Customer;
import entities.DistanceCalculator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main Solution Class
 *
 * Created by sofia on 21/02/17.
 */
public class Solution {

    private final static Logger logger = Logger.getLogger(Solution.class);

    private static String encoding = "UTF-8";

    public static void main(String[] args){

        double referenceLat = Config.getDouble("reference.latitude" , -200);
        double referenceLong = Config.getDouble("reference.longtitude" , -200);
        double maxDistance = Config.getDouble("max.distance",0 );

        String customerFile = Config.get("customer.file");

        List<Customer> customerList = parseAndCalculateInviteList(customerFile, maxDistance, referenceLat, referenceLong);

        if (customerList != null && customerList.size() > 0) {
            System.out.println("List of customers found within " + maxDistance + " m from coordinates (" + referenceLat + "):(" + referenceLong+")");
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
        }
        else {
            System.out.println("No customers found within distance of " + maxDistance + " m from coordinates (" + referenceLat + "):(" + referenceLong+")");
        }
    }


    /**
     * Gets a json input file, parses the Customers in json format,
     * filters out the Customers who are not within a max distance and sorts the List.
     * @param inputFile json file representing the Customers
     * @param maxDistance max Distance
     * @return sorted List of Customers that are within the max distance
     */
    public static List<Customer> parseAndCalculateInviteList(String inputFile, double maxDistance, double referenceLat, double referenceLong){

        List<Customer> customerList = parseCustomersList(inputFile);

        customerList = filterCustomersByDistance(customerList, maxDistance, referenceLat, referenceLong);

        if (customerList != null) {
            Collections.sort(customerList);
        }

        return customerList;
    }

    /**
     * Parses a json file that represents the List of the Customers
     * @param inputFile json file representing the Customers
     * @return List of Customers
     */
    public static List<Customer> parseCustomersList(String inputFile) {

        File initialFile = new File(inputFile);
        List<Customer> customerList = new ArrayList<>();

        try {

            InputStream customerStream = new FileInputStream(initialFile);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    customerStream, encoding));

            Gson gson = new GsonBuilder().create();
            String line;

            while ((line = reader.readLine()) != null) {
                Customer p = gson.fromJson(line, Customer.class);
                customerList.add(p);
            }

            return customerList;

        } catch (IOException e) {
            logger.error("Exception when parsing Customers list from file \"" + initialFile + " \"" , e);
            return new ArrayList<>();
        }
    }

    /**
     * Filters out Customers who are not within a max distance
     * @param customerList customer list
     * @param maxDistance max distance
     * @return list of customers within max distance
     */
    private static List<Customer> filterCustomersByDistance(List<Customer> customerList, double maxDistance, double referenceLat, double referenceLong) {

        if (customerList == null)
            return null;

        List<Customer> customerListWithinMaxDistance = new ArrayList<>();

        DistanceCalculator distanceCalculator;
        try {
            distanceCalculator = new DistanceCalculator(referenceLat, referenceLong);
        } catch (Exception e) {
            logger.error("Error when creating Distance Calculators for values: " + referenceLat + " and " + referenceLong, e);
            return customerListWithinMaxDistance; // return empty list
        }

        for (Customer customer : customerList) {
            double distance = distanceCalculator.calculateHaversineDistance(customer.getLatitude(),customer.getLongitude());

            if (distance <= maxDistance) {
                customerListWithinMaxDistance.add(customer);
            }
        }

        return customerListWithinMaxDistance;
    }
}
