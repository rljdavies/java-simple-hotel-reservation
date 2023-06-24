package model;

/**
 * This class models a Free Room by extending Room.
 *
 * @author Rich Davies https://github.com/rljdavies
 * @see Room
 */
public class FreeRoom extends Room {

    public FreeRoom(int roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType, RoomValue.FREE);
    }

}




