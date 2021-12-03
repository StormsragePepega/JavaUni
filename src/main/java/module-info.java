module com.example.kursach {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.kursach to javafx.fxml, javafx.graphics;
    exports components;
    opens com.example.kursach.controllers to javafx.fxml, javafx.graphics;
}