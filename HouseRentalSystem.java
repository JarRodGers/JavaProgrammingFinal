///////////////////////////////////////////////////////////////////////////////// 
// Student name: Jarrod Rodgers                                                //    
// Date due: 05/03/2023                                                        // 
// Date submitted: 05/03/2023                                                  //
// Program name: House Rental System                                           //
// Program description: This program keeps track of houses and guests. Houses  //
// and guests can be registered, rented, or deleted. Availablity for houses    //
// will also be available. The program is built on a basic CRUD framework.     //
/////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;

public class HouseRentalSystem {
    public static void main(String[] args) {
        // ArrayList that will hold all created House objects
        ArrayList<House> houseList = new ArrayList<House>(0);

        // ArrayList to hold all created Guest objects
        ArrayList<Guest> guestList = new ArrayList<Guest>(0);

        System.out.println("You are now viewing the rental system for Luxury Rentals Worldwide\n");
        menu(houseList, guestList);
    }

    // Method to view information about all registered houses
    public static void viewHouseInfo(ArrayList<House> list) {
        if (list.size() == 0) {
            System.out.println("\nThere are no registered houses in the system.");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                House currentHouse = list.get(i);
                System.out.println("\n\nHouse ID: " + currentHouse.getRegistrationID());
                System.out.println("House Type: " + currentHouse.getHouseType());
                System.out.println("Owner Name: " + currentHouse.getOwnerName());
                System.out.println("House Address: " + currentHouse.getAddress());
                System.out.println("Cost Per Night: " + currentHouse.getCostPerNight());
            }
        }
    }

    // Method to view all registered guests
    public static void viewGuestInfo(ArrayList<Guest> list) {
        if (list.size() == 0) {
            System.out.println("\nThere are no registered guests in the system.");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Guest currentGuest = list.get(i);
                System.out.println("\n\nGuest ID: " + currentGuest.getGuestID());
                System.out.println("Guest Name: " + currentGuest.getGuestName());
                System.out.println("Rental Start Date: " + currentGuest.getRentalStartDate());
                System.out.println("Rental End Date: " + currentGuest.getRentalEndDate());
            }
        }
    }

    public static void menu(ArrayList<House> houseList, ArrayList<Guest> guestList) {
        System.out.println("\nMake a selection:\n");
        System.out.println("1. View Registered Houses   4. Remove a House     7. Rent a House\n");
        System.out.println("2. View Registered Guests   5. Register a guest   8. End a Rental\n");
        System.out.println("3. Register a House         6. Remove a Guest     9. Exit\n");

        Scanner input = new Scanner(System.in); 
        int userSelection = Integer.parseInt(input.nextLine());
        
        if (userSelection == 1) {
            viewHouseInfo(houseList);
            menu(houseList, guestList);
        }
        else if (userSelection == 2) {
            viewGuestInfo(guestList);
            menu(houseList, guestList);
        }
        else if (userSelection == 3) {
            // Get owner name, house type, address, and cost per night
            System.out.println("Enter the owner's name: ");
            String newOwnerName = input.nextLine();
            System.out.println("Enter the number of bedrooms and bathrooms: ");
            String newHouseType = input.nextLine();
            System.out.println("Enter the house address: ");
            String newAddress = input.nextLine();
            System.out.println("Enter the cost per night: ");
            double newCostPerNight = Double.parseDouble(input.nextLine());

            // Call house constructor and pass info
            House newHouse = new House(newHouseType, newOwnerName, newAddress, newCostPerNight);

            // Register house in house list
            newHouse.registerHouse(newHouse, houseList);

            menu(houseList, guestList);
        }
        else if (userSelection == 4) {
            // Get ID of house to remove
            System.out.println("Enter the ID of the house to be removed: ");
            int idToDelete = Integer.parseInt(input.nextLine());

            // Pass ID to unregister house method
            houseList.remove(idToDelete);

            menu(houseList, guestList);
        }
        else if (userSelection == 5) {
            // Get guest name, rental start date, and rental end date
            System.out.println("Enter guest name: ");
            String newGuestName = input.nextLine();
            System.out.println("Enter rental start date (mm/dd): ");
            String newRentalStartDate = input.nextLine();
            System.out.println("Enter rental end date (mm/dd): ");
            String newRentalEndDate = input.nextLine();

            // Call Guest constructor and pass info
            Guest newGuest = new Guest(newGuestName, newRentalStartDate, newRentalEndDate);

            // Register guest in guest list
            newGuest.registerGuest(newGuest, guestList);

            menu(houseList, guestList);
        }
        else if (userSelection == 6) {
            // Get ID of guest to remove
            System.out.println("Enter the ID of the guest to be removed: ");
            int idToBeDeleted = Integer.parseInt(input.nextLine());

            // Pass ID to unregister guest method
            guestList.remove(idToBeDeleted);

            menu(houseList, guestList);
        }
        else if (userSelection == 7) {
            // Get ID of guest that wants to rent a house
            System.out.println("Enter guest ID: ");
            int gID = Integer.parseInt(input.nextLine());

            // Get ID of house that guest wants to rent
            System.out.println("Enter ID of house to rent: ");
            int hID = Integer.parseInt(input.nextLine());

            // Pass objects to Rental constructor
            Rental newRental = new Rental(houseList.get(hID), guestList.get(gID));

            // Rent house
            newRental.rentHouse(houseList.get(hID), guestList.get(gID));

            menu(houseList, guestList);
        }
        else if (userSelection == 8) {
            // Get ID of guest that wants to end rental
            System.out.println("Enter guest ID: ");
            int gID = Integer.parseInt(input.nextLine());

            // Get ID of house to be returned
            System.out.println("Enter ID of house to be returned: ");
            int hID = Integer.parseInt(input.nextLine());

            // Pass IDs to return house method
            Rental removedRental = new Rental(houseList.get(hID), guestList.get(gID));
            removedRental.returnHouse(houseList.get(hID), guestList.get(gID));

            menu(houseList, guestList);
        }
        else if (userSelection == 9) {
            System.exit(0);
        }
        else {
            System.out.println("Incorrect menu selection. Try again. Enter only the number corresponding to your selection.\n");
            menu(houseList, guestList);
        }
    }
}

