package application;

import menu.MainMenu;
import api.AdminResource;

/**
 * This application provides a reservation capability for hotel rooms, with user and room management, and admin functions.
 *
 * @author Rich Davies https://github.com/rljdavies
 */
public class HotelApplication {

    /**
     * This is the main entry point to the application.
     *
     * @param args String launch args.
     */
    public static void main(String[] args) {

        AdminResource.getInstance().addAdmin("Testy", "McTesterson", "admin@admin.com", "password");
        new MainMenu();

    }
}
