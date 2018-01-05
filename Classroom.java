/**
 * This is the class that creates and handles the classroom of the buildings
 */


import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;


public class Classroom implements Serializable {

    private boolean hasWhiteboard;
    private boolean hasChalkboard;
    private int numSeats;
    private Set<String> AVEquipmentList;

    /**
     * This is the constructor of the object Classroom that initializes the instance variables of the class
     */
    public Classroom() {

        hasWhiteboard = false;
        hasChalkboard = false;
        numSeats = 0;
        AVEquipmentList = new TreeSet<>();
    }

    public Set<String> getAVEquipmentList() {
        return AVEquipmentList;
    }

    public void setAVEquipmentList(String AVEquipmentList) {
        this.AVEquipmentList.add(AVEquipmentList);
    }

    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    public void setHasWhiteboard(boolean hasWhiteboard) {
        this.hasWhiteboard = hasWhiteboard;
    }

    public boolean isHasChalkboard() {
        return hasChalkboard;
    }

    public void setHasChalkboard(boolean hasChalkboard) {
        this.hasChalkboard = hasChalkboard;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public String toString() {
        return String.format("Seats: %d\nWhiteboard: %b\nChalkboard: %b\nAV Equipment present: %s", getNumSeats(), isHasWhiteboard(), isHasChalkboard(), getAVEquipmentList());
    }

}

