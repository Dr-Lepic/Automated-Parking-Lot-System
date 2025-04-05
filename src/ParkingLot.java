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


}
