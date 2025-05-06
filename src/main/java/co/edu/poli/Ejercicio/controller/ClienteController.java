package co.edu.poli.Ejercicio.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import co.edu.poli.Ejercicio.model.*;
import co.edu.poli.Ejercicio.services.ClienteDAO;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ClienteController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private ListView<String> listComponentes; 
    @FXML private Button btnCrearComposite;
    @FXML private Button btnAgregarEmpleado;
    @FXML private Button btnAgregarDepartamento;
    @FXML private TextArea txtEstructura;
    @FXML private Button btnElegirDecorator;
    @FXML private Label lblResultado;
    @FXML
    private Label labelTotal;

    @FXML
    private Label labelDescripcion;


    private CarritoCompra carrito;
    private Departamento departamentoRaiz;

    @FXML
    public void initialize() {
        carrito = new CarritoBase(100.0); // Total base del carrito
        actualizarVista();
        departamentoRaiz = null;
    }

    private void actualizarVista() {
        if (lblResultado != null && carrito != null) {
            String descripcion = obtenerDescripcion1(carrito);
            lblResultado.setText("Total: $" + carrito.obtenerTotal() + "\n" + descripcion);
        }
    }

    private String obtenerDescripcion1(CarritoCompra carrito) {
        if (carrito instanceof DecoratorCarrito decorator) {
            return obtenerDescripcion1(decorator.getCarrito()) + " + " + decorator.obtenerDescripcion();
        }
        return "Carrito Base";
    }
    public CarritoCompra getCarrito() {
        return carrito;
    }

    // =============================
    // FUNCIONES DECORATOR
    // =============================

    @FXML
    private void aplicarDecoratorPorConsola() {
        Platform.runLater(() -> {
            List<String> opciones = List.of("VIP", "Descuento", "Envío Gratis", "Puntos");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("VIP", opciones);
            dialog.setTitle("Seleccionar Decorador");
            dialog.setHeaderText("Aplicar Decorador al Carrito");
            dialog.setContentText("Elige un decorador:");

            dialog.showAndWait().ifPresent(seleccion -> {

                switch (seleccion) {
                    case "VIP":
                        carrito = new VIP(carrito);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "VIP Aplicado", "Total: $" + carrito.obtenerTotal());
                        actualizarVista();
                        break;

                    case "Descuento":
                        TextInputDialog inputDialog = new TextInputDialog("0.2");
                        inputDialog.setTitle("Porcentaje de Descuento");
                        inputDialog.setHeaderText("Aplicar Descuento");
                        inputDialog.setContentText("Ingrese el porcentaje (Ej: 0.2 para 20%):");

                        inputDialog.showAndWait().ifPresent(valor -> {
                            try {
                                double porcentaje = Double.parseDouble(valor);
                                carrito = new Descuento(carrito, porcentaje);
                                mostrarAlerta(Alert.AlertType.INFORMATION, "Descuento Aplicado", "Se aplicó un descuento de " + (porcentaje * 100) + "%");
                                actualizarVista();
                            } catch (NumberFormatException e) {
                                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Porcentaje inválido.");
                            }
                        });
                        break;

                    case "Envío Gratis":
                        carrito = new EnvioGratis(carrito);
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Envío Gratis", "Total: $" + carrito.obtenerTotal());
                        actualizarVista();
                        break;

                    case "Puntos":
                        carrito = new Puntos(carrito, 4); // se usa 4 como referencia
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Puntos Aplicado", "Total: $" + carrito.obtenerTotal());
                        actualizarVista();
                        break;

                    default:
                        mostrarAlerta(Alert.AlertType.WARNING, "Opción inválida", "No se aplicó ningún decorador.");
                }
            });
        });
    }

    @FXML
    private void aplicarDecorator() {
        CarritoCompra carrito = new CarritoBase(100.0); // puedes hacer que este sea un atributo si necesitas persistencia
        carrito = new VIP(carrito);
        carrito = new Descuento(carrito, 0);
        carrito = new EnvioGratis(carrito);

        System.out.println("Total actualizado: $" + carrito.obtenerTotal());
        System.out.println("Descripción: " + obtenerDescripcion1(carrito));
        
      //  labelTotal.setText("Total: $" + carrito.obtenerTotal());
      //  labelDescripcion.setText(obtenerDescripcion1(carrito));
    }

    private String obtenerDescripcion(CarritoCompra carrito) {
        if (carrito instanceof DecoratorCarrito decorator) {
            return obtenerDescripcion1(decorator.getCarrito()) + " + " + decorator.obtenerDescripcion();
        }
        return "Carrito Base";
    }
    // =============================
    // FUNCIONES CLIENTE (CRUD)
    // =============================
    @FXML
    private void handleLogin() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        try {
            ClienteDAO clienteDAO = new ClienteDAO(); 
            if (clienteDAO.validarCredenciales(id, nombre)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Inicio de sesión correcto.");
                abrirPantallaPrincipal();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Credenciales incorrectas.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al validar credenciales: " + e.getMessage());
        }
    }

    @FXML
    private void handleInsertarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();

        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.insertarCliente(cliente)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente insertado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo insertar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @FXML
    private void handleEditarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();

        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        Cliente cliente = new Cliente(id, nombre);
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.actualizarCliente(cliente)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente actualizado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al actualizar el cliente: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrarCliente() {
        String id = txtId.getText();

        if (id.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe ingresar un ID para borrar.");
            return;
        }

        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.eliminarCliente(id)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente eliminado correctamente.");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el cliente.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    // =============================
    // FUNCIONES COMPOSITE
    // =============================

    @FXML
    public void crearComposite() {
        if (departamentoRaiz == null) {
            departamentoRaiz = new Departamento("Departamento Principal");

            Empleado emp1 = new EmpleadoHoja("E001", "Juan Pérez");
            Empleado emp2 = new EmpleadoHoja("E002", "Ana García");
            Departamento subDepartamento = new Departamento("Subdepartamento A");
            Empleado emp3 = new EmpleadoHoja("E003", "Carlos Martínez");

            departamentoRaiz.agregar(emp1);
            departamentoRaiz.agregar(emp2);
            subDepartamento.agregar(emp3);
            departamentoRaiz.agregar(subDepartamento);

            listComponentes.getItems().add("Composite creado: Departamento Principal");
            listComponentes.getItems().add("Empleado: Juan Pérez");
            listComponentes.getItems().add("Empleado: Ana García");
            listComponentes.getItems().add("Subdepartamento: Subdepartamento A");
            listComponentes.getItems().add("Empleado: Carlos Martínez (en Subdepartamento A)");

            mostrarAlerta(Alert.AlertType.INFORMATION, "Composite creado", "Se ha creado el departamento raíz y se han agregado empleados y subdepartamentos.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "El departamento raíz ya fue creado.");
        }
    }

    @FXML
    public void agregarEmpleado() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe crear primero el departamento raíz.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Empleado");
        dialog.setHeaderText("Ingrese el nombre del empleado:");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                Empleado empleado = new EmpleadoHoja(nombre, nombre);
                departamentoRaiz.agregar(empleado);
                listComponentes.getItems().add("Empleado: " + nombre);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Empleado agregado", "Empleado '" + nombre + "' agregado.");
            }
        });
    }

    @FXML
    public void agregarDepartamento() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Debe crear primero el departamento raíz.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Departamento");
        dialog.setHeaderText("Ingrese el nombre del subdepartamento:");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (!nombre.trim().isEmpty()) {
                TextInputDialog padreDialog = new TextInputDialog();
                padreDialog.setTitle("Departamento Padre");
                padreDialog.setHeaderText("¿En qué departamento desea agregarlo?");
                padreDialog.setContentText("Nombre del departamento padre:");

                padreDialog.showAndWait().ifPresent(nombrePadre -> {
                    Departamento padre = buscarDepartamento(departamentoRaiz, nombrePadre);
                    if (padre != null) {
                        Departamento nuevo = new Departamento(nombre);
                        padre.agregar(nuevo);
                        listComponentes.getItems().add("Subdepartamento: " + nombre + " (padre: " + nombrePadre + ")");
                    } else {
                        mostrarAlerta(Alert.AlertType.WARNING, "No encontrado", "No se encontró el departamento padre.");
                    }
                });
            }
        });
    }

    @FXML
    public void mostrarEstructura() {
        if (departamentoRaiz == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Debe crear un Composite primero.");
            return;
        }

        StringBuilder estructura = new StringBuilder();
        construirEstructura(departamentoRaiz, estructura, "");
        txtEstructura.setText(estructura.toString());
    }

    private void construirEstructura(Componente componente, StringBuilder estructura, String prefijo) {
        estructura.append(prefijo).append("📂 ").append(componente.getNombre()).append("\n");

        if (componente instanceof Departamento) {
            for (Componente sub : ((Departamento) componente).getComponente()) {
                if (sub instanceof Departamento) {
                    construirEstructura(sub, estructura, prefijo + "  ");
                } else {
                    estructura.append(prefijo).append("  👤 ").append(sub.getNombre()).append("\n");
                }
            }
        }
    }

    private Departamento buscarDepartamento(Departamento actual, String nombre) {
        if (actual.getNombre().equalsIgnoreCase(nombre)) return actual;

        for (Componente comp : actual.getComponente()) {
            if (comp instanceof Departamento) {
                Departamento encontrado = buscarDepartamento((Departamento) comp, nombre);
                if (encontrado != null) return encontrado;
            }
        }
        return null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void abrirPantallaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/poli/Ejercicio/view/Productos.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Productos");
            stage.show();

            Stage ventanaActual = (Stage) txtId.getScene().getWindow();
            ventanaActual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la pantalla de productos: " + e.getMessage());
        }
    }

            @FXML
