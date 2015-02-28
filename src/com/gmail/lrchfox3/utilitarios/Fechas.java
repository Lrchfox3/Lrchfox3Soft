/*
 * Fechas.java
 *
 * Created on February 18, 2007, 3:14 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.gmail.lrchfox3.utilitarios;

import java.util.Date;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.gmail.lrchfox3.basedatos.BD;

/**
 *
 * @author Luis R. Chinchilla H.
 */
public class Fechas {
    
    //formatos de fecha / Hora
    public final static int FECHADDMMYYYY = 1;        //"ddMMyyyy";
    public final static int FECHAMMDDYYYY = 2;        //"MMddyyyy";
    public final static int FECHAYYYYMMDD = 3;        //"yyyyMMdd";
    public final static int FECHAYYYYMMDDHORA = 4;    //"yyyyMMdd hh:mm";
    public final static int HORA = 5; //hh:mm:ss.S
    
    /** Creates a new instance of Fechas */
    public Fechas() {
    }
    
    public Date fechaActual(BD bd) throws SQLException, Exception {
        String sql = "";

        int dbms = bd.getDBMS();                
        
        if (dbms == bd.DBMSMSSQLSERVER)  sql = "select getdate()";
        
        Statement st = bd.crearSentencia();
        ResultSet rs = bd.ejecutarSentencia(st, sql);
        if (rs.next()) return rs.getDate(1);
        else return null;
    }
    
    /**
     * Convierte un objeto fecha  a un string con uno de los siguientes 3 formatos:
     * <p>1. dd/mm/yyyy
     * <br>2. mm/dd/yyyy
     * <br>3. yyyy/mm/dd
     * <br>4. yyyy/mm/dd hh:mm:ss.SSS
     * <br>5. hh:mm:ss.SSS
     * 
     * @param fecha Objeto date con la fecha a convertir
     * @param formato string con el formato al cual se desea convertir la fecha
     * 
     * @return String conteniendo la fecha con uno de los formatos deseados.
     *
     * @author Luis Chinchilla 31/12/2006
     */
    public String fechaAString(Date fecha, int formato, String separador) {        
        String f = null;
        
        if (fecha != null) {            
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat();
            switch (formato) {
                case FECHADDMMYYYY:
                    f= "dd" + separador + "MM" + separador + "yyyy";
                    break;
                    
                case FECHAMMDDYYYY:
                    f= "MM" + separador + "dd" + separador + "yyyy";
                    break;
                    
                case FECHAYYYYMMDD:
                    f= "yyyy" + separador + "MM" + separador + "dd";
                    break;
                
                case FECHAYYYYMMDDHORA:
                    f= "yyyy" + separador + "MM" + separador + "dd" + " hh:mm:ss.S a";
                    break;
                
                case HORA:
                    f= "hh:mm:ss.S a";
                    break;
            }

            df.applyPattern(f);
            f = df.format(fecha);            
        }

        return f;
    }
    
    /** 
     * Crea un objeto fecha en base a un String.
     */
    public Date crearFecha(String sFecha, int formato, String separador) throws SQLException, Exception {
        int l = separador.length();
        int ini = 0;
        int fin = 2;
        int dia = 0;
        int mes = 0;
        int anyo = 0;        
        int hora = 0;
        int min = 0;
        int seg = 0;
        Calendar c = Calendar.getInstance();
        
        switch (formato) {
                case FECHADDMMYYYY:
                    dia = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + l;
                    fin = ini + 2;
                    mes = Integer.valueOf(sFecha.substring(ini, fin)).intValue() - 1;
                    ini = fin + l;
                    fin = ini + 4;
                    anyo = Integer.valueOf(sFecha.substring(ini, fin)).intValue();                    
                    break;
                    
                case FECHAMMDDYYYY:
                    mes = Integer.valueOf(sFecha.substring(ini, fin)).intValue() - 1;
                    ini = fin + l;
                    fin = ini + 2;
                    dia = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + l;
                    fin = ini + 4;
                    anyo = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    break;
                    
                case FECHAYYYYMMDD:
                    fin = 4;
                    anyo = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + l;
                    fin = ini + 2;
                    mes = Integer.valueOf(sFecha.substring(ini, fin)).intValue() - 1;
                    ini = fin + l;
                    fin = ini + 2;
                    dia = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    break;
                
                case FECHAYYYYMMDDHORA:
                    fin = 4;
                    anyo = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + l;
                    fin = ini + 2;
                    mes = Integer.valueOf(sFecha.substring(ini, fin)).intValue() - 1;
                    ini = fin + l;
                    fin = ini + 2;
                    dia = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + l;
                    fin = ini + 2;
                    hora = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    if (sFecha.toUpperCase().contains("PM")) hora = hora + 12;                    
                    ini = fin + 1;
                    fin = ini + 2;
                    min = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    ini = fin + 1;
                    fin = ini + 2;
                    seg = Integer.valueOf(sFecha.substring(ini, fin)).intValue();
                    break;
                
                case HORA: 
                    hora = Integer.valueOf(sFecha.substring(0, 2)).intValue();
                    min = Integer.valueOf(sFecha.substring(3, 5)).intValue();
                    seg = Integer.valueOf(sFecha.substring(6, 8)).intValue();
                    
                    if (sFecha.toUpperCase().contains("PM")) hora = hora + 12;
                    
                    Fechas fechas = new Fechas();
                    String f = fechas.fechaAString(new Date(), FECHAYYYYMMDD, "-");
                    //String f = fechas.fechaAString(new Fechas().fechaActual(bd), FECHAYYYYMMDD, "-");
                    anyo = Integer.valueOf(f.substring(0, 4)).intValue();
                    mes = Integer.valueOf(f.substring(5, 7)).intValue() - 1;
                    dia = Integer.valueOf(f.substring(8, 10)).intValue();
                    break;
        } 
        c.set(anyo, mes, dia, hora, min, seg);
        return c.getTime();
    }
    
    public Date sumarDiasFecha(Date fecha, int dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_YEAR, dias);
        
        return c.getTime();
    }
    
    
}

/*Calendar c = Calendar.getInstance();
            c.setTime(fecha);        
            
            if (formato.equals(Propiedades.fechaDDMMYYYY))      f = String.format("%1$td" + separador + "%1$tm" + separador + "%1$tY", c);
            else if (formato.equals(Propiedades.fechaMMDDYYYY)) f = String.format("%1$tm" + separador + "%1$td" + separador + "%1$tY", c);
            else if (formato.equals(Propiedades.fechaYYYYMMDD)) f = String.format("%1$tY" + separador + "%1$tm" + separador + "%1$td", c);
            else if (formato.equals(Propiedades.fechaYYYYMMDDHHMM)) f = String.format("%1$tY" + separador + "%1$tm" + separador + "%1$td" + separador + "%1$th:mm" , c);
            */