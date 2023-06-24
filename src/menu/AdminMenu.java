package menu;

import api.AdminResource;
import api.HotelResource;
import model.*;
import helpers.ConsoleStyles;

import javax.naming.AuthenticationException;
import java.util.Scanner;

/**
 * This class is the Admin Menu UI.
 *
 * @author Rich Davies https://github.com/rljdavies
 */
public class AdminMenu {

    private static boolean rememberMe = false;

    /**
     * Processes user interaction and input with the Admin Menu.
     */
    public AdminMenu() {

        boolean authenticated = false;
        boolean keepAdminRunning = true;
        boolean keepAdding;
        Scanner scanner = new Scanner(System.in);

        try {

            while (keepAdminRunning) {
                try {

                    int failCount = 0;
                    boolean inputValid;

                    if (!rememberMe) {
                        while (!authenticated) {

                            if (failCount > 0) {
                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "The admin menu is for authorised users only (try " + (failCount + 1) + " of 3)." + ConsoleStyles.RESET);
                            } else {
                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "The admin menu is for authorised users only." + ConsoleStyles.RESET);
                            }

                            System.out.println("Please enter your user email:");
                            String email = scanner.nextLine().toLowerCase();


                            System.out.println("Please enter your user password:");
                            String password = scanner.nextLine();

                            try {
                                if (AdminResource.getInstance().authenticateAdmin(email, password)) {

                                    System.out.println("Would you like to stay signed in to the admin menu?: Enter (Y)es or press any other key.");
                                    String rememberMeInput = scanner.nextLine();
                                    if (rememberMeInput.equalsIgnoreCase("y") || rememberMeInput.equalsIgnoreCase("yes")) {
                                        rememberMe = true;
                                    }

                                    authenticated = true;
                                } else {

                                    failCount++;
                                    if (failCount > 2) {
                                        throw new AuthenticationException(ConsoleStyles.RED + "ERROR: Authentication failed!" + ConsoleStyles.RESET);
                                    }
                                }

                            } catch (Exception ex) {
                                failCount++;

                                if (failCount > 2) {
                                    throw new AuthenticationException(ConsoleStyles.RED + "ERROR: Could not authenticate!" + ConsoleStyles.RESET);
                                }
                            }

                        }
                    }

                    displayAdminMenu();

                    String selection = scanner.nextLine();

                    switch (selection) {
                        case "1": //see all customers

                            System.out.println();
                            System.out.println();
                            System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " ALL CUSTOMERS " + ConsoleStyles.RESET);
                            System.out.println(AdminResource.getInstance().getAllCustomers().size() + " in total");
                            System.out.println(HotelResource.getInstance().userColumnHeader());

                            for (IUser customer : AdminResource.getInstance().getAllCustomers()) {
                                System.out.println(customer.toColumnString());
                            }

                            System.out.println();

                            System.out.println("Press any key to return to the main menu.");
                            scanner.nextLine();
                            break;

                        case "2": //see all reservations

                            System.out.println();
                            System.out.println();
                            System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " ALL RESERVATIONS " + ConsoleStyles.RESET);
                            System.out.println(AdminResource.getInstance().getAllReservations().size() + " in total");
                            System.out.println(HotelResource.getInstance().reservationColumnHeader());

                            for (Reservation reservation : AdminResource.getInstance().getAllReservations()) {
                                System.out.println(reservation.toColumnString());
                            }

                            System.out.println();

                            System.out.println("Press any key to return to the main menu.");
                            scanner.nextLine();
                            break;

                        case "3": //see all rooms

                            System.out.println();
                            System.out.println();
                            System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " ALL ROOMS " + ConsoleStyles.RESET);
                            System.out.println(AdminResource.getInstance().getAllRooms().size() + " in total");
                            System.out.println(HotelResource.getInstance().roomColumnHeader());

                            for (IRoom room : AdminResource.getInstance().getAllRooms()) {
                                System.out.println(room.toColumnString());
                            }

                            System.out.println();

                            System.out.println("Press any key to return to the main menu.");
                            scanner.nextLine();
                            break;

                        case "4": //add a room

                            keepAdding = true;

                            while (keepAdding) {


                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Add a room" + ConsoleStyles.RESET);

                                int roomNumber = 0;
                                RoomType roomType = null;
                                double price = 0.0;
                                RoomValue roomValue = null;

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Please enter a " + ConsoleStyles.WHITE_UNDERLINED + "unique" + ConsoleStyles.RESET + " room number:");
                                    } else {
                                        System.out.println("Please try again, enter a " + ConsoleStyles.WHITE_UNDERLINED + "unique" + ConsoleStyles.RESET + " room number, between 1 and 16 characters in length:");
                                    }

                                    int input = Integer.parseInt(scanner.nextLine());

                                    if (input > 0 && input < 99999) {
                                        roomNumber = input;
                                        inputValid = true;
                                    } else {
                                        failCount++;
                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("What type of room is this?: Please enter either (S)ingle or (D)ouble?:");
                                    } else {
                                        System.out.println("Please try again, what type of room is this?: Please enter either (S)ingle or (D)ouble?:");
                                    }

                                    String input = scanner.nextLine();

