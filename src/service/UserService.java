package service;

import java.util.*;
import java.util.stream.Collectors;

import helpers.ConsoleStyles;
import model.*;

/**
 * A User related data storage class.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class UserService {

    private static final UserService reference = new UserService();

    private final Map<String, IUser> users = new HashMap<>();

    public static UserService getInstance() {
        return reference;
    }

    /**
     * Returns a collection comprising all Customers sorted by last name.
     *
     * @return Collection<IUser>
     */
    public Collection<IUser> getAllCustomers() {

        return users.values().stream().filter(u -> UserType.CUSTOMER.equals(u.getUserType())).sorted().collect(Collectors.toList());

    }


    /**
     * Returns a collection comprising all admin users sorted by last name.
     *
     * @return Collection<IUser>
     */
    public Collection<IUser> getAllAdmin() {

        return users.values().stream().filter(u -> UserType.ADMIN.equals(u.getUserType())).sorted().collect(Collectors.toList());
    }

    /**
     * Returns the Customer User object associated with the email String.
     *
     * @param customerEmail String customer email.
     * @return Customer
     */
    public Customer getCustomer(String customerEmail) {
        return (Customer) users.get(customerEmail);
    }

    /**
     * Returns the Admin User object associated with the email String.
     *
     * @param adminEmail String admin email.
     * @return Admin
     */
    public Admin getAdmin(String adminEmail) {
        return (Admin) users.get(adminEmail);
    }

    /**
     * Adds a new Customer object.
     *
     * @param firstName Customer first name.
     * @param lastName  Customer last name.
     * @param email     Customer email.
     * @param password  Customer password.
     */
    public void addCustomer(String firstName, String lastName, String email, String password) {

        try {

            if (checkUserExists(email)) {
                Customer customer = new Customer(firstName, lastName, email, password);
                users.put(customer.getEmail(), customer);
            } else {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Email already exists, account not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);
        }

    }

    /**
     * Adds a new Admin object.
     *
     * @param firstName Admin first name.
     * @param lastName  Admin last name.
     * @param email     Admin email.
     * @param password  Admin password.
     */
    public void addAdmin(String firstName, String lastName, String email, String password) {

        try {

            if (checkUserExists(email)) {
                Admin adminUser = new Admin(firstName, lastName, email, password);
                users.put(adminUser.getEmail(), adminUser);
            } else {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Email already exists, account not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);
        }

    }

    /**
     * Checks the User collection to see if the email String supplied is already indexed.
     *
     * @param email User email to check exists.
     * @return boolean
     */
    public boolean checkUserExists(String email) {
        return !users.containsKey(email);
    }

    /**
     * Authenticates Admin users based on String email and String password provided.
     *
     * @param email    Admin email to authenticate.
     * @param password Admin password to authenticate.
     * @return boolean
     */
    public boolean authenticateAdmin(String email, String password) {
        if (users.containsKey(email)) {
            if (getAdmin(email).getPassword().equals(password)) {
                return getAdmin(email).isAdmin();
            }
        }
        return false;
    }

    /**
     * Authenticates Customer Users based on String email and String password provided.
     *
     * @param email    Customer email to authenticate.
     * @param password Customer password to authenticate.
     * @return boolean
     */
    public boolean authenticateCustomer(String email, String password) {
        if (users.containsKey(email)) {
            return getCustomer(email).getPassword().equals(password);
        }
        return false;
    }

    /**
     * Helper method to print formatted column header for lists of this type.
     */
    public String userColumnHeader = ConsoleStyles.CYAN_BOLD +
            String.format("%-33.31s", "Last Name") +
            String.format("%-22.20s", "First Name") +
            String.format("%-14.12s", "Password") +
            "Email" + ConsoleStyles.RESET;


}
