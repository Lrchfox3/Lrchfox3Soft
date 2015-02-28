/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.utilitarios;

/**
 *
 * @author Luis R. Chinchilla H.
 */
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import com.gmail.lrchfox3.basedatos.SqlTipos;
import com.gmail.lrchfox3.controles.botones.Alignable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import static javax.swing.SwingConstants.HORIZONTAL;


public class Utileria {

    public final static String excel = "xls";
    public final static String texto = "txt";
    //Charset de caracteres cuando se cifra
    private static final String charset1 = "1234567890!#$%&/()=¿?¡_-+*:";
    //Charset de caracteres cuando se descifra
    private static final String charset2 = "abcdefghijklmnopqrstuvwxyz ";
  
    
    public Utileria() {
    }

    /**
     * This is a utility method to help in loading icon images. It takes the
     * name of a resource file associated with the current object's class file
     * and loads an image object from that file. Typically images will be GIFs.
     * <p>
     *
     * @param resourceName A pathname relative to the directory holding the
     * class file of the current class. For example, "wombat.gif".
     * @return an image object. May be null if the load failed.
     */
    public java.awt.Image loadImage(final String resourceName) {
        try {            
           final Class c = this.getClass();
            java.awt.image.ImageProducer ip = (java.awt.image.ImageProducer) java.security.AccessController.doPrivileged(
                    new java.security.PrivilegedAction() {
                public Object run() {
                    java.net.URL url;
                    if ((url = c.getResource(resourceName)) == null) {
                        return null;
                    } else {
                        try {
                            return url.getContent();
                        } catch (java.io.IOException ioe) {
                            return null;
                        }
                    }
                }
            });

            if (ip == null) {
                return null;
            }
            java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
            return tk.createImage(ip);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void lookAndFeelSystem() throws Exception {
        setlookAndFeel(null);
    }

    public static void setlookAndFeel(String _lookAndFeel) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        if ("Nimbus".equals(_lookAndFeel)) {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    lookAndFeel = info.getClassName();
                    break;
                }
            }
        }
        UIManager.setLookAndFeel(lookAndFeel);
    }

    /**
     * Ejecuta una sentencia SQL que devuelve un unico valor
     *
     * @throws oracle.jbo.JboException
     * @return Devuelve el valor (String) según la sentencia ejecutada
     * @param sql Sentencia SQL que devuelve el valor unico
     * @param dbTransaction Conexón abierta pata ejecutar sentencia al manejador
     * de BDD
     */
    public static String obtieneValorString(Connection cnn, String sql) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = cnn.createStatement();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString("VALOR");
        }
        // Sin importar que pasó se debe cerrar el resultset y el statement.
        if (rs != null) {
            rs.close();
        }

        if (stmt != null) {
            stmt.close();
        }
        return null;
    }

    /**
     * Ejecuta una sentencia SQL que devuelve un unico valor
     *
     * @throws oracle.jbo.JboException
     * @return Devuelve el valor (String) según la sentencia ejecutada
     * @param sql Sentencia SQL que devuelve el valor unico
     * @param cnn Conexón abierta pata ejecutar sentencia al manejador de BDD
     */
    public static Date fechaActual(Connection cnn, String sql) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = cnn.createStatement();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            java.util.Date tmp = rs.getTimestamp("VALOR");
            return new java.sql.Date(tmp.getTime());
        }
        // Sin importar que pasó se debe cerrar el resultset y el statement.
        if (rs != null) {
            rs.close();
        }

        if (stmt != null) {
            stmt.close();
        }
        return null;
    }

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static Object delimitadorValor(Object valor, int tipoCampo) {
        if (valor != null) {
            if (tipoCampo == SqlTipos.VARCHAR) {
                valor = "'" + valor + "'";
            }
        }
        return valor;
    }

    public static java.sql.Date getFechaActual() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.sql.Date now = new java.sql.Date(cal.getTime().getTime());
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            return new java.sql.Date(sdfDate.parse(sdfDate.format(now)).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utileria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return now;
    }

    public static String getStrFechaActual() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.sql.Date now = new java.sql.Date(cal.getTime().getTime());
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdfDate.format(now);

    }

    public static String getStrFechaActual(java.sql.Date date, String formato) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(formato);
        return sdfDate.format(date);

    }

    public static int getIntValue(Connection cnn, String sql) throws SQLException {
        int value = -1;
        Statement st = cnn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            value = rs.getInt("VALOR");
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return value;
    }

    /**
     * This method can be used to fix two JDK bugs. One is to fix the row height is wrong when the first element in the
     * model is null or empty string. The second bug is only on JDK1.4.2 where the vertical scroll bar is shown even all
     * rows are visible. To use it, you just need to override JList#getPreferredScrollableViewportSize and call this
     * method.
     * <pre><code>
     * public Dimension getPreferredScrollableViewportSize() {
     *    return JideSwingUtilities.adjustPreferredScrollableViewportSize(this, super.getPreferredScrollableViewportSize());
     * }
     * <p/>
     * </code></pre>
     *
     * @param list                the JList
     * @param defaultViewportSize the default viewport size from JList#getPreferredScrollableViewportSize().
     * @return the adjusted size.
     */
    public static Dimension adjustPreferredScrollableViewportSize(JList list, Dimension defaultViewportSize) {
        // workaround the bug that the list is tiny when the first element is empty
        Rectangle cellBonds = list.getCellBounds(0, 0);
        if (cellBonds != null && cellBonds.height < 3) {
            ListCellRenderer renderer = list.getCellRenderer();
            if (renderer != null) {
                Component c = renderer.getListCellRendererComponent(list, "DUMMY STRING", 0, false, false);
                if (c != null) {
                    Dimension preferredSize = c.getPreferredSize();
                    if (preferredSize != null) {
                        int height = preferredSize.height;
                        if (height < 3) {
                            try {
                                height = list.getCellBounds(1, 1).height;
                            }
                            catch (Exception e) {
                                height = 16;
                            }
                        }
                        list.setFixedCellHeight(height);
                    }
                }
            }
        }
        if (SystemInfo.isJdk15Above()) {
            return defaultViewportSize;
        }
        else {
            // in JDK1.4.2, the vertical scroll bar is shown because of the wrong size is calculated.
            defaultViewportSize.height++;
            return defaultViewportSize;
        }
    }
    
      /**
     * Checks if the two objects equal. If both are null, they are equal. If o1 and o2 both are Comparable, we will use
     * compareTo method to see if it equals 0. At last, we will use <code>o1.equals(o2)</code> to compare. If none of
     * the above conditions match, we return false.
     *
     * @param o1 the first object to compare
     * @param o2 the second object to compare
     * @return true if the two objects are equal. Otherwise false.
     */
    public static boolean equals(Object o1, Object o2) {
        return equals(o1, o2, false);
    }

    /**
     * Checks if the two objects equal. If both are the same instance, they are equal. If both are null, they are equal.
     * If o1 and o2 both are Comparable, we will use compareTo method to see if it equals 0. If considerArrayOrList is true
     * and o1 and o2 are both array, we will compare each element in the array. At last, we will use
     * <code>o1.equals(o2)</code> to compare. If none of the above conditions match, we return false.
     *
     * @param o1                  the first object to compare
     * @param o2                  the second object to compare
     * @param considerArrayOrList If true, and if o1 and o2 are both array, we will compare each element in the array instead
     *                      of just compare the two array objects.
     * @return true if the two objects are equal. Otherwise false.
     */
    public static boolean equals(Object o1, Object o2, boolean considerArrayOrList) {
        if (o1 == o2) {
            return true;
        }
        else if (o1 != null && o2 == null) {
            return false;
        }
        else if (o1 == null) {
            return false;
        }
        else if (o1 instanceof CharSequence && o2 instanceof CharSequence) {
            return equals((CharSequence) o1, (CharSequence) o2, true);
        }
        else if (o1 instanceof Comparable && o2 instanceof Comparable && o1.getClass().isAssignableFrom(o2.getClass())) {
            return ((Comparable) o1).compareTo(o2) == 0;
        }
        else if (o1 instanceof Comparable && o2 instanceof Comparable && o2.getClass().isAssignableFrom(o1.getClass())) {
            return ((Comparable) o2).compareTo(o1) == 0;
        }
        else if (considerArrayOrList && o1 instanceof List && o2 instanceof List) {
            int length1 = ((List) o1).size();
            int length2 = ((List) o2).size();
            if (length1 != length2) {
                return false;
            }
            for (int i = 0; i < length1; i++) {
                if (!equals(((List) o1).get(i), ((List) o2).get(i), true)) {
                    return false;
                }
            }
            return true;
        }
        else {
            if (considerArrayOrList && o1.getClass().isArray() && o2.getClass().isArray()) {
                int length1 = Array.getLength(o1);
                int length2 = Array.getLength(o2);
                if (length1 != length2) {
                    return false;
                }
                for (int i = 0; i < length1; i++) {
                    if (!equals(Array.get(o1, i), Array.get(o2, i), true)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return o1.equals(o2);
            }
        }
    }

    
        /**
     * Inserts the mouse listener at the particular index in the listeners' chain.
     *
     * @param component
     * @param l
     * @param index
     */
    public static void insertMouseListener(Component component, MouseListener l, int index) {
        MouseListener[] listeners = component.getMouseListeners();
        for (MouseListener listener : listeners) {
            component.removeMouseListener(listener);
        }
        for (int i = 0; i < listeners.length; i++) {
            MouseListener listener = listeners[i];
            if (index == i) {
                component.addMouseListener(l);
            }
            component.addMouseListener(listener);
        }
        // index is too large, add to the end.
        if (index < 0 || index > listeners.length - 1) {
            component.addMouseListener(l);
        }
    }
    
    public static int getOrientationOf(Component component) {
        if (component instanceof Alignable) {
            return ((Alignable) component).getOrientation();
        }
        else if (component instanceof JComponent) {
            Integer value = (Integer) ((JComponent) component).getClientProperty(Alignable.PROPERTY_ORIENTATION);
            if (value != null)
                return value;
        }
        return HORIZONTAL;
    }

    public static void setOrientationOf(Component component, int orientation) {
        int old = getOrientationOf(component);
        if (orientation != old) {
            if (component instanceof Alignable) {
                ((Alignable) component).setOrientation(orientation);
            }
            else if (component instanceof JComponent) {
                ((JComponent) component).putClientProperty(Alignable.PROPERTY_ORIENTATION, orientation);
            }
        }
    }
    
    /**
     * Metodo que recibe un texto y cifra el contenido de acuerdo a los charset
     * definidos
     *
     * @param texto
     * @return texto
     */
//    public static String cifrar(String texto) {
//        //Convierto a minuscula las letras del alfabeto que existan en el texto
//        texto = texto.toLowerCase();
//        //Reemplazo los caracteres del charset2 con los del charset1
//        for (int i = 0; i < charset2.length(); i++) {
//            texto = texto.replace(charset2.charAt(i), charset1.charAt(i));
//        }
//        //Retorno el texto cifrado con el charset2
//        return texto;
//    }

    /**
     * Metodo que recibe un texto y descifra el contenido de acuerdo a los
     * charset definidos
     *
     * @param texto
     * @return texto
     */
//    public static String descifrar(String texto) {
//        //Convierto a minuscula las letras del alfabeto que existan en el texto
//        texto = texto.toLowerCase();
//        //Reemplazo los caracteres del charset1 con los del charset2
//        for (int i = 0; i < charset1.length(); i++) {
//            texto = texto.replace(charset1.charAt(i), charset2.charAt(i));
//        }
//        //Retorno el texto cifrado con el charset2
//        return texto;
//    }
    
    
    
    	
}
