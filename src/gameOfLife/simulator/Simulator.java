package gameOfLife.simulator;


import gameOfLife.gui.BoardDisplay;
import gameOfLife.gui.CellDisplay;
import gameOfLife.model.Board;
import gameOfLife.model.Cell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/*
The Simulator class updates the BoardDisplay with the appropriate Cell values at each turn.
The turns are managed with a timeline animation. It can be paused or restarted.
 */

public class Simulator {
    private Board board;
    private Timeline timeline;
    private double probabilityForEachCell;

    public Simulator(double probabilityForEachCell) {
        this.board = new Board(BoardDisplay.getWidth(), BoardDisplay.getHeight());
        this.timeline = new Timeline();
        this.probabilityForEachCell = probabilityForEachCell;
    }

    public void initiateGame() {
        setUpTimeline();
        BoardDisplay.createContent(this);
        board.initiateBoard(probabilityForEachCell);
        updateBoardDisplay();
        timeline.play();
    }

    public void playTurn() {
        board.playTurn();
        updateBoardDisplay();
    }

    public void updateBoardDisplay() {
        for(CellDisplay cellDisplay : BoardDisplay.getCells()) {
            Cell cell = board.getBoard()[cellDisplay.getColumn()][cellDisplay.getRow()];
            cellDisplay.setCell(cell);
        }
    }

    public void pauseResume() {
        if(timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
        } else if(timeline.getStatus() == Animation.Status.PAUSED) {
            timeline.play();
        }
    }

    public void restart() {
        timeline.stop();
        board.initiateBoard(probabilityForEachCell);
        updateBoardDisplay();
        timeline.play();
    }

    private void setUpTimeline() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> playTurn()));
    }

    public Animation.Status getTimelineStatus() {
        return timeline.getStatus();
    }
}
