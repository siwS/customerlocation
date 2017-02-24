package runtime;

import entities.Customer;
import org.junit.Test;

import java.util.List;


/**
 * Tests on Solution Class
 * Created by sofia.tzima on 2/24/17.
 */
public class SolutionTest {

    private static String customerFile1 = "src/main/resources/testCustomerLocations1";
    private static String customerFile2 = "src/main/resources/testCustomerLocations2";

    private static String expectedCustomers1 = "src/main/resources/testExpectedCustomers1";
    private static String expectedCustomers2 = "src/main/resources/testExpectedCustomers2";

    private static double maxDistance = 10000000;

    @Test
    public void parseAndCalculateInviteList1() throws Exception {
        List<Customer> customerList = Solution.parseAndCalculateInviteList(customerFile1, maxDistance);
        List<Customer> expectedCustomerList = buildExpectedCustomerList1();

        if (!customerList.equals(expectedCustomerList))
            assert(false);
    }

    @Test
    public void parseAndCalculateInviteList2() throws Exception {
        List<Customer> customerList = Solution.parseAndCalculateInviteList(customerFile2, maxDistance);
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