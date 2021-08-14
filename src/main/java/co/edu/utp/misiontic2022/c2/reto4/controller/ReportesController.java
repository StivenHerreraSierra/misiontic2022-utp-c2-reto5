package co.edu.utp.misiontic2022.c2.reto4.controller;

import java.util.List;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.DaoException;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.PagadoPorProyectoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;

public class ReportesController {
	private final ProyectoBancoDao proyectoBancoDao;
	private final PagadoPorProyectoDao pagadoPorProyectoDao;
	private final ComprasDeLiderDao comprasDeLiderDao;

	public ReportesController() {
		proyectoBancoDao = new ProyectoBancoDao();
		pagadoPorProyectoDao = new PagadoPorProyectoDao();
		comprasDeLiderDao = new ComprasDeLiderDao();
	}

	public List<ProyectoBancoVo> getProyectosFinanciadosPorBanco(String banco) throws DaoException {
		return proyectoBancoDao.proyectosFinanciadosPorBanco(banco);
	}

	public List<PagadoPorProyectoVo> getTotalPagadoPorProyectos(Double limiteInferior) throws DaoException {
		return pagadoPorProyectoDao.getTotalPagadoPorProyectos(limiteInferior);
	}

	public List<ComprasDeLiderVo> getLideresQueMenosGastan() throws DaoException {
		return comprasDeLiderDao.getLideresQueMenosGastan();
	}
}
