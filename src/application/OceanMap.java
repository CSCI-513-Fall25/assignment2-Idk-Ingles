package application;

import java.util.Random;

public class OceanMap {

    private boolean[][] oceanGrid;  // true = island, false = water
    private int dimension;
    private Random random = new Random();

    public OceanMap(int dimension) {
        this.dimension = dimension;
        oceanGrid = new boolean[dimension][dimension];
        generateIslands(10);  // create 10 random islands
    }

    private void generateIslands(int islandCount) {
        for (int i = 0; i < islandCount; i++) {
            int x = random.nextInt(dimension);
            int y = random.nextInt(dimension);
            // avoid ship's starting position
            if (x == 5 && y == 5) {
                i--;
                continue;
            }
            oceanGrid[x][y] = true;  // true = island
        }
    }

    public boolean[][] getMap() {
        return oceanGrid;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isIsland(int x, int y) {
        return oceanGrid[x][y];
    }
}
