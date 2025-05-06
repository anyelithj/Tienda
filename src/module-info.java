module Tienda {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.controller to javafx.fxml;
    exports co.edu.poli.controller;
    exports co.edu.poli.View;
}


