package application;

import java.util.Observer;
import java.util.Observable;
import javafx.scene.effect.Light.Point;

//In below PirateShip class, we are holding position data and logic of movement for pirate ships.
public class PirateShip implements Observer {

    private Point pirateLocation; //Variable to hold pirate's current location
    private OceanMap oceanMap;    //Reference to ocean grid for island checks

    public PirateShip(Point startLocation, OceanMap oceanMap) { //Constructor for initializing pirate ship position
        this.pirateLocation = startLocation;
        this.oceanMap = oceanMap;
        //Above, we created a new point object to represent pirate's start position.
    }

    public Point getPirateLocation() { //Get method for Ocean Explorer and other classes to read pirate's position
        return pirateLocation;  //Basically, returns current pirate's position.
    }

    @Override
    public void update(Observable o, Object arg) { //This gets called automatically when the main Ship moves.
        if (o instanceof Ship) { //Checks if the object that moved is our main Ship
            Ship mainShip = (Ship) o;
            moveToward(mainShip.getShipLocation()); //Moves pirate one step closer to ship
        }
    }

    //This method moves pirate one step closer towards the ship’s new location
    private void moveToward(Point shipPoint) {
        int px = (int) pirateLocation.getX();
        int py = (int) pirateLocation.getY();
        int sx = (int) shipPoint.getX();
        int sy = (int) shipPoint.getY();

        //Move horizontally toward the main ship
        int nextX = px;
        int nextY = py;
        if (px < sx) {
            nextX++;
        } else if (px > sx) {
            nextX--;
        }

        //Move vertically toward the main ship
        if (py < sy) {
            nextY++;
        } else if (py > sy) {
            nextY--;
        }

        //Before moving, make sure the next cell is not an island
        if (!oceanMap.isIsland(nextX, py)) {
            px = nextX;
        }
        if (!oceanMap.isIsland(px, nextY)) {
            py = nextY;
        }

        //Update new location
        pirateLocation.setX(px);
        pirateLocation.setY(py);
    }
}
