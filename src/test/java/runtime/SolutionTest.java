package runtime;

import entities.Config;
import entities.Customer;
import org.junit.Test;

import java.util.List;


/**
 * Tests on Solution Class
 * Created by sofia.tzima on 2/24/17.
 */
public class SolutionTest {

    private static String customerFile1 = "src/test/resources/testCustomerLocations1";
    private static String customerFile2 = "src/test/resources/testCustomerLocations2";

    private static String expectedCustomers1 = "src/test/resources/testExpectedCustomers1";
    private static String expectedCustomers2 = "src/test/resources/testExpectedCustomers2";

    private static double maxDistance = 10000000;

    @Test
    public void parseAndCalculateInviteList1() throws Exception {
        double referenceLat = 53.3393;
        double referenceLong = -6.25768410;

        List<Customer> customerList = Solution.parseAndCalculateInviteList(customerFile1, maxDistance, referenceLat, referenceLong);
        List<Customer> expectedCustomerList = buildExpectedCustomerList1();

        if (!customerList.equals(expectedCustomerList))
            assert(false);
    }

    @Test
    public void parseAndCalculateInviteList2() throws Exception {
        double referenceLat = 53.3393;
        double referenceLong = -6.25768410;

        List<Customer> customerList = Solution.parseAndCalculateInviteList(customerFile2, maxDistance, referenceLat, referenceLong);
        List<Customer> expectedCustomerList = buildExpectedCustomerList2();

        if (!customerList.equals(expectedCustomerList))
            assert(false);
    }

    private List<Customer> buildExpectedCustomerList1() {
        return Solution.parseCustomersList(expectedCustomers1);
    }

    private List<Customer> buildExpectedCustomerList2() {
        return Solution.parseCustomersList(expectedCustomers2);
    }
}