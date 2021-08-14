/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic2022.c2.reto4.view;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author stiven
 */
class TableModelTop extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private final String[] columnas = { "Líder", "Valor" };
	private List<ComprasDeLiderVo> compras;

	public TableModelTop() {
		compras = new ArrayList<>();
	}

	public void updateData(List<ComprasDeLiderVo> compras) {
		this.compras = compras;
	}

	@Override
	public int getRowCount() {
		return compras.size();
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
			return compras.get(row).getLider();
		case 1:
			return compras.get(row).getValor();
		default:
			return "";
		}
	}

}
