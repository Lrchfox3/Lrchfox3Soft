/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.ludwen.DBObject;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;

/**
 *
 * @author Tienda Ludwen
 */
public class CierreDiario extends Base {

    protected Campo secuencia = null;        //1
    protected Campo codigoTienda = null;        //2
    protected Campo fechaCierre = null;        //3
    protected Campo cajaInicio = null;        //4
    protected Campo cantidad500 = null;        //5
    protected Campo cantidad100 = null;        //6
    protected Campo cantidad50 = null;        //7
    protected Campo cantidad20 = null;        //8
    protected Campo cantidad10 = null;        //9
    protected Campo cantidad5 = null;        //10
    protected Campo cantidad2 = null;        //11
    protected Campo cantidad1 = null;        //12
    protected Campo montoMoneda = null;        //13
    protected Campo montoFacturado = null;        //14
    protected Campo montoPOS1 = null;        //15
    protected Campo montoPOS2 = null;        //16
    protected Campo montoJustificacion = null;        //17
    protected Campo justificacion = null;        //18
    protected Campo montoDeposito = null;        //19
    protected Campo cajaFinal = null;        //20

    public CierreDiario() {
        secuencia = new Campo("secuencia", "Secuencia", SqlTipos.INTEGER, true);
        codigoTienda = new Campo("codigo_tienda", "Código Tienda", SqlTipos.INTEGER);
        fechaCierre = new Campo("fecha_cierre", "Fecha Cierre", SqlTipos.DATE_TIME);
        cajaInicio = new Campo("caja_Inicio", "Caja Inicio", SqlTipos.DECIMAL);
        cantidad500 = new Campo("cantidad_500", "Billetes 500", SqlTipos.INTEGER);
        cantidad100 = new Campo("cantidad_100", "Billetes 100", SqlTipos.INTEGER);
        cantidad50 = new Campo("cantidad_50", "Billetes 50", SqlTipos.INTEGER);
        cantidad20 = new Campo("cantidad_20", "Billetes 20", SqlTipos.INTEGER);
        cantidad10 = new Campo("cantidad_10", "Billetes 10", SqlTipos.INTEGER);
        cantidad5 = new Campo("cantidad_5", "Billetes 5", SqlTipos.INTEGER);
        cantidad2 = new Campo("cantidad_2", "Billetes 2", SqlTipos.INTEGER);
        cantidad1 = new Campo("cantidad_1", "Billetes 1", SqlTipos.INTEGER);
        montoMoneda = new Campo("monto_Moneda", "Monedas", SqlTipos.INTEGER);
        montoFacturado = new Campo("monto_Facturado", "Facturado", SqlTipos.INTEGER);
        montoPOS1 = new Campo("monto_POS1", "POS 1", SqlTipos.INTEGER);
        montoPOS2 = new Campo("monto_POS2", "POS 2", SqlTipos.INTEGER);
        montoJustificacion = new Campo("monto_Justificacion", "Monto Justificación", SqlTipos.INTEGER);
        justificacion = new Campo("justificacion", "Justificación", SqlTipos.INTEGER);
        montoDeposito = new Campo("cantidad_1", "Billetes 1", SqlTipos.INTEGER);
        cajaFinal = new Campo("monto_Deposito", "Deposito", SqlTipos.INTEGER);
        setTabla("CIERRE_DIARIO");
        setTitulo("Cierre Diario");
    }

    public Campo Secuencia() {
        return secuencia;
    }

    public Campo CodigoTienda() {
        return codigoTienda;
    }

    public Campo FechaCierre() {
        return fechaCierre;
    }

    public Campo CajaInicio() {
        return cajaInicio;
    }

    public Campo Cantidad500() {
        return cantidad500;
    }

    public Campo Cantidad100() {
        return cantidad100;
    }

    public Campo Cantidad50() {
        return cantidad50;
    }

    public Campo Cantidad20() {
        return cantidad20;
    }

