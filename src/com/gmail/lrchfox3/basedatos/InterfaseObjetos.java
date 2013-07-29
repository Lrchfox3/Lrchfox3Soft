/*
 * InterfaseObjetos.java
 *
 * Created on January 7, 2007, 6:05 PM
 *
 * Interfase entre Objetos.
 *
 * Copyright 2007 GO Consultores, Inc. All rights reserved.
 * Use is subject to license terms.
 *
 * @author Luis R. Chinchilla H.
 */
package com.gmail.lrchfox3.basedatos;


import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios;
import com.gmail.lrchfox3.controles.tablas.JTablaBase;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.Fechas;
import com.gmail.lrchfox3.utilitarios.Strings;
import com.gmail.lrchfox3.utilitarios.Objetos;
import com.gmail.lrchfox3.utilitarios.Mensajes;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.util.ArrayList;


/** 
 * <code>InterfaseObjetos</code> se utiliza para definir el flujo de informacion 
 * entre la Base de Datos y Objetos de las ventana del sistema.  Permite actualizar
 * datos de la Base de Datos con informacion de los objetos y ventanas del sistema, 
 * y presenta informacion de la Base de Datos en las ventanas del sistema.
 * 
 * @author Luis R. Chinchilla H.
 */
public class InterfaseObjetos {
    public static final String TABLAVIRTUAL = "TABLAVIRTUAL";
            
    private BD bd;    
    private ArrayList tablas = new ArrayList();       /** Tabla de Base de Datos con la q interactua el objeto. */    
    private String condicionesRelacionTablas;   /** Guarda las condiciones por las cuales las diferentes tablas se relacionan*/
    private ArrayList campos = new ArrayList();       /** Vector que tiene los campos dividos en un arreglos de 3 objetos: (0)Tabla (1)Campo de la tabla (2)tipo de datos. */    
    //private ArrayList tiposDatos;             /** Tipo de datos de c/u de los campos definidos. */    
    
    private ArrayList objetos;                  /** Los objetos de una ventana que se asocian al campo y con los cuales interactuan. */
    private ArrayList valores = new ArrayList();            //valores temporales que se leen de los objetos para editar la BD,
                                                            //o que se extraen de la misma para presentarlos en los objetos de 
                                                            //una ventana.
    private ArrayList valoresActualesBD = new ArrayList();  //Valores actuales que tiene el registro con el que actualmente
                                                             //esta trabajando la BD.    
    
    private int campoAutoIncremento = -1;    //campo definido con incremento automatico al agregar;
    private int[] camposNoEditables;         //campos que no pueden ser actualizados por esta clase y su descendencia.
    private int[] objetosNoEditables;        //objetos de una ventana que no pueden ser editados manualmente.
    private int[] objetosObligatorios;       //estos objetos deben contener valores no nulos.
    private int[] celdasObligatorias;   //en caso de que la interfase sea multiple (tabla), define las celdas obligatorias
    private int[] llave;                     //la llave definida en la tabla.  Esta no puede ser actualizada por su descendencia    
    private InterfaseObjetos objetoPadre;    //el objeto padre al que se desea relacionar este objeto.
    private int[] camposPadre;               //los indices que representan los campos del padre.
    private int[] camposHijo;                //los indices que representan los campos del hijo.        
    
    private javax.swing.JLabel[] etiquetas;  //titulos de Objetos para desplegar informacion como ser campos obligatorios.
    private com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios etiquetaObligatoria; //
       
    private boolean multiple  = false;       //indica cuando esta clase maneja un objeto de multiples campos y registros.
    private Object[][] valoresExternos;      /** 1. posicion del campo cuyo valor se obtiene de otra tabla (diferente a la de este objeto) */
    
    private Propiedades propiedades = new Propiedades();
    
    public InterfaseObjetos() {}
    
    /**
     * Retorna la Base de Datos que se utiliza
     * @return 
     */
    public BD getBD() {
        return bd;
    }

    public void setBD(BD bd) {
        this.bd = bd;
    }
    
    public String getTablas() {
        Strings s = new Strings();
        String t = s.arrayListAString(tablas, ",");        
        return t;
    }
    
    /**
     * Lee la tabla que se ha definido para interactuar con los objetos de una ventana.
     *
     * @param indice
     * @return tabla nombre de la tabla
     */
    public String getTabla(int indice) {
        return tablas.get(indice).toString();
    }
    
    public void setTabla(String tabla)   {
        tablas.add(tabla);
    }
    
    private String getCondicionesRelacionTablas() {
        if (condicionesRelacionTablas == null) condicionesRelacionTablas = "";
        condicionesRelacionTablas = condicionesRelacionTablas.trim();
        return condicionesRelacionTablas;
    }
    
    /**
     * Define mediante sentencia sql las relaciones existentes entre las diferentes 
     * tablas definidas en el objeto.
     * @param condicionesRelacionTablas
     */
    public void setCondicionesRelacionTablas(String condicionesRelacionTablas) {
        this.condicionesRelacionTablas = condicionesRelacionTablas;
    }
            
    /**
     * Obtiene los indices de los campos de un objeto.
     *
     * @return Vector de �ndices de campos de tabla.
     */
    public ArrayList getIndiceCampos() {
        ArrayList c = new ArrayList();        
        int n = campos.size();
        for (int i = 0; i < n; i++)  c.add(i);
        return c;
    }
    
    /**
     * Obtiene los indices de los campos de una tabla.
     *
     * @param tabla Tabla de BD de la cual se van a tomar los �ndices.
     *
     * @return Vector de �ndices de campos de tabla.
     */
    private ArrayList getIndiceCampos(String tabla) {
        ArrayList c = new ArrayList();
        
        int n = campos.size();
        for (int i = 0; i < n; i++) {
            Object[] a = (Object[])campos.get(i);
            if (a[0].equals(tabla)) c.add(i);
        }
        return c;
    }
    
    private ArrayList excluirIndiceAutoIncrement(ArrayList indices) {
        int indice = indices.indexOf(campoAutoIncremento);
        if (indice > - 1) indices.set(indice, null);        
        return indices;
    }
    
    private ArrayList excluirIndicesLlave(ArrayList indices) {        
        if (llave != null) {
            for (int i = 0; i < llave.length; i ++) {            
                int indice = indices.indexOf(llave[i]);
                if (indice > - 1) indices.set(indice, null);
            }
        }
        return indices;
    }
    
    private ArrayList excluirIndicesNoEditables(ArrayList indices) {
        if (camposNoEditables != null) {
            for (int i = 0; i < camposNoEditables.length; i ++) {
                int indice = indices.indexOf(camposNoEditables[i]);
                if (indice > - 1) indices.set(indice, null);
            }
        }                
        return indices;
    }
    
    /**
     * Obtiene los campos de todas las tablas.
     *
     *
     * @return ArrayList de campos.
     */
    public ArrayList getCampos() {
        ArrayList c = new ArrayList();

        int n = campos.size();
        for (int i = 0; i < n; i++) {
            Object[] a = (Object[])campos.get(i);            
            if (a[0].equals(TABLAVIRTUAL)) c.add(null);
            else    c.add(a[0] + "." +a[1]);
        }        
        return c;
    }
    
