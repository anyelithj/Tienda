package co.edu.poli.Ejercicio.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.Ejercicio.model.Producto;
import co.edu.poli.Ejercicio.model.ProductoElectronico;
import co.edu.poli.Ejercicio.model.ProductoAlimento;

public class ProductoDAO implements DAO<Producto> {
	 @Override
	    public void insertar(Producto producto) throws SQLException {
	        String sql = "INSERT INTO Producto (id, descripcion, precio, tipo, voltaje_entrada, aporte_calorico) VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DatabaseConnection.getInstance().getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, producto.getId());
	            stmt.setString(2, producto.getDescripcion());
	            stmt.setDouble(3, producto.getPrecio());
	            stmt.setString(4, producto.getTipo());
	            if (producto instanceof ProductoElectronico) {
	                stmt.setString(5, ((ProductoElectronico) producto).getVoltajeEntrada());
	                stmt.setNull(6, Types.INTEGER);
	            } else if (producto instanceof ProductoAlimento) {
	                stmt.setNull(5, Types.VARCHAR);
	                stmt.setInt(6, ((ProductoAlimento) producto).getAporteCalorico());
	            } else {
	                stmt.setNull(5, Types.VARCHAR);
	                stmt.setNull(6, Types.INTEGER);
	            }
	            stmt.executeUpdate();
	        }
	    }
	 @Override
	 public void actualizar(Producto producto) throws SQLException {
	     String sql = "UPDATE Producto SET descripcion = ?, precio = ?, tipo = ?, voltaje_entrada = ?, aporte_calorico = ? WHERE id = ?";
	     try (Connection conn = DatabaseConnection.getInstance().getConnection();
	          PreparedStatement stmt = conn.prepareStatement(sql)) {

	         stmt.setString(1, producto.getDescripcion());
	         stmt.setDouble(2, producto.getPrecio());
	         stmt.setString(3, producto.getTipo());

	         if (producto instanceof ProductoElectronico) {
	             stmt.setString(4, ((ProductoElectronico) producto).getVoltajeEntrada());
	             stmt.setNull(5, Types.INTEGER);
	         } else if (producto instanceof ProductoAlimento) {
	             stmt.setNull(4, Types.VARCHAR);
	             stmt.setInt(5, ((ProductoAlimento) producto).getAporteCalorico());
	         } else {
	             stmt.setNull(4, Types.VARCHAR);
	             stmt.setNull(5, Types.INTEGER);
	         }

	         stmt.setString(6, producto.getId()); 
	         int filasAfectadas = stmt.executeUpdate();
	         if (filasAfectadas == 0) {
	             throw new SQLException("No se encontró el producto con ID: " + producto.getId());
	         }
	     }
	 }

	 @Override
	 public Producto obtenerPorId(String id) throws SQLException {
	     String sql = "SELECT * FROM Producto WHERE id = ?";
	     try (Connection conn = DatabaseConnection.getInstance().getConnection();
	          PreparedStatement stmt = conn.prepareStatement(sql)) {
	         stmt.setString(1, id);
	         try (ResultSet rs = stmt.executeQuery()) {
	             if (rs.next()) {
	                 return mapearProducto(rs);
	             }
	         }
	     }
	     return null;
	 }

	 @Override
	    public void eliminar(String id) throws SQLException {
	        String sql = "DELETE FROM Producto WHERE id = ?";
	        try (Connection conn = DatabaseConnection.getInstance().getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, id);
	            stmt.executeUpdate();
	        }
	    }
	 @Override
	 public List<Producto> obtenerPorTipo(String tipo) throws SQLException {
		    List<Producto> productos = new ArrayList<>();
		    String sql = "SELECT * FROM Producto WHERE tipo = ?";
		    try (Connection conn = DatabaseConnection.getInstance().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, tipo);
		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                productos.add(mapearProducto(rs));
		            }
		        }
		    }
		    return productos;
		}

	  @Override
	    public List<Producto> obtenerTodos() throws SQLException {
	        List<Producto> productos = new ArrayList<>();
	        String sql = "SELECT * FROM Producto";
	        try (Connection conn = DatabaseConnection.getInstance().getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                productos.add(mapearProducto(rs));
	            }
	        }
	        return productos;
	    }
	  @Override
	    public int count() throws SQLException {
	        String sql = "SELECT COUNT(*) FROM Producto";
	        try (Connection conn = DatabaseConnection.getInstance().getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
	        }
	        return 0;
	    }
	  private Producto mapearProducto(ResultSet rs) throws SQLException {
		    String id = rs.getString("id");
		    String descripcion = rs.getString("descripcion");
		    double precio = rs.getDouble("precio");
		    String tipo = rs.getString("tipo");
		    String voltajeEntrada = rs.getString("voltaje_entrada");
		    Integer aporteCalorico = (rs.getObject("aporte_calorico") != null) ? rs.getInt("aporte_calorico") : null;

		    if ("Electrónico".equalsIgnoreCase(tipo)) {
		        return new ProductoElectronico(id, descripcion, precio, tipo, voltajeEntrada).crearProducto(id, descripcion, precio, tipo, voltajeEntrada, null);
		    } else if ("Alimento".equalsIgnoreCase(tipo)) {
		        return new ProductoAlimento(id, descripcion, precio, tipo, aporteCalorico).crearProducto(id, descripcion, precio, tipo, null, aporteCalorico);
		    } else {
		        throw new SQLException("Tipo de producto desconocido: " + tipo);
		    }
		}

	}

