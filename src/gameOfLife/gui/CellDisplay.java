package gameOfLife.gui;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import gameOfLife.model.Cell;

/*
The CellDisplay class displays the cell value as a circle of the appropriate color
 */

public class CellDisplay extends Parent {
    private Cell cell;
    private Circle circle = new Circle(BoardDisplay.getSIZE() / 2);

    public CellDisplay(Point2D point, Cell cell) {
        this.cell = cell;
        circle.setCenterX(BoardDisplay.getSIZE() / 2);
        circle.setCenterY(BoardDisplay.getSIZE() / 2);
        circle.setFill(cell.getColor());

        getChildren().add(circle);

        setTranslateY(point.getY() * BoardDisplay.getSIZE());
        setTranslateX(point.getX() * BoardDisplay.getSIZE());
    }

    public int getColumn() {
        return (int)getTranslateX() / BoardDisplay.getSIZE();
    }

    public int getRow() {
        return (int)getTranslateY() / BoardDisplay.getSIZE();
    }

    public void setCell(Cell cell) {
        this.cell = cell;
        circle.setFill(cell.getColor());
    }

    public Cell getCell() {
        return cell;
    }

    public String toString() {
        return "CellDisplay: "+cell+", "+circle;
    }
}
