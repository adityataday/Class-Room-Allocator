/**
 * This is the class that creates and handles the buildings of the campus
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Building implements Serializable {

    HashMap<Integer, Classroom> hashMap = new HashMap<>();   // This creates the HashMap of classrooms in the building
    private Set<String> AVEquipmentList;  // This keeps a count of the AVEquipmentList in the entire building

    public Building() {
        AVEquipmentList = new TreeSet<>();
    }

    public Set<String> getAVEquipmentList() {
        return AVEquipmentList;
    }

    public void setAVEquipmentList(Set<String> AVEquipmentList) {
        this.AVEquipmentList = AVEquipmentList;
    }

    public void addClassroom(int roomNumber, Classroom classroom) {
        if (roomNumber == 0 || hashMap.containsKey(roomNumber)) {
            throw new IllegalArgumentException("Incorrect action");
        }
        hashMap.put(roomNumber, classroom);

    }

    public Classroom getClassroom(int roomNumber) {
        if (hashMap.containsKey(roomNumber) == false)
            return null;
        else
            return hashMap.get(roomNumber);
    }

    public void removeClassroom(int roomNumber) {
        if (hashMap.containsKey(roomNumber) == false) {
            throw new IllegalArgumentException("Incorrect action");
        } else {
            hashMap.remove(roomNumber);
        }

    }

    /**
     * This is the method that prints all the AVEquipments present in the building
     */
    public void printAVlist() {
        Set<Integer> keySet = hashMap.keySet();
        for (Integer key : keySet)
            setAVEquipmentList(getClassroom(key).getAVEquipmentList());
        System.out.println(getAVEquipmentList());
    }
}