private void handleBuilder() {
    Proveedor proveedor = new Proveedor.ProveedorBuilder()
            .setId("PROV123")
            .setNombre("Proveedor de Ejemplo")
            .setCertificaciones(Arrays.asList(
                    new Certificacion("CERT456", "ISO 9001",
                            java.sql.Date.valueOf(LocalDate.of(2022, 1, 1)),
                            java.sql.Date.valueOf(LocalDate.of(2024, 1, 1)))
            ))
            .setEvaluaciones(Arrays.asList(
                    new Evaluacion("EVAL789",
                            java.sql.Date.valueOf(LocalDate.now()),
                            4.5, "Buen proveedor")
            ))
            .setPoliticaEntrega(new PoliticaEntrega(7, 10.0, true, Arrays.asList("Zona A", "Zona B")))
            .build();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Proveedor Creado");
    alert.setHeaderText("Se ha creado un proveedor usando el patrón Builder");
    alert.setContentText("Nombre: " + proveedor.getNombre() +
            "\nContacto: " + proveedor.getContacto() +
            "\nCertificación: " + proveedor.getCertificaciones().get(0).getNombre() +
            "\nEvaluación: " + proveedor.getEvaluaciones().get(0).getComentarios() +
            "\nPolítica de Entrega: " + proveedor.getPoliticaEntrega().getZonasCobertura());

    alert.showAndWait();
}
 @FXML
    private void handleBridge() {
        StringBuilder output = new StringBuilder();

        // carga frágil
        Envio envioNacional = new Nacional();
        Mercancia cargaFragil = new CargaFragil(envioNacional, "Carga frágil", 10);
        envioNacional.setMercancia(cargaFragil);
        output.append(envioNacional.enviar()).append("\n");
        output.append(cargaFragil.procesar()).append("\n");

        // carga pesada
        Envio envioInternacional = new Internacional();
        Mercancia cargaPesada = new CargaPesada(envioInternacional, "Carga pesada", 100.0);
        envioInternacional.setMercancia(cargaPesada);
        output.append(envioInternacional.enviar()).append("\n");
        output.append(cargaPesada.procesar()).append("\n");

        // Carga express
        Envio envioCargaExpress = new CargaExpress();
        Mercancia cargaPesadaExpress = new CargaPesada(envioCargaExpress, "Carga express", 100.0);
        envioCargaExpress.setMercancia(cargaPesadaExpress);
        output.append(envioCargaExpress.enviar()).append("\n");
        output.append(cargaPesadaExpress.procesar()).append("\n");

        // Mostrar en ventana
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Envíos Bridge");
        alert.setHeaderText("Se procesaron los siguientes envíos:");
        alert.setContentText(output.toString());
        alert.showAndWait();
    }
}