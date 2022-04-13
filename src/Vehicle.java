import java.util.ArrayList;
import java.util.Date;

abstract public class Vehicle {
    final String plateNumber;
    final int numberOfTires;
    int dailyFee;

    boolean isCanDroppedOff = true;
    boolean isCanDelivered = true;



    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Rent> rentList = new ArrayList<>();

    public Vehicle(String plateNumber, int numberOfTires, int dailyFee) {
        this.plateNumber = plateNumber;
        this.numberOfTires = numberOfTires;
        this.dailyFee = dailyFee;

    }
    public int getDailyFee(Date StartingDate,Date EndingDate){
        int numberOfDays = (int)(EndingDate.getTime()-StartingDate.getTime());
        return numberOfDays*dailyFee;
    }


    public void bookMe(Date StartingDate,Date EndingDate) throws Exception {
        if (isAvailable(StartingDate,EndingDate)){
            bookList.add(new Book(StartingDate,EndingDate));
        }else{
            throw new Exception("SorryWeDon'tHaveThatOneException");
        }

    }
    public void cancelMe(Book booking) throws Exception {
        bookList.remove(booking);
        if (booking.StartingDate.after(new Date())){
            throw new Exception("NoCancellationYouMustPayException");
        }
    }
    public void rentMe(Date StartingDate,Date EndingDate,boolean isCanDelivered,String dropOffLocation) throws Exception {
        if (isAvailable(StartingDate,EndingDate)){
            if (isCanDelivered){
                rentList.add(new Rent(StartingDate,EndingDate,dropOffLocation));
            }else{
                rentList.add(new Rent(StartingDate,EndingDate));
            }

        }else{
            throw new Exception("SorryWeDon'tHaveThatOneException");
        }

    }
    public void dropMe(Rent rent){
        rentList.remove(rent);
        System.out.println(getDailyFee(rent.StartingDate,rent.EndingDate));
    }
    public boolean isAvailable(Date StartingDate,Date EndingDate){
        for (Book book:bookList) {
            if (StartingDate.after(book.StartingDate) && StartingDate.before(book.EndingDate)){
                return false;
            }
            if (EndingDate.after(book.StartingDate)&& EndingDate.before(book.EndingDate)){
                return false;
            }
        }

        for (Rent rent:rentList) {
            if (StartingDate.after(rent.StartingDate) && StartingDate.before(rent.EndingDate)){
                return false;
            }
            if (EndingDate.after(rent.StartingDate)&& EndingDate.before(rent.EndingDate)){
                return false;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        return "plate Number: "+this.plateNumber+" type: "+this.getClass().toString();

    }

}

class Book{
    final Date StartingDate;
    final Date EndingDate;
    final Date bookDate;

    Book(Date startingDate, Date endingDate) {
        StartingDate = startingDate;
        EndingDate = endingDate;
        this.bookDate = new Date();
    }
}
class Rent{
    final Date StartingDate;
    final Date EndingDate;
    String dropOffLocation="";


    Rent(Date startingDate, Date endingDate) {
        StartingDate = startingDate;
        EndingDate = endingDate;
    }
    Rent(Date startingDate, Date endingDate,String dropOffLocation) {
        StartingDate = startingDate;
        EndingDate = endingDate;
        this.dropOffLocation = dropOffLocation;
    }
}

