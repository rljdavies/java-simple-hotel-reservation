package service;

import java.util.*;
import java.util.stream.Collectors;

import helpers.ConsoleStyles;
import model.*;

/**
 * A Room related data storage class.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see User
 */
public class RoomService {

    private static final RoomService reference = new RoomService();

    private final Set<IRoom> rooms = new HashSet<>();

    public static RoomService getInstance() {
        return reference;
    }

    /**
     * Returns a collection comprising all Rooms sorted by Room number.
     *
     * @return Collection<IRoom>
     */
    public Collection<IRoom> getAllRooms() {

        List<IRoom> allRooms = new ArrayList<>(rooms);
        Collections.sort(allRooms);

        return allRooms;
    }

    /**
     * Returns a collection comprising all Free Rooms sorted by Room number.
     *
     * @return Collection<IRoom>
     */
    public Collection<IRoom> getAllFreeRooms() {
        return rooms.stream().filter(u -> RoomValue.FREE.equals(u.getRoomValue())).sorted().collect(Collectors.toList());
    }

    /**
     * Returns a collection comprising all Paid Rooms sorted by Room number.
     *
     * @return Collection<IRoom>
     */
    public Collection<IRoom> getAllPaidRooms() {

        return rooms.stream().filter(u -> RoomValue.PAID.equals(u.getRoomValue())).sorted().collect(Collectors.toList());

    }

    /**
     * Gets a Room object based on String Room number provided.
     *
     * @param roomNumber Number of Room.
     * @return IRoom
     */
    public IRoom getRoom(int roomNumber) {

        Room foundRoom = null;

        for (IRoom room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                foundRoom = (Room) room;
            }
        }

        return foundRoom;
    }

    /**
     * Builds an Room from String inputs and adds to the data store.
     *
     * @param roomNumber Number of Room.
     * @param price Price of Room.
     * @param roomType Type of Room.
     * @param roomValue Value of Room.
     */
    public void addRoom(int roomNumber, double price, RoomType roomType, RoomValue roomValue) {
        try {
            IRoom room = new Room(roomNumber, price, roomType, roomValue);
            if (!rooms.add(room)) {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Room number exists, room not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);

        }

    }

    /**
     * Builds an Free Room from String inputs and adds to the data store.
     *
     * @param roomNumber Number of Room.
     * @param roomType Type of Room.
     */
    public void addFreeRoom(int roomNumber, RoomType roomType) {
        try {
            IRoom room = new FreeRoom(roomNumber, roomType);
            if (!rooms.add(room)) {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Room number exists, room not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);

        }

    }

    /**
     * Builds an Paid Room from String inputs and adds to the data store.
     *
     * @param roomNumber Number of Room.
     * @param roomType Type of Room.
     */
    public void addPaidRoom(int roomNumber, double price, RoomType roomType) {
        try {
            IRoom room = new PaidRoom(roomNumber, price, roomType);
            if (!rooms.add(room)) {
                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Room number exists, room not created." + ConsoleStyles.RESET);
            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);

        }

    }

    /**
     * Gets a RoomValue from a String or shortened String input.
     *
     * @param roomValueInput String to derive from.
     * @return RoomValue
     */
    public RoomValue getRoomValueFromString(String roomValueInput) {

        if (roomValueInput.length() == 1 && roomValueInput.toLowerCase().charAt(0) == 'p') {
            return RoomValue.PAID;
        } else if (roomValueInput.length() == 1 && roomValueInput.toLowerCase().charAt(0) == 'f') {
            return RoomValue.FREE;
        } else {
            return RoomValue.valueOf(roomValueInput.toUpperCase());
        }

    }

    /**
     * Gets a RoomType from a String or shortened String input.
     *
     * @param roomTypeInput String to derive from.
     * @return RoomType
     */
    public RoomType getRoomTypeFromString(String roomTypeInput) {

        if (roomTypeInput.length() == 1 && roomTypeInput.toLowerCase().charAt(0) == 's') {
            return RoomType.SINGLE;
        } else if (roomTypeInput.length() == 1 && roomTypeInput.toLowerCase().charAt(0) == 'd') {
            return RoomType.DOUBLE;
        } else {
            return RoomType.valueOf(roomTypeInput.toUpperCase());
        }

    }

    /**
     * Helper method to print formatted column header for lists of this type.
     */
    public String roomColumnHeader = ConsoleStyles.CYAN_BOLD + String.format("%-12.8s", "Number") + String.format("%-12.8s", "Type") + String.format("%-12.8s", "Price") + ConsoleStyles.RESET;


}









