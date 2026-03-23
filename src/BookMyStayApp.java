import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.getRoomAvailability().containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Booking Validation");
        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);
            System.out.println("Validation successful for " + guestName);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}