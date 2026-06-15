package org.example.lab3m;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageViewerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        ImageViewerApplication.class.getResource("image-view.fxml")
                );

        Scene scene = new Scene(
                fxmlLoader.load(),
                1000,
                700
        );

        stage.setTitle("Просмотр изображений");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}