class House {
    int registrationID;
    String houseType, ownerName, address;
    double costPerNight;
    int[][] dates;

    // Static variable used to create unique registration ID; will be incremented in constructor
    public static int id = 0;

    // Class constructor
    public House(String newHouseType, String newOwnerName, String newAddress, double newCostPerNight) {
        this.registrationID = id;
        id++;
        houseType = newHouseType;
        ownerName = newOwnerName;
        address = newAddress;
        costPerNight = newCostPerNight;
        dates = createCalendar();
    }

    // Getter methods
    String getHouseType() {
        return houseType;
    }

    String getOwnerName() {
        return ownerName;
    }

    int[][] getDates() {
        return dates;
    }

    double getCostPerNight() {
        return costPerNight;
    }

    int getRegistrationID() {
        return registrationID;
    }

    String getAddress() {
        return address;
    }

    // Setter methods
    void setUnavailableDates(String startDate, String endDate, int[][] calendar) {
        // Split start date by forward slash
        String[] splitStart = startDate.split("/");
        String[] splitEnd = endDate.split("/");

        // Store results in temp variables; account for differences in array
        int startRow = (Integer.parseInt(splitStart[0]) - 1);
        int startColumn = (Integer.parseInt(splitStart[1]) - 1);
        int endRow = (Integer.parseInt(splitEnd[0]) - 1);
        int endColumn = (Integer.parseInt(splitEnd[1]) - 1);

        // Loop through array at given location and change dates to 1
        if (startRow == endRow) {
            for (int i = 0; i < endColumn; i++) {
                calendar[startRow][startColumn] = 1;
                startColumn++;
            }
        }
        else {
            for (int i = startColumn; i < calendar[startRow].length; i++) {
                calendar[startRow][startColumn] = 1;
                startColumn++;
            }
            for (int i = 0; i < endColumn; i++) {
                calendar[endRow][i] = 1;
            }
        }
    }

    void setAvailableDates(String startDate, String endDate, int[][] calendar) {
        // Split start date by forward slash
        String[] splitStart = startDate.split("/");
        String[] splitEnd = endDate.split("/");

        // Store results in temp variables; account for differences in array
        int startRow = (Integer.parseInt(splitStart[0]) - 1);
        int startColumn = (Integer.parseInt(splitStart[1]) - 1);
        int endRow = (Integer.parseInt(splitEnd[0]) - 1);
        int endColumn = (Integer.parseInt(splitEnd[1]) - 1);

        // Loop through array at given location and change dates to 1
        if (startRow == endRow) {
            for (int i = 0; i < endColumn; i++) {
                calendar[startRow][startColumn] = 0;
                startColumn++;
            }
        }
        else {
            for (int i = startColumn; i < calendar[startRow].length; i++) {
                calendar[startRow][startColumn] = 0;
                startColumn++;
            }
            for (int i = 0; i < endColumn; i++) {
                calendar[endRow][i] = 0;
            }
        }
    }

    // This method adds a House object to the house list
    void registerHouse(House property, ArrayList<House> list) {
        list.add(property);
    }

    // This method removes a House object from the house list
    void unregisterHouse(int selection, ArrayList<House> list) {
        list.remove(selection);
    }

    // This method creates a calendar year in a 2 dimensional array; February is assumed to have 28 days
    int[][] createCalendar() {
        // create 2d array with 12 rows
        int[][] calendar = new int[12][];

        // initialize each row with number of days in corresponding month
        calendar[0] = new int[31];
        calendar[1] = new int[28];
        calendar[2] = new int[31];
        calendar[3] = new int[30];
        calendar[4] = new int[31];
        calendar[5] = new int[30];
        calendar[6] = new int[31];
        calendar[7] = new int[31];
        calendar[8] = new int[30];
        calendar[9] = new int[31];
        calendar[10] = new int[30];
        calendar[11] = new int[31];

        return calendar;
    }
}

