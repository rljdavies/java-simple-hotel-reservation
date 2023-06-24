package api;

import model.*;
import service.ReservationService;
import service.RoomService;
import service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * This class mediates requests for admin users between menu package classes and the service package classes.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class AdminResource {

    private static final AdminResource reference = new AdminResource();

    public static AdminResource getInstance() {
        return reference;
    }

    /**
     * Gets a Customer based on their String email.
     *
     * @param email String email.
     * @return Customer
     */
    public Customer getCustomer(String email) {
        return HotelResource.getInstance().getCustomer(email);
    }

    /**
     * Adds a Room
     *
     * @param roomNumber Room number.
     * @param price Room price.
     * @param roomType Room type.
     * @param roomValue Room value.
     */
    public void addRoom(int roomNumber, double price, RoomType roomType, RoomValue roomValue) {
        RoomService.getInstance().addRoom(roomNumber, price, roomType, roomValue);
    }

    /**
     * Adds a Free Room
     *
     * @param roomNumber Room number.
     * @param roomType Room type.
     */
    public void addFreeRoom(int roomNumber, RoomType roomType) {
        RoomService.getInstance().addFreeRoom(roomNumber, roomType);
    }

    /**
     * Adds a Paid Room
     *
     * @param roomNumber Room number.
     * @param roomType Room type.
     */
    public void addPaidRoom(int roomNumber, double price, RoomType roomType) {
        RoomService.getInstance().addPaidRoom(roomNumber, price, roomType);

    }

    /**
     * Gets all Rooms.
     *
     * @return Collection<IRoom>
     */
    public Collection<IRoom> getAllRooms() {
        return RoomService.getInstance().getAllRooms();

    }

    /**
     * Gets all Customer Users.
     *
     * @return Collection<IUser>
     */
    public Collection<IUser> getAllCustomers() {
        return UserService.getInstance().getAllCustomers();

    }

    /**
     * gets all Admin Users.
     *
     * @return Collection<IUser>
     */
    public Collection<IUser> getAllAdmin() {
        return UserService.getInstance().getAllAdmin();

    }

    /**
     * Gets all Reservations.
     *
     * @return Collection<Reservation>
     */
    public Collection<Reservation> getAllReservations() {
        return ReservationService.getInstance().getAllReservations();
    }

    /**
     * @param firstName String first name.
     * @param lastName String last name.
     * @param email String email.
     * @param password String password.
     */
    public void addAdmin(String firstName, String lastName, String email, String password) {
        UserService.getInstance().addAdmin(firstName, lastName, email, password);
    }

    /**
     * Authenticates Admin Users.
     *
     * @param email String email.
     * @param password String password.
     * @return boolean
     */
    public boolean authenticateAdmin(String email, String password) {
        return UserService.getInstance().authenticateAdmin(email, password);
    }

    /**
     * Method to populate the application with sample data and help testing.
     */
    public void populateTestData() {

        populateTestRoomData();
        populateTestCustomerData();
        try {
            populateTestReservationData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to populate the application with sample Room data and help testing.
     */
    private void populateTestRoomData() {
        RoomService.getInstance().addRoom(101, 0.00, RoomType.SINGLE, RoomValue.FREE);
        RoomService.getInstance().addRoom(102, 0.00, RoomType.SINGLE, RoomValue.FREE);
        RoomService.getInstance().addRoom(103, 79.99, RoomType.SINGLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(104, 79.99, RoomType.SINGLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(105, 0.00, RoomType.DOUBLE, RoomValue.FREE);
        RoomService.getInstance().addRoom(106, 99.99, RoomType.DOUBLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(107, 109.99, RoomType.DOUBLE, RoomValue.PAID);

        RoomService.getInstance().addRoom(201, 0.00, RoomType.SINGLE, RoomValue.FREE);
        RoomService.getInstance().addRoom(202, 0.00, RoomType.DOUBLE, RoomValue.FREE);
        RoomService.getInstance().addRoom(203, 179.99, RoomType.SINGLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(204, 209.99, RoomType.DOUBLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(205, 209.99, RoomType.DOUBLE, RoomValue.PAID);

        RoomService.getInstance().addRoom(301, 309.99, RoomType.DOUBLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(302, 279.99, RoomType.SINGLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(303, 309.99, RoomType.DOUBLE, RoomValue.PAID);

        RoomService.getInstance().addRoom(401, 405.99, RoomType.DOUBLE, RoomValue.PAID);
        RoomService.getInstance().addRoom(402, 405.99, RoomType.DOUBLE, RoomValue.PAID);

        RoomService.getInstance().addRoom(501, 479.99, RoomType.DOUBLE, RoomValue.PAID);
    }

    /**
     * Method to populate the application with sample Customer data and help testing.
     */
    private void populateTestCustomerData() {
        UserService.getInstance().addCustomer("Paul", "McGrath", "pm@villa.fc", "password");
        UserService.getInstance().addCustomer("Gordon", "Cowans", "gc@villa.fc", "password");
        UserService.getInstance().addCustomer("Gabby", "Agbonlahor", "ga@villa.fc", "password");
        UserService.getInstance().addCustomer("Peter", "Withe", "pw@villa.fc", "password");
        UserService.getInstance().addCustomer("Charlie", "Aitken", "ca@villa.fc", "password");
        UserService.getInstance().addCustomer("Pongo", "Waring", "pongo@villa.fc", "password");
        UserService.getInstance().addCustomer("Dalian", "Atkinson", "da@villa.fc", "password");
        UserService.getInstance().addCustomer("Ugo", "Ehiogu", "ue@villa.fc", "password");
        UserService.getInstance().addCustomer("Dean", "Saunders", "ds@villa.fc", "password");
        UserService.getInstance().addCustomer("Gareth", "Southgate", "gs@villa.fc", "password");
        UserService.getInstance().addCustomer("Dwight", "Yorke", "dy@villa.fc", "password");
        UserService.getInstance().addCustomer("Hannah", "Hampton", "hh@villa.fc", "password");
        UserService.getInstance().addCustomer("Rachel", "Daly", "rd@villa.fc", "password");
        UserService.getInstance().addCustomer("Mayumi", "Pacheco", "mp@villa.fc", "password");

        UserService.getInstance().addCustomer("Gabriel", "Batistuta", "gb@fiorentina.acf", "password");
        UserService.getInstance().addCustomer("Luca", "Toni", "lt@fiorentina.acf", "password");
        UserService.getInstance().addCustomer("Federico", "Chiesa", "fc@fiorentina.acf", "password");
        UserService.getInstance().addCustomer("Riccardo", "Montolivo", "rm@fiorentina.acf", "password");
        UserService.getInstance().addCustomer("Sergio", "Cervato", "sc@fiorentina.acf", "password");

        UserService.getInstance().addCustomer("Alfred", "Preissler", "ap@dortmund.fb", "password");

    }

    /**
     * Method to populate the application with sample Reservation data and help testing.
     */
    private void populateTestReservationData() throws ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.MONTH, Calendar.APRIL);

        cal.set(Calendar.DAY_OF_MONTH, 23);
        Date checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 25);
        Date checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("ds@villa.fc", 101, checkIn, checkOut);

        cal.set(Calendar.DAY_OF_MONTH, 26);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 28);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("gc@villa.fc", 102, checkIn, checkOut);

        cal.set(Calendar.DAY_OF_MONTH, 29);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 30);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("da@villa.fc", 101, checkIn, checkOut);

        cal.set(Calendar.MONTH, Calendar.MAY);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 14);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("ca@villa.fc", 301, checkIn, checkOut );

        cal.set(Calendar.DAY_OF_MONTH, 4);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 7);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("dy@villa.fc", 401, checkIn, checkOut );

        cal.set(Calendar.DAY_OF_MONTH, 9);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 15);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("lt@fiorentina.acf", 401, checkIn, checkOut );

        cal.set(Calendar.DAY_OF_MONTH, 15);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("pm@villa.fc", 401, checkIn, checkOut );

        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 14);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 25);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("rd@villa.fc", 201, checkIn, checkOut );

        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 14);
        checkIn = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 26);
        checkOut = dateFormatter.parse(dateFormatter.format(cal.getTime()));
        ReservationService.getInstance().addReservation("ap@dortmund.fb", 201, checkIn, checkOut );

    }

}
