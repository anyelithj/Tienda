package co.edu.poli.controller;

import co.edu.poli.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            producto.setPrecio(nuevoPrecio);
            actualizarVista();
            txtNuevoPrecio.clear();
        } catch (NumberFormatException e) {
            txtHistorial.setText("⚠️ Ingresa un número válido.");
        }
    }

    private void actualizarVista() {
        lblPrecio.setText("$" + producto.getPrecio());

        StringBuilder historialTexto = new StringBuilder("Historial de Precios:\n");
        for (ProductoMemento mem : producto.getHistorial().obtenerHistorialProducto()) {
            historialTexto.append("- $").append(mem.getPrecio()).append(" (").append(mem.getFecha()).append(")\n");
        }
        txtHistorial.setText(historialTexto.toString());
    }
}

