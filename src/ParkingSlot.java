public class ParkingSlot {
    private int slotNumber;
    private Car parkedCar;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.parkedCar = null;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public boolean isOccupied() {
        return parkedCar != null;
    }

    public void parkCar(Car car) {
        if(!isOccupied()) {
            parkedCar = car;
        }
        else{
            System.out.println("Slot is already occupied by " + parkedCar.getRegistrationNumber());
        }
    }

    public void leave() {
        parkedCar = null;
    }
}