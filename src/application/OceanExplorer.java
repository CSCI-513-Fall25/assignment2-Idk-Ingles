package application;

import javafx.application.Application; // We are importing "Point" class from JavaFX light effect package.
// We are using the above thing because "Point" basically helps in storing (x, y, z, color) values, but we need this for x and y grid coordinates
import javafx.scene.Scene; //This holds everything- shapes, window, images, controls, etc.
import javafx.scene.image.Image; //This is to load our ship.png or island.png if used in assignment
import javafx.scene.image.ImageView;//This line is to display the above image inside the scene
import javafx.scene.layout.AnchorPane; //Basically, a layout container for shapes and images 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage; //Our main window for Java FX app
import javafx.scene.input.KeyEvent;  //This handles keyboard input, 
import javafx.event.EventHandler;

public class OceanExplorer extends Application { //Basically like main class, for Java FX to call start()method

    final int dimension = 10;   // 10x10 grid 
    final int scale = 50;  //Just means 50 pixels per cell
    OceanMap oceanMap; //Reps island and ocean map
    Ship ship; //Represents ship
    ImageView shipImageView; //Displays ship image
    AnchorPane root; 
    Scene scene;
    //We already declared da above stuff at class level

    @Override  //Java FX automatically calls this, when our pgm runs
    public void start(Stage oceanStage) {  //Our Game Window
        oceanMap = new OceanMap(dimension);
        ship = new Ship(oceanMap);  //Creates a new ship and puts it on the said map. But, we keep it so da ship can check islands

        root = new AnchorPane();
        drawMap();   //
        loadShipImage();  //Displays ship img

        scene = new Scene(root, dimension * scale, dimension * scale);
        oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
        oceanStage.setScene(scene);
        oceanStage.show();
        //Above 2 lines, it adds scene to da window and makes it visible on screen

        startSailing(); //Our Keyboard control logic for arrow keys.
    }

    private void drawMap() {
        boolean[][] grid = oceanMap.getMap(); //true= island, false = false
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                rect.setStroke(Color.BLACK); //This is thin border line for better visibility

                if (grid[x][y]) {
                    rect.setFill(Color.DARKGREEN); //So, color it green if it's an island or else color it water like below
                } else {
                    rect.setFill(Color.PALETURQUOISE); // ocean color
                }

                root.getChildren().add(rect);
            }
        }
    }

    private void loadShipImage() { //Method to put our ship on the map.
        Image shipImage = new Image("ship.png", 50, 50, true, true);
        shipImageView = new ImageView(shipImage);
        shipImageView.setX(ship.getShipLocation().getX() * scale);
        shipImageView.setY(ship.getShipLocation().getY() * scale);
        root.getChildren().add(shipImageView);
    }

    private void startSailing() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case RIGHT:
                        ship.goEast();
                        break;
                    case LEFT:
                        ship.goWest();
                        break;
                    case UP:
                        ship.goNorth();
                        break;
                    case DOWN:
                        ship.goSouth();
                        break;
                    default:
                        break;
                }

                // update ship position
                shipImageView.setX(ship.getShipLocation().getX() * scale);
                shipImageView.setY(ship.getShipLocation().getY() * scale);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
