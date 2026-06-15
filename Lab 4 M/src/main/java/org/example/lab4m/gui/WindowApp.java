package org.example.lab4m.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.lab4m.core.DataProcessor;

public class WindowApp extends Application {

    @Override
    public void start(Stage stage) {

        TextArea inputArea =
                new TextArea();

        inputArea.setPromptText(
                "Введите числа по одному в строке"
        );

        Button processButton =
                new Button("Обработать");

        ListView<String> resultView =
                new ListView<>();

        processButton.setOnAction(event -> {

            String text =
                    inputArea.getText();

            String[] lines =
                    text.split("\\R");

            String[] result =
                    DataProcessor.processPipeline(
                            lines
                    );

            resultView.setItems(
                    FXCollections.observableArrayList(
                            result
                    )
            );
        });

        VBox topBox =
                new VBox(
                        10,
                        inputArea,
                        processButton
                );

        BorderPane root =
                new BorderPane();

        root.setTop(topBox);
        root.setCenter(resultView);

        Scene scene =
                new Scene(
                        root,
                        600,
                        400
                );

        stage.setTitle(
                "Lab 4 - Data Processing"
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}