module com.example.fithub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fithub to javafx.fxml;
    exports com.example.fithub;
}