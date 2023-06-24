package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The Reservation class combines a Customer, Room and check in and check out dates to represent a hotel room booking.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see Customer
 * @see IRoom
 */
public class Reservation implements Comparable<Reservation> {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    /**
     * Get the customer on the Reservation class.
     *
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the customer on the Reservation class.
     *
     * @param customer the reservation Customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the room on the Reservation class.
     *
     * @return IRoom
     */
    public IRoom getRoom() {
        return room;
    }

    /**
     * Set the room on the Reservation class.
     *
     * @param room the reservation Room.
     */
    public void setRoom(IRoom room) {
        this.room = room;
    }

    /**
     * Get the check in date on the Reservation class.
     *
     * @return Date
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Set the check in date on the Reservation class.
     *
     * @param checkInDate Check in date for Reservation.
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Get the check out date on the Reservation class.
     *
     * @return Date
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Set the check out date on the Reservation class.
     *
     * @param checkOutDate Check in date for Reservation.
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Single line representation of object as string.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Customer: " + customer + System.lineSeparator() +
                "Room: " + room + System.lineSeparator() +
                "Check In: " + dateFormatter.format(checkInDate) + System.lineSeparator() +
                "Check Out: " + dateFormatter.format(checkOutDate);
    }

    /**
     * Formatted column representation of object for readability in lists.
     *
     * @return String
     */
    public String toColumnString() {
        String roomSummary = room.getRoomNumber() + " (" + room.getRoomType() + ") ";
        if (room.isFree()) {
            roomSummary += "FREE";
        } else {
            roomSummary += "" + room.getRoomPrice();
        }

        String customerSummary = customer.getLastName() + ", " + customer.getFirstName() +
                " (" + customer.getEmail() + ")";

        return String.format("%-15.13s", dateFormatter.format(checkInDate)) +
                String.format("%-15.13s", dateFormatter.format(checkOutDate)) +
                String.format("%-25.25s", roomSummary) +
                customerSummary;
    }

    /**
     * Hashcode based on roomNumber, customer email, check in and checkout.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
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
        Reservation reservation = (Reservation) o;
        return customer.equals(reservation.customer) && room.equals(reservation.room) && checkInDate.equals(reservation.checkInDate) && checkOutDate.equals(reservation.checkOutDate);
    }

    /**
     * CompareTo based on check in date.
     *
     * @return int
     */
    @Override
    public int compareTo(Reservation o) {
        return checkInDate.compareTo(o.getCheckInDate());
    }
}
