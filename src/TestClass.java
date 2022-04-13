
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestClass {
    public static VehiclePark vehiclePark = new VehiclePark();
    static Person loggedInUser;
   public static void main(String[] args)  {

        Scanner scn = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Person.Admin suheyl = new Person.Admin("süheyl","12345","kazakistan","üzqünüm");
       System.out.println(Person.admins);


        while(true) {

            if (loggedInUser == null){
                if (!TestClass.Menu1(formatter,scn)){
                    break;
                }
            }else if(Person.registeredPerson.contains(loggedInUser)){
                TestClass.Menu4(formatter,scn);

            }else if(Person.admins.contains(loggedInUser)){
                TestClass.Menu5(formatter,scn);

            }

        }


    }
    public static boolean Menu1(SimpleDateFormat formatter,Scanner scn){
        System.out.println("""
                (Press 1) Display all vehicles
                • (Press 2) Display available vehicles: asks the dates, returns to the main menu
                • (Press 3) Register me: asks the name and contact info, returns to the main menu.
                • (Press 4) Continue as registered user: asks the id, and calls menu4.
                • (Press 5) Continue as admin: asks userName, and calls menu5.""");
        int input = scn.nextInt();
        menu:
        switch (input) {
            case 1:
                vehiclePark.displayVehicles();

                break;
            case 2:
                System.out.println("enter starting date:");
                String startingDate = scn.next();
                System.out.println("enter ending Date:");
                String endingDate = scn.next();
                try {
                    vehiclePark.displayAvailableVehicles(formatter.parse(endingDate), formatter.parse(startingDate));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 3:
                System.out.println("enter your name,surname and address respectively");
                String name = scn.next();
                String phone = scn.next();
                String address = scn.next();
                try {
                    Person.register(name, phone, address);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


                break;
            case 4:
                System.out.println("please enter your Id:");
                int id = scn.nextInt();
                for (Person person : Person.registeredPerson) {
                    if (person.id == id) {
                        loggedInUser = person;
                        System.out.println("you have successfully logged in!");
                        break menu;
                    }
                }
                System.out.println("sorry we can not find your id, please register first");



                break;
            case 5:
                System.out.println("please enter your user name:");
                String userName = scn.next();
                for (Person person : Person.admins) {
                    if (person.userName.equals(userName)) {
                        loggedInUser = person;
                        System.out.println("you have successfully logged in as admin!");
                        break menu;

                    }
                }
                System.out.println("you are not an admin, please register as normal person with your id");


                break;
            case 6:

                return false;
            default:
                System.out.println("please enter valid number");




        }
        return true;
    }
    public static void Menu4(SimpleDateFormat formatter,Scanner scn)  {
        System.out.println("""
                • (Press 1) Display all vehicles
                • (Press 2) Display available vehicles: asks the dates, returns to the Menu4
                • (Press 3) Display available vehicles by type: asks the dates, the vehicle type, returns to the Menu4
                • (Press 4..n) Book Vehicle, cancel my booking, rent vehicle, drop vehicle. All returns to Menu4.
                • (Press n+1) Quit: returns back to main menu.""");
        int input = scn.nextInt();

        switch (input) {
            case 1 -> vehiclePark.displayVehicles();
            case 2 -> {
                System.out.println("enter starting date:");
                String startingDate = scn.next();
                System.out.println("enter ending Date:");
                String endingDate = scn.next();
                try {
                    vehiclePark.displayAvailableVehicles(formatter.parse(endingDate), formatter.parse(startingDate));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                System.out.println("enter starting date:");
                String startingDate2 = scn.next();
                System.out.println("enter ending Date:");
                String endingDate2 = scn.next();
                try {
                    Class vehicleType = TestClass.vehicleTypeSwitch();
                    for (Vehicle vehicle : vehiclePark.vehicles) {
                        if (vehicle.isAvailable(formatter.parse(startingDate2), formatter.parse(endingDate2))) {
                            if (vehicle.getClass() == vehicleType) {
                                System.out.println(vehicle + " id:" + vehiclePark.vehicles.indexOf(vehicle));
                            }

                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 4 -> {
                System.out.println("please enter vehicle id:");
                int id = scn.nextInt();
                System.out.println("enter starting date:");
                String startingDate3 = scn.next();
                System.out.println("enter ending Date:");
                String endingDate3 = scn.next();
                try {


                    System.out.println(vehiclePark.bookVehicle(vehiclePark.vehicles.get(id), formatter.parse(startingDate3), formatter.parse(endingDate3)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 5 -> {
                System.out.println("please enter your vehicles id");
                int id2 = scn.nextInt();
                Vehicle vehicle = vehiclePark.vehicles.get(id2);
                System.out.println("bookings for your vehicle are :");
                for (Book book : vehicle.bookList) {
                    System.out.println(book + " id:" + vehicle.bookList.indexOf(book));

                }
                System.out.println("please enter your booking id:");
                int id3 = scn.nextInt();
                System.out.println(vehiclePark.cancelBooking(vehicle.bookList.get(id3), vehicle));
            }
            case 6 -> {
                System.out.println("please enter your vehicle id:");
                int id4 = scn.nextInt();
                Vehicle vehicle2 = vehiclePark.vehicles.get(id4);
                System.out.println("please enter starting date");
                String endingDate4 = scn.next();
                System.out.println("please enter ending date");
                String startingDate4 = scn.next();
                String dropOffLocation = "";
                if (vehicle2.isCanDelivered) {
                    System.out.println("please enter drop off location");
                    dropOffLocation = scn.next();
                }
                try {


                    System.out.println(vehiclePark.rentVehicle(vehicle2, formatter.parse(startingDate4), formatter.parse(endingDate4), dropOffLocation));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 7 -> {
                System.out.println("please enter your vehicles id");
                int id5 = scn.nextInt();
                Vehicle vehicle3 = vehiclePark.vehicles.get(id5);
                System.out.println("rents for your vehicle are :");
                for (Rent rent : vehicle3.rentList) {
                    System.out.println(rent + " id:" + vehicle3.rentList.indexOf(rent));

                }
                System.out.println("please enter your rent id:");
                int id6 = scn.nextInt();
                System.out.println(vehiclePark.dropVehicle(vehicle3, vehicle3.rentList.get(id6)));
            }
            case 8 -> loggedInUser = null;
        }

    }
    public static void Menu5(SimpleDateFormat formatter,Scanner scn){
        System.out.println("""
                (Press 1) Display all
                • (Press 2) Display available vehicles: asks the dates, returns to the Menu5
                • (Press 3) Add a new vehicle to the system: asks the necessary attribute values, and adds the item\s
                into inventory. Returns to Menu5.
                • (Press 4) Remove vehicle: asks which item to be removed, removes from the inventory. Returns\s
                to Menu5.
                • (Press 5) Reports: creates daily reports. Returns to Menu5.
                • (Press 6) Quit: returns back to main menu. Note that, if necessary all files should be updated""");
        int input = scn.nextInt();
        switch (input) {
            case 1 -> vehiclePark.displayVehicles();
            case 2 -> {
                System.out.println("enter starting date:");
                String startingDate = scn.next();
                System.out.println("enter ending Date:");
                String endingDate = scn.next();
                try {
                    vehiclePark.displayAvailableVehicles(formatter.parse(endingDate), formatter.parse(startingDate));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                try{vehiclePark.addVehicle();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            case 4 -> {
                System.out.println("please enter your vehicle id:");
                int id = scn.nextInt();
                vehiclePark.removeVehicle(vehiclePark.vehicles.get(id));
            }
            case 5 -> vehiclePark.dailyReport("dailyReport.txt");
            case 6 -> loggedInUser = null;
        }
    }
    public static Class vehicleTypeSwitch() throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("press 1 for car and 2 for truck");
        int answer = scn.nextInt();
        switch (answer) {
            case 1 -> {
                System.out.println("which car type do you want? 1 for sports, 2 for SW or 3 for SUV");
                int answer2 = scn.nextInt();
                switch (answer2) {
                    case 1:
                        return Car.Sports.class;

                    case 2:
                        return Car.StationWagon.class;
                    case 3:
                        return Car.SUV.class;
                }
            }
            case 2 -> {
                System.out.println("which truck type do you want? 1 for Small Trucks, 2 for Transport Truck ");
                int answer3 = scn.nextInt();
                switch (answer3) {
                    case 1:
                        return Truck.SmallTrucks.class;

                    case 2:
                        return Truck.TransportTrucks.class;

                }
            }
        }
        throw new Exception("CannotFindSuchAVehicleException");
    }



}
