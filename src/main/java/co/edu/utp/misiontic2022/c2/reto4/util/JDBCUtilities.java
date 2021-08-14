package co.edu.utp.misiontic2022.c2.reto4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.DaoException;

public class JDBCUtilities {
	// Atributos de clase para gestión de conexión con la base de datos.
	private static final String UBICACION_BD = "ProyectosConstruccion.db";
	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			String url = "jdbc:sqlite:" + UBICACION_BD;
			connection = DriverManager.getConnection(url);
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Error cerrando conexión: " + e.getMessage());
			}
		}
	}

	public static void cerrarStatement(PreparedStatement stat) throws DaoException {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				throw new DaoException("Error en SQL: " + e);
			}
		}
	}

	public static void cerrarResult(ResultSet rs) throws DaoException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DaoException("Error en SQL: " + e);
			}
		}
	}
}
