module com.example.graphtheoryproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graphtheoryproject to javafx.fxml;
    exports com.example.graphtheoryproject;
}