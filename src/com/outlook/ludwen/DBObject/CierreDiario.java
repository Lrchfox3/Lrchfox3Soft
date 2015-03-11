/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.ludwen.DBObject;

import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;
import java.sql.Date;

/**
 *
 * @author Tienda Ludwen
 */
public class CierreDiario extends Base {

    protected Campo secuencia= null;        //1
    protected Campo codigoTienda= null;        //2
    protected Campo fechaCierre= null;        //3
    protected Campo cajaInicio= null;        //4
    protected Campo cantidad500= null;        //5
    protected Campo cantidad100= null;        //6
    protected Campo cantidad50= null;        //7
    protected Campo cantidad20= null;        //8
    protected Campo cantidad10= null;        //9
    protected Campo cantidad5= null;        //10
    protected Campo cantidad2= null;        //11
    protected Campo cantidad1= null;        //12
    protected Campo montoMoneda= null;        //13
    protected Campo montoFacturado= null;        //14
    protected Campo montoPOS1= null;        //15
    protected Campo montoPOS2= null;        //16
    protected Campo montoJustificacion= null;        //17
    protected Campo justificacion= null;        //18
    protected Campo montoDeposito=null;        //19
    protected Campo cajaFinal=null;        //20

    public CierreDiario() {
        secuencia= new Campo("secuencia", "Secuencia", SqlTipos.INTEGER, true);
        codigoTienda= new Campo("codigo_tienda", "Código Tienda", SqlTipos.INTEGER, true);
        fechaCierre= new Campo("fecha_cierre", "Fecha Cierre", SqlTipos.DATE_TIME);
        cajaInicio= new Campo("caja_Inicio", "Caja Inicio", SqlTipos.DECIMAL);
        cantidad500= new Campo("cantidad_500", "Billetes 500", SqlTipos.INTEGER);
        cantidad100= new Campo("cantidad_100", "Billetes 100", SqlTipos.INTEGER);
        cantidad50= new Campo("cantidad_50", "Billetes 50", SqlTipos.INTEGER);
        cantidad20= new Campo("cantidad_20", "Billetes 20", SqlTipos.INTEGER);
        cantidad10= new Campo("cantidad_10", "Billetes 10", SqlTipos.INTEGER);
        cantidad5= new Campo("cantidad_5", "Billetes 5", SqlTipos.INTEGER);
        cantidad2= new Campo("cantidad_2", "Billetes 2", SqlTipos.INTEGER);
        cantidad1= new Campo("cantidad_1", "Billetes 1", SqlTipos.INTEGER);
        montoMoneda= new Campo("monto_Moneda", "Monedas", SqlTipos.DECIMAL);
        montoFacturado= new Campo("monto_Facturado", "Facturado", SqlTipos.DECIMAL);
        montoPOS1= new Campo("monto_POS1", "POS 1", SqlTipos.DECIMAL);
        montoPOS2= new Campo("monto_POS2", "POS 2", SqlTipos.DECIMAL);
        montoJustificacion= new Campo("monto_Justificacion", "Monto Justificación", SqlTipos.DECIMAL);
        justificacion= new Campo("justificacion", "Justificación", SqlTipos.VARCHAR);
        montoDeposito= new Campo("deposito", "Deposito", SqlTipos.DECIMAL);
        cajaFinal= new Campo("caja_Final", "Caja Final", SqlTipos.DECIMAL);
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
        this.secuencia.setValue(secuencia);
    }

    public int getCodigoTienda() {
        return codigoTienda.getIntValue();
    }

    public void setCodigoTienda(int codigoTienda) {
        this.codigoTienda.setValue(codigoTienda);
    }

    public Date getFechaCierre() {
        return fechaCierre.getDateValue();
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre.setValue(fechaCierre);
    }

    public double getCajaInicio() {
        return cajaInicio.getDoubleValue();
    }

    public void setCajaInicio(double cajaInicio) {
        this.cajaInicio.setValue(cajaInicio);
    }

    public int getCantidad500() {
        return cantidad500.getIntValue();
    }

    public void setCantidad500(int cantidad500) {
        this.cantidad500.setValue(cantidad500);
    }

    public int getCantidad100() {
        return cantidad100.getIntValue();
    }

    public void setCantidad100(int cantidad100) {
        this.cantidad100.setValue(cantidad100);
    }

    public int getCantidad50() {
        return cantidad50.getIntValue();
    }

    public void setCantidad50(int cantidad50) {
        this.cantidad50.setValue( cantidad50);
    }

    public int getCantidad20() {
        return cantidad20.getIntValue();
    }

    public void setCantidad20(int cantidad20) {
        this.cantidad20.setValue(cantidad20);
    }

    public int getCantidad10() {
        return cantidad10.getIntValue();
    }

    public void setCantidad10(int cantidad10) {
        this.cantidad10.setValue(cantidad10);
    }

    public int getCantidad5() {
        return cantidad5.getIntValue();
    }

    public void setCantidad5(int cantidad5) {
        this.cantidad5.setValue( cantidad5);
    }

    public int getCantidad2() {
        return cantidad2.getIntValue();
    }

    public void setCantidad2(int cantidad2) {
        this.cantidad2.setValue(cantidad2);
    }

    public int getCantidad1() {
        return cantidad1.getIntValue();
    }

    public void setCantidad1(int cantidad1) {
        this.cantidad1.setValue(cantidad1);
    }

    public double getMontoMoneda() {
        return montoMoneda.getDoubleValue();
    }

    public void setMontoMoneda(double montoMoneda) {
        this.montoMoneda.setValue( montoMoneda);
    }

    public double getMontoFacturado() {
        return montoFacturado.getDoubleValue();
    }

    public void setMontoFacturado(double montoFacturado) {
        this.montoFacturado.setValue(montoFacturado);
    }

    public double getMontoPOS1() {
        return montoPOS1.getDoubleValue();
    }

    public void setMontoPOS1(double montoPOS1) {
        this.montoPOS1.setValue(montoPOS1);
    }

    public double getMontoPOS2() {
        return montoPOS2.getDoubleValue();
    }

    public void setMontoPOS2(double montoPOS2) {
        this.montoPOS2.setValue(montoPOS2);
    }

    public double getMontoJustificacion() {
        return montoJustificacion.getDoubleValue();
    }

    public void setMontoJustificacion(double montoJustificacion) {
        this.montoJustificacion.setValue(montoJustificacion);
    }

    public String getJustificacion() {
        return justificacion.getStringValue();
    }

    public void setJustificacion(String justificacion) {
        this.justificacion.setValue(justificacion);
    }

    public double getMontoDeposito() {
        return montoDeposito.getDoubleValue();
    }

    public void setMontoDeposito(double montoDeposito) {
        this.montoDeposito.setValue(montoDeposito);
    }

    public double getCajaFinal() {
        return cajaFinal.getDoubleValue();
    }

    public void setCajaFinal(double cajaFinal) {
        this.cajaFinal.setValue(cajaFinal);
    }

}
