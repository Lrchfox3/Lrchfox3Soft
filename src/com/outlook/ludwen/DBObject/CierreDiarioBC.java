/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.outlook.ludwen.DBObject;


import java.util.List;

import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class CierreDiarioBC {
    private static CierreDiarioDAC dac = new CierreDiarioDAC();

    public static List<CierreDiario> getListaCierreDiarios() throws SQLException, IllegalArgumentException, IllegalAccessException{
        return dac.getListaCierreDiarios();
    }

    public static CierreDiario getCierreDiario(int codigo) throws SQLException, IllegalArgumentException, IllegalAccessException{
        return dac.getCierreDiario(codigo);
    }

    public static boolean editar(int accion, CierreDiario dto ) throws SQLException, IllegalArgumentException, IllegalAccessException{
        return dac.editar(accion, dto);
    }

    public static List<CierreDiario> buscarPorCriterio(String criterio) throws SQLException, IllegalArgumentException, IllegalAccessException {
        return dac.buscarPorCriterio(criterio);
    }

    public static boolean importarCierreDiariosFromExcel(String archivo) throws Exception{
        return dac.importarCierreDiariosFromExcel(archivo);
    }
}
