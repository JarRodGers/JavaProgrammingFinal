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
        ArrayList<House> houseList = new ArrayList<House>(0);

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
}