package Lesson7;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

class Player {


    public static HashMap<Integer, Destination> destinations = new HashMap<>();

    public static class Destination {
        private int id = (-1);
        private int floor =(-1);
        private int position =(-1);
        private boolean isExit = false;
        private boolean isClone = false;

        public Destination(){
            this.id = (-1);
            this.floor =(-1);
            this.position =(-1);
            this.isExit = false;
            this.isClone = false;
        }

        public void set(Integer id, Integer floor, Integer position) {
            this.setId(id);
            this.setFloor(floor);
            this.setPosition(position);
        }

        public void setExit(Integer floor, Integer position) {
            this.setId(-1);
            this.setFloor(floor);
            this.setPosition(position);
            this.setExit(true);
        }
        public void setClone(Integer floor, Integer position) {
            int i= 1;
            while(destinations.containsKey(i)){
                i++;
            }
            this.setId(i);
            this.setFloor(floor);
            this.setPosition(position);
            this.setClone(true);
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFloor() {
            return floor;
        }

        public void setFloor(Integer floor) {
            this.floor = floor;
        }

        public Integer getPosition() {
            return position;
        }

        public void setPosition(Integer position) {
            this.position = position;
        }

        public boolean isExit() {
            return isExit;
        }

        public void setExit(boolean exit) {
            isExit = exit;
        }

        public boolean isClone() {
            return isClone;
        }

        public void setClone(boolean clone) {
            isClone = clone;
        }
    }

    public static boolean isFloorChanged(int lastFloor, int cloneFloor){
        return lastFloor!=cloneFloor?true:false;
    }

    public static void addBlockerAsDestination(int cloneFloor, int clonePosition){
        Destination destination = new Destination();
        destination.setClone(cloneFloor, clonePosition);
        destinations.put(destination.id, destination);
    }
    public static boolean isObjectInCloneDirection(int cloneFloor, int clonePosition, String cloneDirection ){
        boolean result = false;
        for(Destination destination: destinations.values()){
            if(destination.getFloor()==cloneFloor){
                if(clonePosition>destination.getPosition() && cloneDirection.equals("LEFT") ||
                        clonePosition<destination.getPosition() && cloneDirection.equals("RIGHT")){
                    result = true;
                    break;
                }

            }
        }
        return result;
    }


    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        int nbFloors = in.nextInt(); // number of floors
        int width = in.nextInt(); // width of the area
        int nbRounds = in.nextInt(); // maximum number of rounds
        int exitFloor = in.nextInt(); // floor on which the exit is found
        int exitPos = in.nextInt(); // position of the exit on its floor
        int nbTotalClones = in.nextInt(); // number of generated clones
        int nbAdditionalElevators = in.nextInt(); // ignore (always zero)
        int nbElevators = in.nextInt(); // number of elevators

        Destination exit = new Destination();
        exit.setExit(exitFloor, exitPos);
        destinations.put(exit.getId(), exit);

        for (int i = 0; i < nbElevators; i++) {
            int elevatorFloor = in.nextInt(); // floor on which this elevator is found
            int elevatorPos = in.nextInt(); // position of the elevator on its floor

            Destination d = new Destination();
            d.set(i, elevatorFloor, elevatorPos);
            destinations.put(d.getId(), d);
        }

        int lastFloor=-1;
        // game loop
        while (true) {
            int cloneFloor = in.nextInt(); // floor of the leading clone
            int clonePos = in.nextInt(); // position of the leading clone on its floor
            String direction = in.next(); // direction of the leading clone: LEFT or RIGHT

            //TEST
            System.err.println("DIRECTION:" + direction);
            System.err.println("Position: " + clonePos);
            System.err.println("isFloorChanged: " + isFloorChanged(lastFloor, cloneFloor));

            if(isFloorChanged(lastFloor, cloneFloor)) {
                lastFloor=cloneFloor;
                if (isObjectInCloneDirection(cloneFloor, clonePos, direction)) {
                    System.out.println("WAIT");
                } else {
                    System.out.println("BLOCK");
                    addBlockerAsDestination(cloneFloor, clonePos);
                }
            }else{
                System.out.println("WAIT");
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
        }
    }
}