package org.example.lab2m;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Pane gamePane;

    @FXML
    private Circle ball;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Button newGameButton;

    private final GameModel model = new GameModel();

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ball.centerXProperty().bind(model.ballXProperty());
        ball.centerYProperty().bind(model.ballYProperty());

        scoreLabel.textProperty().bind(
                Bindings.concat("Счет: ", model.scoreProperty())
        );

        statusLabel.textProperty().bind(
                Bindings.when(model.gameActiveProperty())
                        .then("Игра активна")
                        .otherwise("Игра на паузе")
        );

        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(50),
                        event -> model.moveBall(
                                gamePane.getWidth(),
                                gamePane.getHeight()
                        )
                )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Одинарный клик по шарику = очко
        ball.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1
                    && model.gameActiveProperty().get()) {

                model.increaseScore();
            }
        });

        // Двойной клик по игровому полю = пауза
        gamePane.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {

                model.togglePause();
            }
        });

        // Шарик убегает от курсора
        gamePane.setOnMouseMoved(event ->
                model.runAwayFromMouse(
                        event.getX(),
                        event.getY()
                )
        );

        // Новая игра
        newGameButton.setOnAction(event ->
                model.resetGame()
        );
    }
}