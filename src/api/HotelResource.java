package api;

import model.*;
import service.ReservationService;
import service.RoomService;
import service.UserService;

import java.util.Collection;
import java.util.Date;

/**
 * This class mediates requests for standard users between menu package classes and the service package classes.
 *
 * @author Rich Davies https://github.com/rljdavies
 */
public class HotelResource {

    private static final HotelResource reference = new HotelResource();

    public static HotelResource getInstance() {
        return reference;
    }

    /**
     * Gets a Customer based on their email.
     *
     * @param email String customer email.
     * @return Customer
     */
    public Customer getCustomer(String email) {
        return UserService.getInstance().getCustomer(email);
    }

    /**
     * Adds Customer based on String inputs.
     *
     * @param email String customer email.
     * @param firstName String customer first name.
     * @param lastName String customer last name.
     * @param password String customer password.
     */
    public void addCustomer(String firstName, String lastName, String email, String password) {
        UserService.getInstance().addCustomer(firstName, lastName, email, password);
    }

    /**
     * Adds Reservation using a Customer email rather than a Customer object.
     *
     * @param customerEmail String customer email.
     * @param room Room object for reservation.
     * @param checkInDate Date check in date for reservation.
     * @param checkOutDate Date check out date for reservation.
     */
    public void addReservation(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer reservationCustomer = UserService.getInstance().getCustomer(customerEmail);
        ReservationService.getInstance().addReservation(reservationCustomer, room, checkInDate, checkOutDate);
    }

    /**
     * Gets a list of reservations for a Customer.
     *
     * @param customerEmail String customer email.
     * @return Collection<Reservation>
     */
    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = UserService.getInstance().getCustomer(customerEmail);
        return ReservationService.getInstance().getCustomerReservations(customer);
    }

    /**
     * Authenticates a Customer so they may see their reservations.
     *
     * @param email String customer email.
     * @param password String customer password.
     * @return boolean
     */
    public boolean authenticateCustomer(String email, String password) {
        return UserService.getInstance().authenticateCustomer(email, password);
    }

    /**
     * Gets a RoomValue from a shortened String.
     *
     * @param roomValueInput String to find room value.
     * @return RoomValue
     */
    public RoomValue getRoomValueFromString(String roomValueInput) {
        return RoomService.getInstance().getRoomValueFromString(roomValueInput);
    }

    /**
     * Gets a RoomType from a shortened String.
     *
     * @param roomTypeInput String to find room type.
     * @return RoomType
     */
    public RoomType getRoomTypeFromString(String roomTypeInput) {
        return RoomService.getInstance().getRoomTypeFromString(roomTypeInput);
    }

    /**
     * Finds available Rooms based on dates and RoomType.
     *
     * @param checkInDate Date check in date for reservation.
     * @param checkOutDate Date check out date for reservation.
     * @param roomType RoomType for reservation.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomType roomType) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate, roomType);
    }

    /**
     * Finds available Rooms based on dates and RoomValue.
     *
     * @param checkInDate Date check in date for reservation.
     * @param checkOutDate Date check out date for reservation.
     * @param roomValue RoomValue for reservation.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomValue roomValue) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate, roomValue);
    }

    /**
     * Finds available Rooms based on dates, RoomValue and RoomType.
     *
     * @param checkInDate Date check in date for reservation.
     * @param checkOutDate Date check out date for reservation.
     * @param roomValue Find Rooms of this RoomValue.
     * @param roomType Find Rooms of this RoomType.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomValue roomValue, RoomType roomType) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate, roomValue, roomType);
    }

    /**
     * Finds available Rooms based on dates alone.
     *
     * @param checkInDate Date check in date for reservation.
     * @param checkOutDate Date check out date for reservation.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
    }

    /**
     * Gets a Room based on String input of Room number.
     *
     * @param reservationRoomNumberInput String Room number.
     * @return IRoom
     */
    public IRoom getRoom(int reservationRoomNumberInput) {
        return RoomService.getInstance().getRoom(reservationRoomNumberInput);
    }

    /**
     * Helper method for setting up column headers when listing reservations.
     *
     * @return String
     */
    public String reservationColumnHeader() {
        return ReservationService.getInstance().reservationColumnHeader;
    }

    /**
     * Helper method for setting up column headers when listing rooms.
     *
     * @return String
     */
    public String roomColumnHeader() {
        return RoomService.getInstance().roomColumnHeader;
    }

    /**
     * Helper method for setting up column headers when listing Users.
     *
     * @return String
     */
    public String userColumnHeader() {
        return UserService.getInstance().userColumnHeader;
    }


}