package co.edu.poli.Ejercicio.controller;

import java.sql.SQLException;
import java.util.List;
import co.edu.poli.Ejercicio.services.PayPalAdapter;
import co.edu.poli.Ejercicio.services.NequiAdapter;
import co.edu.poli.Ejercicio.services.Pagos;
import co.edu.poli.Ejercicio.model.Producto;
import co.edu.poli.Ejercicio.model.ProductoAlimento;
import co.edu.poli.Ejercicio.model.ProductoElectronico;
import co.edu.poli.Ejercicio.services.DAO;
import co.edu.poli.Ejercicio.services.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoController {

    @FXML
    private ComboBox<String> cmbTipoProducto; 
    
    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCaracteristica;

    @FXML
    private TableView<Producto> tblProductos; 

    @FXML
    private TableColumn<Producto, String> colId;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, String> colCaracteristica;
  
    private final DAO<Producto> productoDAO = new ProductoDAO();
    
    @FXML
    private Button btnBuilder;

    @FXML
    private void initialize() {
        cmbTipoProducto.getItems().addAll("Electrónico", "Alimento");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCaracteristica.setCellValueFactory(cellData -> 
            javafx.beans.binding.Bindings.createStringBinding(() -> 
                cellData.getValue().obtenerCaracteristica()
            )
        );

        tblProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtDescripcion.setText(newValue.getDescripcion());
                txtPrecio.setText(String.valueOf(newValue.getPrecio()));
                cmbTipoProducto.setValue(newValue.getTipo());

                if (newValue instanceof ProductoElectronico) {
                    txtCaracteristica.setText(((ProductoElectronico) newValue).getVoltajeEntrada());
                } else if (newValue instanceof ProductoAlimento) {
                    txtCaracteristica.setText(String.valueOf(((ProductoAlimento) newValue).getAporteCalorico()));
                }
            }
        });

        cmbTipoProducto.setOnAction(event -> cargarProductos());
    }


    @FXML
    private void handleConsultar() {
        cargarProductos();
    }

    @FXML
    private void handleInsertar() {
        String id = "P" + System.currentTimeMillis();
        String descripcion = txtDescripcion.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String tipo = cmbTipoProducto.getValue();
        String caracteristicaTexto = txtCaracteristica.getText().trim();

        if (descripcion.isEmpty() || precioTexto.isEmpty() || tipo == null || caracteristicaTexto.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un número válido.");
            return;
        }

        Producto nuevoProducto = null;
        if ("Electrónico".equals(tipo)) {
            nuevoProducto = new ProductoElectronico(id, descripcion, precio, tipo, caracteristicaTexto);
        } else if ("Alimento".equals(tipo)) {
            try {
                int aporteCalorico = Integer.parseInt(caracteristicaTexto);
                nuevoProducto = new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El aporte calórico debe ser un número válido.");
                return;
            }
        }

        if (nuevoProducto != null) {
            try {
                productoDAO.insertar(nuevoProducto);
                mostrarAlerta("Éxito", "Producto insertado correctamente.");
                cargarProductos();
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo insertar el producto: " + e.getMessage());
            }
        }
    }
    @FXML
    private void handleActualizar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                String id = seleccionado.getId(); 
                String descripcion = txtDescripcion.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                String tipo = cmbTipoProducto.getValue();
                String caracteristicaTexto = txtCaracteristica.getText().trim();

                Producto productoActualizado = null;

                if ("Electrónico".equals(tipo)) { 
                    productoActualizado = new ProductoElectronico(id, descripcion, precio, tipo, caracteristicaTexto);
                } else if ("Alimento".equals(tipo)) {
                    int aporteCalorico = Integer.parseInt(caracteristicaTexto);
                    productoActualizado = new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico);
                }

                if (productoActualizado != null) {
                    productoDAO.actualizar(productoActualizado);
                    mostrarAlerta("Éxito", "Producto actualizado correctamente.");
                    cargarProductos();
                }
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo actualizar el producto: " + e.getMessage());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Verifica que el precio y la característica sean números válidos.");
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para actualizar.");
        }
    }
    @FXML
    private void handlePagar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        
        if (seleccionado == null) {
            mostrarAlerta("Error", "Seleccione un producto para pagar.");
            return;
        }

        double monto = seleccionado.getPrecio();
        String metodoPago = cmbTipoProducto.getValue(); // Se asume que aquí está el método de pago

        Pagos procesadorPago;
        String mensajePago = "";

        if ("Electrónico".equals(metodoPago)) {
            PayPalAdapter adapter = new PayPalAdapter();
            adapter.realizarPago(monto);
            mensajePago = adapter.getMensajePago();
        } else if ("Alimento".equals(metodoPago)) {
            NequiAdapter adapter = new NequiAdapter();
            adapter.realizarPago(monto);
            mensajePago = adapter.getMensajePago();
        } else {
            mostrarAlerta("Error", "Método de pago no reconocido.");
            return;
        }

        mostrarAlerta("Pago Exitoso", mensajePago);
    }


    @FXML
    private void handleEliminar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                productoDAO.eliminar(seleccionado.getId());
                mostrarAlerta("Éxito", "Producto eliminado correctamente.");
                cargarProductos();
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo eliminar el producto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para eliminar.");
        }
    }

    private void cargarProductos() {
        String tipoSeleccionado = cmbTipoProducto.getValue();
        if (tipoSeleccionado != null) {
            try {
                List<Producto> productos = productoDAO.obtenerPorTipo(tipoSeleccionado);
                ObservableList<Producto> listaProductos = FXCollections.observableArrayList(productos);
                tblProductos.setItems(listaProductos);
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo cargar los productos: " + e.getMessage());
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(titulo.equals("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void handleClonar() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                Producto clon = seleccionado.clone(); 
                productoDAO.insertar(clon);
                mostrarAlerta("Clonación Exitosa", "El producto ha sido clonado correctamente con ID: " + clon.getId());
                cargarProductos();
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo clonar el producto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un producto para clonar.");
        }
    }   
}
