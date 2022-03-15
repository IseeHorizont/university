module ru.tusur.university {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ru.tusur.university to javafx.fxml;
    exports ru.tusur.university;
}