                                    try {
                                        RoomType roomTypeCheck = HotelResource.getInstance().getRoomTypeFromString(input);

                                        if (roomTypeCheck != null) {
                                            roomType = roomTypeCheck;
                                            inputValid = true;
                                        } else {
                                            failCount++;
                                            if (failCount > 2) {
                                                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        failCount++;

                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Input validation problem!" + ConsoleStyles.RESET);
                                        }
                                    }
                                }

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Will this room be chargeable?: Please enter (P)aid or (F)ree:");
                                    } else {
                                        System.out.println("Please try again, will this room be chargeable?: Please enter (P)aid or (F)ree:");
                                    }

                                    String input = scanner.nextLine();

                                    try {

                                        RoomValue roomValueCheck = HotelResource.getInstance().getRoomValueFromString(input);

                                        if (roomValueCheck != null) {
                                            roomValue = roomValueCheck;
                                            inputValid = true;
                                        } else {
                                            failCount++;
                                            if (failCount > 2) {
                                                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        failCount++;

                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Input validation problem!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                if (roomValue.equals(RoomValue.PAID)) {

                                    failCount = 0;
                                    inputValid = false;

                                    while (!inputValid) {

                                        if (failCount == 0) {
                                            System.out.println("What is the daily price for this room?:");
                                        } else {
                                            System.out.println("Please try again, What is the daily price for this room?: Please enter a decimal value");
                                        }

                                        String input = scanner.nextLine();

                                        try {

                                            price = Double.parseDouble(input);
                                            inputValid = true;


                                        } catch (Exception ex) {
                                            failCount++;

                                            if (failCount > 2) {
                                                throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Input validation problem!" + ConsoleStyles.RESET);
                                            }
                                        }

                                    }

                                }


                                if (roomValue.equals(RoomValue.PAID)) {
                                    AdminResource.getInstance().addPaidRoom(roomNumber, price, roomType);
                                } else {
                                    AdminResource.getInstance().addFreeRoom(roomNumber, roomType);
                                }


                                System.out.println("Enter (Y)es to add another, else press ENTER to return to the admin menu.");
                                String input = scanner.nextLine();

                                if (input.length() == 0 || (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes"))) {
                                    keepAdding = false;
                                }

                            }

                            break;

                        case "5": //see all admin

                            System.out.println();
                            System.out.println();
                            System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " ADMIN USERS " + ConsoleStyles.RESET);
                            System.out.println(AdminResource.getInstance().getAllAdmin().size() + " in total");
                            System.out.println(HotelResource.getInstance().userColumnHeader());

                            for (IUser admin : AdminResource.getInstance().getAllAdmin()) {
                                System.out.println(admin.toColumnString());
                            }

                            System.out.println();

                            System.out.println("Press any key to return to the main menu.");
                            scanner.nextLine();
                            break;

                        case "6": //add an admin

                            keepAdding = true;

                            while (keepAdding) {

                                String firstName = "";
                                String lastName = "";
                                String email = "";
                                String password = "";

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Please enter a first name:");
                                    } else {
                                        System.out.println("Please try again, enter a first name between 1 and 20 characters in length:");
                                    }

                                    String input = scanner.nextLine();

                                    if (input.length() > 0 && input.length() < 21) {
                                        firstName = input;
                                        inputValid = true;
                                    } else {
                                        failCount++;
                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Please enter a last name:");
                                    } else {
                                        System.out.println("Please try again, enter a last name between 1 and 30 characters in length:");
                                    }

                                    String input = scanner.nextLine();

                                    if (input.length() > 0 && input.length() < 31) {
                                        lastName = input;
                                        inputValid = true;
                                    } else {
                                        failCount++;
                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Please enter a valid email address:");
                                    } else {
                                        System.out.println("Please try again, enter a valid email address:");
                                    }

                                    String input = scanner.nextLine();

                                    if (input.length() > 3 && input.length() < 321) {
                                        email = input;
                                        inputValid = true;
                                    } else {
                                        failCount++;
                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                failCount = 0;
                                inputValid = false;

                                while (!inputValid) {

                                    if (failCount == 0) {
                                        System.out.println("Please enter a password:");
                                    } else {
                                        System.out.println("Please try again, enter an password between 4 and 12 characters in length:");
                                    }

                                    String input = scanner.nextLine();

                                    if (input.length() > 3 && input.length() < 13) {
                                        password = input;
                                        inputValid = true;
                                    } else {
                                        failCount++;
                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                                AdminResource.getInstance().addAdmin(firstName, lastName, email, password);


                                System.out.println("Enter (Y)es to add another, else press ENTER to return to the Admin Menu.");
                                String input = scanner.nextLine();

                                if (input.length() == 0 || (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes"))) {
                                    keepAdding = false;
                                }


                            }

                            break;

                        case "7": //populate test data

                            AdminResource.getInstance().populateTestData();
                            break;

                        case "8": //back to main menu

                            keepAdminRunning = false;
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid selection: " + selection);

                    }
                } catch (Exception ex) {
                    System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);

                    if (ex.getLocalizedMessage().contains("auth")) {
                        throw new Exception("Returning to main menu...");
                    }

                }


            }

        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);
        }

    }

    /**
     * Builds and prints the Admin Menu.
     */
    private void displayAdminMenu() {

        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println(ConsoleStyles.BLUE_BACKGROUND_BRIGHT + " ADMIN MENU " + ConsoleStyles.RESET);
        System.out.println();
        System.out.println("Please enter the number for the menu item required...");
        System.out.println();
        System.out.println("1. See all customers");
        System.out.println("2. See all reservations");
        System.out.println("3. See all rooms");
        System.out.println("4. Add a room");
        System.out.println("5. See all admin users");
        System.out.println("6. Add an admin user");
        System.out.println("7. Populate test data");
        System.out.println("8. Back to the main menu");
        System.out.println();

    }

}