class Guest {
    // Static variable for unique ID; will be incremented in constructor
    public static int id2 = 0;
    
    int guestID;
    String guestName, rentalStartDate, rentalEndDate;

    // Constructor for class
    public Guest(String newGuestName, String newRentalStartDate, String newRentalEndDate) {
        this.guestID = id2;
        id2++;
        guestName = newGuestName;
        rentalStartDate = newRentalStartDate;
        rentalEndDate = newRentalEndDate;
    }

    // Getter methods
    String getGuestName() {
        return guestName;
    }

    String getRentalStartDate() {
        return rentalStartDate;
    }

    String getRentalEndDate() {
        return rentalEndDate;
    }

    int getGuestID() {
        return guestID;
    }

    // Method to add a guest to the guest list
    void registerGuest(Guest person, ArrayList<Guest> list) {
        list.add(person);
    }

    void unregisterGuest(int selection, ArrayList<Guest> list) {
        list.remove(selection);
    }
}

class Rental {
    // Static variable for unique ID; will be incremented in constructor
    public static int id3 = 0;

    int rentalID;
    String guestName, houseType, ownerName, rentalStartDate, rentalEndDate;

    // Array list to hold all created Rental objects
    ArrayList<Rental> rentalList = new ArrayList<Rental>(0);

    // Constructor for class
    public Rental(House property, Guest person) {
        this.rentalID = id3;
        id3++;
        this.guestName = person.getGuestName();
        this.houseType = property.getHouseType();
        this.ownerName = property.getOwnerName();
        this.rentalStartDate = person.getRentalStartDate();
        this.rentalEndDate = person.getRentalEndDate();
    }

    // This method will take a house and a guest as parameters. It will check if the
    // rental dates associated with the guest are available in the dates associated 
    // with the house. If dates are available, a rental object will be created and 
    // added to the rental list. If dates are not available, an error message will show.
    void rentHouse(House property, Guest person) {
        // Split start date and end date for guest
        String[] splitStart = person.getRentalStartDate().split("/");
        String[] splitEnd = person.getRentalEndDate().split("/");

        // Store results in temp variables
        int startRow = (Integer.parseInt(splitStart[0]) - 1);
        int startColumn = (Integer.parseInt(splitStart[1]) - 1);
        int endRow = (Integer.parseInt(splitEnd[0]) - 1);
        int endColumn = (Integer.parseInt(splitEnd[1]) - 1);

        // Import house's dates array
        int[][] currentSchedule = property.getDates();
        
        // Loop through array at given location
        if (startRow == endRow) {
            for (int i = 0; i < endColumn; i++) {
                int currentDay = currentSchedule[startRow][startColumn];
                if (currentDay == 1) {
                    System.out.println("\nThe proposed rental dates are unavailable for the selected property.\n");
                    return;
                }
                else {
                    startColumn++;
                }
            }
        }
        else {
            for (int i = startColumn; i < currentSchedule[startRow].length; i++) {
                int currentDay = currentSchedule[startRow][startColumn];
                if (currentDay == 1) {
                    System.out.println("\nThe proposed rental dates are unavailable for the selected property.\n");
                    return; 
                }
                else {
                    startColumn++;
                }
            }
            for (int i = 0; i < endColumn; i++) {
                int currentDay = currentSchedule[endRow][i];
                if (currentDay == 1) {
                    System.out.println("\nThe proposed rental dates are unavailable for the selected property.\n");
                    return;
                }
            }
        }

        // If no ones are found, create rental object
        System.out.println("\nThe dates are available for the given property. The rental has been scheduled;");
        Rental newRental = new Rental(property,person);
        rentalList.add(newRental);

        // Set rental dates to unavailable for house
        property.setUnavailableDates(person.getRentalStartDate(), person.getRentalEndDate(), property.getDates());
    }

    // This method returns a house by setting its unavailable dates back
    // to available. It also caluclates the total rental cost and removes
    // the associated rental from the rental list.
    void returnHouse(House property, Guest person) {
        // Set unavailable dates to available
        property.setAvailableDates(person.getRentalStartDate(), person.getRentalEndDate(), property.getDates());

        // Calculate total rental cost
        String[] splitStart = person.getRentalStartDate().split("/");
        String[] splitEnd = person.getRentalEndDate().split("/");

        int startMonth = Integer.parseInt(splitStart[0]);
        int startDay = Integer.parseInt(splitStart[1]);
        int endMonth = Integer.parseInt(splitEnd[0]);
        int endDay = Integer.parseInt(splitEnd[1]);

        int numberOfDays;

        if (startMonth == endMonth) {
            numberOfDays = endDay - startDay;
        }

        else {
            int[][] monthCalendar = property.getDates();
            int tempNumber = monthCalendar[startMonth - 1].length;
            numberOfDays = (tempNumber - startDay) + endDay;
        }

        double totalRentalCost = numberOfDays * property.getCostPerNight();

        NumberFormat currency = NumberFormat.getCurrencyInstance();

        System.out.println("Rental has been ended successfully.\nTotal rental cost: " + currency.format(totalRentalCost));
    }
}