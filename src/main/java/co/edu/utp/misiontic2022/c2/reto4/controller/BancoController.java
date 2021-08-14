/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic2022.c2.reto4.controller;

import co.edu.utp.misiontic2022.c2.reto4.model.dao.DaoException;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.IOUtilities;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author stiven
 */
public class BancoController {
	private ProyectoBancoDao proyectoBancoDao;

	public BancoController() {
		proyectoBancoDao = new ProyectoBancoDao();
	}

	public List<ProyectoBancoVo> getProyectosFinanciadosPorBanco(String banco) {
		List<ProyectoBancoVo> proyectos = new ArrayList<>(0);

		try {
			proyectos = proyectoBancoDao.proyectosFinanciadosPorBanco(banco);
		} catch (DaoException ex) {
			IOUtilities.mostrarMensaje(ex.getMessage(), "Error Consulta", JOptionPane.ERROR_MESSAGE);
		}

		return proyectos;
	}
}
