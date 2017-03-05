package gameOfLife.model;

import java.util.Random;

/*
The Board class manages the Cell values in a two-dimensional array.
At each turn the Cell values are updated depending on the status of the neighbors.
 */

public class Board {
    private Cell[][] board;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[width][height];
    }

    //Initiate a board given a probability for each cell to be alive
    public void initiateBoard(double probabilityForEachCell) {
        Random random = new Random();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (random.nextDouble() < probabilityForEachCell) {
                    getBoard()[x][y] = Cell.ALIVE;
                } else {
                    getBoard()[x][y] = Cell.DEAD;
                }
            }
        }
    }

    //Loop through all board positions and manage each cell
    public void playTurn() {
        for(int x = 0; x < this.getWidth(); ++x) {
            for(int y = 0; y < this.getHeight(); ++y) {
                manageCell(x, y, getNumberOfLivingNeighbours(x, y));
            }
        }
    }

    //Turn the cell to dead or alive depending on its neighbours
    public void manageCell(int x, int y, int livingNeighbours) {
        if (livingNeighbours < 2 || livingNeighbours > 3) turnToDead(x, y);
        if (!isAlive(x, y) && livingNeighbours == 3) turnToLiving(x, y);
    }

    public int getNumberOfLivingNeighbours(int x, int y) {
        int numberLiving = 0;
        int[] direction = {-1, 0, 1};

        for (int xNeighbour : direction) {
            for (int yNeighbour : direction) {
                if (xNeighbour == 0 && yNeighbour == 0) {
                    continue;
                }

                if (!isAlive(x + xNeighbour, y + yNeighbour)) {
                    continue;
                }
                numberLiving++;
            }
        }
        return numberLiving;
    }

    public boolean isInsideBoard(int x, int y) {
        if (x >= 0 && x < getWidth() && y >=0 && y < getHeight()) {
            return true;
        }
        return false;
    }

    public void turnToLiving(int x, int y) {
        if (isInsideBoard(x, y)) {
            getBoard()[x][y] = Cell.ALIVE;
        }
    }

    public void turnToDead(int x, int y) {
        if (isInsideBoard(x, y)) {
            getBoard()[x][y] = Cell.DEAD;
        }
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAlive(int x, int y) {
        if (isInsideBoard(x, y) && getBoard()[x][y] == Cell.ALIVE) {
           return true;
        }
        return false;
    }
}
