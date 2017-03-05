package gameOfLife.model;

import javafx.scene.paint.Color;

public enum Cell {
    ALIVE(Color.ROYALBLUE),
    DEAD(Color.WHITE);

    private Color color;

    Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
