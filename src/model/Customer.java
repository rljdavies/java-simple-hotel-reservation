package model;

/**
 * This class models a Customer by extending User.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class Customer extends User {

    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserType.CUSTOMER);
    }

}