module com.example.approject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens View to javafx.fxml;
    exports View;
}