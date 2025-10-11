package application;

import javafx.scene.effect.Light.Point; 
//In below Ship class, we are holding position data and logic of movement.
public class Ship {

    private Point currentLocation;
    // We are keeping the above thing private, as only this class can modify directly.
    private final int gridSize = 10; // Here, we are keeping 10*10 grid squares of ocean
    private OceanMap oceanMap; //We are storing a reference to "Ocean Map" object, which is used in Ocean Explorer.

    public Ship(OceanMap oceanMap) { //Basic constructor for the ship class
        this.oceanMap = oceanMap; //Job: Assigns Oceanmap to ship's own variable. After this, ship knows island positions.
        currentLocation = new Point(5, 5, 0, null);
        //Above, we created a new point object to represent ship's start position. We kept 5,5` which means center of our 10*10 grid
        //3rd parameter is z-axis, which is unused, 4th parameter null means no color
    }

    public Point getShipLocation() { //Get method for Ocean Explorer and other classes to read ship's position
        return currentLocation;  //Basically, returns current ship's position.
    }

    public void goNorth() {
        int currX = (int) currentLocation.getX();
        int currY = (int) currentLocation.getY(); //We are also casting them into int because grid uses integer numbers.
        int newY = currY - 1; //Calculates the new Y position, so -1 = one cell up.
        if (newY >= 0 && !oceanMap.isIsland(currX, newY)) { 
     //We use newY>=0 to prevent ship from going outside the top edge.
        	//Second condition is to make sure, it's not an island square.
            currentLocation.setY(newY); // int widens to double automatically
        }
    }

    public void goSouth() { //Moves the ship down by increasing Y coordinate
        int currX = (int) currentLocation.getX();
        int currY = (int) currentLocation.getY();
        int newY = currY + 1;
        if (newY < gridSize && !oceanMap.isIsland(currX, newY)) {
            currentLocation.setY(newY);
        }
    }

    public void goEast() { //Same as North and South change
        int currX = (int) currentLocation.getX();
        int currY = (int) currentLocation.getY();
        int newX = currX + 1;
        if (newX < gridSize && !oceanMap.isIsland(newX, currY)) {
            currentLocation.setX(newX);
        }
    }

    public void goWest() { //Same as North and South with axis chgange
        int currX = (int) currentLocation.getX();
        int currY = (int) currentLocation.getY();
        int newX = currX - 1;
        if (newX >= 0 && !oceanMap.isIsland(newX, currY)) {
            currentLocation.setX(newX);
        }
    }
}
