import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VehiclePark {
    ArrayList<Vehicle> vehicles = new ArrayList();

    public void displayVehicles(){
        for (Vehicle vehicle:vehicles) {
            System.out.println(vehicle+"id: "+vehicles.indexOf(vehicle));
        }

    }
    public void displayAvailableVehicles(Date EndingDate,Date StartingDate){
        for (Vehicle vehicle:vehicles) {
            if (vehicle.isAvailable(StartingDate,EndingDate)) {
                System.out.println(vehicle);
            }
        }
    }
    public void addVehicle() throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("press 1 for car and 2 for truck");
        int answer = scn.nextInt();
        Vehicle vehicle = null;


        switch (answer) {
            case 1 -> {
                System.out.println("which car type do you want? 1 for sports, 2 for SW or 3 for SUV");
                int answer2 = scn.nextInt();
                switch (answer2) {
                    case 1 -> {
                        System.out.println("please enter plate number,number of tires,daily fee,color, seating cap,number of doors, horse power respectively");
                        String plateNumber = scn.next();
                        int numberOfTires = scn.nextInt();
                        int dailyFee = scn.nextInt();
                        String color = scn.next();
                        int seatingCap = scn.nextInt();
                        int numberOfDoors = scn.nextInt();
                        int horsePower = scn.nextInt();
                        vehicle = new Car.Sports(plateNumber, numberOfTires, dailyFee, color, seatingCap, numberOfDoors, horsePower);
                    }
                    case 2 -> {
                        System.out.println("please enter plate number,number of tires,daily fee,color, seating cap,number of doors,loading capacity  respectively");
                        String plateNumber2 = scn.next();
                        int numberOfTires2 = scn.nextInt();
                        int dailyFee2 = scn.nextInt();
                        String color2 = scn.next();
                        int seatingCap2 = scn.nextInt();
                        int numberOfDoors2 = scn.nextInt();
                        int loadingCap = scn.nextInt();
                        vehicle = new Car.StationWagon(plateNumber2, numberOfTires2, dailyFee2, color2, seatingCap2, numberOfDoors2, loadingCap);
                    }
                    case 3 -> {
                        System.out.println("please enter plate number,number of tires,daily fee,color, seating cap,number of doors,wheel drive  respectively");
                        String plateNumber3 = scn.next();
                        int numberOfTires3 = scn.nextInt();
                        int dailyFee3 = scn.nextInt();
                        String color3 = scn.next();
                        int seatingCap3 = scn.nextInt();
                        int numberOfDoors3 = scn.nextInt();
                        String wheelDrive = scn.next();
                        vehicle = new Car.SUV(plateNumber3, numberOfTires3, dailyFee3, color3, seatingCap3, numberOfDoors3, wheelDrive);
                    }
                    default -> throw new Exception("CanNotAddSuchAVehicleException");
                }

            }
            case 2 -> {
                System.out.println("which truck type do you want? 1 for Small Trucks, 2 for Transport Truck ");
                int answer3 = scn.nextInt();
                switch (answer3) {
                    case 1 -> {
                        System.out.println("please enter plate number, number Of Tires, daily Fee,loading capacity");
                        String plateNumber = scn.next();
                        int numberOfTires = scn.nextInt();
                        int dailyFee = scn.nextInt();
                        int loadingCap = scn.nextInt();
                        vehicle = new Truck.SmallTrucks(plateNumber, numberOfTires, dailyFee, loadingCap);
                    }
                    case 2 -> {
                        System.out.println("please enter plate number, number Of Tires, daily Fee,goes abroad(true) or not(false),loading capacity");
                        String plateNumber2 = scn.next();
                        int numberOfTires2 = scn.nextInt();
                        int dailyFee2 = scn.nextInt();
                        int loadingCap2 = scn.nextInt();
                        boolean isAbroad = scn.hasNext();
                        vehicle = new Truck.TransportTrucks(plateNumber2, numberOfTires2, dailyFee2, isAbroad, loadingCap2);
                    }
                    default -> throw new Exception("CanNotAddSuchAVehicleException");
                }
            }
        }
        if(vehicle!=null){vehicles.add(vehicle);
        }else{
            throw new Exception("CanNotAddSuchAVehicleException");
        }
    }
    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);

    }
    public String bookVehicle(Vehicle vehicle,Date startingDate,Date endingDate) {
        try {
            vehicle.bookMe(startingDate,endingDate);
            return "Successful";
        }catch (Exception e){
            return e.getMessage();
        }



    }
    public String cancelBooking(Book booking,Vehicle vehicle){
        try{
            vehicle.cancelMe(booking);
            return "Successful";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    public String rentVehicle(Vehicle vehicle,Date startingDate,Date endingDate, String dropOffLocation){
        try{
            vehicle.rentMe(startingDate,endingDate, vehicle.isCanDelivered,dropOffLocation);
            return "Successful";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    public String dropVehicle(Vehicle vehicle, Rent rent){
        try{
            vehicle.dropMe(rent);
            return "Successful";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public String load(int loadAmount, Truck truck){
        try{
            truck.loadMe(loadAmount);
            return "Successful";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    public void dailyReport(String fileName){
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            out.write("vehicles:".getBytes(StandardCharsets.UTF_8));
            for (Vehicle vehicle:vehicles){
                out.write(vehicle.toString().getBytes(StandardCharsets.UTF_8));
            }
            out.write("registeredUsers:".getBytes(StandardCharsets.UTF_8));
            for (Person registeredUser:Person.registeredPerson){
                out.write(registeredUser.toString().getBytes(StandardCharsets.UTF_8));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
