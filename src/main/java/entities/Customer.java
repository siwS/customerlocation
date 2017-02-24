package entities;

/**
 * Customer Entity
 *
 * Created by Sofia on 21/02/17.
 */

public class Customer implements Comparable {

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
        return "User Id: " + user_id + " Customer Name: " + name;
    }

    @Override
    public int compareTo(Object o) {

        Customer customer = ((Customer)o);

        if (this.user_id < customer.user_id) {
            return -1;
        }
        else if (this.user_id == customer.user_id) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {

        Customer customer = ((Customer)o);

        return this.getName().equals(customer.getName())
                && this.getLatitude() == customer.getLatitude()
                && this.getLongitude() == customer.getLongitude()
                && this.getUser_id() == customer.getUser_id();
    }
}
