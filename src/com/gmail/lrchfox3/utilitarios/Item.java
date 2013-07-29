/**
 * clsItemData.java
 *
 * Created on 04 de agosto de 2006, 09:23 PM
 * @author Chinchilla
 *
 * @Objetivo: registra un elemento el contiene inforamcion adicional.
 */

package com.gmail.lrchfox3.utilitarios;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gmail.lrchfox3.basedatos.BD;

public class Item {    
    
    public Item() { }
    
    public String toString(){                    
        return getItem();   
    }
    
    /**
     * Retorna la descripcion del combo box
     * @author Chinchilla 04/08/2006
     */    
    public String getItem(){
        return item;
    }  
    
    /**
     * Retorna el Objeto que identifica la descripcion del combo box
     * @author Chinchilla 04/08/2006
     */    
    public Object getItemData(){
        return itemData;
    }

    /**
     * 
     * @author Chinchilla 04/08/2006
     */    
    public void setItem( String value ) {
        item = value;
    }

   /**
     * 
     * @author Chinchilla 04/08/2006
     */
    public void setItemData(Object value) {
        itemData = value;
    }         
    
    public void cargarItem(BD bd, String sql) throws SQLException, Exception {
        Statement st = null;
        ResultSet rs = null;
        
        try {
            st = bd.crearSentencia();
            rs = bd.ejecutarSentencia(st, sql);
            if (rs.next()) {                
                setItemData( rs.getObject(1));
                setItem(rs.getString(2));                
            }
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }        
    }
    
    
    private String item = "";
    private Object itemData = null;
}
