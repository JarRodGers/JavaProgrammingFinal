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
    }

    // This method adds a House object to the house list
    void registerHouse(House property, ArrayList<House> list) {
        list.add(property);
    }
}

class Guest {
    
}