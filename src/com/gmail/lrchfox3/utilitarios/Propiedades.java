/*
 * Propiedades.java
 *
 * Created on December 26, 2006, 10:28 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.gmail.lrchfox3.utilitarios;

import java.awt.Font;
import java.awt.Color;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Propiedades implements Serializable {

    public static final ResourceBundle config = ResourceBundle.getBundle("com/gmail/lrchfox3/resources/config");
    public static ResourceBundle appBundle = ResourceBundle.getBundle("com/gmail/lrchfox3/resources/application"); // NOI18N

    //Tipo Letras
    private final Font fontEtiquetas = new Font("Segoe UI", Font.PLAIN, 11);
    private final Font fontTextos = new Font("Segoe UI", Font.PLAIN, 11);
    private final Font fontBotones = new Font("Segoe UI", Font.BOLD, 11);
    private final Font fontTitulos1 = new Font("Segoe UI", Font.PLAIN, 18);
    private final Font fontObligatorio = new Font("Segoe UI", Font.PLAIN, 14);
    //Tipos Colores
    private final Color colorTitulo1 = new Color(0, 0, 153);
    private final Color colorFondo = new Color(236, 233, 216);
    private final Color colorEtiquetaObligatorios = new Color(255, 51, 102);
    private final Color colorTextoDeshabilitado = new Color(224, 223, 227);
    private final Color colorListas = new Color(255, 255, 255);

    /*
     * imagenes
     */
    private final String imgAceptar = "/com/gmail/lrchfox3/imagenes/imgAceptar.png";
    private final String imgCancelar = "/com/gmail/lrchfox3/imagenes/imgCancelar.png";
    private final String imgCancelar2 = "/com/gmail/lrchfox3/imagenes/imgCancelar2.gif";
    private final String imgGuardar = "/com/gmail/lrchfox3/imagenes/imgGuardar.png";
    // Botones Paint
    private final String imgLapiz = "/com/gmail/lrchfox3/imagenes/imgLapiz.gif";
    private final String imgBorrador = "/com/gmail/lrchfox3/imagenes/imgBorrador.gif";
    private final String imgRectangulo = "/com/gmail/lrchfox3/imagenes/imgRectangulo.gif";
    private final String imgRectanguloRedondo = "/com/gmail/lrchfox3/imagenes/imgRectanguloRedondo.gif";
    private final String imgOvalo = "/com/gmail/lrchfox3/imagenes/imgOvalo.gif";
    private final String imgPoligono = "/com/gmail/lrchfox3/imagenes/imgPoligono.gif";
    private final String imgLinea = "/com/gmail/lrchfox3/imagenes/imgLinea.gif";
    private final String imgArco = "/com/gmail/lrchfox3/imagenes/imgArco.gif";
    private final String imgCurva = "/com/gmail/lrchfox3/imagenes/imgCurva.gif";
    private final String imgTriangulo = "/com/gmail/lrchfox3/imagenes/imgTriangulo.gif";
    private final String imgAgregarItem = "/com/gmail/lrchfox3/imagenes/imgAgregarElemento.gif";
    private final String imgCliente = "/com/gmail/lrchfox3/imagenes/imgCliente.png";
    private final String imgAgregarCliente = "/com/gmail/lrchfox3/imagenes/imgClienteAgregar.png";
    private final String imgEditarCliente = "/com/gmail/lrchfox3/imagenes/imgClienteEditar.png";
    private final String imgEliminarCliente = "/com/gmail/lrchfox3/imagenes/imgClienteEliminar.png";
    private final String imgBuscar = "/com/gmail/lrchfox3/imagenes/imgBuscar.gif";
    private final String imgListaClientes = "/com/gmail/lrchfox3/imagenes/imgClientes_Lista.gif";
    private final String imgLimpiar = "/com/gmail/lrchfox3/imagenes/imgLimpiar.png";
    private final String imgAgregar = "/com/gmail/lrchfox3/imagenes/imgAgregar.png";
    private final String imgEditar = "/com/gmail/lrchfox3/imagenes/imgEditar.png";
    private final String imgEliminar = "/com/gmail/lrchfox3/imagenes/imgEliminar.png";
    private final String imgVentanaAgregar = "/com/gmail/lrchfox3/imagenes/imgVentanaAgregar.png";
    private final String imgVentanaEditar = "/com/gmail/lrchfox3/imagenes/imgVentanaEditar.png";
    private final String imgVentanaEliminar = "/com/gmail/lrchfox3/imagenes/imgVentanaEliminar.png";
    private final String imgObligatorio = "/com/gmail/lrchfox3/imagenes/imgObligatorio.gif";
    private final String imgRecargar = "/com/gmail/lrchfox3/imagenes/imgRecargar.gif";
    private final String imgCotizaciones = "/com/gmail/lrchfox3/imagenes/imgCotizaciones.png";
    private final String imgSeguimientos = "/com/gmail/lrchfox3/imagenes/imgSeguimientos.png";
    private final String imgVerTipos = "/com/gmail/lrchfox3/imagenes/imgVer_Tipos.gif";
    private final String imgAplicar = "/com/gmail/lrchfox3/imagenes/imgAplicar.gif";
    private final String imgEditarCampo = "/com/gmail/lrchfox3/imagenes/imgEditarCampo.gif";
    private final String imgHora = "/com/gmail/lrchfox3/imagenes/imgHora.png";
    private final String imgTelefonos = "/com/gmail/lrchfox3/imagenes/imgTelefonos.png";
    private final String imgDirecciones = "/com/gmail/lrchfox3/imagenes/imgDirecciones.png";
    private final String imgContactos = "/com/gmail/lrchfox3/imagenes/imgContactos.png";
    private final String imgOpciones = "/com/gmail/lrchfox3/imagenes/imgOpciones.gif";
    private final String imgEsconderOpciones = "/com/gmail/lrchfox3/imagenes/imgEsconderOpciones.gif";
    private final String imgMostarOpciones = "/com/gmail/lrchfox3/imagenes/imgMostarOpciones.gif";
    private final String imgServicios = "/com/gmail/lrchfox3/imagenes/imgServicios.png";
    private final String imgSalirSistema = "/com/gmail/lrchfox3/imagenes/imgSalirSistema.png";
    private final String imgVerMontos = "/com/gmail/lrchfox3/imagenes/imgVerMontos.png";
    private final String imgLogo = "/com/gmail/lrchfox3/imagenes/imgLogo.png";
    private final String imgCalendario = "/com/gmail/lrchfox3/imagenes/imgCalendario.gif";
    private final String imgCambiarPanel = "/com/gmail/lrchfox3/imagenes/imgCambiarPanel.gif";
    private final String imgPropiedades = "/com/gmail/lrchfox3/imagenes/imgPropiedades.png";
    private final String imgBuscarEnTabla = "/com/gmail/lrchfox3/imagenes/imgBuscarEnTabla.png";
    private final String imgLimpiarTexto = "/com/gmail/lrchfox3/imagenes/imgLimpiarTexto.png";
    private final String imgComentario = "/com/gmail/lrchfox3/imagenes/botones/imgComentario.png";
    private final String imgImportar = "/com/gmail/lrchfox3/imagenes/botones/imgImportar.png";
    private final String imgExportar = "/com/gmail/lrchfox3/imagenes/botones/imgExportar.png";
    private final String imgCerrar = "/com/gmail/lrchfox3/imagenes/botones/imgCerrar.png";
    private final String imgConectar = "/com/gmail/lrchfox3/imagenes/botones/imgConectar.png";
    //Iconos
    private final String imgInfo = "/com/gmail/lrchfox3/imagenes/icon/imgInfo.png";
    private final String imgError = "/com/gmail/lrchfox3/imagenes/icon/imgError.png";
    private final String imgWarning = "/com/gmail/lrchfox3/imagenes/icon/imgWarning.png";
    private final String imgNote = "/com/gmail/lrchfox3/imagenes/icon/imgNote.png";
    private final String imgQuestion = "/com/gmail/lrchfox3/imagenes/icon/imgQuestion.png";

    /**
     * Creates a new instance of Propiedades
     */
    public Propiedades() {
    }

