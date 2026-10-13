package com.cinema.booking;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

// ==========================================
// 1. BOOKING OBJECT BLUEPRINT CLASS
// ==========================================
class Booking {
    private String bookingID;
    private String customerName;
    private String movieTitle;
    private String movieType;
    private int numberOfTickets;
    private double totalCost;

    public Booking(String bookingID, String customerName, String movieTitle, String movieType, int numberOfTickets, double totalCost) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.numberOfTickets = numberOfTickets;
        this.totalCost = totalCost;
    }

    public String getBookingID() { return bookingID; }
    public String getCustomerName() { return customerName; }
    public String getMovieTitle() { return movieTitle; }
    public String getMovieType() { return movieType; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public double getTotalCost() { return totalCost; }
}

// ==========================================
// 2. MAIN EXECUTIVE RUNNER CLASS
// ==========================================
public class CinemaTicketBookingSystem {
    private static ArrayList<Booking> bookingList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        while (choice != 8) {
            displayMenu();
            try {
                choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1: addBooking(); break;
                    case 2: displayBookings(); break;
                    case 3: searchBooking(); break;
                    case 4: processCostCalculation(); break;
                    case 5: cancelBooking(); break;
                    case 6: countMovieBookings(); break;
                    case 7: processDiscountCheck(); break;
                    case 8: System.out.println("\nExiting. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Choose 1-8."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Enter numbers only.");
                input.next(); 
                choice = 0;   
            }
        }
        input.close();
    }

    public static void displayMenu() {
        System.out.println("\n========== CINEMA BOOKING SYSTEM ==========");
        System.out.println("1. Add Booking\n2. Display All Bookings\n3. Search Booking\n4. Calculate Booking Cost\n5. Cancel Booking\n6. Count Bookings by Movie\n7. Check Customer Discount\n8. Exit");
        System.out.println("===========================================");
        System.out.print("Enter your choice: ");
    }

    public static void addBooking() {
        System.out.print("Enter Booking ID: ");
        String id = input.nextLine().trim();
        if (id.isEmpty()) { System.out.println("Cannot be empty."); return; }

        for (Booking b : bookingList) {
            if (b.getBookingID().equalsIgnoreCase(id)) {
                System.out.println("Error: Booking ID already exists.");
                return;
            }
        }

        System.out.print("Enter Customer Name: ");
        String name = input.nextLine().trim();
        System.out.print("Enter Movie Title: ");
        String title = input.nextLine().trim();

        System.out.println("Select Movie Type:\n1. Standard (R50)\n2. 3D (R70)\n3. IMAX (R100)\n4. VIP (R150)");
        System.out.print("Enter choice: ");
        int typeChoice = input.nextInt();
        input.nextLine();

        double price = 50;
        String typeString = "Standard";

        switch (typeChoice) {
            case 1: price = 50; typeString = "Standard"; break;
            case 2: price = 70; typeString = "3D"; break;
            case 3: price = 100; typeString = "IMAX"; break;
            case 4: price = 150; typeString = "VIP"; break;
        }

        System.out.print("Enter Number of Tickets: ");
        int tickets = input.nextInt();
        input.nextLine();
        if (tickets <= 0) { System.out.println("Must be greater than zero."); return; }

        double rawCost = calculateCost(price, tickets);
        double discountRate = 0.0;
        if (tickets >= 4 && tickets <= 5) discountRate = 0.10;
        else if (tickets >= 6) discountRate = 0.15;

        double totalCost = rawCost * (1.0 - discountRate);
        bookingList.add(new Booking(id, name, title, typeString, tickets, totalCost));

        System.out.println("\nTicket Price: R" + (int)price);
        System.out.println("Total Cost: R" + (int)totalCost);
        System.out.println("Discount: " + (int)(discountRate * 100) + "%");
        System.out.println("Booking successfully registered.");
    }

    public static void displayBookings() {
        if (bookingList.isEmpty()) { System.out.println("No bookings registered."); return; }
        for (Booking b : bookingList) {
            System.out.println("ID: " + b.getBookingID() + " | Customer: " + b.getCustomerName() + " | Movie: " + b.getMovieTitle() + " | Type: " + b.getMovieType() + " | Tickets: " + b.getNumberOfTickets() + " | Cost: R" + b.getTotalCost());
        }
    }

    public static void searchBooking() {
        System.out.print("Enter Booking ID: ");
        String id = input.nextLine().trim();
        for (Booking b : bookingList) {
            if (b.getBookingID().equalsIgnoreCase(id)) {
                System.out.println("Found -> ID: " + b.getBookingID() + " | Customer: " + b.getCustomerName() + " | Movie: " + b.getMovieTitle() + " | Cost: R" + b.getTotalCost());
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    public static void processCostCalculation() {
        System.out.print("Enter Ticket Price: R");
        double price = input.nextDouble();
        System.out.print("Enter Number of Tickets: ");
        int tickets = input.nextInt();
        input.nextLine();
        System.out.println("Calculated Cost: R" + calculateCost(price, tickets));
    }

    public static double calculateCost(double price, int tickets) {
        return price * tickets;
    }

    public static void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        String id = input.nextLine().trim();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getBookingID().equalsIgnoreCase(id)) {
                bookingList.remove(i);
                System.out.println("Booking successfully canceled.");
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    public static void countMovieBookings() {
        System.out.print("Enter Movie Title to count: ");
        String searchTitle = input.nextLine().trim();
        int count = 0;
        for (Booking b : bookingList) {
            if (b.getMovieTitle().equalsIgnoreCase(searchTitle)) count++;
        }
        System.out.println("Total matching bookings for '" + searchTitle + "': " + count);
    }

    public static void processDiscountCheck() {
        System.out.print("Enter number of tickets: ");
        int tickets = input.nextInt();
        input.nextLine();
        System.out.println("Qualifies for discount: " + checkDiscount(tickets));
    }

    public static boolean checkDiscount(int tickets) {
        return tickets >= 4;
    }
}

