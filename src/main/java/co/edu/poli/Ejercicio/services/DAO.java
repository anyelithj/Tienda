package co.edu.poli.Ejercicio.services;

import java.sql.SQLException;

public interface DAO<T> extends CrudDAO<T> {
    int count() throws SQLException;
}
