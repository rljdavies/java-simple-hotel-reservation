package model;

/**
 * This class models an Admin by extending User.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserType.ADMIN);
    }

}
