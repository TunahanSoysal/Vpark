import java.util.Date;

public abstract class Truck extends Vehicle{
    int loadingCap;

    public Truck(String plateNumber, int numberOfTires, int dailyFee,int loadingCap) {
        super(plateNumber, numberOfTires, dailyFee);
        this.loadingCap = loadingCap;
        isCanDelivered = false;
        isCanDroppedOff = false;

    }

    @Override
    public void rentMe(Date StartingDate,Date EndingDate, boolean isCanDelivered, String dropOffLocation) throws Exception {
        for (Book book:bookList) {
            if (new Date().getTime()-book.bookDate.getTime()>604800000) {
                if (book.StartingDate == StartingDate ) {
                    if (book.EndingDate == EndingDate) {
                        super.rentMe(StartingDate,EndingDate,isCanDelivered, dropOffLocation);
                    }
                }
            }
        }


    }

    public void loadMe(int loadAmount) throws Exception {
        if (loadAmount>this.loadingCap){
            throw new Exception("OverWeightException");
        }

    }




    public static class TransportTrucks extends Truck {
        boolean isAbroad;


        public TransportTrucks(String plateNumber, int numberOfTires, int dailyFee,boolean isAbroad,int loadingCap) {
            super(plateNumber, numberOfTires, dailyFee, loadingCap);
            this.isAbroad=isAbroad;

        }
    }

    public static class SmallTrucks extends Truck {


        public SmallTrucks(String plateNumber, int numberOfTires, int dailyFee,int loadingCap) {
            super(plateNumber, numberOfTires, dailyFee, loadingCap);
        }
    }
}