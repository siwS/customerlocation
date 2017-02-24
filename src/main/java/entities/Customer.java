package entities;

/**
 * Customer Entity
 * Created by sofia on 21/02/17.
 */

public class Customer {

    private double latitude;
    private int user_id;
    private String name;
    private double longitude;


    public double getLatitude() {
        return latitude;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString(){
        return name + ": " + user_id;
    }

}
