package menu;

import api.HotelResource;
import helpers.ConsoleStyles;
import model.*;

import javax.naming.AuthenticationException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This class is the Main Menu UI.
 *
 * @author Rich Davies https://github.com/rljdavies
 */
public class MainMenu {

    /**
     * Processes user interaction and input with the Main Menu.
     */
    public MainMenu() {

        boolean keepRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Welcome to the " +
                    ConsoleStyles.CYAN_BOLD + "S" + ConsoleStyles.RESET + "ierra " +
                    ConsoleStyles.CYAN_BOLD + "W" + ConsoleStyles.RESET + "hiskey " +
                    ConsoleStyles.CYAN_BOLD + "I" + ConsoleStyles.RESET + "ndia " +
                    ConsoleStyles.CYAN_BOLD + "F" + ConsoleStyles.RESET + "oxtrot " +
                    ConsoleStyles.CYAN_BOLD + "T" + ConsoleStyles.RESET + "ango " +
                    "Hotel");

            while (keepRunning) {

                try {

                    int failCount = 0;
                    boolean inputValid = false;

                    displayMainMenu();

                    String selection = scanner.nextLine();

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
                    switch (selection) {
                        case "1": //find a reserve a room

                            String dayWeekText;

                            Calendar checkIn = Calendar.getInstance();

                            System.out.println(ConsoleStyles.WHITE_UNDERLINED + "When would you like to check in?:" + ConsoleStyles.RESET);

                            System.out.println("1. Today - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            System.out.println("2. Tomorrow - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            dayWeekText = new SimpleDateFormat("EEEE").format(checkIn.getTime());
                            System.out.println("3. " + dayWeekText + " - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            dayWeekText = new SimpleDateFormat("EEEE").format(checkIn.getTime());
                            System.out.println("4. " + dayWeekText + " - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            dayWeekText = new SimpleDateFormat("EEEE").format(checkIn.getTime());
                            System.out.println("5. " + dayWeekText + " - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            dayWeekText = new SimpleDateFormat("EEEE").format(checkIn.getTime());
                            System.out.println("6. " + dayWeekText + " - " + dateFormatter.format(checkIn.getTime()));

                            checkIn.add(Calendar.DATE, 1);
                            dayWeekText = new SimpleDateFormat("EEEE").format(checkIn.getTime());
                            System.out.println("7. " + dayWeekText + " - " + dateFormatter.format(checkIn.getTime()));

                            System.out.println(ConsoleStyles.WHITE + "or" + ConsoleStyles.RESET);

                            System.out.println("8. Enter your own check in date today onwards...");

                            int checkInInput = Integer.parseInt(scanner.nextLine());

                            checkIn.add(Calendar.DATE, -6);
                            boolean requiredCheckInKnown = false;
                            Date checkInDate = new Date();

                            while (!requiredCheckInKnown) {

                                if (checkInInput < 8) {
                                    checkIn.add(Calendar.DATE, checkInInput - 1);
                                    checkInDate = dateFormatter.parse(dateFormatter.format(checkIn.getTime()));
                                    requiredCheckInKnown = true;
                                } else {

                                    //System.out.println("Please enter your check in date: eg 30 Apr " + Year.now().getValue());
                                    checkIn.add(Calendar.DATE, 7);
                                    System.out.println("Please enter your check out date: eg " + dateFormatter.format(checkIn.getTime()));
                                    checkIn.add(Calendar.DATE, -7);

                                    String input = scanner.nextLine();

                                    try {

                                        Date today = dateFormatter.parse(dateFormatter.format((new Date())));
                                        Date inputDate = dateFormatter.parse(input);

                                        if (inputDate.after(today) || inputDate.equals(today)) {
                                            checkInDate = dateFormatter.parse(dateFormatter.format(inputDate));
                                            requiredCheckInKnown = true;
                                        }

                                    } catch (Exception ex) {
                                        throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid entry!" + ConsoleStyles.RESET);
                                    }


                                }

                            }


                            Calendar checkOut = Calendar.getInstance();
                            checkOut.setTime(checkInDate);

                            checkOut.add(Calendar.DATE, 1);

                            System.out.println(ConsoleStyles.WHITE_UNDERLINED + "How long would you like to stay?:" + ConsoleStyles.RESET);

                            System.out.println("1. One day - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("2. Two days - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("3. Three days - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("4. Four days - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("5. Five days - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("6. Six days - checking out " + dateFormatter.format(checkOut.getTime()));

                            checkOut.add(Calendar.DATE, 1);
                            System.out.println("7. Seven days - checking out " + dateFormatter.format(checkOut.getTime()));

                            System.out.println(ConsoleStyles.WHITE + "or" + ConsoleStyles.RESET);

                            System.out.println("8. Enter your own check out date check in onwards...");


                            int checkOutInput = Integer.parseInt(scanner.nextLine());

                            checkOut.add(Calendar.DATE, -6);
                            boolean requiredCheckOutKnown = false;
                            Date checkOutDate = new Date();

                            while (!requiredCheckOutKnown) {

                                if (checkOutInput < 8) {
                                    checkOut.add(Calendar.DATE, checkOutInput - 1);
                                    checkOutDate = dateFormatter.parse(dateFormatter.format(checkOut.getTime()));
                                    requiredCheckOutKnown = true;
                                } else {

                                    //System.out.println("Please enter your check out date: eg 30 Apr " + Year.now().getValue());
                                    checkOut.add(Calendar.DATE,  7);
                                    System.out.println("Please enter your check out date: eg " + dateFormatter.format(checkOut.getTime()));
                                    checkOut.add(Calendar.DATE, -7);
                                    String input = scanner.nextLine();

                                    try {

                                        Date inputDate = dateFormatter.parse(input);

                                        if (inputDate.after(checkInDate)) {
                                            checkOutDate = dateFormatter.parse(dateFormatter.format(inputDate));
                                            requiredCheckOutKnown = true;
                                        }

                                    } catch (Exception ex) {
                                        throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid entry!" + ConsoleStyles.RESET);
                                    }

                                }

                            }


                            boolean requiredRoomValueKnown = false;
                            RoomValue roomValue = null;

                            while (!requiredRoomValueKnown) {

                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Would you like to search for (F)ree rooms, (P)aid rooms? Just press ENTER for either:" + ConsoleStyles.RESET);

                                String roomValueInput = scanner.nextLine();

                                if (roomValueInput.length() == 0) {
                                    requiredRoomValueKnown = true;

                                } else {

                                    try {

                                        roomValue = HotelResource.getInstance().getRoomValueFromString(roomValueInput);

                                        requiredRoomValueKnown = true;

                                    } catch (Exception ex) {
                                        failCount++;

                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid entry!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                            }

                            boolean requiredRoomTypeKnown = false;
                            RoomType roomType = null;

                            while (!requiredRoomTypeKnown) {

                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Would you like a (S)ingle or (D)ouble? room.  Just press ENTER if either one is suitable:" + ConsoleStyles.RESET);

                                String roomTypeInput = scanner.nextLine();

                                if (roomTypeInput.length() == 0) {
                                    requiredRoomTypeKnown = true;

                                } else {

                                    try {
                                        roomType = HotelResource.getInstance().getRoomTypeFromString(roomTypeInput);
                                        requiredRoomTypeKnown = true;

                                    } catch (Exception ex) {
                                        failCount++;

                                        if (failCount > 2) {
                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid entry!" + ConsoleStyles.RESET);
                                        }
                                    }

                                }

                            }

                            Collection<IRoom> availableRooms;

                            if (roomType != null || roomValue != null) {
                                if (roomType != null && roomValue != null) {
                                    availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomValue, roomType);
                                } else if (roomType != null) {
                                    availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomType);
                                } else {
                                    availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomValue);
                                }

                            } else {
                                availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate);
                            }

                            if (availableRooms.size() == 0) {
                                System.out.println();
                                System.out.println(ConsoleStyles.YELLOW + "There are no rooms matching your exact requirements available, we will now try and recommend some alternatives for you." + ConsoleStyles.RESET);
                                System.out.println();
                                System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Enter the number of days ahead you would like to check for availability, or just hit ENTER to use the default 7 days." + ConsoleStyles.RESET);

                                String input = scanner.nextLine();

                                int expandedSearch = 7;
                                if (input.length() != 0) {
                                    expandedSearch = Integer.parseInt(input);
                                }

                                checkIn.add(Calendar.DATE, expandedSearch);
                                checkInDate = dateFormatter.parse(dateFormatter.format(checkIn.getTime()));


                                checkOut.add(Calendar.DATE, expandedSearch);
                                checkOutDate = dateFormatter.parse(dateFormatter.format(checkOut.getTime()));


                                if (roomType != null || roomValue != null) {
                                    if (roomType != null && roomValue != null) {
                                        availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomValue, roomType);
                                    } else if (roomType != null) {
                                        availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomType);
                                    } else {
                                        availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate, roomValue);
                                    }

                                } else {
                                    availableRooms = HotelResource.getInstance().findRooms(checkInDate, checkOutDate);
                                }

                            }

                            if (availableRooms.size() == 0) {

                                System.out.println(ConsoleStyles.RED + "We are unable to find any suitable recommendations for you this time." + ConsoleStyles.RESET);
                                System.out.println();
                                System.out.println("Please press any key to return to the main menu.");
                                scanner.nextLine();

                            } else {

                                long lengthOfStayMilliseconds = Math.abs(checkInDate.getTime() - checkOutDate.getTime());
                                long lengthOfStayDays = TimeUnit.DAYS.convert(lengthOfStayMilliseconds, TimeUnit.MILLISECONDS);

                                System.out.println();
                                System.out.println();
                                System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " ROOMS AVAILABLE " + ConsoleStyles.RESET);
                                System.out.println(availableRooms.size() + " available for a " + lengthOfStayDays + " day stay checking in on " + dateFormatter.format(checkInDate) + ", and out on " + dateFormatter.format(checkOutDate) + ".");

                                System.out.println(HotelResource.getInstance().roomColumnHeader());

                                for (IRoom availableRoom : availableRooms) {
                                    System.out.println(availableRoom.toColumnString());
                                }

                                System.out.println();
                                System.out.println("Please enter the room number you wish to book, or just press ENTER to go back to the Main Menu:");

                                int reservationRoomNumberInput = Integer.parseInt(scanner.nextLine());

                                if (reservationRoomNumberInput > 0) {


                                    IRoom reservationRoom = HotelResource.getInstance().getRoom(reservationRoomNumberInput);

                                    if (reservationRoom.getRoomNumber() == reservationRoomNumberInput) {


                                        String reservationEmailInput = "";
                                        failCount = 0;
                                        inputValid = false;

                                        while (!inputValid) {
                                            if (failCount == 0) {
                                                System.out.println("Please enter your user email:");
                                            } else {
                                                System.out.println("Please try again, enter your user email:");
                                            }
                                            String input = scanner.nextLine().toLowerCase();

                                            if (input.length() > 0 && input.length() < 21) {
                                                reservationEmailInput = input;
                                                inputValid = true;
                                            } else {
                                                failCount++;
                                                if (failCount > 2) {
                                                    throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                                }
                                            }

                                        }


                                        boolean reservationAccountFound = false;

                                        Customer reservationCustomer = null;
                                        String reservationFirstName = "";
                                        String reservationLastName = "";
                                        String reservationPassword = "";

                                        while (!reservationAccountFound) {

                                            reservationCustomer = HotelResource.getInstance().getCustomer(reservationEmailInput);

                                            if (reservationCustomer != null) {

                                                System.out.println("Please enter your user password");
                                                String reservationPasswordInput = scanner.nextLine();

                                                if (reservationCustomer.getPassword().equals(reservationPasswordInput)) {
                                                    reservationAccountFound = true;
                                                }

                                            } else {

                                                System.out.println("There is no user account for " + reservationEmailInput + ", lets get one setup now...");

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
                                                        reservationFirstName = input;
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
                                                        reservationLastName = input;
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
                                                        reservationPassword = input;
                                                        inputValid = true;
                                                    } else {
                                                        failCount++;
                                                        if (failCount > 2) {
                                                            throw new IllegalArgumentException(ConsoleStyles.RED + "ERROR: Invalid input!" + ConsoleStyles.RESET);
                                                        }
                                                    }

                                                }

                                                HotelResource.getInstance().addCustomer(reservationEmailInput, reservationFirstName, reservationLastName, reservationPassword);

                                                reservationCustomer = HotelResource.getInstance().getCustomer(reservationEmailInput);
                                                reservationAccountFound = true;
                                            }

                                        }
                                        HotelResource.getInstance().addReservation(reservationCustomer.getEmail(), reservationRoom, checkInDate, checkOutDate);
                                    }
                                }
                            }

                            break;

                        case "2": //see my reservations

                            boolean authenticated = false;
                            String authEmail = "";
                            failCount = 0;

                            while (!authenticated) {

                                if (failCount > 0) {
                                    System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Please login to see your reservations (try " + (failCount + 1) + " of 3)." + ConsoleStyles.RESET);
                                } else {
                                    System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Please login to see your reservations." + ConsoleStyles.RESET);
                                }

                                System.out.println("Please enter your user email:");
                                String customerEmail = scanner.nextLine().toLowerCase();

                                System.out.println("Please enter your user password:");
                                String customerPassword = scanner.nextLine();

                                try {
                                    if (HotelResource.getInstance().authenticateCustomer(customerEmail, customerPassword)) {
                                        authenticated = true;
                                        authEmail = customerEmail;

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

                            System.out.println();
                            System.out.println();
                            System.out.println(ConsoleStyles.PURPLE_BACKGROUND + " MY RESERVATIONS " + ConsoleStyles.RESET);
                            System.out.println(HotelResource.getInstance().getCustomerReservations(authEmail).size() + " found for " + authEmail + ":");
                            System.out.println(HotelResource.getInstance().reservationColumnHeader());

                            for (Reservation reservation : HotelResource.getInstance().getCustomerReservations(authEmail)) {
                                System.out.println(reservation.toColumnString());
                            }

                            System.out.println();

                            System.out.println("Press any key to return to the main menu.");
                            scanner.nextLine();
                            break;

                        case "3": //create an account

                            System.out.println(ConsoleStyles.WHITE_UNDERLINED + "Create an account" + ConsoleStyles.RESET);

                            String firstName = "";
                            String lastName = "";
                            String email = "";
                            String password = "";

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

                            HotelResource.getInstance().addCustomer(firstName, lastName, email, password);
                            break;

                        case "4": //admin menu

                            new AdminMenu();
                            break;

                        case "5": //exit application

                            keepRunning = false;
                            break;

                        default:
                            throw new IllegalArgumentException("ERROR: Invalid selection! " + selection);

                    }
                } catch (Exception ex) {
                    System.out.println(ConsoleStyles.RED + "ERROR: Invalid input!!" + ConsoleStyles.RESET);
                }
            }
        } catch (Exception ex) {
            System.out.println(ConsoleStyles.RED + ex.getLocalizedMessage() + ConsoleStyles.RESET);
        }

    }

    /**
     * Builds and prints the Main Menu.
     */
    private static void displayMainMenu() {

        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println(ConsoleStyles.BLUE_BACKGROUND_BRIGHT + " MAIN MENU " + ConsoleStyles.RESET);
        System.out.println();
        System.out.println("Please enter the number for the menu item required...");
        System.out.println();
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println();

    }

}
