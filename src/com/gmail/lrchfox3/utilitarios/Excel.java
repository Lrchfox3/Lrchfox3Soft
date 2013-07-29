package com.gmail.lrchfox3.utilitarios;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import jxl.write.*;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * Clase que permite la manipulación de hojas de excel. permite poder importar
 * la información de una hoja de excel a una tabla de java, además, la de poder
 * exportar la información de una tabla de java a una hoja de excel.
 *
 * @author chinchillal
 */
public class Excel {

    public int numD; // Declaración del numero de valores a leer
    public int[] valores; // Vector de valores leídos

    public Excel() {
    }

    /**
     * Permite importar la información de una hoja de excel creando sentencia
     * sql, por ejemplo SQL Insert con la información contenida en la hoja de
     * excel
     *
     * @param archivo indica la dirección fisica de la hoja de excel a importar
     * @param sql genera sentencia sql parametrizada con la información
     * contenidas en la hoja de excel
     * @param tiposDatos se indican los tipos de datos que estan contenidas en
     * cada columna de la hoja de excel
     *
     * @return devuelve un arreglo de string con las sentencias sql que se
     * generaron
     * @exception se generan excepciones de entrada o salida de archivos o algún
     * error generico
     */
    public List<String> importarExcel(String archivo, String sql, int[] tipoDatos) throws IOException, Exception {
        boolean fin = false;
        int numeroFila = 0;
        File f;
        String query = "";
        List<String> dml = new ArrayList<String>();
        f = new File(archivo);
        if (!f.exists() && f.length() < 0) {
            throw new IOException("El archivo especificado no existe");
        } else {
            FileInputStream fis = new FileInputStream(archivo);

            HSSFWorkbook wb = new HSSFWorkbook(fis);

            HSSFSheet hoja = wb.getSheetAt(0);
            HSSFRow fila;
            while (!fin) {
                fila = hoja.getRow(numeroFila);
                if (fila == null) {
                    fin = true;
                } else {
                    String datos[] = datos(fila, fila.getLastCellNum());
                    datos = formateoDatos(datos, tipoDatos);
                    if (!finArchivo(datos)) {
                        query = sql;
                        for (int i = 0; i < datos.length; i++) {
                            query = query.replaceFirst("@param", datos[i]);
                        }
                        dml.add(query + ";");
                    }
                }
                numeroFila++;
            }
        }
        return dml;
    }

    /**
     * Permite importar la información de una hoja de excel a una Jtable.
     *
     * @param table indica el objeto Jtable donde van a ser cargados los datos
     * de la hoja de excel
     * @param archivo indica la dirección fisica de la hoja de excel a importar
     * @param tiposDatos se indican los tipos de datos que estan contenidas en
     * cada columna de la hoja de excel
     *
     * @return devuelve un arreglo de string con las sentencias sql que se
     * generaron
     * @exception se generan excepciones de entrada o salida de archivos o algún
     * error generico
     */
    public DefaultTableModel importarExcel(javax.swing.JTable table, String archivo) throws IOException, Exception {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        boolean fin = false;
        int numeroFila = 0;
        File f;
        f = new File(archivo);
        if (!f.exists() && f.length() < 0) {
            throw new IOException("El archivo especificado no existe");
        } else {
            FileInputStream fis = new FileInputStream(archivo);

            HSSFWorkbook wb = new HSSFWorkbook(fis);

            HSSFSheet hoja = wb.getSheetAt(0);
            HSSFRow fila, cFila;
            int c = 0;
            while (!fin) {
                fila = hoja.getRow(numeroFila);
                cFila = hoja.getRow(0);
                if (fila == null) {
                    fin = true;
                } else {
                    if (numeroFila <= 0) {
                        String colNames[] = datos(cFila, fila.getLastCellNum());
                        for (int i = 0; i < colNames.length; i++) {
                            defaultTableModel.addColumn(colNames[i]);
                        }
                    } else {
                        String datos[] = datos(fila, fila.getLastCellNum());
                        if (!finArchivo(datos)) {
                            defaultTableModel.addRow(datos);
                        }
                    }
                }

                numeroFila++;
            }
        }
        return defaultTableModel;
    }

    public boolean exportarJtable(javax.swing.JTable table, String archivo, String nombreTab) throws IOException, Exception {
        File file = new File(archivo);
        if (!file.exists()) {
            file.createNewFile();
        }
        return export(table, file, nombreTab);
    }

    public String[] datos(HSSFRow fila, int numeroColumnas) {
        int i;
        HSSFCell columna;
        String datos[] = new String[numeroColumnas];

        for (i = 0; i < numeroColumnas; i++) {
            columna = fila.getCell(i);

            if (columna != null) {
                switch (columna.getCellType()) {
                    case HSSFCell.CELL_TYPE_ERROR:
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        datos[i] = columna.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        datos[i] = new Double(columna.getNumericCellValue()).toString();
                        break;
                    default:
                        datos[i] = columna.getStringCellValue();
                        break;
                }
            }
        }

        return datos;
    }

    public String[] formateoDatos(String datos[], int tipoDatos[]) {
        int i = 0;
        int tamanio = datos.length;

        for (i = 0; i < tamanio; i++) {
            if (datos[i] != null) {
                String val[] = datos[i].split("\\.0");
                if (val.length > 1); else {
                    datos[i] = val[0];
                }
                switch (tipoDatos[i]) {
                    case Types.INTEGER:
                        long ll = Double.valueOf(datos[i]).longValue();
                        datos[i] = String.valueOf(ll);
                        break;

                    case Types.DATE:
                        datos[i] = "TO_DATE('" + datos[i] + "', 'dd/MM/yyyy')";
                        break;

                    case Types.VARCHAR:
                        datos[i] = "'" + datos[i] + "'";
                        break;
                }
            }
        }
        return datos;
    }

    private boolean finArchivo(String datos[]) {
        int i = 0;
        int tamanio = datos.length;

        for (i = 0; i < tamanio; i++) {
            if (datos[i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean export(javax.swing.JTable table, File archivo, String nombreTab) throws IOException, Exception {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(archivo));
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet(nombreTab, 0);
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object objeto = table.getValueAt(i, j);
                s.addCell(new Label(j, 0, table.getColumnName(j)));
                s.addCell(new Label(j, i + 1, String.valueOf(objeto)));
            }
        }
        w.write();
        w.close();
        out.close();
        return true;
    }
}
