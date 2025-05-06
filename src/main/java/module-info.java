module co.edu.poli.Ejercicio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens co.edu.poli.Ejercicio.controller to javafx.fxml;
    opens co.edu.poli.Ejercicio.view to javafx.fxml;

    exports co.edu.poli.Ejercicio.controller;
    exports co.edu.poli.Ejercicio.view;
    exports co.edu.poli.Ejercicio.model;
    exports co.edu.poli.Ejercicio.services;
}
