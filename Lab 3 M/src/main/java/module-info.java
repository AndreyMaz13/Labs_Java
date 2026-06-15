module org.example.lab3m {

    requires javafx.controls;
    requires javafx.fxml;

    requires metadata.extractor;

    opens org.example.lab3m to javafx.fxml;
    exports org.example.lab3m;
}