package org.example.lab2m;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModel {

    private final IntegerProperty score =
            new SimpleIntegerProperty(0);

    private final BooleanProperty gameActive =
            new SimpleBooleanProperty(true);

    private final DoubleProperty ballX =
            new SimpleDoubleProperty(400);

    private final DoubleProperty ballY =
            new SimpleDoubleProperty(300);

    private double dx = 5;
    private double dy = 5;

    public IntegerProperty scoreProperty() {
        return score;
    }

    public BooleanProperty gameActiveProperty() {
        return gameActive;
    }

    public DoubleProperty ballXProperty() {
        return ballX;
    }

    public DoubleProperty ballYProperty() {
        return ballY;
    }

    public void increaseScore() {
        score.set(score.get() + 1);
    }

    public void togglePause() {
        gameActive.set(!gameActive.get());
    }

    public void resetGame() {
        score.set(0);
        gameActive.set(true);

        ballX.set(400);
        ballY.set(300);

        dx = 5;
        dy = 5;
    }

    public void moveBall(double width, double height) {

        if (!gameActive.get()) {
            return;
        }

        ballX.set(ballX.get() + dx);
        ballY.set(ballY.get() + dy);

        if (ballX.get() <= 30 || ballX.get() >= width - 30) {
            dx = -dx;
        }

        if (ballY.get() <= 30 || ballY.get() >= height - 30) {
            dy = -dy;
        }
    }

    public void runAwayFromMouse(double mouseX, double mouseY) {

        double distance =
                Math.sqrt(
                        Math.pow(ballX.get() - mouseX, 2)
                                + Math.pow(ballY.get() - mouseY, 2)
                );

        if (distance < 100) {

            if (mouseX < ballX.get()) {
                dx = Math.abs(dx);
            } else {
                dx = -Math.abs(dx);
            }

            if (mouseY < ballY.get()) {
                dy = Math.abs(dy);
            } else {
                dy = -Math.abs(dy);
            }
        }
    }
}