package org.example.lab1m;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> fromBox;

    @FXML
    private ComboBox<String> toBox;

    @FXML
    private Label resultLabel;

    private final ConverterModel model =
            new ConverterModel();

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle) {

        fromBox.getItems().addAll(
                "Координаты",
                "Адрес",
                "Почтовый индекс"
        );

        toBox.getItems().addAll(
                "Координаты",
                "Адрес",
                "Почтовый индекс"
        );

        fromBox.setValue("Адрес");
        toBox.setValue("Почтовый индекс");
    }

    @FXML
    private void handleConvert() {

        String value =
                inputField.getText();

        if (InputValidator.isEmpty(value)) {

            resultLabel.setText(
                    "Ошибка: пустое поле"
            );

            return;
        }

        String result =
                model.convert(
                        value,
                        fromBox.getValue(),
                        toBox.getValue()
                );

        resultLabel.setText(result);
    }
}