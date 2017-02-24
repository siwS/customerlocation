package runtime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Customer;
import entities.DistanceCalculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Solution Class
 * Created by sofia on 21/02/17.
 */
public class Solution {


    private static String customerFile = "src/main/resources/gistfile1.txt";
    private static String encoding = "UTF-8";

    private static double dublinOfficeLatitude = 53.3393;
    private static double dublinOfficeLongtitude = -6.25768410;



    public static void main(String[] args){

        File initialFile = new File(customerFile);
        List<Customer> customerList = new ArrayList<>();
        DistanceCalculator distanceCalculator = new DistanceCalculator(dublinOfficeLatitude,dublinOfficeLongtitude);

        try {

            InputStream customerStream = new FileInputStream(initialFile);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    customerStream, encoding));

            Gson gson = new GsonBuilder().create();
            String line;

            while ((line = reader.readLine()) != null) {

                Customer p = gson.fromJson(line, Customer.class);

                double distance = distanceCalculator.calculateVincentyDistance(p.getLatitude(),p.getLongitude());
                if (distance  <= 100) {
                    customerList.add(p);
                }


                System.out.println(p);
                System.out.println(distance);
            }

            System.out.println(customerList.size());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
