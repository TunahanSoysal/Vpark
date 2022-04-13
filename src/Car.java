public abstract class Car extends Vehicle{
    String color;
    int seatingCap;
    int numOfDoors;

    public Car(String plateNumber, int numberOfTires, int dailyFee,String color, int seatingCap,int numOfDoors) {
        super(plateNumber, numberOfTires, dailyFee);
        this.color = color;
        this.seatingCap = seatingCap;
        this.numOfDoors = numOfDoors;
    }


    public static class Sports extends Car {
        int horsePower;


        public Sports(String plateNumber, int numberOfTires, int dailyFee, String color, int seatingCap, int numOfDoors,int horsePower) {
            super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
            this.horsePower = horsePower;
        }
    }

    public static class StationWagon extends Car {
        int loadingCap;

        public StationWagon(String plateNumber, int numberOfTires, int dailyFee, String color, int seatingCap, int numOfDoors,int loadingCap) {
            super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
            this.loadingCap = loadingCap;
        }


        public void loadMe(int loadAmount) throws Exception {
            if (loadAmount>this.loadingCap){
                throw new Exception("OverWeightException");
            }
        }


    }

    public static class SUV extends Car {
        String typeOfWD;


        public SUV(String plateNumber, int numberOfTires, int dailyFee, String color, int seatingCap, int numOfDoors,String typeOfWD) {
            super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
            this.typeOfWD = typeOfWD;
            isCanDelivered =false;
            isCanDroppedOff = false;
        }
    }
}