    /**
     * Obtiene todos los campos de una vector de indices.
     *
     * @param indices Vector que contiene los indices de los cuales se quiere obtener
     * el campo.
     *
     * @return ArrayList de campos.
     */
    public ArrayList getCampos(ArrayList indices) {
        ArrayList c = new ArrayList();
        
        int n = indices.size();
        for (int i = 0; i < n; i++) {
            Object ind = indices.get(i);
            if (ind != null) {
                int indice = Integer.valueOf(ind.toString()).intValue();
                Object[] a = (Object[])campos.get(indice);
                c.add(a[0] + "." + a[1]);
            }
        }        
        return c;
    }
    
    public Object getCampo(int indice) {
        Object[] a = (Object[])campos.get(indice); 
        return a[0] + "." + a[1];
    }
    
    public void setCampo(String tabla, String campo, int tipoDato)    {
        Object[] c = {tabla, campo, tipoDato};
        campos.add(c);
    }
    
    public void setCampo(String campo, int tipoDato) {
        setCampo(tablas.get(0).toString(), campo, tipoDato);
    }        
    
    private int getTipoDato(int indice) {
        Object[] a = (Object[])campos.get(indice);
        return Integer.valueOf(a[2].toString()).intValue();
    }
    
    private int[] getTiposDatos(ArrayList indices) {                
        int s = 0;
        int n = indices.size();
        ArrayList v = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            Object ind = indices.get(i);
            if (ind != null) {
                int indice = Integer.valueOf(ind.toString()).intValue();
                v.add(getTipoDato(indice));
            }
        }   
        
