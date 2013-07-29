package com.gmail.lrchfox3.paint;

// <editor-fold defaultstate="collapsed" desc=" Librerias ">
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import com.gmail.lrchfox3.utilitarios.Raton2;
// </editor-fold>

/**
 * Arrastra uno de los trazos dibujados cuando se arrastra el ratón.
 */
public class ArrastraTrazo implements InterfaceArrastrarRaton {

    /** Lista de trazos dibujados */
    private LinkedList<Trazo> trazos;
    /** Lienzo en el que se dibuja */
    private Component lienzo;
    /** Indice en la lista de trazos del trazo que se est� arrastrando */
    private int trazoArrastrado;
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
    private OpcionesDibujo optDibujo;
    private static int SIZE = 5;
    // cargado totalmente de disco

    /**
     * Construye una instancia de esta clase, guardando los par�metros que se
     * le pasan.
     * @param trazos Lista de trazos dibujados
     * @param lienzo Lienzo en el que están dibujados los trazos.
     */
    public ArrastraTrazo(LinkedList<Trazo> trazos, Component lienzo) {
        this.trazos = trazos;
        this.lienzo = lienzo;
        optDibujo = new OpcionesDibujo(trazos, lienzo);
    }

    /**
     * Busca el trazo más cercano a la posición x,y que se le pasa y lo marca
     * como trazo para arrastrar.
     */
    public void comienzaArrastra(int x, int y) {
        trazoArrastrado = 0;
        if (trazos.size() == 0) {
            return;
        }
        double distancia = trazos.get(0).dameDistanciaMinima(x, y);
        for (int i = 1; i < trazos.size(); i++) {
            double distanciaAux = trazos.get(i).dameDistanciaMinima(x, y);
            if (distanciaAux < distancia) {
                distancia = distanciaAux;
                trazoArrastrado = i;
            }
        }
    }

    /**
     * Arrastra el trazo marcado como trazo para arrastrar y lo desplaza en 
     * x una distancia xNueva - xAntigua y en y una distnacia yNueva - yAntigua
     */
    public void arrastra(int xAntigua, int yAntigua, int xNueva, int yNueva) {
        if (trazos.size() == 0) {
            return;
        }
        Trazo trazo = trazos.get(trazoArrastrado);
        for (int i = 0; i < trazo.getNumeroPuntos(); i++) {
            Point2D punto = trazo.getPunto(i);
            punto.setLocation(punto.getX() + (xNueva - xAntigua), punto.getY() + (yNueva - yAntigua));
        }
        lienzo.repaint();
    }

    public int[] trazoActual(Point2D p) {
        int actual[] = new int[2];
        actual[0] = -1;
        actual[1] = -1;
        if (trazos.size() == 0) {
            return actual;
        }
        for (int i = 0; i < trazos.size(); i++) {
            Trazo trazo = trazos.get(i);

            for (int j = 0; j < trazo.getNumeroPuntos(); j++) {
                double px = trazo.getPunto(j).getX() - SIZE / 2;
                double py = trazo.getPunto(j).getY() - SIZE / 2;
                Rectangle2D r = new Rectangle2D.Double(px, py, SIZE, SIZE);
                if (r.contains(p)) {
                    actual[0] = i;
                    actual[1] = j;
                }
            }
        }
        return actual;

    }

    public void actualizarTrazo(int[] current, Point2D eventPoint) {
        if (trazos.size() > 0) {
            Trazo trazo = trazos.get(current[0]);
            trazo.setPunto(current[1], eventPoint);
            lienzo.repaint();
        }
    }

    /**
     * Zoom de alejar
     */
    public void Alejar() {
        if (ancho_imagen_original == -1) {
            return;
        }

        //
        // Se limita la imagen para que no se pueda reducir mas que
        // el tamano del applet. En caso de que no vaya a quedar
        //	mas pequena, se cambia.
        // Se reduce la imagen un 1%
        //
        if (((alto_imagen * 0.99) >= lienzo.getSize().height) &&
                ((ancho_imagen * 0.99) >= lienzo.getSize().width)) {
            alto_imagen = alto_imagen * 0.99;
            ancho_imagen = ancho_imagen * 0.99;

            x = x_raton - (x_raton - x) * 0.99;
            y = y_raton - (y_raton - y) * 0.99;
            if (x > 0.0) {
                x = 0.0;
            }
            if (y > 0.0) {
                y = 0.0;
            }

            lienzo.repaint();
        }
    }

    /**
     * @return the modoArrastrar
     */
    public boolean isModoArrastrar() {
        return ((JPanelPaint) lienzo).isModoArrastrar();
    }

    public int getOpcionDibujo() {
        return OpcionesDibujo.MANO_ALZADA;
    }

    // <editor-fold defaultstate="collapsed" desc=" No Hacen Nada ">
    public void finalizaArrastra(int x, int y) {
    }

    public void simularLinea(MouseEvent e) {
    }

    public void crearLinea() {
    }

    public void manoAlzada(MouseEvent e) {
    }

    public void soltarManoAlzada() {
    }

    public void simularRectangulo(MouseEvent e) {
    }

    public void crearRectangulo() {
    }

    public void simularOvalo(MouseEvent e) {
    }

    public void crearOvalo() {
    }

    public void simularRectanguloRedondo(MouseEvent e) {
    }

    public void crearRectanguloRedondo() {
    }

    public void setShapeMaker() {
    }
    // </editor-fold>
}
