package gameOfLife.gui;

import gameOfLife.simulator.Simulator;
import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import gameOfLife.model.Cell;
import javafx.scene.layout.VBox;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardDisplay {
    private static final int HEIGHT = 80;
    private static final int WIDTH = 100;
    private static final int SIZE = 5;
    private static List<CellDisplay> cells;
    private static VBox root;

    //Generate the GUI
    public static void createContent(Simulator simulator) {
        root = new VBox();
        root.getStyleClass().add("board");

        Pane gamePane = createGamePane();
        HBox buttons = createButtons(simulator);

        root.getChildren().addAll(gamePane, buttons);
    }

    //Generate the pane where the game of life will be displayed
    public static Pane createGamePane() {
        Pane gamePane = new Pane();

        gamePane.setPrefSize(WIDTH * SIZE, HEIGHT * SIZE);

        //Create CellDisplay objects for each board position and save them in a list
        cells = IntStream.range(0, WIDTH * HEIGHT)
                .mapToObj(i -> new Point2D(i % WIDTH, i / WIDTH))
                .map(point -> new CellDisplay(point, Cell.DEAD))
                .collect(Collectors.toList());

        //
        gamePane.getChildren().addAll(cells);

        return gamePane;
    }

    public static HBox createButtons(Simulator simulator) {
        //create HBox to place buttons
        HBox box = new HBox();
        box.getStyleClass().add("buttonsPanel");

        //create pause button
        Button pauseButton = new Button();
        pauseButton.setText("Pause");
        pauseButton.getStyleClass().add("button");

        pauseButton.setOnAction(evt -> {
            simulator.pauseResume();
            if(simulator.getTimelineStatus() == Animation.Status.PAUSED) {
                pauseButton.setText("Resume");
            } else if(simulator.getTimelineStatus() == Animation.Status.RUNNING) {
                pauseButton.setText("Pause");
            }
        });

        //create restart button
        Button restartButton = new Button();
        restartButton.setText("Restart");
        restartButton.getStyleClass().add("button");
        restartButton.setOnAction(evt -> simulator.restart());

        //Add buttons
        box.getChildren().addAll(pauseButton, restartButton);

        return box;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static List<CellDisplay> getCells() {
        return cells;
    }

    public static VBox getRoot() {
        return root;
    }


}
