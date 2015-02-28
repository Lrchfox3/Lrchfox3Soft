package com.gmail.lrchfox3.utilitarios;

//
// Javier Abell�n, 20 Jun 2000
//
// Clase Raton2, que es MouseAdapter y MouseMotionListener,
// atendiendo tanto a clicks de los botones del raton como a su
// movimiento
//
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread.*;
import java.lang.*;

//
// La clase Raton2 ve a Panoramica, encargandose de actualizar
// sus variables "operacion" y "x_raton" e "y_raton" en funcion
// de los clicks y movimientos del raton
//
public class Raton2 extends MouseAdapter implements MouseMotionListener
{
	Panoramica ventana;

	//
	// Constructor, se guarda la clase Panoramica para poder
	// tocar sus variables.
	//
	public Raton2 (Panoramica ventana)
	{
		this.ventana = ventana;
	}

	//
	// Se ha pulado un boton del raton. Se obtienen las coordenadas
	// del raton y se llama al metodo Dame_Operacion, que en funcion
	// de ellas dice si se quiere zoom+, zoom- o desplazar.
	// Actualiza las variables de Panoramica
	//
	public void mousePressed (MouseEvent e)
	{
		ventana.x_raton = e.getX();
		ventana.y_raton = e.getY();

		ventana.operacion = Dame_Operacion (e.getX(), e.getY());
	}

	//
	// Boton soltado. Se dice a Panoramica que no se quiere hacer
	// nada. (operacion = 0)
	//
	public void mouseReleased (MouseEvent e)
	{
		ventana.operacion = 0;
	}

	//
	// Devuelve la operacion que se desea hacer en funcion de la
	// posicion del raton.
	// Raton en los bordes de la foto -> desplazar imagen
	// Raton en el centro, de la mitad hacia arriba -> zoom+
	// Raton en el centro, de la mitad hacia abajo -> zoom-
	//	
	int Dame_Operacion (int x_raton, int y_raton)
	{
		int ancho = 0;
		int alto = 0;

		//
		// Se obtienen las dimensiones del applet
		//
		ancho = ventana.getSize().width;
		alto = ventana.getSize().height;

		//
		// Se echa la cuenta de la operacion que se quiere
		// hacer en funcion de las coordenadas del raton
		//
		if (Math.abs(ancho/2 - x_raton) < .1 * ancho)
		{
			if (((alto/2 - y_raton) < .1 * alto) &&
					((alto/2 - y_raton) > 0))
				return 1;
			else
			if (((alto/2 - y_raton) > -.1 * alto) &&
					((alto/2 - y_raton) < 0))
				return 2;
			else
				return 3;
		}
		else
			return 3;
	}

	//
	// Raton arrastrado, no se hace nada
	//
	public void mouseDragged (MouseEvent e)
	{
	}

	//
	// Se mueve el raton. Se llama a Dame_Operacion y en funcion
	// de lo que devuelva, se cambia el cursor
	// desplazar -> cursor mano
	// zoom+ -> cursor cruz
	// zoom- -> cursor de texto (linea vertical)
	//
	public void mouseMoved (MouseEvent e)
	{
		int operacion;

		operacion = Dame_Operacion (e.getX(), e.getY());

		switch (operacion)
		{
			case 1:
				ventana.setCursor (Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				break;
			case 2:
				ventana.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				break;
			case 3:
				ventana.setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				break;
		}
	}
}
