package co.edu.utp.misiontic2022.c2.reto4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.util.JDBCUtilities;

public class ComprasDeLiderDao {
	private final String CONSULTA = "SELECT L.Nombre||\" \"||L.Primer_Apellido||\" \"||L.Segundo_Apellido as \"LIDER\", SUM(M.Precio_Unidad * C.Cantidad) VALOR\n"
			+ "FROM Lider L\n" + "JOIN Proyecto P\n" + "ON L.ID_Lider = P.ID_Lider\n" + "JOIN Compra C\n"
			+ "ON P.ID_Proyecto = C.ID_Proyecto\n" + "JOIN MaterialConstruccion M\n"
			+ "ON M.ID_MaterialConstruccion = C.ID_MaterialConstruccion\n" + "GROUP BY LIDER\n" + "ORDER BY VALOR ASC\n"
			+ "LIMIT 10";

	public List<ComprasDeLiderVo> getLideresQueMenosGastan() throws DaoException {
		List<ComprasDeLiderVo> compras = new ArrayList<>(0);
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtilities.getConnection();
			stat = conn.prepareStatement(CONSULTA);
			rs = stat.executeQuery();

			while (rs.next()) {
				compras.add(convertir(rs));
			}
		} catch (SQLException e) {
			throw new DaoException("Error en SQL: " + e);
		} finally {
			JDBCUtilities.cerrarStatement(stat);
			JDBCUtilities.cerrarResult(rs);
		}

		return compras;
	}

	private ComprasDeLiderVo convertir(ResultSet rs) throws SQLException {
		String lider = rs.getString("LIDER");
		Double valor = rs.getDouble("VALOR");
		return new ComprasDeLiderVo(lider, valor);
	}

}