module org.example.lab2m {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.lab2m to javafx.fxml;
    exports org.example.lab2m;
}