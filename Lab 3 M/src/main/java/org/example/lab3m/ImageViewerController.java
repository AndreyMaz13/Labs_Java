package org.example.lab3m;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageViewerController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Label positionLabel;

    @FXML
    private Label fileInfoLabel;

    @FXML
    private Label exifLabel;

    @FXML
    private Button firstButton;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button lastButton;

    @FXML
    private Button slideShowButton;

    @FXML
    private ComboBox<String> filterBox;

    private final ImageModel model =
            new ImageModel();

    private Timeline slideShow;

    private boolean slideShowRunning = false;

    private final File folder =
            new File("images");

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle) {

        filterBox.getItems().addAll(
                "Все",
                "JPG",
                "PNG"
        );

        filterBox.setValue("Все");

        loadImages();

        firstButton.setOnAction(event ->
                showImage(model.firstImage()));

        prevButton.setOnAction(event ->
                showImage(model.previousImage()));

        nextButton.setOnAction(event ->
                showImage(model.nextImage()));

        lastButton.setOnAction(event ->
                showImage(model.lastImage()));

        filterBox.setOnAction(event ->
                loadImages());

        slideShow = new Timeline(
                new KeyFrame(
                        Duration.seconds(2),
                        event -> showImage(model.nextImage())
                )
        );

        slideShow.setCycleCount(
                Timeline.INDEFINITE
        );

        slideShowButton.setOnAction(event -> {

            if (!slideShowRunning) {

                slideShow.play();

                slideShowRunning = true;

                slideShowButton.setText(
                        "Остановить"
                );

            } else {

                slideShow.stop();

                slideShowRunning = false;

                slideShowButton.setText(
                        "Автопросмотр"
                );
            }
        });
    }

    private void loadImages() {

        File[] files;

        String filter =
                filterBox.getValue();

        if ("JPG".equals(filter)) {

            files = folder.listFiles(file ->
                    file.getName().toLowerCase().endsWith(".jpg")
                            || file.getName().toLowerCase().endsWith(".jpeg"));

        } else if ("PNG".equals(filter)) {

            files = folder.listFiles(file ->
                    file.getName().toLowerCase().endsWith(".png"));

        } else {

            files = folder.listFiles(file ->
                    file.getName().toLowerCase().endsWith(".jpg")
                            || file.getName().toLowerCase().endsWith(".jpeg")
                            || file.getName().toLowerCase().endsWith(".png"));
        }

        model.loadImages(files);

        showImage(model.getCurrentImage());
    }

    private void showImage(File file) {

        if (file == null) {

            positionLabel.setText("0 из 0");

            fileInfoLabel.setText(
                    "Изображения не найдены"
            );

            exifLabel.setText(
                    "EXIF не найден"
            );

            imageView.setImage(null);

            return;
        }

        Image image =
                new Image(file.toURI().toString());

        imageView.setImage(image);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(500),
                        imageView
                );

        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(500),
                        imageView
                );

        scale.setFromX(0.8);
        scale.setFromY(0.8);

        scale.setToX(1.0);
        scale.setToY(1.0);

        scale.play();

        positionLabel.setText(
                model.getCurrentPosition()
                        + " из "
                        + model.getTotalImages()
        );

        fileInfoLabel.setText(
                "Файл: "
                        + file.getName()
                        + " | Размер: "
                        + (file.length() / 1024)
                        + " КБ"
        );

        try {

            Metadata metadata =
                    ImageMetadataReader.readMetadata(file);

            ExifIFD0Directory directory =
                    metadata.getFirstDirectoryOfType(
                            ExifIFD0Directory.class
                    );

            if (directory != null) {

                String camera =
                        directory.getString(
                                ExifIFD0Directory.TAG_MODEL
                        );

                exifLabel.setText(
                        "Камера: "
                                + camera
                );

            } else {

                exifLabel.setText(
                        "EXIF не найден"
                );
            }

        } catch (Exception e) {

            exifLabel.setText(
                    "EXIF не найден"
            );
        }
    }
}