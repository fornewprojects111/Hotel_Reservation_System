package hotel_reservation_system;
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + 
               ", Category: " + category + 
               ", Available: " + (isAvailable ? "Yes" : "No") + 
               ", Price: $" + price;
    }
}

class Booking {
    String guestName;
    int roomNumber;
    String paymentStatus;

    Booking(String guestName, int roomNumber, String paymentStatus) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Guest Name: " + guestName + 
               ", Room Number: " + roomNumber + 
               ", Payment Status: " + paymentStatus;
    }
}

public class HotelReservationSystem {
    private static final ArrayList<Room> rooms = new ArrayList<>();
    private static final ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Hotel Reservation System!");

        while (!exit) {
            System.out.println("\n1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", true, 100.0));
        rooms.add(new Room(102, "Single", true, 100.0));
        rooms.add(new Room(201, "Double", true, 150.0));
        rooms.add(new Room(202, "Double", false, 150.0));
        rooms.add(new Room(301, "Suite", true, 300.0));
        rooms.add(new Room(302, "Suite", true, 300.0));
    }

    private static void searchRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null && selectedRoom.isAvailable) {
            selectedRoom.isAvailable = false;
            System.out.println("Room booked successfully. Processing payment...");
            bookings.add(new Booking(name, roomNumber, "Paid"));
            System.out.println("Payment successful. Booking confirmed!");
        } else {
            System.out.println("Room not available or does not exist. Please try again.");
        }
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("\n--- Booking Details ---");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}
