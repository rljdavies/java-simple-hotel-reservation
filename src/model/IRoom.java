package model;

/**
 * This interface defines what must be implemented by Room.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see Room
 */
public interface IRoom extends Comparable<IRoom> {

    int getRoomNumber();

    void setRoomNumber(int roomNumber);

    double getRoomPrice();

    void setRoomPrice(double roomPrice);

    RoomType getRoomType();

    void setRoomType(RoomType roomType);

    RoomValue getRoomValue();

    void setRoomValue(RoomValue roomValue);

    boolean isFree();

    String toColumnString();

    boolean equals(Object o);

    int compareTo(IRoom o);

    int hashCode();

}
