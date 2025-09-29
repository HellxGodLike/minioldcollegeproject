import java.util.*;

abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    private double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() { return driverName; }
    public String getVehicleNumber() { return vehicleNumber; }
    public double getDistance() { return distance; }

    public abstract double calculateFare();
}

class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 10;
    }
}

class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 20;
    }
}

class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = Double.parseDouble(sc.nextLine());

            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be greater than 0");
            }

            Ride ride;
            if (rideType.equals("bike")) {
                ride = new BikeRide("Ravi Kumar", "BIKE1234", distance);
            } else if (rideType.equals("car")) {
                ride = new CarRide("Suresh Sharma", "CAR5678", distance);
            } else {
                throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
