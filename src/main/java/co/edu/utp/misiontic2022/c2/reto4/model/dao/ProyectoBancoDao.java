package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class ProyectoBancoDao {
	private final String CONSULTA = "SELECT ID_Proyecto ID, Constructora, Ciudad, Proyecto.Clasificacion Clasificacion, Estrato, Nombre||\" \"||Primer_Apellido||\" \"||Segundo_Apellido as \"LIDER\"\n"
			+ "FROM Proyecto\n" + "JOIN Tipo\n" + "ON Proyecto.ID_Tipo = Tipo.ID_Tipo\n" + "JOIN Lider\n"
			+ "ON Proyecto.ID_Lider = Lider.ID_Lider \n" + "WHERE Banco_Vinculado = ?\n"
			+ "ORDER BY Fecha_Inicio DESC, Ciudad, Constructora";

	public List<ProyectoBancoVo> proyectosFinanciadosPorBanco(String banco) throws DaoException {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<ProyectoBancoVo> proyectos = new ArrayList<>(0);
		try {
			conn = JDBCUtilities.getConnection();
			stat = conn.prepareStatement(CONSULTA);
			stat.setString(1, banco);
			rs = stat.executeQuery();
			while (rs.next()) {
				proyectos.add(convertir(rs));
			}
		} catch (SQLException e) {
			throw new DaoException("Error en SQL: " + e.getMessage());
		} finally {
			JDBCUtilities.cerrarStatement(stat);
			JDBCUtilities.cerrarResult(rs);
		}

		return proyectos;
	}

	private ProyectoBancoVo convertir(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		String constructora = rs.getString("Constructora");
		String ciudad = rs.getString("Ciudad");
		String clasificacion = rs.getString("Clasificacion");
		Integer estrato = rs.getInt("Estrato");
		String lider = rs.getString("LIDER");

		return new ProyectoBancoVo(id, constructora, ciudad, clasificacion, estrato, lider);
	}
}
