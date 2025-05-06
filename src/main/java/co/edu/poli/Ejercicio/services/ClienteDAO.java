package co.edu.poli.Ejercicio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.edu.poli.Ejercicio.model.Cliente;

public class ClienteDAO {
    private final Connection conexion;

    public ClienteDAO() throws SQLException {
        this.conexion = DatabaseConnection.getInstance().getConnection();
    }

    public boolean insertarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (id, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getNombre());
            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        }
    }

    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getId());
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        }
    }

    public boolean eliminarCliente(String id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;
        }
    }

    public boolean validarCredenciales(String id, String nombre) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id = ? AND nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, nombre);
            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
