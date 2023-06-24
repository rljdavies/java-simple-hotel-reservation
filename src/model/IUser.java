package model;

/**
 * This interface defines what must be implemented by User.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public interface IUser extends Comparable<IUser> {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    UserType getUserType();

    void setUserType(UserType userType);

    boolean isAdmin();

    String toColumnString();

    boolean equals(Object o);

    int compareTo(IUser o);

    int hashCode();

}

