/*
 * jTreeBase.java
 *
 * Created on January 29, 2007, 12:28 PM
 */

package com.gmail.lrchfox3.controles.listas;

/**
 *
 * @author  Luis Chinchilla
 */
// <editor-fold defaultstate="collapsed" desc=" Librerias ">  
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.text.*;
import com.gmail.lrchfox3.basedatos.InterfaseObjetos;
import com.gmail.lrchfox3.basedatos.BD;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import com.gmail.lrchfox3.utilitarios.Item;
import com.gmail.lrchfox3.utilitarios.Propiedades;
import javax.swing.text.JTextComponent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.util.Enumeration; 
import java.util.Vector;

// </editor-fold>

public class JTreeBase extends JTree {
    
    /** Creates new form BeanForm */
    public JTreeBase() {
        inicializar();
    }
    
    /**
     * Indica el textComponent del cual obtendra el criterio de busqueda
     * @param textComponent: criterio de busqueda
     *
     * @author Luis Chinchilla 04/02/2007
     */
    public JTreeBase( javax.swing.text.JTextComponent textComponent) {
        inicializar();
        this.textComponent = textComponent;
        setCriterio();        
    }
            
    
    private void inicializar() {       
        try {
            setFont(propiedades.getFontTextos());                       
            popupMenu = new JPopupMenu();
            menuItem1 = new JMenuItem();
            menuItem2 = new JMenuItem();
            menuItem3 = new JMenuItem();
            menuItem4 = new JMenuItem();
            separator = new JSeparator();

            menuItem1.setText("Expandir");
            menuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            popupMenu.add(menuItem1);

            menuItem2.setText("Expandir Todos");
            menuItem2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem2ActionPerformed(evt);
                }
            });
            popupMenu.add(menuItem2);  

            menuItem3.setText("Colapsar");
            menuItem3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem3ActionPerformed(evt);
                }
            });
            popupMenu.add(menuItem3);

            menuItem4.setText("Colapsar Todos");
            menuItem4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem4ActionPerformed(evt);
                }
            });
            popupMenu.add(menuItem4);
            this.setComponentPopupMenu(popupMenu);

            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    trvListaServiciosMouseReleased(evt);
                }        
            });    
        }
        catch (Exception ex) {
            new com.gmail.lrchfox3.utilitarios.Mensajes().mensajesErrores(this, ex.getMessage(), "Lista de Arbol.");
        }
    }    
    
    private void trvListaServiciosMouseReleased(java.awt.event.MouseEvent evt) {
        this.setCellRenderer(null);
    } 
   
    /**
     * Define la BD que se va a utilizar para manipular los datos relacionados
     * al Arbol.
     *
     * @param BD Base de Datos que se utilizar�.
     */
    public void setBD(BD bd) throws Exception {
        this.bd = bd;
    }
    
    public void setComponentPopupMenu( JPopupMenu popup ) {    
        if (popup != null){
            popup.add(separator);
            popup.add(menuItem1);
            popup.add(menuItem2);
            popup.add(menuItem3);
            popup.add(menuItem4);
            super.setComponentPopupMenu(popup);
        }        
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCellRenderer(null);
        expandir();
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCellRenderer(null);
        expandirTodos();
    }
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCellRenderer(null);
        colapsar();
    }
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setCellRenderer(null);
        colapsarTodos();
    }
    /**
     * hace set a la variable de criterio de busqueda     
     *
     * @author Luis Chinchilla 04/02/2007
     */
    private void setCriterio() {
        JTextComponent txt = (JTextComponent)textComponent;        
        if ( txt == null ) {
            criterioBusqueda = "";
        } 
        else {
            criterioBusqueda = txt.getText();
        }
    }
    
    /**
     * Indica el textComponent del cual obtendra el criterio de busqueda
     * @param textComponent: criterio de busqueda
     *
     * @author Luis Chinchilla 04/02/2007
     */
    public  void setCriterio( JTextComponent textComponent ) {
        this.textComponent = textComponent;
    }
    
    /**
     * Obtiene el criterio de busqueda
     *
     * @author Luis Chinchilla 04/02/2007
     */
    public String getCriterioBusqueda() {
        setCriterio();
        return criterioBusqueda;
    }
             
    /**
     * busca las coincidencias entre los nodos del arbol y el criterio de busqueda
     * Nota: marca los nodos que coincidieron con el criterio de busqueda
     *
     * @param buscar: criterio de busqueda.      
     *
     * @author Luis Chinchilla 03/02/2007
     */
    public void buscarNodo( String buscar ){
        criterioBusqueda = buscar;
        if (criterioBusqueda.length()>0) { 
            DefaultMutableTreeNode node = recorrerArbol(criterioBusqueda);              
            if(node != null) { 
                //make the node visible by scroll to it 
                TreeNode[] nodes = modeloArbol.getPathToRoot(node); 
                TreePath path = new TreePath(nodes);  
                scrollPathToVisible(path);  
                setSelectionPath(path); 
            } 
            else { 
                //node with string not found show message 
                JOptionPane.showMessageDialog(this, "Elemento con la etiqueta " + criterioBusqueda + " no se encontro",  "Elemento no encontrado", JOptionPane.INFORMATION_MESSAGE);                          
            }             
        }           
    }
    
    public void buscarNodo() {
        setCriterio();
        if (criterioBusqueda.length()>0) {            
            DefaultMutableTreeNode node = recorrerArbol(criterioBusqueda);              
            if(node != null) { 
                //make the node visible by scroll to it 
                TreeNode[] nodes = modeloArbol.getPathToRoot(node); 
                TreePath path = new TreePath(nodes);                  
                scrollPathToVisible(path);  
                setSelectionPath(path); 
            } 
            else {                 
                //node with string not found show message 
                JOptionPane.showMessageDialog(this, "Elemento con la etiqueta " + criterioBusqueda + " no se encontro",  "Elemento no encontrado", JOptionPane.INFORMATION_MESSAGE);                          
            }        
        }                         
    }                
    
    /**
     * recorre el arbol hasta encontrar coincidencias con el criterio de busqueda 
     * @author Luis Chinchilla 11/02/2007
     */
    private DefaultMutableTreeNode recorrerArbol(String nodeStr)  { 
        DefaultMutableTreeNode node = null; 
         
        //Get the enumeration 
        Enumeration enum1 = nodos.breadthFirstEnumeration();          
        //iterate through the enumeration 
        while(enum1.hasMoreElements())  { 
            //get the node 
            String texto = "", strCodigo = "";
            node = (DefaultMutableTreeNode)enum1.nextElement();             
            if (node.getUserObject() instanceof Item) {
                item = new Item();
                item = (Item)node.getUserObject();              
                strCodigo = item.getItemData().toString();
                texto = item.getItem();                
            }
            else if (node.getUserObject() instanceof Object) {
                texto = (String)node.getUserObject();              
            } 
            //match the string with the user-object of the node 
            if (strCodigo.compareToIgnoreCase(nodeStr) == 0) {
                //tree node with string found 
                return node;
            }
            else if(texto.compareToIgnoreCase(nodeStr) == 0) { 
                //tree node with string found 
                return node;                          
            } 
            else if (texto.toLowerCase().contains(nodeStr.toLowerCase())) {
                //tree node with string found 
                return node;
            }                            
        }          
        //tree node with string node found return null 
        return null; 
    }
        
    public void expandir() { 
        if (this.getSelectionRows() != null) {
            for ( int row=0; row<this.getSelectionRows().length;row++){
                this.expandRow(this.getSelectionRows()[row]);            
            }        
        }
    }
    
    /**
     *  Expande todos los nodos del arbol
     *  @author Luis Chinchilla 03/02/2007
     */
    public void expandirTodos() {
        int row = 0;
        while (row < this.getRowCount()) {
            this.expandRow(row);            
            row++;
        }        
    }        
    
    public void colapsar() {    
        if (this.getSelectionRows() != null) {
            for ( int row = 0;row<this.getSelectionRows().length;row++){
                this.collapseRow(this.getSelectionRows()[row]);            
            }        
        }        
    }
    
    /**
     *  Colapsa todos los nodos del arbol
     *  @author Luis Chinchilla 03/02/2007
     */
    public void colapsarTodos() {
        int row = 1; //deja visible los hijos de la ra�z.
        while (row < this.getRowCount()) {
            this.collapseRow(row);             
            row++;
        }                        
    }
    
    public void refrescar() throws Exception{
        try {
            crearArbolFromTabla(tabla, campos);
        } catch(Exception e){
            throw new Exception( e.getMessage() );
        } 
    }
    
   /**
     *
     * @author Chinchilla 05/12/2006
     */
    public Item obtenerInfoNodo() {
        item = new Item();        
        item = null;
        TreePath seleccion = this.getSelectionPath(); //Consigo la selecci�n        
        if(seleccion!=null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)seleccion.getLastPathComponent(); //consigo el nodo seleccionado            
            item = (Item)nodo.getUserObject(); //consigo el itemdata                                  
        } 
       return item;
    }
    
    
    public void crearArbolFromTabla( InterfaseObjetos tabla, int[] campos, String OrdenerNiveles ) throws Exception {
        this.OrdenarNiveles = (OrdenerNiveles==null ? "" : OrdenerNiveles);
        crearArbolFromTabla( tabla, campos );
    }   
    
    /**
     * crear el arbol desde datos de una tabla.
     * @param tabla: InterfaseObjetos tabla
     * @param campos: campos que seran utilizados para crear el arbol. 
     *                (codigo de la tabla, nombre para la hoja del arbol, codigo padre en la tabla, codigo hijo en la tabla)
     *
     * @author Luis Chinchilla 03/02/2007
     */
    public void crearArbolFromTabla( InterfaseObjetos tabla, int[] campos ) throws Exception {
        this.tabla = tabla;
        this.campos = campos;
        String nombreTabla = tabla.getTabla(0);
        ArrayList indices = tabla.getIndiceCampos();
        
        ArrayList aRaiz = new ArrayList();
        ArrayList aCampos = new ArrayList();
        for (int i=0; i<campos.length; i++ ){
            aCampos.add( tabla.getCampo(campos[i]));
        }   
        aRaiz = obtenerRaiz(nombreTabla, getCodigoRaiz(), aCampos);
        if (aRaiz.size()>0) {       
            item = new Item();
            item.setItemData( aRaiz.get(0).toString() );
            item.setItem( aRaiz.get(1).toString() );
                                              
            nodos = new DefaultMutableTreeNode(item);  
            agregarRaiz ( tabla, campos, nodos, getCodigoRaiz() );             
            modeloArbol = new DefaultTreeModel( nodos );           
            this.setModel(modeloArbol);   
            setSelectionRow(0);
        }                
    }
    
    /**
     * Agrega recursivamente las raices y sus hojas
     * @param tabla: InterfaseObjetos tabla.
     * @param campos: campos que seran utilizados para crear el arbol. 
     *                (codigo de la tabla, nombre para la hoja del arbol, codigo padre en la tabla, codigo hijo en la tabla).
     * @param nodo: representa el nodo ra�z (ya sea padre o hijo).
     * @param codigoNodo: indica el codigo en la tabla de la ra�z (ya sea padre o hijo).
     *
     * @author Luis Chinchilla 03/02/2007
     */    
    private void agregarRaiz( InterfaseObjetos tabla, int[] campos, DefaultMutableTreeNode nodo, Integer codigoNodo ) throws Exception {                
        Statement st1 = null;
        ResultSet rs1 = null;
        String nombreTabla = tabla.getTabla(0);
        try {           
            ArrayList cp = tabla.getCampos();
            String[] c = {cp.get(campos[0]).toString(), cp.get(campos[1]).toString(), cp.get(campos[2]).toString()};
            c[0] = c[0].substring(c[0].lastIndexOf(".") + 1);
            c[1] = c[1].substring(c[1].lastIndexOf(".") + 1);
            c[2] = c[2].substring(c[2].lastIndexOf(".") + 1);
            
            st1 = bd.crearSentencia();
            rs1 = bd.ejecutarSentencia(st1, bd.selectSQL(nombreTabla, "", c[2] + " = " + codigoNodo.intValue()) );                        
                        
            while (rs1.next()) {                              
                String codigo = rs1.getString(c[0]);
                String nombreNodo = rs1.getString(c[1]);
                Object raiz = rs1.getObject(c[2]);
                if ( raiz != null ){
                    item = new Item();
                    item.setItem( nombreNodo );
                    item.setItemData( codigo );
                    DefaultMutableTreeNode nodo1 = new DefaultMutableTreeNode(item);                    
            
                    Statement st2 = null;
                    ResultSet rs2 = null;
                    
                    try {       
                        ArrayList hijos = new ArrayList();            
                        
                        st2 = bd.crearSentencia();
                        rs2 = bd.ejecutarSentencia(st2, bd.selectSQL(nombreTabla, "", cp.get(campos[3]).toString() + " = " + codigoNodo.intValue() + OrdenarNiveles) );
                        
                        while (rs2.next()) {   
                            codigo = rs2.getString(c[0]);
                            nombreNodo = rs2.getString(c[1]);
                            raiz = rs2.getObject(c[2]);
                            item = new Item();
                            item.setItem( nombreNodo );
                            item.setItemData( codigo );                                                                                   
                            if ( raiz != null ) {                                                                                                                                               
                                if ( codigoNodo == getCodigoRaiz() ) {
                                    agregarRaiz( tabla, campos, nodo, (Integer)raiz );                                
                                }   
                                else {
                                    agregarRaiz( tabla, campos, nodo1, (Integer)raiz );                                
                                }
                            }
                            else {
                                hijos.add(item);                
                            }
                        }                                              
                        
                        if ( codigoNodo != getCodigoRaiz() ) {
                            agregarHojas(nodo1, hijos);
                            nodo.add(nodo1);                               
                        }
                        else {                                                        
                            agregarHojas(nodo, hijos);                            
                        }                        
                    } 
                    catch(Exception e) { 
                         throw new Exception( e.getMessage() );
                    }   
                    finally {
                        try {
                            if (rs2 != null) rs2.close();
                            if (st2 != null) st2.close();
                        } 
                        catch(Exception nex) {
                            throw new Exception( nex.getMessage() );
                        }            
                    }   
                }              
            }               
        } 
        catch(Exception e) { 
            throw new Exception( e.getMessage() );
        } 
        finally {
            try {
                if (rs1 != null) rs1.close();
                if (st1 != null) st1.close();
            } 
                catch(Exception nex) {
                    throw new Exception( nex.getMessage() );
            }            
        }
    }    
    
    /**
     * agrega a un nodo una lista de hijos
     * @author Chinchilla 25/10/2006
     */    
    public void agregarHojas( DefaultMutableTreeNode nodoPadre, ArrayList nodosHijos ) {
        if ( nodosHijos.size()>0 ) {
            for(int i=0; i<nodosHijos.size(); i++) {
                nodoPadre.add( new DefaultMutableTreeNode(nodosHijos.get(i)) ); 
            }                    
        }        
        
    } 
    
    /**
     * retorna una lista de campos de un nodo padre
     * @author Chinchilla 05/12/2006     
     */
    public ArrayList obtenerRaiz( String Tabla, int codigo, ArrayList campos ) throws Exception {
        Statement st = null;
        ResultSet rs = null;
        ArrayList aData = new ArrayList();
        
        try {            
            st = bd.crearSentencia();            
            rs = bd.ejecutarSentencia(st, bd.selectSQL(Tabla, campos, tabla.getCampos().get(this.campos[2]).toString() + " = " + codigo) );       
            int n = campos.size();
            while (rs.next()){         
                 for (int i=1; i <= n; i++) { 
                     aData.add( rs.getObject( i ));
                 }
            } 
        } 
        catch(Exception e){
            throw new Exception( e.getMessage() );
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } 
            catch(Exception nex) {
                throw new Exception( nex.getMessage() );
            }            
        }
        return aData; 
    }    
    
    //Codigo Raiz por defecto
    public int getCodigoRaiz() { 
        return codigoRaiz; 
    }        
    
    public boolean tieneHijos(){        
        boolean retValue = false;
        TreePath seleccion = this.getSelectionPath(); //Consigo la selecci�n        
        if(seleccion!=null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)seleccion.getLastPathComponent(); //consigo el nodo seleccionado            
            retValue = !nodo.isLeaf();
        } 
        return retValue;
    }

      /**
     * obtiene el listado de los hijos de un nodo del arbol, 
     * @author Chinchilla 07/12/2006
     * @param codigoBusqueda: valor por el cual se hara la busqueda de las opciones
     * @param lstHijos: listado de codigos de los hijos del nodo padre
     */
    public void listaHijos( Object codigoBusqueda, ArrayList lstHijos ) throws Exception {        
        Statement st1 = null;
        ResultSet rs1 = null;        
        String nombreTabla = tabla.getTabla(0);
        
        try {                        
            ArrayList cp = tabla.getCampos();
            String[] c = {cp.get(campos[0]).toString(), cp.get(campos[1]).toString(), cp.get(campos[2]).toString()};
            c[0] = c[0].substring(c[0].lastIndexOf(".") + 1);
            c[1] = c[1].substring(c[1].lastIndexOf(".") + 1);
            c[2] = c[2].substring(c[2].lastIndexOf(".") + 1);
            
            st1 = bd.crearSentencia();
            rs1 = bd.ejecutarSentencia(st1, bd.selectSQL(nombreTabla, "", c[0] + " = '" + codigoBusqueda + "'") );
            while (rs1.next()) {                              
                String codigo = rs1.getString(c[0]);
                Object codigoNodo = rs1.getObject(c[2]);                
                if ( codigoNodo != null ) {
                    Statement st2 = null;
                    ResultSet rs2 = null;                    
                    try {       
                        ArrayList hijos = new ArrayList();                                    
                        st2 = bd.crearSentencia();
                        rs2 = bd.ejecutarSentencia(st2, bd.selectSQL(nombreTabla, "", tabla.getCampos().get(campos[3]) + " = " + codigoNodo ) );                                    
                        while (rs2.next()) {   
                            codigo = rs2.getString(c[0]);
                            codigoNodo = rs2.getObject(c[2]); 
                            if ( codigoNodo != null ) {  
                                listaHijos(codigo, lstHijos);
                            }  
                            lstHijos.add(codigo);
                        }                                                                                                            
                    } 
                    catch(Exception e) { }   
                    finally {
                        try {
                            if (rs2 != null) rs2.close();
                            if (st2 != null) st2.close();
                        } 
                        catch(Exception nex) {  }            
                    }   
                }              
            }               
        } 
        catch(Exception e) {  } 
        finally {
            try {
                if (rs1 != null) rs1.close();
                if (st1 != null) st1.close();
            } 
            catch(Exception nex) { }            
        }             
    }    
    // <editor-fold defaultstate="collapsed" desc=" Declaraci�n de Variables ">        
    private final int codigoRaiz = 0;
    private DefaultTreeModel modeloArbol;
    private DefaultMutableTreeNode nodos;
    private BD bd = null;
    private Propiedades propiedades = new Propiedades();      
    private InterfaseObjetos tabla = new InterfaseObjetos();
    private Item item = new Item();     
    private String criterioBusqueda = "";
    private JTextComponent textComponent;
    private JSeparator separator;
    private JPopupMenu popupMenu;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private String OrdenarNiveles = "";
    private int[] campos;    
    // </editor-fold>    
}

    // <editor-fold defaultstate="collapsed" desc=" RendererBuscarNodo: No Utilizable ">
