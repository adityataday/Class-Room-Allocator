/**
 * This is the class that creates and handles the campus of the program
 */

import java.io.Serializable;
import java.util.HashMap;

public class Campus implements Serializable {

    HashMap<String, Building> hashMap = new HashMap<>();   // This creates the HashMap of buildings on campus

    /**
     * Default Constructor
     */
    public Campus() {

    }

    public void addBuilding(String buildingName, Building building) {
        if (buildingName == null || hashMap.containsKey(buildingName)) {
            throw new IllegalArgumentException("Incorrect action");
        }
        hashMap.put(buildingName, building);

    }

    public Building getBuilding(String buildingName) {
        if (hashMap.containsKey(buildingName) == false)
            return null;
        else
            return hashMap.get(buildingName);
    }

    public void removeBuilding(String buildingName) {
        if (hashMap.containsKey(buildingName) == false) {
            throw new IllegalArgumentException("Incorrect action");
        } else {
            hashMap.remove(buildingName);
        }
    }


}

