module com.example.individualproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.individualproject to javafx.fxml;
    exports com.example.individualproject;
}