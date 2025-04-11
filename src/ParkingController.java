import java.util.*;
import java.util.stream.Collectors;

public class ParkingController {
    private static ParkingLot parkingLot;

    public void initialize(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearTerminal();
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if (choice.isEmpty()) {
                continue;
            }
            if (choice.equals("8")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }
            processChoice(choice, scanner);
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void clearTerminal() {
        System.out.println("\n".repeat(15));
    }

    private static void displayMenu() {
        System.out.println("----- Automated Parking Lot System -----");
        System.out.println("1. Create Parking Lot");
        System.out.println("2. Park a Car");
        System.out.println("3. Leave a Slot");
        System.out.println("4. Status of Parking Lot");
        System.out.println("5. Find Registration Numbers by Color");
        System.out.println("6. Find Slot Number by Registration Number");
        System.out.println("7. Find Slot Numbers by Color");
        System.out.println("8. Exit");
        System.out.println("----------------------------------------");
    }

    private static void processChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                handleCreateParkingLot(scanner);
                break;
            case "2":
                handlePark(scanner);
                break;
            case "3":
                handleLeave(scanner);
                break;
            case "4":
                handleStatus();
                break;
            case "5":
                handleRegistrationNumbersByColor(scanner);
                break;
            case "6":
                handleSlotNumberByRegistration(scanner);
                break;
            case "7":
                handleSlotNumbersByColor(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleCreateParkingLot(Scanner scanner) {
        System.out.print("Enter the number of slots: ");
        String input = scanner.nextLine().trim();
        try {
            int capacity = Integer.parseInt(input);
            if (capacity <= 0) {
                System.out.println("Number of slots must be a positive integer.");
                return;
            }
            parkingLot = new ParkingLot(capacity);
            System.out.println("Created a parking lot with " + capacity + " slots");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private static void handlePark(Scanner scanner) {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        System.out.print("Enter registration number: ");
        String regNumber = scanner.nextLine().trim();
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        int slot = parkingLot.parkCar(regNumber, color);
        if (slot == -1) {
            System.out.println("Sorry, parking lot is full");
        } else {
            System.out.println("Allocated slot number: " + slot);
        }
    }

    private static void handleLeave(Scanner scanner) {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        System.out.print("Enter slot number to leave: ");
        String input = scanner.nextLine().trim();
        try {
            int slotNumber = Integer.parseInt(input);
            boolean success = parkingLot.leaveSlot(slotNumber);
            if (success) {
                System.out.println("Slot number " + slotNumber + " is free");
            } else {
                System.out.println("Invalid slot number or slot already free");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid slot number.");
        }
    }

    private static void handleStatus() {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        List<ParkingSlot> occupiedSlots = parkingLot.getOccupiedSlots();
        System.out.println("Slot No. Registration No. Colour");
        for (ParkingSlot slot : occupiedSlots) {
            Car car = slot.getParkedCar();
            System.out.println(slot.getSlotNumber() + "\t\t\t " + car.getRegistrationNumber() + "\t\t\t " + car.getColor());
        }
    }

    private static void handleRegistrationNumbersByColor(Scanner scanner) {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        List<String> regNumbers = parkingLot.getRegistrationNumbersByColor(color);
        if (regNumbers.isEmpty()) {
            System.out.println("Not found");
        } else {
            System.out.println(String.join(", ", regNumbers));
        }
    }

    private static void handleSlotNumberByRegistration(Scanner scanner) {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        System.out.print("Enter registration number: ");
        String regNumber = scanner.nextLine().trim();
        Integer slotNumber = parkingLot.getSlotNumberByRegistration(regNumber);
        if (slotNumber == null) {
            System.out.println("Not found");
        } else {
            System.out.println(slotNumber);
        }
    }

    private static void handleSlotNumbersByColor(Scanner scanner) {
        if (parkingLot == null) {
            System.out.println("Parking lot not created. Please create a parking lot first.");
            return;
        }
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        List<Integer> slotNumbers = parkingLot.getSlotNumbersByColor(color);
        if (slotNumbers.isEmpty()) {
            System.out.println("Not found");
        } else {
            System.out.println(slotNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
        }
    }
}