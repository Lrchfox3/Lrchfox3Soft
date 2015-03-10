/*
 * JTextoFormato.java
 *
 * Created on February 1, 2007, 12:50 PM
 */

package com.gmail.lrchfox3.controles.textos;

// <editor-fold defaultstate="collapsed" desc=" Librerias "> 
import com.gmail.lrchfox3.utilitarios.Propiedades;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JSeparator;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
// </editor-fold>

/**
 *
 * @author Luis Chinchilla 01/02/2007
 */
public class JTextoFormato extends javax.swing.JFormattedTextField implements java.io.Serializable {
        
    public JTextoFormato() {
         inicializar();
    }    
    
    public void inicializar() {
        setFormato(FORMATO_DEFAULT);
        setFont(propiedades.getFontTextos());
        setBorder(new javax.swing.border.EtchedBorder()); 
        setPreferredSize(new java.awt.Dimension(90, 20));
        setSize(getPreferredSize());                       
        
        popupMenu = new JPopupTextoBase();
        this.setComponentPopupMenu(popupMenu);
        
        addFocusListener(new FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                loseFocus(evt);
            }
       });
       
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validarCaracter(evt);
            }
        });        

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                forzarFocus(evt);
            }
        });
    }    
    
    private void forzarFocus(java.awt.event.HierarchyEvent evt) {
        requestFocusInWindow();
    }
    
    private void loseFocus(java.awt.event.FocusEvent evt) {
        int formato = getFormato();
        if (formato != FORMATO_ENTERO && formato != FORMATO_DECIMAL) return;
            
        Object valor = getValue();
        String texto = getText();
        
        if (valor != null) {
            if (texto.equals("")) {
                if (!valor.equals(texto)) 
                    setValue(null);        
            }    
        }
    }    
    
    private void validarCaracter(KeyEvent evt) {
        int formatoActual = getFormato();        
        char c = evt.getKeyChar();        
        
        //if (c == '\b') return;                              //backspace
        //if (c == '\u007f') return;                          //delete
        if ((c == '\u0003') || (c == '\u0018')) return;     //copy, cut,                 
        if (c == '\u0016')  {                               //paste
            validarPaste(formatoActual);
            return;
        }
                    
        switch(formatoActual) {
            case FORMATO_ENTERO:            
                if (!numeros.isNumero(c)) {
                    evt.consume();
                    return;
                }
                break;
                
            case FORMATO_DECIMAL:
                boolean isPunto = false;
                isPunto = numeros.isPuntoDecimal(c);
                if (isPunto) {
                    if (getText().indexOf('.') > -1) {
                        evt.consume();
                        return;
                    }
                }
                boolean isMenos = false;
                isMenos = numeros.isSignoMenos(c);
                if (isMenos) {
                    if (getText().indexOf('-') > -1) {
                        evt.consume();
                        return;
                    }
                }
                
                if (!numeros.isNumero(c) && !isPunto && !isMenos && c != '\b' && c != '\u007f') {
                    evt.consume();
                    return;
                }
                break;
        }
                        
        int max = getMaxCaracteres();
        int l = getText().length();
        
        String textoSel = getSelectedText();
        if (textoSel != null)  l = l - textoSel.length();        
                
        if (l >= max) {
            getToolkit().beep();
            evt.consume();
        }
                
        if (formatoActual == FORMATO_DECIMAL) {
            int i = getSelectionStart();
            int f = getSelectionEnd();

            String t = getText(); 
            if ((c != '\b') && (c != '\u007f')) t = t.substring(0, i) + c + t.substring(f);
            else                                 t = t.substring(0, i) + t.substring(f);
            
            int pos = t.indexOf(".");
            if (pos > -1) {
                if(t.substring(0, pos).length() > maximoEntero) evt.consume();
                if(t.substring(pos + 1).length() > maximoDecimal) evt.consume();
            }
            else {
                if(t.length() > maximoEntero) {
                    if ((c != '\b') && (c != '\u007f')) evt.consume();
                    else setText(t.substring(0, maximoEntero));
                    
                }
            }
        }
    }
    
    public void validarPaste(int formatoActual) {
        String p = null;
        com.gmail.lrchfox3.utilitarios.Numeros n = new com.gmail.lrchfox3.utilitarios.Numeros();
        
        java.awt.datatransfer.Clipboard sc = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        sc.getAvailableDataFlavors();
        try {                
                p = sc.getData(sc.getAvailableDataFlavors()[0]).toString();
                sc.getContents(this);
                switch(formatoActual) {
                    case FORMATO_ENTERO:
                        p = p.replaceAll(",",  "");
                        if (!n.isNumber(p)) p ="";
                        p = p.substring(0, maximoEntero);
                
                        break;
                        
                    case FORMATO_DECIMAL:
                        p = p.replaceAll(",",  "");
                        if (!n.isDecimal(p)) p = "";
                        
                        int pos = p.indexOf(".");
                        if (pos > -1)  {
                            String entero = "";
                            String decimal = "";
                            entero = p.substring(0, pos);
                            decimal = p.substring(pos + 1);
                            p = entero.substring(0, maximoEntero) + "." + decimal.substring(0, maximoDecimal);
                        }            
                        else p = p.substring(0, maximoEntero);
                        
                        break;
                    
                    default:
                        p = p.substring(0, getMaxCaracteres());
                        break;
                }
                
                setText(p);                
        }
        catch (Exception ex) {;}
    }
    
    public final void setMascara(final String m) {     
        mascara = m;     
        try {                     
            formatoMascara = new MaskFormatter(mascara);
            formatoMascara.setPlaceholder("");
            formatoMascara.install(this); 
        } 
        catch (java.text.ParseException e) {        
            e.printStackTrace();     //TODO: llamar funcion mensaje
        } 
    }   
    
    public String getTextoSinFormato() {
        String texto = getText();
        
        int f = getFormato() ;
        if (f == FORMATO_ENTERO || f == FORMATO_DECIMAL) 
            texto = texto.replaceAll(",", "");
        else {
            String m = getMascara();
            if (m != null && !m.equals("")) {            
                texto = texto.replaceAll("-", "");
                texto = texto.replaceAll("/", "");
            }
        }                    
                
        return texto;
    }
    
    public final String getMascara() {     
        return mascara; 
    }
            
    public final String getExpresionRegular() {     
        return expresionRegular; 
    }                
    
    public final void setExpresionRegular(final String er) {     
        this.expresionRegular = er;     
        try {              
            Pattern expresion = Pattern.compile( this.expresionRegular, Pattern.CASE_INSENSITIVE);            
            expresion.matcher("").matches();
            
            patron = new RegexPatternFormatter(expresion, getMaxCaracteres());                        
            patron.setAllowsInvalid(false);                                    
            patron.setCommitsOnValidEdit(true);
            patron.install(this);
        } 
        catch (Exception e) {        
            e.printStackTrace();     //TODO: llamar funcion mensaje
        } 
    }        
    
    public int getFormato() {
        return formato;
    }
    
    /**
     * Define el Formato del texto que se asigna al texto.
     *
     * @param opcionFormato .
     */
    public final void setFormato(final int formato) { 
        this.formato = formato;

        switch(this.formato) {
            case FORMATO_DEFAULT:
                setExpresionRegular("[\\p{ASCII}]*");  
                setHorizontalAlignment(javax.swing.JTextField.LEADING);
                break;
                
            case FORMATO_ENTERO:    
                setComas(true);
        //        setExpresionRegular("[\\-]?[0-9]*");
                setHorizontalAlignment(RIGHT);
                setLimitesEntero(maximoEntero); 
                break;
                
            case FORMATO_DECIMAL:
                setComas(true);
       //         setExpresionRegular("[\\-]?[0-9]*[\\.]?[0-9]*");      
                setExpresionRegular("^(\\+|-)?[0-9]+(\\.([0-9]{1,2})?)?$");
                setHorizontalAlignment(RIGHT);
                setLimitesDecimal(maximoDecimal);
                break;
                
            case FORMATO_SOLO_NUMEROS:
                setComas(false);
                setExpresionRegular("[0-9]*");
                setHorizontalAlignment(LEFT);
                break;
                
            case FORMATO_SOLO_LETRAS: 
                setExpresionRegular("[[a-z]*[A-Z]*]*");
                break;
                
            case FORMATO_SOLO_LETRAS_NUMEROS:
                setExpresionRegular("[[a-z]*[A-Z]*[0-9]*]*");
                setHorizontalAlignment(javax.swing.JTextField.LEADING);
                break;
                
            default:
                setExpresionRegular("[\\p{ASCII}]*");
                setHorizontalAlignment(javax.swing.JTextField.LEADING);
                break;
        }
    } 
    
    public void setMaxCaracteres(int numeroMaximoCaracteres) {
        this.numeroMaximoCaracteres = numeroMaximoCaracteres;
    }
    
    public int getMaxCaracteres() {
        return numeroMaximoCaracteres;
    }
    
    
    /** Define el lï¿½mite mï¿½ximo de un texto definido como entero.
     *
     * @param maxEntero Mï¿½ximo nï¿½mero de caracteres enteros permitidos.
     *
     * ej: si maxEntero es 4, solamente se aceptarï¿½n nï¿½meros menores que 10000.
     */
    public void setLimitesEntero(int maxEntero) {        
        setLimitesEntero(0, maxEntero);
    }
    
    /** Define los lï¿½mites mï¿½nimos y mï¿½ximos de un texto definido como nï¿½mero entero.
     *
     * @param minEntero Mï¿½nimo nï¿½mero de caracteres enteros permitidos.
     * @param maxEntero Mï¿½ximo nï¿½mero de caracteres enteros permitidos.
     *
     * ej: si minEntero es 2 y maxEntero es 3, solamente se aceptarï¿½n nï¿½meros
     * mayores a 9 y menores que 1000.
     */
    public void setLimitesEntero(int minEntero, int maxEntero) {                         
        setLimitesNumero(minEntero, maxEntero, minimoDecimal, maximoDecimal);        
    }
    
    /** Define el lï¿½mite mï¿½ximo de la parte decimal de un texto definido como nï¿½mero.
     *
     * @param maxDecimal Mï¿½ximo nï¿½mero de caracteres decimales permitidos.
     *
     * ej: si maxDecimal es 2, solamente se aceptarï¿½n nï¿½meros con decimales menores a .100.
     */
    public void setLimitesDecimal(int maxDecimal) {
        setLimitesDecimal(0, maxDecimal);
    }
    
    /** Define los lï¿½mites mï¿½nimos y mï¿½ximos de la parte decimal de un texto definido como nï¿½mero.
     *
     * @param minDecimal Mï¿½nimo nï¿½mero de caracteres decimales permitidos.
     * @param maxDecimal Mï¿½ximo nï¿½mero de caracteres decimales permitidos.
     *
     * ej: si minDecimal es 1 y maxDecimal es 2, solamente se aceptarï¿½n nï¿½meros
     * con decimales mayores a .0 y menores que .100.
     */
    public void setLimitesDecimal(int minDecimal, int maxDecimal) {                        
        setLimitesNumero(minimoEntero, maximoEntero, minDecimal, maxDecimal);
    }
    
    /** Define los lï¿½mites mï¿½ximos de un texto definido como nï¿½mero.
     *
     * @param maxEntero Mï¿½ximo nï¿½mero de caracteres permitidos en la parte entera del nï¿½mero.
     * @param maxDecimal Mï¿½ximo nï¿½mero de caracteres decimales permitidos.
     *
     * ej: si maxEntero es 2 y maxDecimal es 2, solamente se aceptarï¿½n nï¿½meros
     * con decimales menores o iguales a 99.99.
     */
    public void setLimitesNumero(int maxEntero, int maxDecimal) {                
        setLimitesNumero(0, maxEntero, 0, maxDecimal);
    }
    
    public void setLimitesNumero(int minEntero, int maxEntero, int minDecimal, int maxDecimal) {
        DecimalFormat dfDisplay = new DecimalFormat();
        DecimalFormat dfEdit  = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        
        int formato = getFormato();
        if ( formato == FORMATO_ENTERO) {
            minDecimal = 0;
            maxDecimal = 0;
        }        
        else if ( formato == FORMATO_DECIMAL) dfs.setDecimalSeparator('.');
        
        this.minimoEntero = minEntero;
        this.maximoEntero = maxEntero;
        this.minimoDecimal = minDecimal;
        this.maximoDecimal = maxDecimal;
        
        dfDisplay.setMinimumIntegerDigits(minEntero); 
        dfDisplay.setMaximumIntegerDigits(maxEntero);
        dfDisplay.setMinimumFractionDigits(minDecimal);       
        dfDisplay.setMaximumFractionDigits(maxDecimal);
        
        if (conComas) {                        
            dfs.setGroupingSeparator(',');                         
            dfDisplay.setGroupingSize(3);                       
            dfDisplay.setGroupingUsed(true);
        }
        else {
            dfs.setGroupingSeparator(' ');
            dfDisplay.setGroupingUsed(false);
        }                  
        dfDisplay.setDecimalFormatSymbols(dfs);
        
        dfEdit.setMinimumIntegerDigits(minEntero); 
        dfEdit.setMaximumIntegerDigits(maxEntero);
        dfEdit.setMinimumFractionDigits(minDecimal);       
        dfEdit.setMaximumFractionDigits(maxDecimal);
        dfEdit.setGroupingUsed(false);

        //tamanyo maximo de caracteres para numeros
        int m = maximoEntero + maximoDecimal;
        if (getFormato() == FORMATO_DECIMAL) m = m + 1;
        setMaxCaracteres(m);
        
        JFormattedTextField jftfDisplay = new JFormattedTextField(dfDisplay);
        JFormattedTextField jftfEdit = new JFormattedTextField(dfEdit);
        
        DefaultFormatterFactory factory = new DefaultFormatterFactory(jftfDisplay.getFormatter(), 
                                                                      jftfDisplay.getFormatter(), 
                                                                      jftfEdit.getFormatter(), jftfDisplay.getFormatter());        
        setFormatterFactory(factory);                        
    }        
    
    public int getNumeroPuntos() {
        java.util.StringTokenizer token = new java.util.StringTokenizer(getText(), ".");
        return token.countTokens();
    }
    
    public int getNumeroComas() {
        java.util.StringTokenizer token = new java.util.StringTokenizer(getText(), ",");
        return token.countTokens();
    }
      
    public void setComas( boolean value ){
        conComas = value;
    }
    
    public void setComponentPopupMenu( javax.swing.JPopupMenu popup ) {        
        if (popup!= null){
            super.setComponentPopupMenu(popup);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc=" RegexPatternFormatter ">
    public class RegexPatternFormatter extends DefaultFormatter {
        
        protected java.util.regex.Matcher matcher;
        private int numeroMaximoCaracteres=32000;
        
        public RegexPatternFormatter(java.util.regex.Pattern regex, int numeroMaximoCaracteres) {
            setOverwriteMode(false);
            matcher = regex.matcher(""); // create a Matcher for the regular expression   
            this.numeroMaximoCaracteres = numeroMaximoCaracteres; 
        }
         
        public Object stringToValue(String string) throws java.text.ParseException {
            if (string == null)  return null;

            matcher.reset(string); // set 'string' as the matcher's input

            if (!matcher.matches()) { // Does 'string' match the regular expression?
                throw new java.text.ParseException("No mantiene el formato de la expresiï¿½n regular.", 0);
            }            
            if ( string.length() >=  numeroMaximoCaracteres) {               
               getToolkit().beep();               
               this.getFormattedTextField().setText(string.substring(0, numeroMaximoCaracteres));
           }
            
            // If we get this far, then it did match.
            return super.stringToValue(string); // will honor the 'valueClass'
                                                // property
        }
    }     
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" DeclaraciÃ³n de variables ">
    
    public static final int FORMATO_DEFAULT = 0;    
    public static final int FORMATO_ENTERO = 1;
    public static final int FORMATO_DECIMAL = 2;
    public static final int FORMATO_SOLO_LETRAS = 3;
    public static final int FORMATO_SOLO_LETRAS_NUMEROS = 4;
    public static final int FORMATO_SOLO_NUMEROS = 5;
    
    private Propiedades propiedades = new Propiedades();    
    private String mascara = "";    
    private String expresionRegular = "";    
    private int formato = 0;
    private RegexPatternFormatter patron = null;
    private MaskFormatter formatoMascara = null;
    private String texto = "";
    private int minimoEntero = 0;
    private int maximoEntero = 18; //309
    private int minimoDecimal= 0;
    private int maximoDecimal= 2; //340
    private JPopupTextoBase popupMenu;
    private JSeparator separator; 
    private int numeroMaximoCaracteres=32000;
    private boolean conComas = false;    
    private com.gmail.lrchfox3.utilitarios.Numeros numeros = new com.gmail.lrchfox3.utilitarios.Numeros();
    // </editor-fold>
}
