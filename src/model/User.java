package model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This class implements IUser and is extended to represent both Customer users and Admin users.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see IUser
 * @see Customer
 * @see Admin
 */
public class User implements IUser, Comparable<IUser> {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserType userType;

    public User(String firstName, String lastName, String email, String password, UserType userType) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("ERROR: Email invalid!");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;

    }

    /**
     * Get the firstName on the User class.
     *
     * @return String
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstName on the User class
     *
     * @param firstName User first name.
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the lastName on the User class.
     *
     * @return String
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastName on the User class.
     *
     * @param lastName User last name.
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the email on the User class.
     *
     * @return String
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Set the email on the User class.
     *
     * @param email User email.
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password on the User class.
     *
     * @return String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Set the password on the User class.
     *
     * @param password User password.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the userType on the User class.
     *
     * @return UserType
     */
    @Override
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set the userType on the User class.
     *
     * @param userType Type of User.
     */
    @Override
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Returns true if the user is of type Admin, otherwise false.
     *
     * @return boolean
     */
    @Override
    public boolean isAdmin() {
        return userType.equals(UserType.ADMIN);
    }

    /**
     * Equals based on lastName.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return lastName.equals(user.lastName);
    }

    /**
     * Hashcode based on lastName.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName);
    }

    /**
     * CompareTo based on lastName.
     *
     * @return int
     */
    @Override
    public int compareTo(IUser o) {
        return lastName.compareTo(o.getLastName());
    }

    /**
     * Single line representation of object as string.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "First Name: " + String.format("%-31s", lastName) +
                "   Last Name: " + String.format("%-20s", firstName) +
                "   Password: " + String.format("%-12s", password) +
                "   Email: " + email;
    }

    /**
     * Formatted column representation of object for readability in lists.
     *
     * @return String
     */
    public String toColumnString() {
        return String.format("%-33.31s", lastName) +
                String.format("%-22.20s", firstName) +
                String.format("%-14.12s", password) +
                email;
    }

}