    public Campo Cantidad10() {
        return cantidad10;
    }

    public Campo Cantidad5() {
        return cantidad5;
    }

    public Campo Cantidad2() {
        return cantidad2;
    }

    public Campo Cantidad1() {
        return cantidad1;
    }

    public Campo MontoMoneda() {
        return montoMoneda;
    }

    public Campo MontoFacturado() {
        return montoFacturado;
    }

    public Campo MontoPOS1() {
        return montoPOS1;
    }

    public Campo MontoPOS2() {
        return montoPOS2;
    }

    public Campo MontoJustificacion() {
        return montoJustificacion;
    }

    public Campo Justificacion() {
        return justificacion;
    }

    public Campo MontoDeposito() {
        return montoDeposito;
    }

    public Campo CajaFinal() {
        return cajaFinal;
    }

    public int getSecuencia() {
        return secuencia.getIntValue();
    }

    public void setSecuencia(int secuencia) {
        this.secuencia.setValue( secuencia);
    }

    public int getCodigoTienda() {
        return codigoTienda.getIntValue();
    }

    public void setCodigoTienda(int codigoTienda) {
        this.codigoTienda.setValue( codigoTienda);
    }

    public Campo getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Campo fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Campo getCajaInicio() {
        return cajaInicio;
    }

    public void setCajaInicio(Campo cajaInicio) {
        this.cajaInicio = cajaInicio;
    }

    public Campo getCantidad500() {
        return cantidad500;
    }

    public void setCantidad500(Campo cantidad500) {
        this.cantidad500 = cantidad500;
    }

    public Campo getCantidad100() {
        return cantidad100;
    }

    public void setCantidad100(Campo cantidad100) {
        this.cantidad100 = cantidad100;
    }

    public Campo getCantidad50() {
        return cantidad50;
    }

    public void setCantidad50(Campo cantidad50) {
        this.cantidad50 = cantidad50;
    }

    public Campo getCantidad20() {
        return cantidad20;
    }

    public void setCantidad20(Campo cantidad20) {
        this.cantidad20 = cantidad20;
    }

    public Campo getCantidad10() {
        return cantidad10;
    }

    public void setCantidad10(Campo cantidad10) {
        this.cantidad10 = cantidad10;
    }

    public Campo getCantidad5() {
        return cantidad5;
    }

    public void setCantidad5(Campo cantidad5) {
        this.cantidad5 = cantidad5;
    }

    public Campo getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(Campo cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public Campo getCantidad1() {
        return cantidad1;
    }

    public void setCantidad1(Campo cantidad1) {
        this.cantidad1 = cantidad1;
    }

    public Campo getMontoMoneda() {
        return montoMoneda;
    }

    public void setMontoMoneda(Campo montoMoneda) {
        this.montoMoneda = montoMoneda;
    }

    public Campo getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(Campo montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public Campo getMontoPOS1() {
        return montoPOS1;
    }

    public void setMontoPOS1(Campo montoPOS1) {
        this.montoPOS1 = montoPOS1;
    }

    public Campo getMontoPOS2() {
        return montoPOS2;
    }

    public void setMontoPOS2(Campo montoPOS2) {
        this.montoPOS2 = montoPOS2;
    }

    public Campo getMontoJustificacion() {
        return montoJustificacion;
    }

    public void setMontoJustificacion(Campo montoJustificacion) {
        this.montoJustificacion = montoJustificacion;
    }

    public Campo getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(Campo justificacion) {
        this.justificacion = justificacion;
    }

    public Campo getMontoDeposito() {
        return montoDeposito;
    }

    public void setMontoDeposito(Campo montoDeposito) {
        this.montoDeposito = montoDeposito;
    }

    public Campo getCajaFinal() {
        return cajaFinal;
    }

    public void setCajaFinal(Campo cajaFinal) {
        this.cajaFinal = cajaFinal;
    }
    
    
    
   
    
}
