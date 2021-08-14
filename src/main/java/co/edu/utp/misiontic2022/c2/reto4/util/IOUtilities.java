/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic2022.c2.reto4.util;

import javax.swing.JOptionPane;

/**
 *
 * @author stiven
 */
public class IOUtilities {

	public static void mostrarMensaje(String mensaje, String titulo, int tipo) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
	}

}
