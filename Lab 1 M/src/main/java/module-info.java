module org.example.lab1m {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.lab1m to javafx.fxml;

    exports org.example.lab1m;
}