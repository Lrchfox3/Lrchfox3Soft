package com.gmail.lrchfox3.utilitarios;

//
//
// Javier Abell�n. 20 Jun 2000
//
//	Desplazamiento y zoom de una imagen .jpg en un applet.
//
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.lang.*;

//
// Clase Panoramica, es el applet y es a su vez thread.
//
// El hilo est� en un bucle infinito realizando la operaci�n que
// indique "operacion". La clase Raton2 atiende al raton y se encarga
// de cambiar la variable "operacion".
//
//
public class Panoramica extends Applet implements Runnable
{
	//
	// Variables de la clase
	//
	Raton2 Movimiento_Raton;      // Clase que trata event. raton
	double x = 0.0;			// esquina sup. izq. de foto
	double y = 0.0;
	public int x_raton = 0;		// pos. de rat�n al hacer click
	public int y_raton = 0;
	double ancho_imagen = 0;	// ancho/alto imagen, zoom incluido
	double alto_imagen = 0;
	int ancho_imagen_original = 0;// ancho/alto inicial de imagen
	int alto_imagen_original = 0;
	int ancho_applet = 0;		// ancho/alto del applet en la pag.
	int alto_applet = 0;
	Image Imagen;			// Imagen a presentar 
	MediaTracker tracker; 		// Control imagen. Avisa cuando se
						// cargado totalmente de disco

	//
	// operacion : 
	//		0 nada
	// 	1 acercar
	// 	2 alejar
	// 	3 desplazar
	//
	public int operacion;

	//
	// Metodo del thread, bucle infinito que ejecuta lo indicado
	// en "operacion".
	//
	public void run ()
	{
		while (true)
		{
			switch (operacion)
			{
				case 1:
					Acercar();
					break;
				case 2:
					Alejar();
					break;
				case 3:
					Desplazar();
					break;
			}

			if (operacion != 0)
				repaint();

			//
			// Espera de 0.1 segundos
			//
			try 
			{
				Thread.currentThread().sleep (100);
			}
			catch (InterruptedException e)
			{
			}
		}
	}

	//
	// Espera a que la imagen se cargue de disco un maximo de
	// 1 minuto.
	//
	public void Espera()
	{
		try 
		{
			tracker.waitForID (1, 60000);
		}
		catch (InterruptedException e)
		{
		}
	}

	//
	//  Metodo de inicializacion del applet. Se encarga de
	// instanciar la clase Raton2, a�adirla al applet, 
	// cargar la imagen y ejecutar el thread.
	//	
	public void init()
	{

		//
		// Carga de la imagen
		//
		String Fichero_Imagen;
		Fichero_Imagen = getParameter ("Fichero_Imagen");
		if (Fichero_Imagen == null)
		{
			return;
		}
		Imagen = getImage (getDocumentBase(), Fichero_Imagen);

		//
		// Espera que la imagen se haya cargado del todo
		//
		tracker = new MediaTracker (this);
		tracker.addImage (Imagen, 1);
		Espera();
		if (Imagen == null)
		{
			return;
		}

		//
		// Coge ancho y alto de la imagen
		//
		ancho_imagen_original = Imagen.getWidth (this);
		alto_imagen_original = Imagen.getHeight (this);
		ancho_imagen = ancho_imagen_original;
		alto_imagen = alto_imagen_original;

		//
		// Anade listener del raton
		//
		Movimiento_Raton = new Raton2(this);
		addMouseListener (Movimiento_Raton);
		addMouseMotionListener (Movimiento_Raton);

		//
		// Arranca un hilo que se encarga de redibujar la imagen
		// continuemente, teniendo en cuenta si se esta haciendo
		//	zoom o desplazando la imagen
		//
		Thread otro = new Thread (this);
		otro.start();
	}

	//
	// El update por defecto del applet borra el applet y llama
	// al metodo paint(). 
	// Se redefine update para que no borre la imagen, puesto que
	// vamos a superponer la nueva, evitando parpadeo, pero si
	// llamamos a paint()
	//
	public void update (Graphics g)
	{
		paint(g);
	}

	//
	// Metodo al que llama el navegador cuando necesita pintar el
	// applet.
	// Se pinta la imagen en el start, por medio de la llamada a 
	// repaint(), definida por defecto en el applet y que se encarga
	// de llamar a update()
	//
	public void start ()
	{
		repaint();
	}

	//
	// Se dibuja la imagen, con al alto_imagen y ancho_imagen
	// calculado por las funciones de zoom y la posicion x, y 
	// calculada en dichos metodos y el desplazamiento. 
	// La suma de 0.5 es para que al redondear a entero, se redondee
	// y no se trunque.
	//
	public void paint (Graphics g)
	{
		g.drawImage (Imagen, (int)(x + 0.5), (int)(y + 0.5), 
			(int)(ancho_imagen + 0.5), (int)(alto_imagen + 0.5), 
			this);
	}

	//
	// Zoom de acercar.
	//
	public void Acercar()
	{
		if (ancho_imagen_original == -1)
		{
			return;
		}

		//
		// Se acerca un 1% y se llama a repaint()
		//
		x = x_raton - (x_raton - x) * 1.01;
		y = y_raton - (y_raton - y) * 1.01;
		alto_imagen = alto_imagen * 1.01;
		ancho_imagen = ancho_imagen * 1.01;
		repaint();
	}

	//
	// Zoom de alejar
	//
	public void Alejar()
	{
		if (ancho_imagen_original == -1)
		{
			return;
		}

		//
		// Se limita la imagen para que no se pueda reducir mas que
		// el tamano del applet. En caso de que no vaya a quedar
		//	mas pequena, se cambia.
		// Se reduce la imagen un 1%
		//
		if (((alto_imagen * 0.99) >= getSize().height) &&
			((ancho_imagen * 0.99) >=  getSize().width))
		{
			alto_imagen = alto_imagen * 0.99;
			ancho_imagen = ancho_imagen * 0.99;

			x = x_raton - (x_raton - x) * 0.99;
			y = y_raton - (y_raton - y) * 0.99;
			if (x>0.0) x = 0.0;
			if (y>0.0) y = 0.0;

			repaint();
		}
	}

	//
	// Desplazamiento de la imagen hacia el raton
	//
	public void Desplazar()
	{
		double desp_x = 0;
		double desp_y = 0;
		double modulo = 0;

		desp_x = (x_raton - getSize().width/2);
		desp_y = (y_raton - getSize().height/2);

		//
		// una cuenta aproximada del modulo, por aquello de no
		// hacer cuentas muy complejas
		//
		modulo = (Math.abs(desp_x) + Math.abs(desp_y));

		desp_x = desp_x / modulo * 4.0;
		desp_y = desp_y / modulo * 4.0;

		if ((x + desp_x <= 0) && 
			(x + desp_x + (int)(ancho_imagen + 0.5)) >= getSize().width)
				x = x + desp_x;

		if ((y + desp_y <= 0) && 
			(y + desp_y + (int)(alto_imagen + 0.5)) >= getSize().height)
				y = y + desp_y;

		repaint();
	}
}
