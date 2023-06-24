package service;

import helpers.ConsoleStyles;
import model.*;

import java.util.*;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A Reservation related data storage class.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class ReservationService {

    private static final ReservationService reference = new ReservationService();

    private final Set<Reservation> reservations = new HashSet<>();

    public static ReservationService getInstance() {
        return reference;
    }

    /**
     * Builds a Reservation from Customer Object, IRoom Object and String inputs for check in and check out, and then adds to the data store.
     *
     * @param customer Customer for Reservation.
     * @param room Room for Reservation.
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     */
    public void addReservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

        boolean reservationResult;

        try {
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

            reservationResult = reservations.add(reservation);

            if (!reservationResult) {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Conflicting reservation exists, reservation not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);
        }


    }

    /**
     * Builds a Reservation from String inputs, and then adds to the data store.
     *
     * @param email String Customer email.
     * @param roomNumber String Room number.
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     */
    public void addReservation(String email, int roomNumber, Date checkInDate, Date checkOutDate) {


        Customer reservationCustomer = UserService.getInstance().getCustomer(email);
        IRoom room = RoomService.getInstance().getRoom(roomNumber);

        this.addReservation(reservationCustomer, room, checkInDate, checkOutDate);

    }

    /**
     * Finds Rooms based on Check in and Check out dates alone.
     *
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        Calendar checkIn = Calendar.getInstance();
        checkIn.setTime(checkInDate);

        Calendar checkOut = Calendar.getInstance();
        checkOut.setTime(checkOutDate);

        List<IRoom> availableRooms = new ArrayList<>(checkRoomAvailability(checkIn, checkOut));

        Collections.sort(availableRooms);

        return availableRooms;
    }

    /**
     * Finds Rooms based on Check in and Check out dates, as well as RoomType.
     *
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     * @param roomType RoomType to find.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomType roomType) {

        Calendar checkIn = Calendar.getInstance();
        checkIn.setTime(checkInDate);

        Calendar checkOut = Calendar.getInstance();
        checkOut.setTime(checkOutDate);

        List<IRoom> availableRooms = new ArrayList<>(checkRoomAvailability(checkIn, checkOut));

        availableRooms.removeIf(r -> (r.getRoomType() != roomType));

        Collections.sort(availableRooms);

        return availableRooms;

    }

    /**
     * Finds Rooms based on Check in and Check out dates, as well as RoomValue.
     *
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     * @param roomValue RoomValue to find.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomValue roomValue) {

        Calendar checkIn = Calendar.getInstance();
        checkIn.setTime(checkInDate);

        Calendar checkOut = Calendar.getInstance();
        checkOut.setTime(checkOutDate);

        List<IRoom> availableRooms = new ArrayList<>(checkRoomAvailability(checkIn, checkOut));

        availableRooms.removeIf(r -> (r.getRoomValue() != roomValue));

        Collections.sort(availableRooms);

        return availableRooms;

    }

    /**
     * Finds Rooms based on Check in and Check out dates, as well as RoomValue & RoomType.
     *
     * @param checkInDate Date check in date.
     * @param checkOutDate Date check out date.
     * @param roomValue RoomValue to find.
     * @param roomType RoomType to find.
     * @return Collection<IRoom>
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, RoomValue roomValue, RoomType roomType) {

        Calendar checkIn = Calendar.getInstance();
        checkIn.setTime(checkInDate);

        Calendar checkOut = Calendar.getInstance();
        checkOut.setTime(checkOutDate);

        List<IRoom> availableRooms = new ArrayList<>(checkRoomAvailability(checkIn, checkOut));

        availableRooms.removeIf(r -> (r.getRoomValue() != roomValue));
        availableRooms.removeIf(r -> (r.getRoomType() != roomType));

        Collections.sort(availableRooms);

        return availableRooms;

    }

    /**
     * Strips out rooms with existing reservation that conflict with the reservation request.
     *
     * @param checkIn Calendar check in date.
     * @param checkOut Calendar check out date.
     * @return Collection<IRoom>
     */
    Collection<IRoom> checkRoomAvailability(Calendar checkIn, Calendar checkOut) {
        Collection<IRoom> availableRooms = RoomService.getInstance().getAllRooms();

        for (Reservation reservation : reservations) {

            if (checkIn.getTime().equals(reservation.getCheckInDate()) || checkIn.getTime().equals(reservation.getCheckOutDate()) || (checkIn.getTime().after(reservation.getCheckInDate())
                    && checkIn.getTime().before(reservation.getCheckOutDate())
                    || checkOut.getTime().before(reservation.getCheckOutDate())
                    && checkOut.getTime().after(reservation.getCheckInDate()))
            ) {
                availableRooms.remove(reservation.getRoom());
            }

        }
        return availableRooms;
    }

    /**
     * Gets a list of reservations for a Customer.
     *
     * @param customer Customer for which to return reservations.
     * @return Collection<Reservation>
     */
    public Collection<Reservation> getCustomerReservations(Customer customer) {
        return reservations.stream().filter(u -> customer.getEmail().equals(u.getCustomer().getEmail())).sorted().collect(Collectors.toList());
    }

    /**
     * Gets a list of All reservations.
     *
     * @return Collection<Reservation>
     */
    public Collection<Reservation> getAllReservations() {

        List<Reservation> allReservations = new ArrayList<>(reservations);

        Collections.sort(allReservations);

        return allReservations;

    }

    /**
     * Helper method to print formatted column header for lists of this type.
     */
    public String reservationColumnHeader = ConsoleStyles.CYAN_BOLD + String.format("%-15.13s", "Check In") +
            String.format("%-15.13s", "Check Out") +
            String.format("%-25.23s", "Room") +
            "Customer" +
            ConsoleStyles.RESET;

}
