module com.example.fithub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.fithub to javafx.fxml;
    exports com.example.fithub;
}