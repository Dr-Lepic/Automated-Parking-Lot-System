import java.util.*;
public class ParkingLot {
    private int capacity;
    private PriorityQueue<Integer> availableSlots;
    private Map<Integer, ParkingSlot> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.availableSlots = new PriorityQueue<>();
        this.slots = new HashMap<>();

        for (int i = 1; i <= capacity; i++) {
            availableSlots.add(i);
            slots.put(i, new ParkingSlot(i));
        }
    }

    public int parkCar(String registrationNumber, String color) {
        if (availableSlots.isEmpty()) {
            return -1;
        }

        int slotNumber = availableSlots.poll();
        ParkingSlot slot = slots.get(slotNumber);
        slot.parkCar(new Car(registrationNumber, color));
        return slotNumber;
    }

    public boolean leaveSlot(int slotNumber) {
        if (slotNumber < 1 || slotNumber > capacity || !slots.get(slotNumber).isOccupied()) {
            return false;
        }

        ParkingSlot slot = slots.get(slotNumber);
        slot.leave();
        availableSlots.add(slotNumber);
        return true;
    }

    public List<ParkingSlot> getOccupiedSlots() {
        List<ParkingSlot> occupied = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot slot = slots.get(i);
            if (slot.isOccupied()) {
                occupied.add(slot);
            }
        }
        return occupied;
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot slot = slots.get(i);
            if (slot.isOccupied()) {
                Car car = slot.getParkedCar();
                if (car.getColor().equalsIgnoreCase(color)) {
                    result.add(car.getRegistrationNumber());
                }
            }
        }
        return result;
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot slot = slots.get(i);
            if (slot.isOccupied()) {
                Car car = slot.getParkedCar();
                if (car.getColor().equalsIgnoreCase(color)) {
                    result.add(slot.getSlotNumber());
                }
            }
        }
        return result;
    }

    public Integer getSlotNumberByRegistration(String registrationNumber) {
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot slot = slots.get(i);
            if (slot.isOccupied()) {
                Car car = slot.getParkedCar();
                if (car.getRegistrationNumber().equals(registrationNumber)) {
                    return slot.getSlotNumber();
                }
            }
        }
        return null;
    }

    public int getAvailableSlotsCount() {
        return availableSlots.size();
    }


}