//    public class RendererBuscarNodo extends DefaultTreeCellRenderer  {
//
//        private String buscar="";  
//        private boolean multipleSeleccion = false;
//        private boolean encontro = false;
//        
//        public RendererBuscarNodo() { }
//        
//        public RendererBuscarNodo(String buscar, boolean multipleSeleccion) { 
//            this.buscar = buscar; 
//            this.multipleSeleccion = multipleSeleccion;
//        }
//    
//        public Component getTreeCellRendererComponent( JTree arbol, Object valor, boolean seleccionado, boolean expandido, 
//                                                       boolean rama, int fila, boolean conFoco ) {        
//            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)valor;
//            String texto = "";
//            if (seleccionado){
//                seleccionado = false;
//            }
//            if (nodo.getUserObject() instanceof Item) {
//                item = new Item();
//                item = (Item)nodo.getUserObject();              
//                texto = item.getItem();
//            }
//            else if (nodo.getUserObject() instanceof Object) {
//                texto = (String)nodo.getUserObject();              
//            }
//                                                
//            if (!nodo.isLeaf()) {                  
//                arbol.expandRow(fila); 
//            }              
//            if ( this.buscar != null ) {        
//                if ( texto.compareToIgnoreCase(this.buscar) == 0 ) {                                                
//                    seleccionado = true; 
//                    conFoco = true;  
//                    encontro = true;                    
//                }
//                else if (texto.toLowerCase().contains(this.buscar.toLowerCase())) {                
//                    seleccionado = true;
//                    conFoco = true; 
//                    encontro = true;                    
//                }
//                if (multipleSeleccion) {
//                    encontro = false;
//                }
//            }
//            super.getTreeCellRendererComponent( arbol, valor, seleccionado, expandido, rama, fila, conFoco);               
//            return(this);
//        }        
//    }
    // </editor-fold> 