        n = v.size();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = Integer.valueOf(v.get(i).toString()).intValue();
        }
                
        return t;
    }    
    
    /**
     * Lee la llave de una tabla de la BD.
     * 
     * @return Arreglo de enteros con los indices de las llaves.
     */
    private ArrayList getIndiceLlave(String tabla) throws Exception       {
        ArrayList indiceLlave = new ArrayList();
        ArrayList indices = getIndiceCampos(tabla);
        
        for (int i = 0; i < llave.length; i ++) {           
            if (indices.indexOf(llave[i]) > -1)  indiceLlave.add(llave[i]);
        }                
        if (indiceLlave.isEmpty()) throw new Exception("No se Ha definido Llave para tabla " + tabla);
                
        return indiceLlave;
    }
    
    public int[] getLlave()   throws Exception       {
        if (llave == null) throw new Exception("No se Ha definido Llave alguna.");
        return llave;
    }
    
    public void setLlave(int[] llave)     {
        this.llave = llave;
    }
      
    /**
     * Genera un string con la condicion para una sentencia sql en base
     * al la llave
     */
    private String condicionLlave() throws Exception {               
        ArrayList _llave = new ArrayList();
        int n = tablas.size();
        
        for (int i = 0; i < n; i++) {
            ArrayList ll = getIndiceLlave(getTabla(i));
            int s = ll.size();
            for (int j = 0; j < s; j++) _llave.add(ll.get(j));                            
        }
        
        ArrayList _campos = getCampos(_llave);
        ArrayList _valores = getValores(_llave, false);        
        int[] tiposDatos = getTiposDatos(_llave);
        String where = bd.condicionesSQL(_campos, _valores, tiposDatos);        
        return where;
    }
    
    /**
     * Genera una subsentencia where que se utilizar� en una sentencia SQL, tomando 
     * como campos de condicion aquellos que se definieron como parte de la llave. 
     * Los valores utilizados en la sentencia, son aquellos de los objetos 
     * relacionados a cada campo que pertenece a la llave.
     *
     * @param tabla
     * @return String conteniendo la condicion (where).
     * @throws java.lang.Exception
     */
    public String condicionLlave(String tabla) throws Exception {        
        ArrayList keys = getIndiceLlave(tabla);
        ArrayList fields = getCampos(keys);
        ArrayList values = getValores(keys, false);        
        int[] tiposDatos;
        tiposDatos = getTiposDatos(keys);
        String where = bd.condicionesSQL(fields, values, tiposDatos);        
        return where;
    }
    
    /**
     * Solamente se utiliza en objetos multiples.
     * Genera una subsentencia where que se utilizar� en una sentencia SQL, tomando 
     * como campos de condicion aquellos que se definieron como parte de la llave. 
     * Los valores utilizados en la sentencia, son aquellos de un arreglo de valores
     * relacionados a campos de la tabla.
     */
    private String condicionLlave(String tabla, Object valoresFila[]) throws Exception {
        ArrayList keys = getIndiceLlave(tabla);
        ArrayList fields = getCampos(keys);
        ArrayList values = getValores(keys, valoresFila, false);
        int[] tiposDatos = getTiposDatos(keys);
        String where = bd.condicionesSQL(fields, values, tiposDatos);        
        return where;
    }
    
    /**
     * Genera una condicion por llave en base a un resultset
     *
     * @param rs ResultSet que contiene el registro del cual se genera la condici�n.
     */
    private String condicionLlave(String tabla, ResultSet rs) throws Exception {
        ArrayList keys = getIndiceLlave(tabla);
        ArrayList fields = getCampos(keys);
        int[] tiposDatos = getTiposDatos(keys);
        int n = keys.size();
        Object valoresFila[] = new Object[n];
        for (int i = 0; i < n; i ++)  valoresFila[i] = rs.getObject(i + 1);
        ArrayList values = getValores(keys, valoresFila, false);
        
        String where = bd.condicionesSQL(fields, values, tiposDatos);        
        return where;
    }
    
    /**
     * Comparar las llaves de dos registros para determinar si los valores de estas son identicos.
     * Los registros pueden venir de la Base de datos o de Objetos de caputra de datos como una tablas, etc.
     * Generalmente el objetivo es comparar la llave del registro de la Base de Datos, contra la llave del registro
     * que esta visualizando en pantalla el usuario.
     * 
     * @param valoresA  Arreglo de valores de primer registro.
     * @param valoresB  Arreglo de valores de segundo registro.
     *
     * @return True si las llaves son iguales, False si no lo son.
     * @throws java.lang.Exception    */
    public boolean compararLlaves(Object[] valoresA, Object[] valoresB) throws Exception {
        int[] key = getLlave();
        boolean existe = true;
        
        for (int i = 0; i < key.length; i ++) {
            int posCampo = key[i];
            if (!valoresA[posCampo].equals(valoresB[posCampo])) {
                existe = false;
                break;
            }
        }                    
        return existe;    
    }
    
    /**
     * Genera una subsentencia where que se utilizar� en una sentencia SQL, tomando 
     * como campos de condicion aquellos que se definieron como parte de la llave foranea. 
     * Los valores utilizados en la sentencia, son aquellos de los objetos 
     * relacionados a cada campo que pertenece a la llave.
     *
     * @return String conteniendo la condicion (where).
     * @throws java.lang.Exception*/
    public String condicionLlaveForanea() throws Exception {
        String where = "";
        InterfaseObjetos padre = getObjetoPadre(); 
        ArrayList indiceCamposHijos = getIndiceCamposHijos();                        
        ArrayList indiceCamposPadres = getIndiceCamposPadres();
        ArrayList fields = getCampos(indiceCamposHijos);
        int[] tiposDatos = getTiposDatos(indiceCamposHijos);
        
        if (!padre.isMultiple()) {
            ArrayList values = padre.getValores(indiceCamposPadres, false);
            where = bd.condicionesSQL(fields, values, tiposDatos);
        }
        else {
            JTablaBase tabla = (JTablaBase)padre.getObjetos().get(0);
            int fila = tabla.getSelectedRow();
            Object valoresFila[] = tabla.getValoresFila(fila);
            ArrayList values = getValores(indiceCamposPadres, valoresFila, false);
            where = bd.condicionesSQL(fields, values, tiposDatos);            
        }
                
        return where;
    }        
    
    /**
     * Encuentra la posicion de un campo dentro de una llave for�nea.
     *
     * @param indiceCampo el indice del campo del cual se desea identificar si es parte de una llave for�nea.
     *
     * @return entero que indica la posicion del campo en la llave for�nea.
     * Devuelve -1 si no se encontr� el campo.
     */
    public int getPosCampoLlaveForanea(int indiceCampo) {
        for (int i = 0; i < camposHijo.length; i ++) {
            if (indiceCampo == camposHijo[i]) return i;
        }
        return -1;
    }
    
    public ArrayList getIndiceCamposPadres() {        
        ArrayList indices = new ArrayList();
        for (int i = 0; i < camposPadre.length; i++)  indices.add(camposPadre[i]);
        return indices;
    }
        
    public void setCamposPadre(int[] camposPadre) {
        this.camposPadre = camposPadre;
    }

    public ArrayList<Object> getIndiceCamposHijos() {        
        ArrayList<Object> indices = new ArrayList<>();
        for (int i = 0; i < camposHijo.length; i++)  indices.add(camposHijo[i]);
        return indices;
    }

    public void setCamposHijo(int[] camposHijo) {
        this.camposHijo = camposHijo;
    }
    
    /**
     * Limpia el valor de un objeto de una ventana.
     * @param indice  
     * @throws java.lang.Exception  
     */
    public void limpiarObjeto(int indice) throws Exception {
       Object objeto = getObjetos().get(indice);
            
        if (objeto != null) {
            int tipo = new Objetos().tipoObjeto(objeto);

            //textos
            if ((tipo == Objetos.TIPO_TEXTO) || (tipo == Objetos.TIPO_TEXTO_FORMATEADO) || (tipo == Objetos.TIPO_TEXTO_FECHA)) {
                javax.swing.text.JTextComponent texto = ( javax.swing.text.JTextComponent)objeto;
                texto.setText(null); 
            }

            //fechas
            else if (tipo == Objetos.TIPO_FECHA) {
                com.gmail.lrchfox3.utilitarios.calendario.JDateChooser fecha = (com.gmail.lrchfox3.utilitarios.calendario.JDateChooser)objeto;
                fecha.setDate(null);
            }

            //combos
            else if (tipo == Objetos.TIPO_COMBO) {
                javax.swing.JComboBox combo = ((javax.swing.JComboBox)objeto);
                if (combo.getItemCount() > 0) combo.setSelectedIndex(0);
            }

            //tabla
            else if (tipo == Objetos.TIPO_TABLA) {
                JTablaBase tabla = (JTablaBase)objeto;
                tabla.crearModelo(0, tabla.getColumnCount());
            }

            //hora
            else if (tipo == Objetos.TIPO_HORA) {
                com.gmail.lrchfox3.controles.textos.JHora hora = (com.gmail.lrchfox3.controles.textos.JHora)objeto;
                hora.setDefaultLocale(hora.getDefaultLocale());
            }

            //check box
            else if (tipo == Objetos.TIPO_CHECKBOX) {
                com.gmail.lrchfox3.controles.JCheckBoxBase check = (com.gmail.lrchfox3.controles.JCheckBoxBase)objeto;
                check.setSelected(false);
            }
            
            //entero
            else if (tipo == Objetos.TIPO_ENTERO) {
                com.gmail.lrchfox3.tiposbasicos.Entero entero = (com.gmail.lrchfox3.tiposbasicos.Entero)objeto;
                entero.setEntero(null);
            }

            else if (tipo == Objetos.TIPO_STRING) {
                com.gmail.lrchfox3.tiposbasicos.Caracteres caracteres = (com.gmail.lrchfox3.tiposbasicos.Caracteres)objeto;
                caracteres.setCaracteres(null);
            }

            else if (tipo == Objetos.TIPO_OBJETO) {

            }

            else {
                throw new Exception("La Interfase Objeto - BD no reconoce el objeto de la clase " + new Objetos().nombreClaseObjeto(objeto));
            }            
        } 
    }
    /**
     *  Limpia todos los datos que contienen los objetos de una ventana.
     * @throws java.lang.Exception  */
    public void limpiarObjetos() throws Exception {
        if (objetos == null) return;        
        int numeroObjetos = objetos.size();                
        for (int i = 0; i < numeroObjetos; i ++)  limpiarObjeto(i);
    }
    
    /**
     *
     * @return 
     */
    public ArrayList getObjetos()  {
        return objetos;
    }
    
    public void setObjetos(ArrayList objetos) {
        if (this.objetos != null) this.objetos.clear();
        this.objetos = objetos;
        
        if (isMultiple()) {
            JTablaBase tabla = (JTablaBase)getObjetos().get(0);
            tabla.setValoresExternos(valoresExternos);
            tabla.setBD(bd);
            tabla.setActualizacionBD(true);
        }
        
        habilitarObjetos(false);
        inicializarValores();
        inicializarValoresActualesBD();
        
    }

    public void setObjeto(int indice, Object objeto) {
        ArrayList listaObjetos = getObjetos();
        if (listaObjetos != null) listaObjetos.set(indice, objeto);
    }
    
    /**
     * Coloca un valor obtenido de la BD en el objeto relacionado para este campo de la BD.
     * @param indice
     * @throws java.lang.Exception
     */
    public void setObjetoFromValor(int indice)  throws Exception {
        limpiarObjeto(indice);
        
        //int tipoDato = getTipoDato(indice);
        Object objeto = getObjetos().get(indice);
        Object valor = getValores().get(indice);

        //
        if (objeto == null ) ;
        else {
            int tipo = new Objetos().tipoObjeto(objeto);

            //strings
            if (tipo == Objetos.TIPO_TEXTO) {
                javax.swing.text.JTextComponent texto = ( javax.swing.text.JTextComponent)objeto;
                if (valor != null) texto.setText(valor.toString()); 
            }

            //strings formateadas
            else if (tipo == Objetos.TIPO_TEXTO_FORMATEADO) {
                com.gmail.lrchfox3.controles.textos.JTextoFormato texto = (com.gmail.lrchfox3.controles.textos.JTextoFormato)objeto;
                if (valor != null) {
                    texto.setText(valor.toString());
                }                                        
            }

            //fechas con boton de calendario incluido
            else if (tipo == Objetos.TIPO_FECHA) {
                com.gmail.lrchfox3.utilitarios.calendario.JDateChooser fecha = (com.gmail.lrchfox3.utilitarios.calendario.JDateChooser)objeto;
                if (valor != null) fecha.setDate((java.util.Date)valor);
            }

            //listas combo
            else if (tipo == Objetos.TIPO_COMBO) {
                com.gmail.lrchfox3.controles.listas.JComboBoxBase combo = ((com.gmail.lrchfox3.controles.listas.JComboBoxBase)objeto);
                if (valor != null) combo.seleccionarDato(valor);                      
            }

            //tabla
            else if (tipo == Objetos.TIPO_TABLA) {
                JTablaBase tabla = (JTablaBase)objeto;
            }

            else if (tipo == Objetos.TIPO_HORA) {
                com.gmail.lrchfox3.controles.textos.JHora hora = (com.gmail.lrchfox3.controles.textos.JHora)objeto;
                if (valor != null) {                        
                    Fechas fechas = new Fechas();
                    Date fecha = fechas.crearFecha(valor.toString(), Fechas.HORA, "");
                    hora.setValue(fecha);
                }
            }

            //check box
            else if (tipo == Objetos.TIPO_CHECKBOX) {
                com.gmail.lrchfox3.controles.JCheckBoxBase check = (com.gmail.lrchfox3.controles.JCheckBoxBase)objeto;
                if (valor != null) check.seleccionar(valor);
            }
            
            //Entero
            else if (tipo == Objetos.TIPO_ENTERO) {
                com.gmail.lrchfox3.tiposbasicos.Entero entero = (com.gmail.lrchfox3.tiposbasicos.Entero)objeto;
                Integer v = null;
                if (valor != null) v = Integer.valueOf(valor.toString());
                entero.setEntero(v);
            }

            else if (tipo == Objetos.TIPO_STRING) {
                com.gmail.lrchfox3.tiposbasicos.Caracteres caracteres = (com.gmail.lrchfox3.tiposbasicos.Caracteres)objeto;
                String v = null;
                if (valor != null) v = valor.toString();
                caracteres.setCaracteres(v);
            }

            //Objeto
            else if (tipo == Objetos.TIPO_OBJETO) {
                objetos.set(indice, valor);
            }

            else {
                throw new Exception("La Interfase Objeto - BD no reconoce el objeto de la clase " + new Objetos().nombreClaseObjeto(objeto));
            }
        }
    }
    
    /**
     * Coloca los valores obtenidos de la BD en los objetos relacionados
     * @throws java.lang.Exception
     */
    public void setObjetosFromValores()  throws Exception {
        Objetos obj = new Objetos();
        int numeroObjetos = getObjetos().size();
        
        for (int indice = 0; indice < numeroObjetos; indice ++) setObjetoFromValor(indice);
    }
    
    /**
     *
     * @param valores
     * @throws java.lang.Exception
     */
    public void setObjetosYValores(Object[] valores)  throws Exception {
        int numeroValores = getValores().size();
        for (int i = 0; i < numeroValores; i++) getValores().set(i, valores[i]);
        setObjetosFromValores(); 
    }
    
    /**
     * Inicializa los valores temporales que se utilizan para transferir datos
     * entre la Base de Datos y los objetos de las ventanas.
     */
    public void inicializarValores() {
        valores.clear();
        int filas = objetos.size();
        for (int i = 0; i < filas; i ++) valores.add(null);
    }
    
    /**
     *
     * @param indice
     */
    public void limpiarValor(int indice) {
        valores.set(indice, null);
    }
    
    public void limpiarValores() {
        int filas = valores.size();
        for (int indice = 0; indice < filas; indice ++) limpiarValor(indice);
    }
    
    /**
     *
     * @return 
     */
    public ArrayList getValores()   {
        return valores;        
    }      
    
    /**
     * Devuelve los valores que actualmente tienen diferentes objetos relacionados 
     * a campos de una tabla de la BD.
     *
     * @param tabla Tabla de BD a la cual estan relacionados los valores que se
     * desean obtener.
     * @param delimitar
     *
     * @return ArrayList con los valores
     */
    public ArrayList getValores(String tabla, boolean delimitar)   {
        ArrayList v = new ArrayList();
        
        int n = campos.size();
        for (int i = 0; i < n; i++) {
            Object[] a = (Object[])campos.get(i);            
            if (a[0].equals(tabla)) {
                Object valor = valores.get(i);
                if (delimitar) valor = getBD().delimitarValor(valor, Integer.valueOf(a[2].toString()).intValue());
                v.add(valor);
            }
        }
        return v;
    }
    
    private ArrayList getValores(ArrayList indices, boolean delimitar)   {
        ArrayList v = new ArrayList();
        
        int n = indices.size();
        for (int i = 0; i < n; i++) {
            Object ind = indices.get(i);
            if (ind != null) {
                int indice = Integer.valueOf(ind.toString()).intValue();
                Object[] a = (Object[])campos.get(indice);
                Object valor = valores.get(indice);
                if (delimitar) valor = getBD().delimitarValor(valor, Integer.valueOf(a[2].toString()).intValue());
                v.add(valor);
            }
        }        
        return v;
    }
    
    private ArrayList getValores(ArrayList indices, Object[] valores, boolean delimitar)   {
        ArrayList v = new ArrayList();
        
        int n = indices.size();
        for (int i = 0; i < n; i++) {
            Object ind = indices.get(i);
            if (ind != null) {
                int indice = Integer.valueOf(ind.toString()).intValue();
                Object[] a = (Object[])campos.get(indice);
                Object valor = valores[indice];
                if (delimitar) valor = getBD().delimitarValor(valor, Integer.valueOf(a[2].toString()).intValue());
                v.add(valor);
            }
        }
        return v;
    }
    
    /**
     *
     */
    public void inicializarValoresActualesBD() {
        valoresActualesBD.clear();
        int filas = objetos.size();
        for (int i = 0; i < filas; i ++) valoresActualesBD.add(null);
    }
    
    /**
     *
     */
    public void limpiarValoresActualesBD() {
        int filas = valoresActualesBD.size();
        for (int i = 0; i < filas; i ++) valoresActualesBD.set(i, null);
    }
    
    /**
     *
     */
    private ArrayList getValoresActualesBD()   {
        return valoresActualesBD;
    }
    
    private ArrayList getValoresActualesBD(ArrayList indices, boolean delimitar)   {
        ArrayList v = new ArrayList();
        
        int n = indices.size();
        for (int i = 0; i < n; i++) {
            Object ind = indices.get(i);
            if (ind != null) {
                int indice = Integer.valueOf(ind.toString()).intValue();
                Object[] a = (Object[])campos.get(indice);
                Object valor = valoresActualesBD.get(indice);
                if (delimitar) valor = getBD().delimitarValor(valor, Integer.valueOf(a[2].toString()).intValue());
                v.add(valor);
            }
        }        
        return v;
    }
    
    /**
     * Lee el valores de un objeto que se ha asociado a una clase relacionada
     * con una tabla de la base de datos.  Extrae el datos y lo agrega
     * al ArrayList valores.
     * @param indice
     * @throws java.lang.Exception
     */
    public void setValorFromObjeto(int indice) throws Exception   {
        limpiarValor(indice);
        
        int tipoDato = getTipoDato(indice);
        Object objeto = getObjetos().get(indice);
            
        //valores nulos
        if (objeto == null ) valores.set(indice, objeto);
        else {
            int tipo = new Objetos().tipoObjeto(objeto);

            //strings
            if (tipo == Objetos.TIPO_TEXTO) {
                javax.swing.text.JTextComponent texto = ( javax.swing.text.JTextComponent)objeto;
                texto.setText(texto.getText().toString().trim());
                if (texto.equals("")) valores.set(indice, null);
                else                  valores.set(indice, texto.getText()); 
            }

            //strings formateadas
            else if (tipo == Objetos.TIPO_TEXTO_FORMATEADO) {
                com.gmail.lrchfox3.controles.textos.JTextoFormato texto = (com.gmail.lrchfox3.controles.textos.JTextoFormato)objeto;
                int m = texto.getMascara().length();
                if ((m == 0) || (texto.getText().trim().length() == m)) {
                    String valor = texto.getTextoSinFormato().trim();                
                    if (valor.equals("")) valores.set(indice, null);
                    else                  valores.set(indice, valor); 
                }
            }

            //fechas con boton de calendario incluido
            else if (tipo == Objetos.TIPO_FECHA) {
                com.gmail.lrchfox3.utilitarios.calendario.JDateChooser fecha = (com.gmail.lrchfox3.utilitarios.calendario.JDateChooser)objeto;
                valores.set(indice, new Fechas().fechaAString(fecha.getDate(), Fechas.FECHAYYYYMMDDHORA, "-")); 
            }

            //listas combo
            else if (tipo == Objetos.TIPO_COMBO) {
                Item item = (Item)((javax.swing.JComboBox)objeto).getSelectedItem();
                if (item == null) valores.set(indice, null);
                else if (tipoDato == java.sql.Types.INTEGER) {
                    valores.set(indice, Integer.valueOf(item.getItemData().toString()).intValue());
                } 
                else valores.set(indice, item.getItemData().toString());
            }

            //hora
            else if (tipo == Objetos.TIPO_HORA) {
                com.gmail.lrchfox3.controles.textos.JHora hora = (com.gmail.lrchfox3.controles.textos.JHora)objeto;
                Date f = (Date)hora.getValue();                    
                valores.set(indice, new Fechas().fechaAString(f, Fechas.HORA, "-")); 
            }
            
            //check box
            else if (tipo == Objetos.TIPO_CHECKBOX) {
                com.gmail.lrchfox3.controles.JCheckBoxBase check = (com.gmail.lrchfox3.controles.JCheckBoxBase)objeto;
                valores.set(indice, check.getSeleccionado());
            }
            
            //Entero
            else if (tipo == Objetos.TIPO_ENTERO) {
                com.gmail.lrchfox3.tiposbasicos.Entero entero = (com.gmail.lrchfox3.tiposbasicos.Entero)objeto;
                valores.set(indice, entero.getEntero());
            }

            else if (tipo == Objetos.TIPO_STRING) {
                com.gmail.lrchfox3.tiposbasicos.Caracteres caracteres = (com.gmail.lrchfox3.tiposbasicos.Caracteres)objeto;
                valores.set(indice, caracteres.getCaracteres());                    
            }

            //Objeto
            else if (tipo == Objetos.TIPO_OBJETO) {
                valores.set(indice, objeto);
            }                

            else {
                throw new Exception("La Interfase Objeto - BD no reconoce el objeto de la clase " + new Objetos().nombreClaseObjeto(objeto));
            }
        }
    }
    
    /**
     * Lee todos los valores de cada objeto que se ha asociado a una clase relacionada
     * con una tabla de la base de datos.  Extrae los datos y los agrega
     * al ArrayList valores.
     * @throws java.lang.Exception
     */
    public void setValoresFromObjetos()  throws Exception   {
        int numeroObjetos = getObjetos().size();        
        for (int i = 0; i < numeroObjetos; i ++) setValorFromObjeto(i);
    }
    
    /**
     * Lee los valores que tiene la Base de Datos y los asigna a los arrayList
     * de valores y valores actuales BD.
     */
    private void setValoresFromBD()  throws Exception, SQLException   {
        String where = "";
        Statement st = null;
        ResultSet rs = null;                
        
        try {
            setValoresFromObjetos();            
            if (getObjetoPadre() == null) where = condicionLlave();
            else                          where = condicionLlaveForanea();
            where = where + " " + getCondicionesRelacionTablas() + " ";
                    
            limpiarValores();
            limpiarValoresActualesBD();

            String sql = getBD().selectSQL(getTablas(), getCampos(), where);
            st = getBD().crearSentencia();
            rs = getBD().ejecutarSentencia(st, sql);
            
            if (!rs.next()) {
                //mensaje de error q no encontro registro con la llave
            } else {
                int numeroCampos = rs.getMetaData().getColumnCount();
                for (int i = 0; i < numeroCampos; i ++) {
                    Object valor = rs.getObject(i + 1);
                    valores.set(i, valor);
                    valoresActualesBD.set(i, valor);
                }
            }
        } 
        catch (Exception ex)     { throw ex;}         
        finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    }        
    
    /**
     *
     * @return 
     */
    public int[] getObjetosNoEditables() {
        return objetosNoEditables;
    }

    public void setObjetosNoEditables(int[] objetosNoEditables) {
        this.objetosNoEditables = objetosNoEditables;
    }    
    
    public void habilitarObjetos(boolean habilitar) {
        Objetos obj = new Objetos();
        int numeroObjetos;        
        numeroObjetos = getObjetos().size();
        
        for (int i = 0; i < numeroObjetos; i++) {
            Object objeto = getObjetos().get(i);            
            if (objeto != null) {
                int tipoObjeto = obj.tipoObjeto(objeto);
                if (tipoObjeto == Objetos.TIPO_ENTERO
                 || tipoObjeto == Objetos.TIPO_STRING
                 || tipoObjeto == Objetos.TIPO_OBJETO) ;
                else {
                    javax.swing.JComponent c = (javax.swing.JComponent)objeto;
                    //if (c != null) {
                    if (isObjetoNoEditable(i))  c.setEnabled(false);
                    else                        c.setEnabled(habilitar);
                }    
            }
        } 
    }

    /**
     * Indica si el valor de un objeto no puede modificare en la ventana, a trav�s
     * de esta clase o su descendencia.  No importa el valor que
     *
     *
     * @param indiceCampo
     * @return True si no es editable o false si es editable.
     */
    public boolean isObjetoNoEditable(int indiceCampo) {
        if (objetosNoEditables != null) {
            for (int i = 0; i < objetosNoEditables.length; i ++) {
                if (indiceCampo == objetosNoEditables[i]) return true;
            }
        }
        return false;
    }
    
    public void setCampoAutoIncremento(int campo) {
        campoAutoIncremento = campo;
    }
    
    public int[] getCamposNoEditables()     {
        return camposNoEditables;
    }
    
    public void setCamposNoEditables(int[] camposNoEditables)           {
        this.camposNoEditables = camposNoEditables;
    }
    
    /**
     * Indica si una campo no puede actualizarse en la base de datos, a trav�s
     * de esta clase o su descendencia.  No importa el valor que se desea actualizar.
     *
     * @param indiceCampo Indice del campo para el cual se desea saber si es editable.
     *
     * @return True si no es editable o false si es editable.
     */
    public boolean isCampoNoEditable(int indiceCampo) {
        if (camposNoEditables != null) {
            for (int i = 0; i < camposNoEditables.length; i ++) {
                if (indiceCampo == camposNoEditables[i]) return true;
            }
        }
        return false;
    }

    private boolean isObjetoObligatorio(int indice) {
        for (int i = 0; i < objetosObligatorios.length; i ++) {
            if (indice == objetosObligatorios[i]) return true;
        }
        return false;
    }
    
    /**
     * Devuelve un arreglo con los objetos que son obligatorios (deben tener algun valor)s
     *
     * @return arreglo de objetos obligatorios.
     */
    private int[] getObjetosObligatorios() {
        return objetosObligatorios;
    }

    public void setObjetosObligatorios(int[] objetosObligatorios) {
        this.objetosObligatorios = objetosObligatorios;
    }       
    
    private boolean isCeldaObligatoria(int indice) {
        for (int i = 0; i < celdasObligatorias.length; i ++) {
            if (indice == celdasObligatorias[i]) return true;
        }
        return false;
    }
    
    private int[] getCeldasObligatorias() {
        return celdasObligatorias;
    }
    
    public void setCeldasObligatorias(int[] celdasObligatorias) {
        this.celdasObligatorias = celdasObligatorias;
    }                
    
    /**
     *
     */
    private InterfaseObjetos getObjetoPadre() {
        return objetoPadre;
    }

    public void setObjetoPadre(InterfaseObjetos objetoPadre) {
        this.objetoPadre = objetoPadre;
    }    
    
    /**
     *
     * @return 
     */
    public javax.swing.JLabel[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(javax.swing.JLabel[] etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    public com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios getEtiquetaObligatoria() {
        return etiquetaObligatoria;
    }

    public void setEtiquetaObligatoria(com.gmail.lrchfox3.controles.textos.JEtiquetaObligatorios etiquetaObligatoria) {
        this.etiquetaObligatoria = etiquetaObligatoria;
    }
    
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;        
    }
    
    private boolean isMultiple() {
        return multiple;        
    }
    
    public void setValoresExternos(Object[][] valoresExternos) {
        this.valoresExternos = valoresExternos;
    }
    
    /**
     * Leer un valor de la base de datos y lo asigna al objeto de una ventana
     * al cual pertenece el indice.
     * @param indice
     * @return 
     * @throws java.lang.Exception
     * @throws java.sql.SQLException
     */
    public boolean leer(int indice) throws  SQLException , Exception       {
        setValoresFromBD();
        setObjetoFromValor(indice);
        return true;
    }
    /**
     * Lee los valores de la base de datos y los agrega a los objetos de una ventana.
     * @return 
     * @throws java.lang.Exception
     * @throws java.sql.SQLException
     */
    public boolean leer() throws  SQLException, Exception        {
        if (!isMultiple()) {
            setValoresFromBD();
            setObjetosFromValores();
        }
        else {
            leerTabla();            
        }
            
        return true;
    }

        /**
     * Lee los valores de la base de datos y los agrega a los objetos de una ventana.
     * @param Where
     * @return 
     * @throws java.lang.Exception
     * @throws java.sql.SQLException
     */
    public boolean leer(String Where) throws  SQLException, Exception        {
        if (!isMultiple()) {
            setValoresFromBD();
            setObjetosFromValores();
        }
        else {
            leerTabla(Where);
        }

        return true;
    }
    /**
     * Lee los valores de la base de datos y los agrega a una tabla (JTable) de una ventana.
     */
    private boolean leerTabla() throws Exception, SQLException        {
        String where = "";
        JTablaBase tabla = (JTablaBase)getObjetos().get(0);
        
        if (getObjetoPadre() == null) ; //where = condicionLlave();
        else                          where = condicionLlaveForanea();
        where = where + " " + getCondicionesRelacionTablas() + " ";
        
        String consulta = bd.selectSQL(getTablas(), getCampos(), where);       
                
        tabla.crearModelo(consulta);        
        return true;
    }        

        /**
     * Lee los valores de la base de datos y los agrega a una tabla (JTable) de una ventana.
     */
    private boolean leerTabla(String where) throws Exception, SQLException        {
        JTablaBase tabla = (JTablaBase)getObjetos().get(0);
        if (getObjetoPadre() == null) ; //where = condicionLlave();
        else                          where = condicionLlaveForanea();
        where = where + " " + getCondicionesRelacionTablas() + " ";

        String consulta = bd.selectSQL(getTablas(), getCampos(), where);

        tabla.crearModelo(consulta);
        return true;
    }
    /**
     * Llama a una ventana de b�squeda de informaci�n.  Esta ventan retorna los valores
     * de un registro seleccionado.
     *
     * @param consulta Sentencia SQL que extraer los datos que se colocaran en la consulta.
     * @param titulos Arreeglo de los titulos para cada campo que se presentar� en la ventana de b�squeda.
     * @param campos Arreglo de campos que se presentar�n en la ventana de b�squeda.
     * @param tiposDatos Arreglo de tipos de datos para cada campo que se presentar� en la ventana de b�squeda.
     * @param tamanyos
     * @parama tamanyos Arreglo de Tama�os de cada campo que se presentar� en la ventana de b�squeda.
     * @param posCamposRetorno Arreglo de enteros que indican la posicion de los campos que se retornar�n.
     *
     * @return Parametros de retorno del registro seleccionado.
     */
    public Object[] buscar(String consulta, String titulos[], String campos[], Class tiposDatos[], int[] tamanyos, int[] posCamposRetorno) {
        com.gmail.lrchfox3.controles.tablas.ParametrosBusqueda params = new com.gmail.lrchfox3.controles.tablas.ParametrosBusqueda();

        params.setNumeroCampos(campos.length);
        params.setConsulta(consulta);       
        params.setTitulos(titulos);
        params.setCampos(campos);
        params.setTiposDatos(tiposDatos);
        params.setTamanyos(tamanyos);
       
        params.setPosCamposRetorno(posCamposRetorno);
        
        com.gmail.lrchfox3.formularios.FrmBuscar buscar = new com.gmail.lrchfox3.formularios.FrmBuscar(bd, new javax.swing.JFrame(), true, params);
        buscar.setVisible(true);

        return buscar.params.getValoresRetorno();
    }        

    /**
     * Ejecuta el proceso de agregar, editar o eliminar informacion el la 
     * Base de Datos.
     *
     * @return True si la accion de Base de Datos se ejecut� corrrectamente.
     * False si no pudo realizarse la acci�n
     * @throws java.sql.SQLException
     */
    public boolean aceptar() throws SQLException, Exception {
        boolean resultado = false;
        boolean habilitar = false;
        int accion = getBD().getAccion();        

        try {
            if (!isMultiple()) {
                if ( accion == BD.ACCION_AGREGAR)   {                                   
                    resultado = agregar();
                    habilitar = true;
                } 
                else {                
                    if ( accion == BD.ACCION_EDITAR) resultado = editar();
                    else if (accion == BD.ACCION_ELIMINAR) resultado = eliminar();
                }

                if (resultado)  {
                    habilitarObjetos(habilitar);
                }
                else  getBD().rollback();
            } 
            //
            else  resultado = actualizarTabla();
        }
        catch (Exception ex) {
            resultado = false;
            Object msj = ex.getMessage();
            getBD().rollback();
            new Mensajes().mensajesErrores(null, msj, "");            
        }
        
        return resultado;
    }
    
    /**
     * Cancela la accion de agregar, editar o elminar que se iba a ejecutar.
     * @return 
     * @throws java.sql.SQLException
     */
    public boolean cancelar() throws SQLException, Exception {               
        limpiar();
        habilitarObjetos(false);        
        
        return true;
    }

    /**
     * Limpia los objetos y valores (actuales y nuevos).  Asigna nulo a todos
     * los elementos.
     * @throws java.sql.SQLException
     */
    public void limpiar() throws SQLException, Exception {
        limpiarObjetos();
        limpiarValores();
        limpiarValoresActualesBD();
        JEtiquetaObligatorios jeo = getEtiquetaObligatoria();
        if (jeo != null) jeo.setVisible(false);
        
        int oo[] = null;
        if (isMultiple()) oo = celdasObligatorias;
        else              oo = objetosObligatorios;
        if (oo == null) return;
        
        for (int i = 0; i < oo.length; i++) {
            int obligatorio = oo[i];
            javax.swing.JLabel[] je = getEtiquetas();
            if (je != null) {
                if (je[obligatorio] != null) je[obligatorio].setIcon(null);
            }
        }
        
    }
    
    /**
     * Valida que los campos definidos como obligatorios, contengan informaci�n.
     * Adicionalmente valida que los campos fecha tenga fechas validas.
     *
     * @return True si las validaciones se cumplen.  False si una o varias no se cumplen.
     * @throws java.lang.Exception
     */
    public boolean validarCampos() throws Exception{
        int campoFocus = 0;
        Objetos obj = new Objetos();
        String obligatorios[] = {""};
        
        if (objetosObligatorios != null) {
            for (int i = 0; i < objetosObligatorios.length; i++) {
                int obligatorio = objetosObligatorios[i];
                int tipo = obj.tipoObjeto(getObjetos().get(obligatorio));

                if ((tipo == Objetos.TIPO_TEXTO) || (tipo == Objetos.TIPO_TEXTO_FORMATEADO)) {
                    Object val = getValores().get(obligatorio);
                    if (val == null || val.toString().trim().length() <= 0) {                           
                        getEtiquetas()[obligatorio].setIcon(new javax.swing.ImageIcon(getClass().getResource(propiedades.getImgObligatorio())));
                        obligatorios[0] = obligatorios[0] + "\n" + getEtiquetas()[obligatorio].getText();
                        if (campoFocus == 0) campoFocus = obligatorio;
                    }
                }
                else if (tipo == Objetos.TIPO_FECHA) {
                    if (getValores().get(obligatorio) == null) {
                        getEtiquetas()[obligatorio].setIcon(new javax.swing.ImageIcon(getClass().getResource(propiedades.getImgObligatorio())));
                        obligatorios[0] = obligatorios[0] + "\n" + getEtiquetas()[obligatorio].getText();
                        if (campoFocus == 0) campoFocus = obligatorio;
                    }
                }                
            }
        }
        
        if (obligatorios[0].length() > 0) {
            getEtiquetaObligatoria().setVisible(true);            
            ((java.awt.Component)getObjetos().get(campoFocus)).requestFocus();            
            
            Mensajes msg = new Mensajes();
            msg.mensajes( null, bd, 111, "", obligatorios);
                        
            return false;
        }        
        if (getEtiquetaObligatoria() != null) getEtiquetaObligatoria().setVisible(false);
        
        return true;
    }
    
    /**
     *
     */
    private boolean agregar() throws Exception, SQLException        {
        boolean resultadoTrans = false;
        PreparedStatement pst = null;
        
        try {
            setValoresFromObjetos();
            if (validarCampos())
            {                                        
                int n = tablas.size();
                for (int i = 0; i < n; i++) {
                    resultadoTrans = false;
                    String tabla = getTabla(i);
                    ArrayList indices = getIndiceCampos(tabla);
                    indices = excluirIndiceAutoIncrement(indices);
                    String sql = getBD().insertSQL(tabla, getCampos(indices), getValores(indices, true)); 
                    pst = getBD().prepararSentencia(sql);
                    resultadoTrans = getBD().ejecutarSentencia(pst);
                    if (!resultadoTrans) break;
                    pst.close();
                }
            }
        } 
        catch (SQLException se) { throw se;} 
        catch (Exception e)     { throw e;} 
        finally {
            if (pst != null) pst.close();            
        }
        
        return resultadoTrans;
    }
    
    /**
     *
     */
    private boolean editar(String tabla, String where, ArrayList campos, int[] tiposDatos, ArrayList valoresActuales, ArrayList valoresNuevos) throws SQLException, Exception         {
        boolean resultadoTrans = false;
        PreparedStatement pst = null;                        
        ArrayList camposActualizar = new ArrayList();
        ArrayList valoresActualizar = new ArrayList();        
        
        try {
            int n = campos.size();
            for (int i = 0; i < n; i++) {
                Object vn = valoresNuevos.get(i);
                Object va = valoresActuales.get(i);
                if (vn  == null && va == null) ;
                else if ((vn == null && va != null) || (vn != null && va == null)   //valores actual y nuevo son diferentes
                      || (!vn.equals(va))) {
                    boolean actualizar = true;

                    if (tiposDatos[i] == java.sql.Types.DATE) {  //cambios en fechas
                        String fechaBD = new Fechas().fechaAString((Date)va, Fechas.FECHAYYYYMMDDHORA, "-"); 
                        if (fechaBD.equals(vn)) actualizar = false;
                    }                
                    if (actualizar) {
                        camposActualizar.add(campos.get(i));
                        Object o = valoresNuevos.get(i);
                        if (o == null) valoresActualizar.add(o);
                        else valoresActualizar.add(getBD().delimitarValor(o.toString(), tiposDatos[i]));
                    }                
                }
            }  
            
            if (valoresActualizar.isEmpty()) resultadoTrans = true;            
            else {                                
                String sql = getBD().updateSQL(tabla, camposActualizar, valoresActualizar, where);
                pst = getBD().prepararSentencia(sql);
                resultadoTrans = getBD().ejecutarSentencia(pst);
            }
        } 
        catch (SQLException se) { throw se;} 
        catch (Exception e)     { throw e;} 
        finally {
            if (pst != null) pst.close();
        }
        return resultadoTrans;
    }
        
    /**
     *
     */
    private boolean editar() throws SQLException, Exception         {
        boolean resultadoTrans = false;        
        setValoresFromObjetos();
        if (validarCampos()) {
            int n = tablas.size();
            for (int i = 0; i < n; i++) {
                String tabla = getTabla(i);                                                
                
                ArrayList indices = getIndiceCampos(tabla);
                indices = excluirIndicesLlave(indices);
                indices = excluirIndicesNoEditables(indices);
                indices = excluirIndiceAutoIncrement(indices);
                
                int[] tiposDatos = getTiposDatos(indices);
                ArrayList fields = getCampos(indices);
                ArrayList valoresNuevos = getValores(indices, false);
                ArrayList valoresActuales = getValoresActualesBD(indices, false);
                String where = condicionLlave(tabla);
                resultadoTrans = editar(tabla, where, fields, tiposDatos, valoresActuales, valoresNuevos);
                if (!resultadoTrans) break;
            }
        }        
        return resultadoTrans;
    }        
    
    /**
     * Elimina un registro de una tabla de la base de datos relacionada a una
     * clase que se esta utilizando.  La eliminacion se realiza en base a la
     * llave de dicha clase.
     *
     * @return true si se elimino el registro exitosamente o false si fallo la
     * sentencia de eliminacion.
     */
    private boolean eliminar() throws SQLException, Exception         {
        boolean resultadoTrans = false;
        PreparedStatement pst = null;
        
        try {
            setValoresFromObjetos();
                        
            int n = tablas.size();
            for (int i = 0; i < n; i++) {
                resultadoTrans = false;
                String tabla = getTabla(i);
                String where = condicionLlave(tabla);
                String sql = getBD().deleteSQL(tabla, where);
                pst = getBD().prepararSentencia(sql);
                resultadoTrans = getBD().ejecutarSentencia(pst);
                if (!resultadoTrans) break;
                pst.close();
            }
        } 
        catch (SQLException se) { throw se;} 
        catch (Exception e)     { throw e;} 
        finally {
            if (pst != null) pst.close();
        }        
        return resultadoTrans;
    }    
    
    /**
     * Agrega los valores de la relacion del objeto Padre a una nueva fila
     * de la tabla.
     *
     * @param tabla Tabla a la cual se agregan los valores.
     * @param fila numero de fila de la tabla.
     */
    public void setValoresRelacionPadre(JTablaBase tabla, int fila) {
        InterfaseObjetos padre = getObjetoPadre();                
        if (padre == null) return;
        
        ArrayList indicesCamposPadre = getIndiceCamposPadres();
        ArrayList values;
        values = padre.getValores(indicesCamposPadre, false);
        
        for (int i = 0; i < camposHijo.length; i ++) {            
            tabla.setValorCelda(fila, camposHijo[i], values.get(i));
        }
    }
    
    /**
     *
     */
    private boolean validarCamposTabla(JTablaBase tabla, int fila, Object[] valoresFila) throws Exception {
        //int campoFocus = 0;
        //Objetos obj = new Objetos();
        String obligatorios[] = {""};
        
        //validar obligatorios
        if (celdasObligatorias != null){
            for (int i = 0; i < celdasObligatorias.length; i++) {
                if (i != campoAutoIncremento) {
                    int obligatorio = celdasObligatorias[i];

                    if ((valoresFila[obligatorio] == null) || (valoresFila[obligatorio].toString().trim().length() <= 0)) {                           
                        obligatorios[0] = obligatorios[0] + "\n" + tabla.getTitulos()[obligatorio];
                    }
                }                    
            } 
        }
        
        if (obligatorios[0].length() > 0) {
            getEtiquetaObligatoria().setVisible(true);            
            tabla.setRowSelectionInterval(fila, fila);
            
            Mensajes msg = new Mensajes();
            msg.mensajes( null, bd, 111, "", obligatorios);
                        
            return false;
        }        
        if (getEtiquetaObligatoria() != null) getEtiquetaObligatoria().setVisible(false);
        
        return true;
    }
    
    /**
     *
     */
    private boolean agregarTabla(JTablaBase tablaV, int fila) throws Exception, SQLException        {
        PreparedStatement pst = null;
        setValoresRelacionPadre(tablaV, fila);        
        Object[] valoresFila = tablaV.getValoresFila(fila);
        
        boolean resultadoTrans = validarCamposTabla(tablaV, fila, valoresFila); //validar campos obligatorios
        if (!resultadoTrans) return resultadoTrans;
                                 
        try {    
                int n = tablas.size();
                for (int i = 0; i < n; i++) {
                    resultadoTrans = false;
                    String tabla = getTabla(i);
                    ArrayList indices = getIndiceCampos(tabla);
                    indices = excluirIndiceAutoIncrement(indices);
                                                 
                    String sql = getBD().insertSQL(tabla, getCampos(indices), getValores(indices, valoresFila, true)); 
                    pst = getBD().prepararSentencia(sql);
                    resultadoTrans = getBD().ejecutarSentencia(pst);
                    if (!resultadoTrans) break;
                    pst.close();                                                            
                }
        } 
        catch (SQLException se) { throw se;} 
        catch (Exception e)     { throw e;} 
        finally {
            if (pst != null) pst.close();
        }
        return resultadoTrans;
    }
    
    /**
     *
     */
    private boolean editarTabla(JTablaBase tablaV, int fila) throws SQLException, Exception   {
        Object[] valoresFila = tablaV.getValoresFila(fila);
        boolean resultadoTrans = validarCamposTabla(tablaV, fila, valoresFila); //validar campos obligatorios
        if (!resultadoTrans) return resultadoTrans;

        int n = tablas.size();
        for (int i = 0; i < n; i++) {
            String tabla = getTabla(i);                                                
                
            ArrayList indices = getIndiceCampos(tabla);
            indices = excluirIndicesLlave(indices);
            indices = excluirIndicesNoEditables(indices);
            indices = excluirIndiceAutoIncrement(indices);

            int[] tiposDatos = getTiposDatos(indices);
            ArrayList fields = getCampos(indices);            
            
            String where = condicionLlave(tabla, valoresFila);
            String consulta = bd.selectSQL(tabla, fields, where);
            Statement st = bd.crearSentencia();                   
            ResultSet rs = bd.ejecutarSentencia(st,  consulta);
            
            ArrayList valoresActuales = new ArrayList();
            if (rs.next()) valoresActuales = bd.registroAListaArreglos(rs);   
            ArrayList valoresNuevos = getValores(indices, valoresFila, false);
            where = condicionLlave(tabla, valoresFila);
            
            resultadoTrans = editar(tabla, where, fields, tiposDatos, valoresActuales, valoresNuevos);
        }
        return resultadoTrans;
    }
            
    /**
     *
     */
    private boolean eliminarTabla(JTablaBase tablaV) throws SQLException, Exception {
        String where = "";
        Statement st = null;
        ResultSet rs = null;
        boolean resultadoTrans = true;
        int filas = tablaV.getRowCount();
        
        try {            
            int n = tablas.size();
            for (int i = 0; i < n; i++) {                             
                String tabla = getTabla(i);                                                
                ArrayList indices = getIndiceCampos(tabla);
                ArrayList fields = getCampos(indices);            
                
                if (getObjetoPadre() != null) where = condicionLlaveForanea();
                String sql = bd.selectSQL(tabla, fields, where);
                st = bd.crearSentencia();
                rs = bd.ejecutarSentencia(st, sql);

                while (rs.next()) {                
                    resultadoTrans = true;
                    boolean existe = false;
                    Object[] valoresBD = bd.registroAArregloObjetos(rs);                
                    for (int fila = 0; fila < filas; fila++) {          
                        Object[] valoresFila = tablaV.getValoresFila(fila);
                        existe = compararLlaves(valoresBD, valoresFila);
                        if (existe) break;                    
                    }
                    if (!existe) {
                        PreparedStatement pstE = null;
                        try {
                            String whereE = condicionLlave(tabla, rs);
                            String sqlE = bd.deleteSQL(tabla, whereE);                    
                            pstE = bd.prepararSentencia(sqlE);
                            resultadoTrans = bd.ejecutarSentencia(pstE);
                            if (!resultadoTrans) break;
                        }
                        catch (SQLException ex) {throw ex; }
                        finally { if(pstE != null) pstE.close(); }
                    }
                }
                if (!resultadoTrans) break;
            }                            
        }
        catch (SQLException se) { throw se;} 
        catch (Exception e)     { throw e;} 
        finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }                
        return resultadoTrans;
    }
    
    /**
     *
     */
    private boolean actualizarTabla() throws SQLException, Exception {
        boolean resultadoTrans = true;
        JTablaBase tabla = (JTablaBase)getObjetos().get(0);
        int filas = tabla.getRowCount();
        
        //eliminar filas
        resultadoTrans = eliminarTabla(tabla);            
        
        //agregar y actualizar
        if (resultadoTrans) {        
            for (int fila = 0; fila < filas; fila++) {                       

                int accion = tabla.getAccionBDFila(fila);            
                if (accion == BD.ACCION_AGREGAR)     { /** Agregar Fila */
                    resultadoTrans = agregarTabla(tabla, fila);
                    if (!resultadoTrans) break;
                }
                else if (accion == BD.ACCION_EDITAR) { /** Editar Fila */
                    resultadoTrans = editarTabla(tabla, fila);
                    if (!resultadoTrans) break;
                }
            }            
        }
        else bd.rollback();    

        return resultadoTrans;
    }
}
