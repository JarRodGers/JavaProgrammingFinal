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

public class HouseRentalSystem {
    public static void main(String[] args) {

    }
}

class House {
    int registrationID;
    String houseType, ownerName, address;
    double costPerNight;
    int[][] dates;

    // ArrayList that will hold all created House objects
    ArrayList<House> houseList = new ArrayList<House>(0);

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
    void unregisterHouse(House property, ArrayList<House> list) {
        list.remove(property);
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

    // ArrayList to hold all created Guest objects
    ArrayList<Guest> guestList = new ArrayList<Guest>(0);

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

    // Method to add a guest to the guest list
    void registerGuest(Guest person, ArrayList<Guest> list) {
        list.add(person);
    }

    void unregisterGuest(Guest person, ArrayList<Guest> list) {
        list.remove(person);
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
                    System.out.println("The proposed rental dates are unavailable for the selected property.\n");
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
                    System.out.println("The proposed rental dates are unavailable for the selected property.\n");
                    return; 
                }
                else {
                    startColumn++;
                }
            }
            for (int i = 0; i < endColumn; i++) {
                int currentDay = currentSchedule[endRow][i];
                if (currentDay == 1) {
                    System.out.println("The proposed rental dates are unavailable for the selected property.\n");
                    return;
                }
            }
        }

        // If no ones are found, create rental object
        System.out.println("The dates are available for the given property. The rental has been scheduled;");
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

        System.out.println("Rental has been ended successfully.\nTotal rental cost: " + totalRentalCost);
    }
}