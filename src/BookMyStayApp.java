<<<<<<< HEAD
import java.util.*;

class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }
}

class AddOnServiceManager {
    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {
        servicesByReservation.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<Service> services = servicesByReservation.get(reservationId);
        if (services == null) return 0.0;

        double total = 0;
        for (Service s : services) {
            total += s.getCost();
        }
        return total;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        AddOnServiceManager manager = new AddOnServiceManager();
        String resId = "Single-1";

        manager.addService(resId, new Service("Breakfast", 500.0));
        manager.addService(resId, new Service("WiFi", 200.0));
        manager.addService(resId, new Service("Late Checkout", 800.0));

        System.out.println("Reservation ID: " + resId);
        System.out.println("Total Add-On Cost: " + manager.calculateTotalServiceCost(resId));
=======
import java.util.ArrayList;
import java.util.List;

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

class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report");
        for (Reservation res : history.getConfirmedReservations()) {
            System.out.println("Guest: " + res.getGuestName() + ", Room Type: " + res.getRoomType());
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        reportService.generateReport(history);
>>>>>>> feature/UC8
    }
}