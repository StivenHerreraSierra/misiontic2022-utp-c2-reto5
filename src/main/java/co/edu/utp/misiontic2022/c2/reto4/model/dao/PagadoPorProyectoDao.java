package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class PagadoPorProyectoDao {
	private final String CONSULTA = "SELECT P.ID_Proyecto ID, SUM(C.Cantidad * M.Precio_Unidad) VALOR\n"
			+ "FROM Proyecto P\n" + "JOIN Compra C\n" + "ON P.ID_Proyecto = C.ID_Proyecto\n"
			+ "JOIN MaterialConstruccion M\n" + "ON C.ID_MaterialConstruccion = M.ID_MaterialConstruccion\n"
			+ "WHERE C.Pagado = \"Si\"\n" + "GROUP BY P.ID_Proyecto\n" + "HAVING VALOR > ?\n" + "ORDER BY VALOR DESC";

	public List<PagadoPorProyectoVo> getTotalPagadoPorProyectos(Double limiteInferior) throws DaoException {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<PagadoPorProyectoVo> pagos = new ArrayList<>(0);
		try {
			conn = JDBCUtilities.getConnection();
			stat = conn.prepareStatement(CONSULTA);
			stat.setDouble(1, limiteInferior);
			rs = stat.executeQuery();

			while (rs.next()) {
				pagos.add(convertir(rs));
			}
		} catch (SQLException e) {
			throw new DaoException("Error en SQL: " + e);
		} finally {
			JDBCUtilities.cerrarStatement(stat);
			JDBCUtilities.cerrarResult(rs);
		}

		return pagos;
	}

	private PagadoPorProyectoVo convertir(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		Double valor = rs.getDouble("VALOR");
		return new PagadoPorProyectoVo(id, valor);
	}
}
