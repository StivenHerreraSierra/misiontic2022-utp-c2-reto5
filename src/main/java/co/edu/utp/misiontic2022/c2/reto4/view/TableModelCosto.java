/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic2022.c2.reto4.view;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.PagadoPorProyectoVo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author stiven
 */
public class TableModelCosto extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private final String[] columnas = { "ID Proyecto", "Valor" };
	private List<PagadoPorProyectoVo> proyectos;

	public TableModelCosto() {
		proyectos = new ArrayList<>();
	}

	public void updateData(List<PagadoPorProyectoVo> proyectos) {
		this.proyectos = proyectos;
	}

	@Override
	public int getRowCount() {
		return proyectos.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public String getColumnName(int col) {
		if (col < columnas.length)
			return columnas[col];
		return "";
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return proyectos.get(row).getId();
		case 1:
			return proyectos.get(row).getValor();
		default:
			return "";
		}
	}

}
