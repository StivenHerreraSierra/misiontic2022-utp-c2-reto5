package co.edu.utp.misiontic2022.c2.reto4.view;

import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelBanco extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private final String[] columnas = { "ID Proyecto", "Constructora", "Ciudad", "Clasificación", "Estrato", "Líder" };

	private List<ProyectoBancoVo> proyectos;

	public TableModelBanco() {
		proyectos = new ArrayList<>();
	}

	public void updateData(List<ProyectoBancoVo> proyectos) {
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
		return null;
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return proyectos.get(row).getId();
		case 1:
			return proyectos.get(row).getConstructora();
		case 2:
			return proyectos.get(row).getCiudad();
		case 3:
			return proyectos.get(row).getClasificacion();
		case 4:
			return proyectos.get(row).getEstrato();
		case 5:
			return proyectos.get(row).getLider();
		default:
			return "";
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (!existe(row))
			proyectos.add(new ProyectoBancoVo());

		ProyectoBancoVo proyecto = proyectos.get(row);
		switch (col) {
		case 0:
			proyecto.setId((Integer) value);
			break;
		case 1:
			proyecto.setConstructora((String) value);
			break;
		case 2:
			proyecto.setCiudad((String) value);
			break;
		case 3:
			proyecto.setClasificacion((String) value);
			break;
		case 4:
			proyecto.setEstrato((Integer) value);
			break;
		case 5:
			proyecto.setLider((String) value);
			break;
		}
	}

	private boolean existe(int row) {
		return proyectos.size() > row;
	}
}
