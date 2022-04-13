import java.util.ArrayList;
import java.util.Date;

public abstract class Person {

    static ArrayList<Person> registeredPerson =  new ArrayList<>();
    static ArrayList<Person> admins =  new ArrayList<>();
    int id;
    static int lastId;
    String name;
    String phone;
    String address;
    String userName;

    public Person( String name, String phone, String address) {
        this.id = lastId++;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public static void display(){
        TestClass.vehiclePark.displayVehicles();
    }
    public static void displayAvailable(Date endingDate, Date startingDate){
        TestClass.vehiclePark.displayAvailableVehicles(endingDate,startingDate);
    }
    public static void register(String name, String phone,String address) throws Exception {
        for (Person person:registeredPerson) {
            if (person.phone.equals(phone)){
                throw new Exception("UserAlreadyRegisteredException");
            }
        }
        Person person =  new RegisteredUser(name,phone,address);
        registeredPerson.add(person);
        System.out.println("your id is:"+person.id);


    }
    @Override
    public String toString() {
        return "name: "+this.name+" phone: "+this.phone+" id: "+this.id;

    }

    static class RegisteredUser extends Person{

        public RegisteredUser( String name, String phone, String address) {
            super(name, phone, address);
        }
    }
    static class Admin extends Person{

        public Admin( String name, String phone, String address , String userName) {
            super(name, phone, address);
            this.userName = userName;
            Person.admins.add(this);
        }
    }

}
