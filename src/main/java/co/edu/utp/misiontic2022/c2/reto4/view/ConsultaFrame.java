/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic2022.c2.reto4.view;

import co.edu.utp.misiontic2022.c2.reto4.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.reto4.model.dao.DaoException;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.reto4.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.reto4.util.IOUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author stiven
 */
public final class ConsultaFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	private ReportesController controller;

	public ConsultaFrame() {
		iniciarComponentes();
	}

	public void iniciarComponentes() {
		controller = new ReportesController();

		this.setMinimumSize(new Dimension(700, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Proyecto Construcción");

		tabbedPane = new JTabbedPane();
		tabbedPane.setAlignmentX(CENTER_ALIGNMENT);

		cargarBancoTab();
		cargarTotalPagadoTab();
		topLideresTab();
	}

	private TableModelBanco modelBanco;
	private JComboBox<String> campoBanco;
	private JLabel totalProyectosBanco;

	public void cargarBancoTab() {
		JPanel bancoTab = new JPanel();
		bancoTab.setAlignmentX(CENTER_ALIGNMENT);
		bancoTab.getInsets().set(10, 20, 10, 20);

		BoxLayout box = new BoxLayout(bancoTab, BoxLayout.Y_AXIS);
		bancoTab.setLayout(box);

		JLabel titulo = new JLabel("Proyectos Financiados");
		titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titulo.setMinimumSize(new Dimension(Integer.MAX_VALUE, 15));

		JPanel consultaPanel = new JPanel();
		consultaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel labelFor = new JLabel("Banco:");
		campoBanco = new JComboBox<>();
		campoBanco.setSize(300, 30);
		campoBanco.setPreferredSize(new Dimension(300, 30));
		campoBanco.setMaximumSize(new Dimension(300, 30));
		actualizarComboBoxBancos();
		AutoCompleteDecorator.decorate(campoBanco);
		JButton consultar = new JButton("Consultar");
		consultar.setPreferredSize(new Dimension(120, 30));
		consultar.addActionListener(e -> {
			updateBancoData();
		});

		consultaPanel.add(labelFor);
		consultaPanel.add(campoBanco);
		consultaPanel.add(consultar);
		consultaPanel.getInsets().set(5, 5, 5, 5);
		consultaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

		modelBanco = new TableModelBanco();
		JTable tablaBanco = new JTable(1, 6);
		tablaBanco.setModel(modelBanco);
		tablaBanco.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaBanco.setFillsViewportHeight(true);
		tablaBanco.getTableHeader().setReorderingAllowed(false);
		tablaBanco.getTableHeader().setResizingAllowed(true);
		tablaBanco.getColumnModel().setColumnSelectionAllowed(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		TableColumn column;
		for (int i = 0; i < modelBanco.getColumnCount(); i++) {
			column = tablaBanco.getColumnModel().getColumn(i);
			column.setCellRenderer(centerRenderer);
		}

		JScrollPane scrollTablaBanco = new JScrollPane(tablaBanco);
		scrollTablaBanco.setPreferredSize(new Dimension(620, 500));

		totalProyectosBanco = new JLabel("0 registros.");
		totalProyectosBanco.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		totalProyectosBanco.setMinimumSize(new Dimension(Integer.MAX_VALUE, 15));

		bancoTab.add(titulo);
		bancoTab.add(consultaPanel);
		bancoTab.add(scrollTablaBanco);
		bancoTab.add(totalProyectosBanco);

		bancoTab.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		tabbedPane.addTab("Banco", bancoTab);
		this.add(tabbedPane);
	}
	
	private void actualizarComboBoxBancos() {
		List<String> bancos;
		try {
			bancos = controller.getListaNombreBancos();
			campoBanco.removeAll();
			bancos.forEach(banco -> campoBanco.addItem(banco));
		} catch (DaoException e) {
			IOUtilities.mostrarMensaje("Error cargando datos en el ComboBox.\n" + e.getMessage(),
					"Error Consulta", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private TableModelCosto modelCosto;
	private JLabel totalRegistrosCosto;
	private JSpinner selectorCosto;

	public void cargarTotalPagadoTab() {
		JPanel pagadoTab = new JPanel();
		pagadoTab.setAlignmentX(CENTER_ALIGNMENT);
		pagadoTab.getInsets().set(10, 20, 10, 20);

		BoxLayout box = new BoxLayout(pagadoTab, BoxLayout.Y_AXIS);
		pagadoTab.setLayout(box);

		JLabel titulo = new JLabel("Total Pagado Por Proyecto");
		titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titulo.setMinimumSize(new Dimension(Integer.MAX_VALUE, 15));

		JPanel consultaPanel = new JPanel();
		consultaPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel labelFor = new JLabel("Costo mínimo:");

		selectorCosto = new JSpinner(new SpinnerNumberModel(0.0d, 0.0, Double.MAX_VALUE, 1000d));
		selectorCosto.setSize(300, 30);
		selectorCosto.setPreferredSize(new Dimension(300, 30));
		selectorCosto.setMaximumSize(new Dimension(300, 30));
		// Restringe la entrada del usuario a sólo números.
		((DefaultFormatter) ((JSpinner.DefaultEditor) selectorCosto.getEditor()).getTextField().getFormatter())
				.setAllowsInvalid(false);

		labelFor.setLabelFor(selectorCosto);
		JButton consultar = new JButton("Consultar");
		consultar.setPreferredSize(new Dimension(120, 30));
		consultar.addActionListener(e -> {
			updateCostoData();
		});

		consultaPanel.add(labelFor);
		consultaPanel.add(selectorCosto);
		consultaPanel.add(consultar);
		consultaPanel.getInsets().set(5, 5, 5, 5);
		consultaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

		modelCosto = new TableModelCosto();
		JTable tablaCosto = new JTable(1, 2);
		tablaCosto.setModel(modelCosto);
		tablaCosto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaCosto.setFillsViewportHeight(true);
		tablaCosto.getTableHeader().setReorderingAllowed(false);
		tablaCosto.getTableHeader().setResizingAllowed(true);
		tablaCosto.getColumnModel().setColumnSelectionAllowed(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaCosto.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		DoubleRenderer doubleRenderer = new DoubleRenderer();
		doubleRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaCosto.getColumnModel().getColumn(1).setCellRenderer(doubleRenderer);

		JScrollPane scrollTablaCosto = new JScrollPane(tablaCosto);
		scrollTablaCosto.setPreferredSize(new Dimension(620, 500));

		totalRegistrosCosto = new JLabel("0 registros.");
		totalRegistrosCosto.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		pagadoTab.add(titulo);
		pagadoTab.add(consultaPanel);
		pagadoTab.add(scrollTablaCosto);
		pagadoTab.add(totalRegistrosCosto);

		pagadoTab.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		tabbedPane.addTab("Costo", pagadoTab);
		this.add(tabbedPane);
	}

	private TableModelTop modelTop;
	private JLabel totalRegistrosTop;

	public void topLideresTab() {
		JPanel lideresTab = new JPanel();
		lideresTab.setAlignmentX(CENTER_ALIGNMENT);
		lideresTab.getInsets().set(10, 20, 10, 20);

		BoxLayout box = new BoxLayout(lideresTab, BoxLayout.Y_AXIS);
		lideresTab.setLayout(box);

		JLabel titulo = new JLabel("Top 10 Líderes Con Menos Gastos");
		titulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titulo.setMinimumSize(new Dimension(Integer.MAX_VALUE, 15));

		JPanel actualizarPanel = new JPanel();
		actualizarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton actualizar = new JButton("Actualizar");
		actualizar.setPreferredSize(new Dimension(120, 30));
		actualizar.addActionListener(e -> {
			updateTopData();
		});

		actualizarPanel.add(actualizar);
		actualizarPanel.getInsets().set(5, 5, 5, 5);
		actualizarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

		modelTop = new TableModelTop();
		JTable tablaTop = new JTable(10, 2);
		tablaTop.setModel(modelTop);
		tablaTop.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaTop.setFillsViewportHeight(true);
		tablaTop.getTableHeader().setReorderingAllowed(false);
		tablaTop.getTableHeader().setResizingAllowed(true);
		tablaTop.getColumnModel().setColumnSelectionAllowed(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaTop.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		DoubleRenderer doubleRenderer = new DoubleRenderer();
		doubleRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaTop.getColumnModel().getColumn(1).setCellRenderer(doubleRenderer);

		JScrollPane scrollTablaTop = new JScrollPane(tablaTop);
		scrollTablaTop.setPreferredSize(new Dimension(620, 500));

		totalRegistrosTop = new JLabel("0 registros.");
		totalRegistrosTop.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		lideresTab.add(titulo);
		lideresTab.add(actualizarPanel);
		lideresTab.add(scrollTablaTop);
		lideresTab.add(totalRegistrosTop);

		lideresTab.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		tabbedPane.addTab("Top 10", lideresTab);
		this.add(tabbedPane);
	}

	class DoubleRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		private NumberFormat formatter;

		@Override
		public void setValue(Object value) {
			if (formatter == null)
				formatter = NumberFormat.getCurrencyInstance();
			if (value instanceof Double)
				setText(formatter.format(value));
		}
	}

	private void updateBancoData() {
		String banco = campoBanco.getSelectedItem().toString();
		try {
			List<ProyectoBancoVo> proyectos = controller.getProyectosFinanciadosPorBanco(banco);
			modelBanco.updateData(proyectos);
			modelBanco.fireTableDataChanged();
			totalProyectosBanco.setText(proyectos.size() + " registros.");
		} catch (DaoException ex) {
			IOUtilities.mostrarMensaje(ex.getMessage(), "Error Consulta", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateCostoData() {
		Double valor = (Double) selectorCosto.getValue();
		try {
			List<PagadoPorProyectoVo> proyectos = controller.getTotalPagadoPorProyectos(valor);
			modelCosto.updateData(proyectos);
			modelCosto.fireTableDataChanged();
			totalRegistrosCosto.setText(proyectos.size() + " registros.");
		} catch (DaoException ex) {
			IOUtilities.mostrarMensaje(ex.getMessage(), "Error Consulta", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateTopData() {
		try {
			List<ComprasDeLiderVo> compras = controller.getLideresQueMenosGastan();
			modelTop.updateData(compras);
			modelTop.fireTableDataChanged();
			totalRegistrosTop.setText(compras.size() + " registros.");
		} catch (DaoException ex) {
			IOUtilities.mostrarMensaje(ex.getMessage(), "Error Consulta", JOptionPane.ERROR_MESSAGE);
		}
	}
}
