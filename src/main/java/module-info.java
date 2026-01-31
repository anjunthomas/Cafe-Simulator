module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.cafe to javafx.fxml;
    exports com.cafe;
    exports com.cafe.controllers;
    opens com.cafe.controllers to javafx.fxml;
}