import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
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

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class RoomAllocationService {
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
        assignedRoomsByType.put("Single", new HashSet<>());
        assignedRoomsByType.put("Double", new HashSet<>());
        assignedRoomsByType.put("Suite", new HashSet<>());
    }

    private String generateRoomId(String roomType) {
        int nextNumber = assignedRoomsByType.get(roomType).size() + 1;
        return roomType + "-" + nextNumber;
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String type = reservation.getRoomType();
        int currentStock = inventory.getRoomAvailability().get(type);

        if (currentStock > 0) {
            String roomId = generateRoomId(type);
            allocatedRoomIds.add(roomId);
            assignedRoomsByType.get(type).add(roomId);
            inventory.updateAvailability(type, currentStock - 1);

            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() +
                    ", Room ID: " + roomId);
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        List<Reservation> requests = Arrays.asList(
                new Reservation("Abhi", "Single"),
                new Reservation("Subha", "Single"),
                new Reservation("Vanmathi", "Suite")
        );

        for (Reservation res : requests) {
            allocationService.allocateRoom(res, inventory);
        }
    }
}