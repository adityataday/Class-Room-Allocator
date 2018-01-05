/**
 * This is the Driver class of the program.
 */

import java.io.*;
import java.util.*;


public class RoomLookup {
    public static void main(String[] args) {
        System.out.println("Welcome to SBGetARoom, Stony Brook's premium room lookup system.\n\n");
        Scanner userInput = new Scanner(System.in);
        String choice;

        Campus campus = new Campus();

        int whiteBoardCount = 0;
        int chalkBoardCount = 0;
        double totalClassRoomCount = 0;
        int totalSeats = 0;

        System.out.println("Do you have a File to read from? (Y/N): ");
        String intChoice = userInput.next();

        if (intChoice.equalsIgnoreCase("Y")) {

            /**
             * Implementing the Serializable Interface for reading an object from a file
             */

            System.out.println("Enter a file name: ");
            String fileName = userInput.next();

            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
                campus = (Campus) is.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        do {
            System.out.printf("Main Menu:\n\n");
            System.out.printf("A) Add a building \n");
            System.out.printf("D) Delete a building \n");
            System.out.printf("E) Edit a building\n");
            System.out.printf("F) Find a room\n");
            System.out.printf("S) Search for rooms\n");
            System.out.printf("C) List all buildings on Campus\n");
            System.out.printf("L) List building details\n");
            System.out.printf("Q) Quit\n");

            System.out.printf("Select an Option: ");
            choice = userInput.next();


            /**
             * Adding a new building to the campus
             */

            if (choice.equalsIgnoreCase("A")) {
                Building a = new Building();
                System.out.println("Please Enter a building name: ");
                String buildingName = userInput.next();
                if (campus.hashMap.containsKey(buildingName)) {
                    System.out.println("Building Already Exists");
                } else {
                    campus.addBuilding(buildingName, a);
                    System.out.printf("Building %s added\n", buildingName);
                }
            }

            /**
             * Deleting a building from campus
             */

            else if (choice.equalsIgnoreCase("D")) {

                System.out.println("Please Enter a building name: ");
                String buildingName = userInput.next();
                campus.removeBuilding(buildingName);
                System.out.printf("Building %s Removed\n", buildingName);

            }

            /**
             * Editing a building on campus
             */

            else if (choice.equalsIgnoreCase("E")) {

                System.out.println("Please Enter a building name: ");
                String buildingName = userInput.next();
                if (campus.getBuilding(buildingName) == null)
                    System.out.println("Building not found");
                else {
                    System.out.printf("Building %s selected", buildingName);
                    System.out.println("Options:");
                    System.out.printf("\tA) Add room\n");
                    System.out.printf("\tD) Delete room\n");
                    System.out.printf("\tE) Edit room\n");

                    choice = userInput.next();

                    if (choice.equalsIgnoreCase("A")) {
                        Classroom a = new Classroom();
                        totalClassRoomCount++;
                        System.out.println("Please enter a room number: ");
                        int roomNumber = userInput.nextInt();
                        if (campus.getBuilding(buildingName).hashMap.containsKey(roomNumber)) {
                            System.out.println("Classroom Already Exists");
                        } else {
                            System.out.println("Please enter number of seats: ");
                            int numSeats = userInput.nextInt();
                            a.setNumSeats(numSeats);
                            totalSeats += numSeats;

                            System.out.println("Please enter AV Equipment (separated by commas): ");
                            String AVEquipments = userInput.next();
                            String[] split = AVEquipments.split(",");
                            for (int i = 0; i < split.length; i++)
                                a.setAVEquipmentList(split[i]);

                            System.out.println("Does it have a whiteboard?(Y/n): ");
                            String ans = userInput.next();
                            if (ans.equalsIgnoreCase("Y")) {
                                a.setHasWhiteboard(true);
                                whiteBoardCount++;
                            }
                            System.out.println("Does it have a Chalkboard;(Y/n): ");
                            ans = userInput.next();
                            if (ans.equalsIgnoreCase("Y")) {
                                a.setHasChalkboard(true);
                                chalkBoardCount++;
                            }

                            campus.getBuilding(buildingName).addClassroom(roomNumber, a);
                        }
                    } else if (choice.equalsIgnoreCase("D")) {
                        totalClassRoomCount--;
                        System.out.println("Please enter a room number: ");
                        int roomNumber = userInput.nextInt();
                        totalSeats = totalSeats - campus.getBuilding(buildingName).getClassroom(roomNumber).getNumSeats();
                        if (campus.getBuilding(buildingName).getClassroom(roomNumber).isHasChalkboard())
                            chalkBoardCount--;
                        else
                            whiteBoardCount--;
                        campus.getBuilding(buildingName).removeClassroom(roomNumber);

                    } else if (choice.equalsIgnoreCase("E")) {
                        System.out.println("Please enter a room number: ");
                        int roomNumber = userInput.nextInt();
                        totalSeats -= campus.getBuilding(buildingName).getClassroom(roomNumber).getNumSeats();
                        System.out.println("Please enter number of seats: ");
                        int numSeats = userInput.nextInt();
                        totalSeats += numSeats;

                        campus.getBuilding(buildingName).getClassroom(roomNumber).getAVEquipmentList().clear();
                        System.out.println("Please enter AV Equipment (separated by commas): ");
                        String AVEquipments = userInput.next();
                        String[] split = AVEquipments.split(",");
                        for (int i = 0; i < split.length; i++)
                            campus.getBuilding(buildingName).getClassroom(roomNumber).setAVEquipmentList(split[i]);

                        if (campus.getBuilding(buildingName).getClassroom(roomNumber).isHasChalkboard())
                            chalkBoardCount--;
                        else
                            whiteBoardCount--;

                        System.out.println("Does it have a whiteboard?(Y/n): ");
                        String ans = userInput.next();
                        if (ans.equalsIgnoreCase("Y")) {
                            campus.getBuilding(buildingName).getClassroom(roomNumber).setHasWhiteboard(true);
                            whiteBoardCount++;
                        }
                        System.out.println("Does it have a Chalkboard;(Y/n): ");
                        ans = userInput.next();
                        if (ans.equalsIgnoreCase("Y")) {
                            campus.getBuilding(buildingName).getClassroom(roomNumber).setHasChalkboard(true);
                            chalkBoardCount++;
                        }

                    }
                }


            }

            /**
             * Listing Details of a specified building
             */

            else if (choice.equalsIgnoreCase("L")) {

                ArrayList<Integer> rooms = new ArrayList<>();
                System.out.println("Please Enter a building name: ");
                String buildingName = userInput.next();
                if (campus.getBuilding(buildingName) == null)
                    System.out.println("Building not found");
                else {
                    System.out.println("Details:");
                    Set<Integer> keySet = campus.getBuilding(buildingName).hashMap.keySet();
                    System.out.print("Rooms: ");
                    for (Integer key : keySet)
                        rooms.add(key);
                    for (int i = 0; i < rooms.size(); i++) {
                        System.out.print(rooms.get(i) + ",");
                    }
                    System.out.printf("\nTotal Seats: %d\n", totalSeats);
                    System.out.printf("%.2f percent of rooms have Whiteboards\n", (whiteBoardCount / totalClassRoomCount) * 100);
                    System.out.printf("%.2f percent of rooms have Chalkboards\n", (chalkBoardCount / totalClassRoomCount) * 100);
                    System.out.print("AV Equipments Present: ");
                    campus.getBuilding(buildingName).printAVlist();
                }

            }

            /**
             * Finding a room on campus with building name and room number
             */

            else if (choice.equalsIgnoreCase("F")) {
                Scanner name = new Scanner(System.in);
                System.out.println("Please enter a room name: ");
                String roomName = name.nextLine();
                String[] room = roomName.split(" ");
                int roomNum = Integer.parseInt(room[1]);
                System.out.println(campus.getBuilding(room[0]).getClassroom(roomNum));

            }

            /**
             * Searching for a room on campus with specific details
             */

            else if (choice.equalsIgnoreCase("S")) {
                System.out.println("Options: ");
                System.out.printf("\tB) Blackboard\n");
                System.out.printf("\tW) Whiteboard\n");
                System.out.printf("\tA) AV Equipment\n");

                choice = userInput.next();

                if (choice.equalsIgnoreCase("A")) {
                    System.out.println("Please Enter a Keyword: ");
                    Scanner in = new Scanner(System.in);
                    String AVKeyword = in.next();
                    ArrayList<String> AVkey = new ArrayList<>(Arrays.asList(AVKeyword.split(",")));

                    // Used to access every building on campus
                    Set<String> buildingSet = campus.hashMap.keySet();
                    for (String buildingKey : buildingSet) {

                        Set<Integer> classRoomSet = campus.getBuilding(buildingKey).hashMap.keySet();

                        ArrayList<Integer> rooms = new ArrayList<>();
                        // Access every room in the building
                        for (Integer classRoomKey : classRoomSet) {
                            Classroom c = campus.getBuilding(buildingKey).getClassroom(classRoomKey);

                            // If user requirements greater than classroom features
                            if (AVkey.size() > c.getAVEquipmentList().size()) {
                                continue;
                            }

                            boolean eligible = true;

                            // Iterate through the AVList and check if each exists in the room

                            for (int i = 0; i < AVkey.size(); i++) {
                                if (!eligible)
                                    break;

                                if (!c.getAVEquipmentList().contains(AVkey.get(i)))
                                    eligible = false;
                            }

                            if (eligible) {
                                rooms.add(classRoomKey);
                            }

                        }
                        if (rooms.size() > 0)
                            System.out.println(buildingKey + ": " + rooms.toString());
                        else
                            System.out.println("No rooms found");
                    }

                } else if (choice.equalsIgnoreCase("B")) {
                    Set<String> buildingSet = campus.hashMap.keySet();

                    for (String buildingKey : buildingSet) {
                        Set<Integer> classRoomSet = campus.getBuilding(buildingKey).hashMap.keySet();

                        ArrayList<Integer> rooms = new ArrayList<>();

                        for (Integer classRoomKey : classRoomSet) {

                            Classroom c = campus.getBuilding(buildingKey).getClassroom(classRoomKey);

                            if (c.isHasChalkboard())
                                rooms.add(classRoomKey);


                            if (rooms.size() > 0) {
                                System.out.println(buildingKey + ": " + rooms.toString());
                            } else {
                                System.out.println("No rooms found");
                            }
                        }
                    }
                } else if (choice.equalsIgnoreCase("W")) {
                    Set<String> buildingSet = campus.hashMap.keySet();

                    for (String buildingKey : buildingSet) {
                        Set<Integer> classRoomSet = campus.getBuilding(buildingKey).hashMap.keySet();

                        ArrayList<Integer> rooms = new ArrayList<>();

                        for (Integer classRoomKey : classRoomSet) {

                            Classroom c = campus.getBuilding(buildingKey).getClassroom(classRoomKey);

                            if (c.isHasWhiteboard())
                                rooms.add(classRoomKey);


                            if (rooms.size() > 0) {
                                System.out.println(buildingKey + ": " + rooms.toString());
                            } else {
                                System.out.println("No rooms found");
                            }
                        }
                    }
                }
            }

            /**
             * Listing all the building on campus
             */

            else if (choice.equalsIgnoreCase("C")) {

                Set<String> buildingSet = campus.hashMap.keySet();
                for (String buildingKey : buildingSet) {
                    Set<Integer> classRoomSet = campus.getBuilding(buildingKey).hashMap.keySet();
                    ArrayList<Integer> rooms = new ArrayList<>();
                    for (Integer classRoomKey : classRoomSet) {
                        rooms.add(classRoomKey);
                    }
                    if (rooms.size() > 0)
                        System.out.println(buildingKey + ": " + rooms.toString());

                }
            }
        }
        while (!choice.equalsIgnoreCase("q"));

        System.out.printf("S) Save\n");
        System.out.printf("D) Don't Save\n");


        String saveChoice = userInput.next();

        if (saveChoice.equalsIgnoreCase("S")) {

            /**
             * Implementing the Serializable Interface for writing an object from a file
             */

            System.out.println("Enter a file name: ");
            String fileName = userInput.next();
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                os.writeObject(campus);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("File saved");
        }

        System.out.println("Goodbye!");
    }
}

