package model;

import java.util.Objects;

/**
 * This class implements IRoom and is extended to represent both Paid rooms and Free rooms.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see IRoom
 * @see PaidRoom
 * @see FreeRoom
 */
public class Room implements IRoom, Comparable<IRoom> {

    private int roomNumber;
    private double price;
    private RoomType roomType;
    private RoomValue roomValue;

    public Room(int roomNumber, Double price, RoomType roomType, RoomValue roomValue) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.roomValue = roomValue;
    }

    /**
     * Get the roomNumber on the Room class.
     *
     * @return String
     */
    @Override
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set the roomNumber on the Room class.
     *
     * @param roomNumber Number of room.
     */
    @Override
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Get the roomValue on the Room class.
     *
     * @return RoomValue
     */
    @Override
    public RoomValue getRoomValue() {
        return roomValue;
    }

    /**
     * Set the roomValue on the Room class.
     *
     * @param roomValue Value or room.
     */
    @Override
    public void setRoomValue(RoomValue roomValue) {
        this.roomValue = roomValue;
    }

    /**
     * Get the room price on the Room class.
     *
     * @return double
     */
    @Override
    public double getRoomPrice() {
        return price;
    }

    /**
     * Set the price on the Room class.
     *
     * @param roomPrice Price of Room/
     */
    @Override
    public void setRoomPrice(double roomPrice) {
        this.price = roomPrice;
    }

    /**
     * Get the roomType on the Room class.
     *
     * @return RoomType
     */
    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Set the roomType on the Room class.
     *
     * @param roomType Type of room.
     */
    @Override
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Returns true if the room is of type Free, otherwise false.
     *
     * @return boolean
     */
    @Override
    public boolean isFree() {
        return roomValue.equals(RoomValue.FREE);
    }

    /**
     * Equals based on roomNumber.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    /**
     * CompareTo based on roomNumber.
     *
     * @return int
     */
    @Override
    public int compareTo(IRoom o) {
        return Integer.compare(roomNumber, o.getRoomNumber());
    }

    /**
     * Hashcode based on roomNumber.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    /**
     * Single line representation of object as string.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                "   Room Type: " + roomType +
                "   Price: " + getDisplayPrice(price);
    }

    /**
     * Formatted column representation of object for readability in lists.
     *
     * @return String
     */
    @Override
    public String toColumnString() {
        return String.format("%-12.8s", roomNumber) +
                String.format("%-12.8s", roomType) +
                String.format("%-12.8s", getDisplayPrice(price));
    }

    /**
     * Helper method used for a simplified display to users.
     *
     * @param price Price to process for display.
     * @return String Display string.
     */
    private String getDisplayPrice(double price) {
        if (isFree()) {
            return "FREE";
        } else {
            return "" + price;
        }
    }

}
