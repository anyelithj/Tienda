package co.edu.poli.controller;

import co.edu.poli.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class ProductoController {
    @FXML private Label lblNombre;
    @FXML private Label lblPrecio;
    @FXML private TextField txtNuevoPrecio;
    @FXML private TextArea txtHistorial;

    private Producto producto;

    @FXML
    public void initialize() {
        producto = new Producto(1, "Laptop", "Portátil gamer", 2500);
        producto.registrarObservador(new ObservadorPrecio());

        lblNombre.setText(producto.getNombre());
        actualizarVista();
    }

    @FXML
    public void actualizarPrecio() {
        try {
            double nuevoPrecio = Double.parseDouble(txtNuevoPrecio.getText());
            if (producto.setPrecio(nuevoPrecio)) {
                actualizarVista();
                txtNuevoPrecio.clear();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notificación");
                alert.setHeaderText(null);
                alert.setContentText("Se ha notificado el cambio de precio del producto.");
                alert.showAndWait();
            } else {
                System.out.println("El precio ingresado es el mismo que el actual.");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingresa un número válido para el precio.");
            alert.showAndWait();
        }
    }

    private void actualizarVista() {
        lblPrecio.setText("$" + String.format("%.2f", producto.getPrecio()));

        StringBuilder historialTexto = new StringBuilder("Historial de Precios:\n");
        for (ProductoMemento mem : producto.getHistorial().obtenerHistorialProducto()) {
            historialTexto.append("- $").append(String.format("%.2f", mem.getPrecio())).append(" (").append(mem.getFecha()).append(")\n");
        }
        txtHistorial.setText(historialTexto.toString());
    }
}
