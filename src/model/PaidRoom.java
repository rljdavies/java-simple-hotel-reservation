package model;

/**
 * This class models a Paid Room by extending Room.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see Room
 */
public class PaidRoom extends Room {

    public PaidRoom(int roomNumber, Double price, RoomType roomType) {
        super(roomNumber, price, roomType, RoomValue.PAID);
    }

}