// <editor-fold defaultstate="collapsed" desc=" Funciones Tipos de Letras ">
    public Font getFontEtiquetas() {
        return fontEtiquetas;
    }

    public Font getFontBotones() {
        return fontBotones;
    }

    public Font getFontTitulos1() {
        return fontTitulos1;
    }

    public Font getFontTextos() {
        return fontTextos;
    }

    public Font getFontObligatorio() {
        return fontObligatorio;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Funciones Tipos de Colores ">
    public Color getColorTitulo1() {
        return colorTitulo1;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public Color getColorEtiquetaObligatorios() {
        return colorEtiquetaObligatorios;
    }

    public Color getColorTextoDeshabilitado() {
        return colorTextoDeshabilitado;
    }

    public Color getColorListas() {
        return colorListas;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Funciones imagenes ">
    public URL getImgAceptar() {
        return getClass().getResource(imgAceptar);
    }

    public URL getImgConectar() {
        return getClass().getResource(imgConectar);
    }

    public URL getImgCancelar() {
        return getClass().getResource(imgCancelar);
    }

    public URL getImgCancelar2() {
        return getClass().getResource(imgCancelar2);
    }

    public URL getImgGuardar() {
        return getClass().getResource(imgGuardar);
    }

    public URL getImgLapiz() {
        return getClass().getResource(imgLapiz);
    }

    public URL getImgBorrador() {
        return getClass().getResource(imgBorrador);
    }

    public URL getImgRectangulo() {
        return getClass().getResource(imgRectangulo);
    }

    public URL getImgRectanguloRedondo() {
        return getClass().getResource(imgRectanguloRedondo);
    }

    public URL getImgOvalo() {
        return getClass().getResource(imgOvalo);
    }

    public URL getImgPoligono() {
        return getClass().getResource(imgPoligono);
    }

    public URL getImgLinea() {
        return getClass().getResource(imgLinea);
    }

    public URL getImgCurva() {
        return getClass().getResource(imgCurva);
    }

    public URL getImgArco() {
        return getClass().getResource(imgArco);
    }

    public URL getImgTriangulo() {
        return getClass().getResource(imgTriangulo);
    }

    public URL getImgAgregarItem() {
        return getClass().getResource(imgAgregarItem);
    }

    public String getImgCliente() {
        return imgCliente;
    }

    public String getImgAgregarCliente() {
        return imgAgregarCliente;
    }

    public String getImgEditarCliente() {
        return imgEditarCliente;
    }

    public String getImgEliminarCliente() {
        return imgEliminarCliente;
    }

    public String getImgVentanaAgregar() {
        return imgVentanaAgregar;
    }

    public String getImgVentanaEditar() {
        return imgVentanaEditar;
    }

    public String getImgVentanaEliminar() {
        return imgVentanaEliminar;
    }
//
//    public String getImgCancelar() {
//        return imgCancelar;
//    }

    public String getImgBuscar() {
        return imgBuscar;
    }

    public String getImgListaClientes() {
        return imgListaClientes;
    }

    public String getImgLimpiar() {
        return imgLimpiar;
    }

    public String getImgAgregar() {
        return imgAgregar;
    }

    public String getImgEditar() {
        return imgEditar;
    }

    public String getImgEliminar() {
        return imgEliminar;
    }

//    public String getImgAceptar() {
//        return imgAceptar;
//    }
    public String getImgObligatorio() {
        return imgObligatorio;
    }

    public String getImgRecargar() {
        return imgRecargar;
    }

    public String getImgCotizaciones() {
        return imgCotizaciones;
    }

    public String getImgSeguimientos() {
        return imgSeguimientos;
    }

    public String getImgVerTipos() {
        return imgVerTipos;
    }

    public String getImgAplicar() {
        return imgAplicar;
    }

    public String getImgCerrar() {
        return imgCerrar;
    }

    public String getImgEditarCampo() {
        return imgEditarCampo;
    }

    public String getImgHora() {
        return imgHora;
    }

    public String getImgTelefonos() {
        return imgTelefonos;
    }

    public String getImgDirecciones() {
        return imgDirecciones;
    }

    public String getImgContactos() {
        return imgContactos;
    }

    public String getImgOpciones() {
        return imgOpciones;
    }

    public String getImgEsconderOpciones() {
        return imgEsconderOpciones;
    }

    public String getImgMostarOpciones() {
        return imgMostarOpciones;
    }

    public String getImgServicios() {
        return imgServicios;
    }

    public String getImgSalirSistema() {
        return imgSalirSistema;
    }

    public String getImgVerMontos() {
        return imgVerMontos;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public String getImgCalendario() {
        return imgCalendario;
    }

    public String getImgCambiarPanel() {
        return imgCambiarPanel;
    }

    public String getImgPropiedades() {
        return imgPropiedades;
    }

    public String getImgBuscarEnTabla() {
        return imgBuscarEnTabla;
    }

    public URL getImgLimpiarTexto() {
        return getClass().getResource(imgLimpiarTexto);
    }

    public URL getImgInfo() {
        return getClass().getResource(this.imgInfo);
    }

    public URL getImgError() {
        return getClass().getResource(this.imgError);
    }

    public URL getImgWarning() {
        return getClass().getResource(this.imgWarning);
    }

    public URL getImgComentario() {
        return getClass().getResource(this.imgComentario);
    }

    public URL getImgNota() {
        return getClass().getResource(this.imgNote);
    }

    public URL getImgQuestion() {
        return getClass().getResource(this.imgQuestion);
    }

    public URL getImgImportar() {
        return getClass().getResource(imgImportar);
    }

    public URL getImgExportar() {
        return getClass().getResource(imgExportar);
    }
// </editor-fold>            
}
